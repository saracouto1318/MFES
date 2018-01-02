package MFES.gui;

import MFES.SafetyNetHospital;

import java.util.Scanner;

/*
    Compile program - When inside src folder
        javac -classpath .;..\lib\codegen-runtime.jar; MFES\gui\Main.java
    Run program - When inside src folder
        java -classpath .;..\lib\codegen-runtime.jar; MFES.gui.Main
*/
public class Main {
    private Scanner reader;
    private Menu menu;
    private boolean close;

    public static SafetyNetHospital snh = new SafetyNetHospital();

    public static void main(String[] args) {
        System.out.println("SAFETY NET HOSPITAL\n");
        
        Main main = new Main();
        main.run();
    }

    private Main() {
        reader = new Scanner(System.in);
        
        close = false;
        menu = new HospitalPicker(reader);

    }

    private void run() {
        while(!close) {
            menu.show();
            Menu tmp = menu.action();
            if(tmp == null)
                close = true;
            else
                menu = tmp;
        }
    }
}
