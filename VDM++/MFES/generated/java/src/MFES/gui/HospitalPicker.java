package MFES.gui;

import java.util.Scanner;

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
        else if(number == 2)
            return null;
        else if(number == 3)
            exit = true;
        return null;
	}

	@Override
	public void destroy() {}
}