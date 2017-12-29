package MFES.gui;

import MFES.Hospital;

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
		System.out.println("3. Sair");
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
                System.out.println("Neste momento nao ha hospitais disponiveis");
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
        else if(number == 3)
            exit = true;
        return null;
	}

	@Override
	public void destroy() {}
}