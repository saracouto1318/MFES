package MFES.gui;

import MFES.Schedule;
import MFES.Task;

import java.util.Scanner;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Patient;
import MFES.Agenda;
import MFES.Schedule;
import MFES.gui.CreateSchedule;
import MFES.gui.HospitalPicker;
import MFES.gui.ListSelectabels;
import MFES.gui.ManageHospital;

public class CreateTask extends Menu {
    private static enum CreateState {PATIENT_LIST, HEALTHPROFESSIONAL_LIST, SCHEDULE, INVALID};
    private CreateState state;

    public static enum TaskType {APPOINTMENT, SURGERY, TREATMENT, URGENCIES};
    private TaskType type;

    private Hospital hospital;
    private Patient patient = null;
    private HealthProfessional healthProfessional = null;
    private Schedule schedule = null;

    private boolean invalid;

    public CreateTask(Scanner reader, TaskType type, Hospital hospital) {
        super(reader);
        state = PATIENT_LIST;
        this.type = type;
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
                hArr[i++] = iter.next();
            }

            ListSelectabels<Agenda> menu = new ListSelectabels<>(reader, selectabels, this);

            System.out.println("Informacao paciente");

            /*
            CreatePerson menu = new CreatePerson(reader, CreatePerson.CreateType.PATIENT, hospital);
            menu.show();
            menu.action();
            patient = (Patient)menu.getPerson();
            */
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

	@Override
	public Menu action() {
        if(invalid)
            return new HospitalPicker(reader);

        Menu returnedMenu = null;

        while(!endCondition(returnedMenu)) {
            String str = reader.nextLine();
            returnedMenu = input(str);
        }

		return returnedMenu;
	}

	@Override
	public Menu input(String input) {
        if(input == null || input.equals("")) {
            show();
            return null;
        }

        switch(state) {
        case PATIENT_LIST:
            CreatePerson menu = new CreatePerson(reader, CreatePerson.CreateType.PATIENT, hospital);
            menu.show();
            menu.action();
            patient = (Patient)menu.getPerson();

            state = CreateState.HEALTHPROFESSIONAL_LIST;

            break;
        case HEALTHPROFESSIONAL_LIST:
            System.out.println("\nEscolher medico");
            state = CreateState.SCHEDULE;
            break;
        case SCHEDULE:
            CreateSchedule menu = new CreateSchedule(reader);
            menu.show();
            menu.action();
            schedule = (Schedule)menu.getSchedule();
            
            if(createTask() == null)
                state = CreateState.INVALID;
            else
                return new ManageHospital(reader, hospital);

            show();
        case INVALID:
            state = CreateState.PATIENT_LIST;
            break;
        }

        show();
		return null;
	}

	@Override
	public void destroy() {}
    
    private boolean endCondition(Menu returnedMenu) {
        return patient != null && healthProfessional != null && schedule != null && !invalid;
    }

    private Task createTask() {
        Task t = new Task(healthProfessional, schedule, patient, hospital, type);
        hospital.addTask(t);
        return t;
    }

}