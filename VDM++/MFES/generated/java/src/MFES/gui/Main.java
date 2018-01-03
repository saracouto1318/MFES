package MFES.gui;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.SafetyNetHospital;
import MFES.Schedule;
import MFES.Types;
import MFES.quotes.DoctorQuote;

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
    
    static {
    	Hospital h = new Hospital("Sao Joao", "Circunvalacao", snh);
    	HealthProfessional hp = new HealthProfessional("123123123", "Vasco", "Pereira", "rua Sao Felix", "213445216", "15547", DoctorQuote.getInstance());
    	Schedule sch = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(8, 30)), new Types.Date(2018, 1, 3, new Types.Time(10, 30)));
    	snh.addHospital(h);
    	h.addMedAssociated(hp);
    	h.getAgenda(hp).addSchedule(sch);
    };

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
