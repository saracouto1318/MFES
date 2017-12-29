package MFES.gui;

import java.util.Scanner;

public class HealthProfessionalPage extends Menu {
    public HealthProfessionalPage(Scanner reader) {
        super(reader);
    }

	@Override
	public void show() {
        System.out.println("Profissional de Saude");
        System.out.println("1. Sair");
        System.out.println("Opcao");
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

	@Override
	public Menu input(String input) {
        int option = 0;
        try {
            option = Integer.parseInt(input);
        } catch(NumberFormatException ignore) {
            show();
            return null;
        }

        if(option == 1)
            return new HospitalPicker(reader);

        show();
        return null;
	}

	@Override
	public void destroy() {}
}