package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Patient;
import MFES.Agenda;
import MFES.Appointment;
import MFES.Schedule;
import MFES.Surgery;
import MFES.Task;
import MFES.Treatment;
import MFES.gui.CreateSchedule;
import MFES.gui.ListSelectabels;
import MFES.gui.ManageHospital;

import org.overture.codegen.runtime.*;

public class CreateTask extends Menu {
    public static enum CreateState {PATIENT_LIST, HEALTHPROFESSIONAL_LIST, SCHEDULE, INVALID};
    private CreateState state;

    public static enum TaskType {APPOINTMENT, SURGERY, TREATMENT, URGENCY};
    private TaskType type;

    private CreatePerson.CreateType personType;

    private Hospital hospital;
    private Patient patient = null;
    private HealthProfessional healthProfessional = null;
    private Schedule schedule = null;

    private boolean invalid;

    public CreateTask(Scanner reader, TaskType type, CreatePerson.CreateType personType, Hospital hospital) {
        super(reader);
        state = CreateState.PATIENT_LIST;
        this.type = type;
        this.personType = personType;
        this.hospital = hospital;
        invalid = false;
    }

    @SuppressWarnings("unchecked")
	@Override
	public void show() {
		switch(state) {
        case PATIENT_LIST:
            System.out.println("\n");

            //Fazer display de uma lista de agendas
            VDMSet agendas = hospital.getAgendas();

            if(agendas.size() <= 0) {
                System.out.println("Neste momento nao ha agendas disponiveis");
                invalid = true;
                return;
            }

            Agenda[] selectabels = new Agenda[agendas.size()];
            Iterator<Agenda> iter = agendas.iterator();
            int i = 0;
            while(iter.hasNext()) {
                selectabels[i++] = iter.next();
            }

            ListSelectabels<Agenda> menu = new ListSelectabels<>(reader, selectabels, this);
            menu.show();

            System.out.println("Informacao paciente");
            break;
        case HEALTHPROFESSIONAL_LIST:
            System.out.println("\nEscolher medico");
            break;
        case SCHEDULE:
            System.out.println("\nEscolher hora");
            break;
        case INVALID:
            System.out.println("\nInsucesso");
            break;
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu action() {
        if(invalid)
            return new ManageHospital(reader, hospital);

        while(!endCondition() && !invalid) {

            switch(state) {
            case PATIENT_LIST:
                CreatePerson menu = new CreatePerson(reader, CreatePerson.CreateType.PATIENT, hospital);
                menu.show();
                menu.action();
                patient = (Patient)menu.getPerson();

                state = CreateState.HEALTHPROFESSIONAL_LIST;

                break;
            case HEALTHPROFESSIONAL_LIST:
                VDMSet medics = hospital.getMedicalAssociatedByType(personType);

                if(medics.size() <= 0) {
                    System.out.println("Neste momento nao ha medicos disponiveis");
                    return new ManageHospital(reader, hospital);
                }

                HealthProfessional[] selectabels = new HealthProfessional[medics.size()];
                Iterator<HealthProfessional> iter = medics.iterator();
                int i = 0;
                while(iter.hasNext()) {
                    selectabels[i++] = iter.next();
                }

                ListSelectabels<HealthProfessional> medicsList = new ListSelectabels<>(reader, selectabels, this);
                medicsList.show();
                medicsList.action();

                healthProfessional = medicsList.getSelected();

                state = CreateState.SCHEDULE;
                break;
            case SCHEDULE:
                CreateSchedule cSchedule = new CreateSchedule(reader, this);
                cSchedule.show();
                cSchedule.action();
                schedule = (Schedule)cSchedule.getSchedule();
                
                if(createTask() == null)
                    state = CreateState.INVALID;
                else
                    return new ManageHospital(reader, hospital);
            case INVALID:
                show();
                state = CreateState.PATIENT_LIST;
                break;
            }
            
            show();                        
        }

        return new ManageHospital(reader, hospital);
	}

	@Override
	public Menu input(String input) {
		return null;
	}

	@Override
	public void destroy() {}
    
    private boolean endCondition() {
        return patient != null && healthProfessional != null && schedule != null && !invalid;
    }

    @SuppressWarnings("unchecked")
	private Task createTask() {
    	Task t = null;
    	VDMSet tasks = null;
    	
        switch(type) {
        case APPOINTMENT:
        	t = new Appointment(healthProfessional, schedule, patient, hospital);
            hospital.addTask(t);
            
            tasks = hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance());

            break;
        case URGENCY:
        	t = new Appointment(healthProfessional, MFES.quotes.MediumQuote.getInstance(), schedule, patient, hospital);
            hospital.addTask(t);
            
            tasks = hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance());

            break;
        case SURGERY:
        	t = new Surgery(healthProfessional, schedule, patient, hospital);
            hospital.addTask(t);
            
            tasks = hospital.getTasksByType(MFES.quotes.SurgeryQuote.getInstance());

            break;
        case TREATMENT:
        	t = new Treatment(healthProfessional, "name...", schedule, patient, hospital);
            hospital.addTask(t);
            
            tasks = hospital.getTasksByType(MFES.quotes.OtherQuote.getInstance());

        	break;
        }
        
        if(tasks.size() <= 0) {
            System.out.println("Neste momento nao ha medicos disponiveis");
        } else {
            Task[] hArr = new Task[tasks.size()];
            Iterator<Task> iter = tasks.iterator();
            int i = 0;
            while(iter.hasNext())
                hArr[i++] = iter.next();

            ListSelectabels<Task> m = new ListSelectabels<>(reader, hArr, this);
            m.show();
        }

        return t;
    }

}