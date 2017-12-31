package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSet;

import MFES.HealthProfessional;
import MFES.Hospital;

public class HealthProfessionalPage extends Menu {
	public static enum PageState {MAIN, CHOOSE_SPECIALITY, ADD_AGENDA};
	private PageState state;
	
	private HealthProfessional healthProfessional;
	private Hospital hospital;
	
    public HealthProfessionalPage(Scanner reader) {
        super(reader);
        state = PageState.MAIN;
    }

	@SuppressWarnings("unchecked")
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
        	VDMSet hospitals = Main.snh.getHospitals();

            if(hospitals.size() <= 0) {
                System.out.println("Neste momento nao ha hospitais disponiveis");
                state = PageState.MAIN;
                return;
            }

            Hospital[] hArr = new Hospital[hospitals.size()];
            Iterator<Hospital> iter = hospitals.iterator();
            int i = 0;
            while(iter.hasNext()) {
                hArr[i++] = iter.next();
            }

            ListSelectabels<Hospital> m = new ListSelectabels<>(reader, hArr, this);
            m.show();
            m.action();
            hospital = m.getSelected();
            
            System.out.print("Especialidade: ");
            
	        return;
		case ADD_AGENDA:
			break;
		}
        System.out.print("Opcao");
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
            	VDMSet hospitals = Main.snh.getHospitals();
                if(hospitals.size() <= 0) {
                    System.out.println("Neste momento nao ha hospitais disponiveis");
                    state = PageState.MAIN;
                }                
            } else if(option == 2) {

    			VDMSet hospitals = Main.snh.getHospitals();

                if(hospitals.size() <= 0) {
                    System.out.println("Neste momento nao ha hospitais disponiveis");
                    state = PageState.MAIN;
                    break;
                }

                Hospital[] hArr = new Hospital[hospitals.size()];
                Iterator<Hospital> iter = hospitals.iterator();
                int i = 0;
                while(iter.hasNext()) {
                    hArr[i++] = iter.next();
                }

                ListSelectabels<Hospital> m = new ListSelectabels<>(reader, hArr, this);
                m.show();
                m.action();
                m.getSelected().addMedAssociated(healthProfessional);

                state = PageState.MAIN;
                
            } else if(option == 4)
                return new HospitalPicker(reader);
            
        	break;
        case CHOOSE_SPECIALITY:
        	hospital.addMedAssociated(healthProfessional);
        	state = PageState.MAIN;
        	break;
        case ADD_AGENDA:
        	break;
        }

        show();
        return null;
	}

	@Override
	public void destroy() {}
}