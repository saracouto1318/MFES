package MFES.gui;

import java.util.Scanner;

public class HospitalPicker extends Menu {
    public HospitalPicker(Scanner reader) {
        super(reader);
    }

	@Override
	public void show() {
		System.out.println("\n1. Criar Hospital");
		System.out.println("2. Escolher Hospital");
		System.out.print("Opcao: ");
    }
    
	@Override
	public Menu action() {
        String str = reader.nextLine();
        return input(str);
	}

	@Override
	public Menu input(String input) {
        int number = 0;
        try {
            number = Integer.parseInt(input);
        } catch(NumberFormatException ignore) {
            System.out.println("Input invalido");
            show();
            return null;
        }

        if(number == 1)
            return new CreateHospital(reader);
        else if(number == 2)
            return null;
        return null;
	}

	@Override
	public void destroy() {}
}