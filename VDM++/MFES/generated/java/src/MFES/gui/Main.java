package MFES.gui;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.SafetyNetHospital;
import MFES.Schedule;
import MFES.Types;
import MFES.quotes.DoctorQuote;
import MFES.quotes.NurseQuote;
import MFES.quotes.SurgeonQuote;

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
    
    /*static {
    	Hospital h1 = new Hospital("Sao Joao", "Circunvalacao", snh);
    	Hospital h2 = new Hospital("Trofa Saude", "Vila Nova de Gaia", snh);
    	HealthProfessional hp1 = new HealthProfessional("123123123", "Vasco", "Pereira", "rua Sao Felix", "213445216", "15547", DoctorQuote.getInstance());
    	HealthProfessional hp2 = new HealthProfessional("450135107", "Sara", "Fernanes", "rua Mario Centeno", "915686203", "785136", DoctorQuote.getInstance());
    	HealthProfessional hp3 = new HealthProfessional("458123765", "Fernando", "Couto", "rua Doutor Miguel Cardoso", "935600112", "206957", NurseQuote.getInstance());
    	HealthProfessional hp4 = new HealthProfessional("023467051", "Miguel", "Caetano", "Avenida dos Aliados", "968542658", "100453", SurgeonQuote.getInstance());
    	HealthProfessional hp5 = new HealthProfessional("956213404", "Carlos", "Magalhaes", "IC2", "935214788", "235164", SurgeonQuote.getInstance());
    	Schedule sch1 = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(8, 30)), new Types.Date(2018, 1, 3, new Types.Time(10, 30)));
    	Schedule sch2 = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(8, 30)), new Types.Date(2018, 1, 3, new Types.Time(10, 30)));
    	Schedule sch3 = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(8, 30)), new Types.Date(2018, 1, 3, new Types.Time(10, 30)));
    	Schedule sch4 = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(8, 30)), new Types.Date(2018, 1, 3, new Types.Time(10, 30)));
    	Schedule sch5 = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(10, 31)), new Types.Date(2018, 1, 3, new Types.Time(11, 30)));
    	Schedule sch6 = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(8, 30)), new Types.Date(2018, 1, 3, new Types.Time(10, 30)));
    	Schedule sch7 = new Schedule(new Types.Date(2018, 1, 3, new Types.Time(10, 31)), new Types.Date(2018, 1, 3, new Types.Time(11, 30)));
    	
    	snh.addHospital(h1);
    	snh.addHospital(h2);
    	h1.addMedAssociated(hp1);
    	h2.addMedAssociated(hp1);
    	h1.addMedAssociated(hp2);
    	h1.addMedAssociated(hp3);
    	h1.addMedAssociated(hp4);
    	h2.addMedAssociated(hp4);
    	h1.addMedAssociated(hp5);
    	h1.getAgenda(hp1).addSchedule(sch1);
    	h1.getAgenda(hp2).addSchedule(sch2);
    	h1.getAgenda(hp3).addSchedule(sch3);
    	h1.getAgenda(hp4).addSchedule(sch4);
    	h1.getAgenda(hp4).addSchedule(sch5);
    	h1.getAgenda(hp5).addSchedule(sch6);
    	h1.getAgenda(hp5).addSchedule(sch7);
    };*/

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
