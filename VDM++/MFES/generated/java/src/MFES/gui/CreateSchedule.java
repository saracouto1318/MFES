package MFES.gui;

import MFES.Schedule;
import MFES.Types;
import java.util.Scanner;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Patient;
import MFES.Agenda;
import MFES.Schedule;
import MFES.gui.HospitalPicker;
import MFES.gui.ListSelectabels;

package MFES.gui;

import java.util.Scanner;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Patient;
import MFES.Person;

public class CreateSchedule extends Menu {
    public static enum CreateState {YEAR, MONTH, DAY, HOUR, MINUTES, DURATION};
    private CreateState state;

    private Menu nextMenu;

    private int year = null;
    private int month = null;
    private int day = null;
    private int hour = null;
    private int minutes = null;
    private int duration = null;

    private Schedule schedule = null;

    public CreateSchedule(Scanner reader, Menu nextMenu) {
        super(reader);
        state = CreateState.YEAR;
        this.nextMenu = nextMenu;
    }

	@Override
	public void show() {
		switch(state) {
        case YEAR:
            System.out.println("\n\nCriar");
            System.out.print("Ano: ");
            break;
        case MONTH:
            System.out.print("Mes: ");
            break;
        case DAY:
            System.out.print("Dia: ");
            break;
        case HOUR:
            System.out.print("Hora: ");
            break;
        case MINUTES:
            System.out.print("Minutos: ");
            break;
        case DURATION:
            System.out.print("Duracao: ");
            break;
        }
	}

	@Override
	public Menu action() {
        Menu returnedMenu = null;

        while(!endCondition()) {
            String str = reader.nextLine();
            returnedMenu = input(str);
        }

        createSchedule();
		return returnedMenu;
	}

	@Override
	public Menu input(String input) {
        if(input == null || input.equals("")) {
            show();
            return null;
        }

        int i = 0;
        try {
            i = Integer.parseInt(input);
        } catch(NumberFormatException ignore) {
            System.out.println("Input invalido");
            show();
            return null;
        }

        switch(state) {
        case YEAR:
            year = i;
            state = CreateState.MONTH;
            break;
        case MONTH:
            month = i;
            state = CreateState.DAY;
            break;
        case DAY:
            day = i;
            state = CreateState.HOUR;
            break;
        case HOUR:
            hour = i;
            state = CreateState.MINUTES;
            break;
        case MINUTES:
            minutes = i;
            state = CreateState.DURATION;
            break;
        case DURATION:
            duration = i;
            return nextMenu;
        }

        show();
		return null;
	}

	@Override
	public void destroy() {}
    
    private boolean endCondition() {
        return firstName != null && 
            lastName != null &&
            address != null &&
            phoneNumber != null &&
            ((type != CreateType.PATIENT && medicalNumber != null) || 
            (type == CreateType.PATIENT && healthNumber != null));
    }

    private Person createSchedule() {
        int tmpM = minutes + duration;
        int h = hour, m = minutes;
        if(tmpM > 60) {
            tmpM = 60;
        }

        duration = tmpM - minutes;

        h += ((double)duration-((double)duration%60.0))/60.0;
        duration -= ((double)duration-((double)duration%60.0));

        if(tmpM == 60) {
            m = 0;
            h++;
        }

        m += duration;

        schedule = new Schedule(new Types.Date(year,month,day,new Types.Time(hour, minutes)), 
                                new Types.Date(year,month,day,new Types.Time(h, m)));
        return schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}