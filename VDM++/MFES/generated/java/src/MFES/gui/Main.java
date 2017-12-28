package MFES.gui;

/*
    Compile program - When inside src folder
        javac -classpath .;..\lib\codegen-runtime.jar; MFES\gui\Main.java
    Run program - When inside src folder
        java -classpath .;..\lib\codegen-runtime.jar; MFES.gui.Main
*/
public class Main {
    private Menu menu;
    private boolean close;

    public static void main(String[] args) {
        System.out.println("SAFETY NET HOSPITAL");
        
        Main main = new Main();
        main.run();
    }

    private Main() {
        close = false;
        menu = new HospitalPicker();
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
