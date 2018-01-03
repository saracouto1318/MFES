package MFES.gui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import MFES.gui.ListSelectabels;
import MFES.gui.ManageHospital;

import org.overture.codegen.runtime.*;

public class CreateTask extends Menu {
    public static enum CreateState {PATIENT_LIST, HEALTHPROFESSIONAL_LIST, SCHEDULE, INVALID};
    private CreateState state;

    public static enum TaskType {
		APPOINTMENT {
			@Override
			public Object healthAssociatedType() {
				return MFES.quotes.DoctorQuote.getInstance();
			}
		}, SURGERY {
			@Override
			public Object healthAssociatedType() {
				return MFES.quotes.SurgeonQuote.getInstance();
			}
		}, TREATMENT {
			@Override
			public Object healthAssociatedType() {
				return MFES.quotes.OtherQuote.getInstance();
			}
		}, URGENCY {
			@Override
			public Object healthAssociatedType() {
				return MFES.quotes.DoctorQuote.getInstance();
			}
		};
    	public abstract Object healthAssociatedType();
    };
    private TaskType type;

    private Hospital hospital;
    private Patient patient = null;
    private HealthProfessional healthProfessional = null;
    private List<HealthProfessional> additionalMedics;
    private List<HealthProfessional> additionalNurses;
    private Schedule schedule = null;

    private boolean invalid;

    public CreateTask(Scanner reader, TaskType type, Hospital hospital) {
        super(reader);
        state = CreateState.PATIENT_LIST;
        this.type = type;
        this.hospital = hospital;
        additionalMedics = new LinkedList<>();
        additionalNurses = new LinkedList<>();
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
        VDMSet medics = hospital.getMedicalAssociatedByType(type.healthAssociatedType());
        if(medics.size() <= 0) {
            System.out.println("Neste momento nao ha profissionais de saude disponiveis");
            return new ManageHospital(reader, hospital);
        } else if(invalid)
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

                if(type == TaskType.SURGERY) {
                    System.out.println("Adicionar medicos " + additionalMedics.size());
                    medics = hospital.getMedicalAssociatedByType(MFES.quotes.DoctorQuote.getInstance());

                    if(medics.size() <= 0) {
                        System.out.println("Neste momento nao ha medicos disponiveis");
                    } else {
	                	boolean finish = false;
	                	while(!finish) {
	                		if(medics.size() - additionalMedics.size() <= 0)
	                			break;
	                		
	                		// Get list of doctors
		                    selectabels = new HealthProfessional[medics.size() - additionalMedics.size()];
		                    iter = medics.iterator();
		                    i = 0;
		                    while(iter.hasNext()) {
		                    	boolean add = true;
		                    	HealthProfessional hp = iter.next();
		                    	for(HealthProfessional hp2 : additionalMedics) {
		                    		if(hp.getCC().equals(hp2.getCC())) {
		                    			add = false;
		                    			break;
		                    		}
		                    	}
		                    	if(add)
		                    		selectabels[i++] = hp;
		                    	else
		                    		add = true;
		                    }
		                    
		                    medicsList = new ListSelectabelsOption<>(reader, selectabels, this);
	                        medicsList.show();
	                        medicsList.action();
	                        if(medicsList.getSelected() == null)
	                        	finish = true;
	                        else
	                        	additionalMedics.add(medicsList.getSelected());
	                	}
                    }
                    
                    System.out.println("Adicionar enfermeiros");
                	
                    medics = hospital.getMedicalAssociatedByType(MFES.quotes.NurseQuote.getInstance());

                    if(medics.size() <= 0) {
                        System.out.println("Neste momento nao ha enfermeiros disponiveis");
                    } else {	                    
	                	boolean finish = false;
	                	while(!finish) {
	                		if(medics.size() - additionalNurses.size() <= 0)
	                			break;

	                		// Get list of nurses
		                    selectabels = new HealthProfessional[medics.size() - additionalNurses.size()];
		                    iter = medics.iterator();
		                    i = 0;
		                    while(iter.hasNext()) {
		                    	boolean add = true;
		                    	HealthProfessional hp = iter.next();
		                    	for(HealthProfessional hp2 : additionalNurses) {
		                    		if(hp.getCC().equals(hp2.getCC())) {
		                    			add = false;
		                    			break;
		                    		}
		                    	}
		                    	if(add)
		                    		selectabels[i++] = hp;
		                    	else
		                    		add = true;
		                    }
		                    
		                    medicsList = new ListSelectabelsOption<>(reader, selectabels, this);
	                        medicsList.show();
	                        medicsList.action();
	                        if(medicsList.getSelected() == null)
	                        	finish = true;
	                        else
	                        	additionalNurses.add(medicsList.getSelected());
	                	}
                	}
                }
                
                state = CreateState.SCHEDULE;
                break;
            case SCHEDULE:
                VDMSet schedules = hospital.getAgenda(healthProfessional).getAgenda();

                if(schedules.size() <= 0) {
                    System.out.println("O medico nao se encontra disponivel");
                    return new ManageHospital(reader, hospital);
                }

                Schedule[] schSelectabels = new Schedule[schedules.size()];
                Iterator<Schedule> schIter = schedules.iterator();
                int j = 0;
                while(schIter.hasNext()) {
                	schSelectabels[j++] = schIter.next();
                }

                ListSelectabels<Schedule> scheduleList = new ListSelectabels<>(reader, schSelectabels, this);
                scheduleList.show();
                scheduleList.action();

                schedule = scheduleList.getSelected();
                
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
            break;
        case URGENCY:
        	t = new Appointment(healthProfessional, MFES.quotes.MediumQuote.getInstance(), schedule, patient, hospital);
            hospital.addTask(t);
            break;
        case SURGERY:
        	t = new Surgery(healthProfessional, schedule, patient, hospital);
        	for(HealthProfessional h : additionalMedics)
            	((Surgery)t).addSecondaryDoctor(h);
        	for(HealthProfessional h : additionalNurses)
            	((Surgery)t).addOther(h);
            hospital.addTask(t);
            break;
        case TREATMENT:
        	t = new Treatment(healthProfessional, "name...", schedule, patient, hospital);
            hospital.addTask(t);
        	break;
        }

        tasks = hospital.getTasks();
        
        if(tasks.size() <= 0) {
            System.out.println("Neste momento nao ha tarefas disponiveis");
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