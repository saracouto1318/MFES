package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSet;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Specialty;

public class HealthProfessionalPage extends Menu {
	public static enum PageState {MAIN, CHOOSE_SPECIALITY};
	private PageState state;
	
	private HealthProfessional healthProfessional;
	
    public HealthProfessionalPage(Scanner reader, HealthProfessional healthProfessional) {
        super(reader);
        state = PageState.MAIN;
        this.healthProfessional = healthProfessional;
    }

	@Override
	public void show() {
		switch(state) {
		case MAIN:
	        System.out.println("\n\nProfissional de Saude");
	        System.out.println("1. Adicionar especialidade");
	        System.out.println("2. Adicionar a hospital");
	        System.out.println("3. Adicionar agenda");
	        System.out.println("4. Sair");
			break;
		case CHOOSE_SPECIALITY:            
            System.out.print("Especialidade: ");
	        return;
		}
        System.out.print("Opcao: ");
	}

	@Override
	public Menu action() {
        Menu nextMenu = null;
        while(nextMenu == null) {
            String str = reader.nextLine();
            nextMenu = input(str);
        }
        return nextMenu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu input(String input) {

        switch(state) {
        case MAIN:
            int option = 0;
            try {
                option = Integer.parseInt(input);
            } catch(NumberFormatException ignore) {
                show();
                return null;
            }
            
        	if(option == 1) {
            	state = PageState.CHOOSE_SPECIALITY;
            } else if(option == 2) {

    			VDMSet hospitals = Main.snh.getHospitals();

                if(hospitals.size() <= 0) {
                    System.out.println("Neste momento nao ha hospitais disponiveis");
                    state = PageState.MAIN;
                    break;
                }

                Hospital[] hArr = new Hospital[hospitals.size()];
                Iterator<Hospital> hIter = hospitals.iterator();
                int i = 0;
                while(hIter.hasNext()) {
                    hArr[i++] = hIter.next();
                }

                ListSelectabels<Hospital> m = new ListSelectabels<>(reader, hArr, this);
                m.show();
                m.action();
                m.getSelected().addMedAssociated(healthProfessional);
                
            	System.out.println("\n");
            	
            	VDMSet mHsp = m.getSelected().getMedicalAssociated();
    			Iterator<HealthProfessional> mIter = mHsp.iterator();
            	while(mIter.hasNext()) {
            		System.out.println(mIter.next());
            	}
            	
            	System.out.println("\n");
                
            } else if(option == 3) {
            	CreateAgenda cAgenda = new CreateAgenda(reader, healthProfessional);
            	cAgenda.show();
            	cAgenda.action();            	
            } else if(option == 4)
                return new HospitalPicker(reader);
            
        	break;
        case CHOOSE_SPECIALITY:
        	healthProfessional.addSpecialty(new Specialty(input));
        	
        	System.out.println("\n");
        	
        	VDMSet sps = healthProfessional.getSpecialties();
        	Iterator<Specialty> sIter= sps.iterator();
        	while(sIter.hasNext()) {
        		System.out.println(sIter.next());
        	}
        	
        	System.out.println("\n");
        	
        	state = PageState.MAIN;
        	break;
        }

        show();
        return null;
	}

	@Override
	public void destroy() {}
}