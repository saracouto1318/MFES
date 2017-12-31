package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSet;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Schedule;
import MFES.Agenda;


public class CreateAgenda extends Menu {
	public static enum CreateState {CHOOSE_HOSPITAL, CREATE_SCHEDULE};
	private CreateState state;

	private HealthProfessional hp;
	
	public CreateAgenda(Scanner reader, HealthProfessional hp) {
		super(reader);
		state = CreateState.CHOOSE_HOSPITAL;
		this.hp = hp;				
	}

	@SuppressWarnings("unchecked")
	@Override
	public void show() {
        VDMSet hospitals = Main.snh.getHospitals();

        if(hospitals.size() <= 0) {
            System.out.println("Neste momento nao ha hospitais disponiveis");
            return;
        }

        Hospital[] hArr = new Hospital[hospitals.size()];
        Iterator<Hospital> iter = hospitals.iterator();
        int i = 0;
        while(iter.hasNext()) {
            hArr[i++] = iter.next();
        }

        ListSelectabels<Hospital> m = new ListSelectabels<>(reader, hArr, this);
        m.show();
        m.action();
        
        Hospital h = m.getSelected();
        
        CreateSchedule cs = new CreateSchedule(reader, this);
        cs.show();
        cs.action();
        
        Schedule s = cs.getSchedule();
        
        //h.getAgenda(hp).
	}

	@Override
	public Menu action() {
		show();
		return null;
	}

	@Override
	public Menu input(String input) {
		return null;
	}

	@Override
	public void destroy() {}

}
