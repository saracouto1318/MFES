package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSet;

import MFES.Agenda;
import MFES.Appointment;
import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Patient;
import MFES.Schedule;
import MFES.Surgery;
import MFES.Task;
import MFES.Training;
import MFES.Treatment;
import MFES.gui.CreateTask.CreateState;
import MFES.gui.CreateTask.TaskType;

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

    @SuppressWarnings("unchecked")
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

        Menu returnedMenu = null;

        while(!endCondition(returnedMenu) && !invalid) {

            switch(state) {
            case HEALTHPROFESSIONAL_LIST:
                VDMSet medics = hospital.getMedicalAssociated();

                if(medics.size() <= 0) {
                    System.out.println("Neste momento nao ha medicos disponiveis");
                    invalid = true;
                    return null;
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

        if(invalid)
            return new ManageHospital(reader, hospital);
		return returnedMenu;
	}

	@Override
	public Menu input(String input) {
		return null;
	}

	@Override
	public void destroy() {}
    
    private boolean endCondition(Menu returnedMenu) {
        return healthProfessional != null && schedule != null && !invalid;
    }

	private Training createTraining() {
    	Training t = null;
	
    	t = new Training(MFES.quotes.AddSkillsQuote.getInstance(), schedule, healthProfessional);
        hospital.addTraining(t);

        return t;
    }

}
