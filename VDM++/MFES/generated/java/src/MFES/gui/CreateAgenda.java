package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSet;

import MFES.Agenda;
import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Schedule;


public class CreateAgenda extends Menu {
	private HealthProfessional hp;
	
	public CreateAgenda(Scanner reader, HealthProfessional hp) {
		super(reader);
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
        
        h.getAgenda(hp).addSchedule(s);
        
        System.out.println("Sucesso");
        
    	System.out.println("\n");
    	
    	VDMSet hAgenda = h.getAgendas();
		Iterator<Agenda> aIter = hAgenda.iterator();
    	while(aIter.hasNext()) {
    		System.out.println(aIter.next());
    	}
    	
    	System.out.println("\n");
        
	}

	@Override
	public Menu action() {
		return null;
	}

	@Override
	public Menu input(String input) {
		return null;
	}

	@Override
	public void destroy() {}

}
