package MFES.gui;

import MFES.Schedule;
import MFES.Types;
import java.util.Scanner;

public class CreateSchedule extends Menu {
    public static enum CreateState {YEAR, MONTH, DAY, HOUR, MINUTES, DURATION};
    private CreateState state;

    private Menu nextMenu;

    private int year = -1;
    private int month = -1;
    private int day = -1;
    private int hour = -1;
    private int minutes = -1;
    private int duration = -1;

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
            if(i <= 0)
                break;
            year = i;
            state = CreateState.MONTH;
            break;
        case MONTH:
            if(i <= 0 || i > 12)
                break;
            month = i;
            state = CreateState.DAY;
            break;
        case DAY:
            if(i <= 0 || i > 31)
                break;
            day = i;
            state = CreateState.HOUR;
            break;
        case HOUR:
            if(i < 0 || i >= 24)
                break;
            hour = i;
            state = CreateState.MINUTES;
            break;
        case MINUTES:
            if(i < 0 || i >= 60)
                break;
            minutes = i;
            state = CreateState.DURATION;
            break;
        case DURATION:
            if(i <= 0 || i > 180)
                break;
            duration = i;
            return nextMenu;
        }

        show();
		return null;
	}

	@Override
	public void destroy() {}
    
    private boolean endCondition() {
        return year > 0 && month > 0 && day > 0 && day < 31 && hour >= 0 && hour < 24 && minutes >= 0 && minutes < 60 && duration > 0;
    }

    private Schedule createSchedule() {
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

        if(h >= 24) {
            h -= 24;
            day++;
        }

        if(day > 31) {
            day -= 31;
            month++;
        }

        if(month > 12) {
            month -= 12;
            year++;
        }

        schedule = new Schedule(new Types.Date(year,month,day,new Types.Time(hour, minutes)), 
                                new Types.Date(year,month,day,new Types.Time(h, m)));
        return schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}