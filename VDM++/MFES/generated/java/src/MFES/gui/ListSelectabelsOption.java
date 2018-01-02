package MFES.gui;

import java.util.Scanner;

public class ListSelectabelsOption<T> extends ListSelectabels<T> {

	@Override
	public void show() {
        System.out.println("\n\n");
        int i = 1;
        for(T t : selectabels)
            System.out.println(i++ + ". " + t.toString());

        System.out.print(i + ". Sair");
        System.out.print("Opcao: ");
	}

	@Override
	public Menu action() {
		return super.action();
	}

	@Override
	public Menu input(String input) {
        if(input == null) {
            show();
            return null;
        }

        int option = 0;
        try {
            option = Integer.parseInt(input);
        } catch(NumberFormatException ignore) {
            show();
            return null;
        }

        if(option < 1 || option > selectabels.length + 1) {
            show();
            return null;
        }

        if(option == selectabels.length)
        	return nextMenu;
        
        selected = (T)selectabels[option - 1];
		return nextMenu;
	}

	public ListSelectabelsOption(Scanner reader, T[] selectabels, Menu nextMenu) {
		super(reader, selectabels, nextMenu);
	}

}
