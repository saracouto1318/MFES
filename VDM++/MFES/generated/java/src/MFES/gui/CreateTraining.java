package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSet;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Schedule;
import MFES.Training;

public class CreateTraining extends Menu {
    public static enum CreateState {HEALTHPROFESSIONAL_LIST, SCHEDULE, INVALID};
    private CreateState state;

    private Hospital hospital;
    private HealthProfessional healthProfessional = null;
    private Schedule schedule = null;

    private boolean invalid;

    public CreateTraining(Scanner reader, Hospital hospital) {
        super(reader);
        state = CreateState.HEALTHPROFESSIONAL_LIST;
        invalid = false;
        this.hospital = hospital;
    }

	@Override
	public void show() {
		switch(state) {
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

        while(!endCondition()) {

            switch(state) {
            case HEALTHPROFESSIONAL_LIST:
                VDMSet medics = hospital.getMedicalAssociated();

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
                
                if(createTraining() == null)
                    state = CreateState.INVALID;
                else
                    return new ManageHospital(reader, hospital);
            case INVALID:
            	show();
                state = CreateState.HEALTHPROFESSIONAL_LIST;
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
        return healthProfessional != null && schedule != null && !invalid;
    }

	@SuppressWarnings("unchecked")
	private Training createTraining() {
    	Training t = null;
	
    	t = new Training(MFES.quotes.AddSkillsQuote.getInstance(), schedule, healthProfessional);
        hospital.addTraining(t);
        
        VDMSet trainings = hospital.getTrainings();
        
        if(trainings.size() <= 0) {
            System.out.println("Neste momento nao ha tarefas disponiveis");
        } else {
            Training[] hArr = new Training[trainings.size()];
            Iterator<Training> iter = trainings.iterator();
            int i = 0;
            while(iter.hasNext())
                hArr[i++] = iter.next();

            ListSelectabels<Training> m = new ListSelectabels<>(reader, hArr, this);
            m.show();
        }

        return t;
    }

}
