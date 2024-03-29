package MFES.gui;

import java.util.Scanner;

public class ListSelectabels<T> extends Menu {
    protected T[] selectabels;
    protected Menu nextMenu;
    protected T selected;

    public ListSelectabels(Scanner reader, T[] selectabels, Menu nextMenu) {
        super(reader);
        this.selectabels = selectabels;
        this.nextMenu = nextMenu;
        selected = null;
    }

	@Override
	public void show() {
        System.out.println("\n\n");
        int i = 1;
        for(T t : selectabels)
            System.out.println(i++ + ". " + t.toString());
        
        System.out.print("Opcao: ");
	}

	@Override
	public Menu action() {
        Menu next = null;
        while(next == null) {
            String str = reader.nextLine();
            next = input(str);
        }
		return next;
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

        if(option < 1 || option > selectabels.length) {
            show();
            return null;
        }

        selected = (T)selectabels[option - 1];
		return nextMenu;
	}

	@Override
    public void destroy() {}
    
    public T getSelected() {
        return selected;
    }

}