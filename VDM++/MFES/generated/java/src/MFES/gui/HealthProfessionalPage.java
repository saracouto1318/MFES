package MFES.gui;

import java.util.Scanner;

public class HealthProfessionalPage extends Menu {
    public HealthProfessionalPage(Scanner reader) {
        super(reader);
    }

	@Override
	public void show() {
        System.out.println("\n\nProfissional de Saude");
        System.out.println("1. Agenda");
        System.out.println("2. Adicionar especialidade");
        System.out.println("3. Adicionar agenda");
        System.out.println("4. Sair");
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

	@Override
	public Menu input(String input) {
        int option = 0;
        try {
            option = Integer.parseInt(input);
        } catch(NumberFormatException ignore) {
            show();
            return null;
        }

        if(option == 4)
            return new HospitalPicker(reader);

        show();
        return null;
	}

	@Override
	public void destroy() {}
}