package MFES.gui;

import java.util.Scanner;

/*
    Compile program - When inside src folder
        javac -classpath .;..\lib\codegen-runtime.jar; MFES\gui\Main.java
    Run program - When inside src folder
        java -classpath .;..\lib\codegen-runtime.jar; MFES.gui.Main
*/
public class HospitalPicker implements Menu {
    private Scanner reader;

    public HospitalPicker() {
        reader = new Scanner(System.in);
    }

	@Override
	public void show() {
		System.out.println("\n1. Create Hospital");
		System.out.println("2. Pick Hospital");
		System.out.print("Option: ");
    }
    
	@Override
	public Menu action() {
        String str = reader.next();
        return input(str);
	}

	@Override
	public Menu input(String input) {
        int number = 0;
        try {
            number = Integer.parseInt(input);
        } catch(NumberFormatException ignore) {
            System.out.println("Invalid input");
            show();
            return null;
        }

        if(number == 1)
            return null;
        else if(number == 2)
            return null;
        return null;
	}

	@Override
	public void destroy() {
        reader.close();
    }

}