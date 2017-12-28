package MFES.gui;

import java.util.Scanner;

import MFES.Hospital;

public class CreateHospital extends Menu {
    private enum CreateState {NAME, ADDRESS};
    private CreateState state;

    private String name = null;
    private String address = null;

    public CreateHospital(Scanner reader) {
        super(reader);
        state = CreateState.NAME;
    }

	@Override
	public void show() {
		System.out.println("\nNovo Hospital");
		System.out.print("Nome: ");
	}

	@Override
	public Menu action() {
        Menu returnedMenu = null;

        while(!endCondition(returnedMenu)) {
            String str = reader.nextLine();
            returnedMenu = input(str);
        }

		return returnedMenu;
	}

	@Override
	public Menu input(String input) {
        if(input == null || input.equals("")) {
            switch(state) {
            case NAME:
                System.out.print("Nome: ");
                break;
            case ADDRESS:
                System.out.print("Morada: ");
            }
            return null;
        }

        switch(state) {
        case NAME:
            name = input;
            state = CreateState.ADDRESS;
            System.out.print("Morada: ");
            break;
        case ADDRESS:
            address = input;
            Hospital h = newHospital();
            return new ManageHospital(reader, h);
        }

		return null;
	}

	@Override
	public void destroy() {}
    
    private boolean endCondition(Menu returnedMenu) {
        return state == CreateState.ADDRESS && name != null && address != null;
    }

    private Hospital newHospital() {
        return new Hospital(name, address, Main.snh);
    }
}