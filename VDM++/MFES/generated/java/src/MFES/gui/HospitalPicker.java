package MFES.gui;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.quotes.AppointmentQuote;
import MFES.quotes.DoctorQuote;
import MFES.quotes.NurseQuote;
import MFES.quotes.OtherQuote;
import MFES.quotes.SurgeonQuote;
import MFES.quotes.SurgeryQuote;
import MFES.quotes.TechnicianQuote;
import MFES.quotes.UrgenciesQuote;

import java.util.Iterator;
import java.util.Scanner;
import org.overture.codegen.runtime.*;

public class HospitalPicker extends Menu {
    private boolean exit;

    public HospitalPicker(Scanner reader) {
        super(reader);
        exit = false;
    }

	@Override
	public void show() {
		System.out.println("\n1. Criar Hospital");
		System.out.println("2. Escolher Hospital");
		System.out.println("3. Profissionais de Saude em mais do que 1 hospital");
		System.out.println("4. Hospitais com mais Tarefas");
		System.out.println("5. Sair");
		System.out.print("Opcao: ");
    }
    
	@Override
	public Menu action() {
        while(!exit) {
            String str = reader.nextLine();
            Menu m = input(str);
            if(m != null)
                return m;
        }

        return null;
	}

    @SuppressWarnings("unchecked")
	@Override
	public Menu input(String input) {
        int number = 0;
        try {
            number = Integer.parseInt(input);
        } catch(NumberFormatException ignore) {
            System.out.println("Input invalido");
            System.out.print("Opcao: ");
            return null;
        }

        if(number == 1)
            return new CreateHospital(reader);
        else if(number == 2) {
            VDMSet hospitals = Main.snh.getHospitals();

            if(hospitals.size() <= 0) {
                System.out.println("\nNeste momento nao ha hospitais disponiveis");
                show();
                return null;
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
            return new ManageHospital(reader, m.getSelected());
        }
        else if(number == 3) {
            System.out.println("\nMedicos");
            VDMSet healthProfessionals = Main.snh.getMedMoreHospitals(DoctorQuote.getInstance());
            HealthProfessional[] hArr;
            Iterator<HealthProfessional> iter;
            ListSelectabels<HealthProfessional> m;
            int i;

            if(healthProfessionals.size() <= 0) {
                System.out.println("\nNeste momento nao ha medicos disponiveis");
            } else {
	            hArr = new HealthProfessional[healthProfessionals.size()];
	            iter = healthProfessionals.iterator();
	            i = 0;
	            while(iter.hasNext()) {
	                hArr[i++] = iter.next();
	            }
	
	            m = new ListSelectabels<>(reader, hArr, this);
	            m.show();
            }
            
            System.out.println("\nEnfermeiros");
            healthProfessionals = Main.snh.getMedMoreHospitals(NurseQuote.getInstance());

            if(healthProfessionals.size() <= 0) {
                System.out.println("\nNeste momento nao ha enfermeiros disponiveis");
            } else {
	            hArr = new HealthProfessional[healthProfessionals.size()];
	            iter = healthProfessionals.iterator();
	            i = 0;
	            while(iter.hasNext()) {
	                hArr[i++] = iter.next();
	            }
	
	            m = new ListSelectabels<>(reader, hArr, this);
	            m.show();
            }
            
            System.out.println("\nTecnicos");
            healthProfessionals = Main.snh.getMedMoreHospitals(TechnicianQuote.getInstance());

            if(healthProfessionals.size() <= 0) {
                System.out.println("\nNeste momento nao ha tecnicos disponiveis");
            } else {
	            hArr = new HealthProfessional[healthProfessionals.size()];
	            iter = healthProfessionals.iterator();
	            i = 0;
	            while(iter.hasNext()) {
	                hArr[i++] = iter.next();
	            }
	
	            m = new ListSelectabels<>(reader, hArr, this);
	            m.show();
            }
            
            System.out.println("\nCirurgioes");
            healthProfessionals = Main.snh.getMedMoreHospitals(SurgeonQuote.getInstance());

            if(healthProfessionals.size() <= 0) {
                System.out.println("\nNeste momento nao ha cirurgioes disponiveis");
            } else {
	            hArr = new HealthProfessional[healthProfessionals.size()];
	            iter = healthProfessionals.iterator();
	            i = 0;
	            while(iter.hasNext()) {
	                hArr[i++] = iter.next();
	            }
	
	            m = new ListSelectabels<>(reader, hArr, this);
	            m.show();
            }
        }
        else if(number == 4) {
            Hospital h = Main.snh.getHospitalsMoreAppointments(AppointmentQuote.getInstance());
            if(h == null) {
                System.out.println("\nNeste momento nao ha hospitais disponiveis");
                show();
                return null;
            }
        	System.out.println("Consulta\n" + h);
        	h = Main.snh.getHospitalsMoreAppointments(UrgenciesQuote.getInstance());
            if(h == null) {
                System.out.println("\nNeste momento nao ha hospitais disponiveis");
                show();
                return null;
            }
        	System.out.println("\nUrgencias\n" + h);
        	h = Main.snh.getHospitalsMoreAppointments(OtherQuote.getInstance());
            if(h == null) {
                System.out.println("\nNeste momento nao ha hospitais disponiveis");
                show();
                return null;
            }
        	System.out.println("\nTratamentos\n" + h);
        	h = Main.snh.getHospitalsMoreAppointments(SurgeryQuote.getInstance());
            if(h == null) {
                System.out.println("\nNeste momento nao ha hospitais disponiveis");
                show();
                return null;
            }
        	System.out.println("\nCirurgias\n" + h);
        }
        else if(number == 5)
            exit = true;
        show();
        return null;
	}

	@Override
	public void destroy() {}
}