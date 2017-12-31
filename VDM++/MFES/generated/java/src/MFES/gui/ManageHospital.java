package MFES.gui;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSet;

import MFES.Hospital;
import MFES.Task;
import MFES.gui.CreateTask;

public class ManageHospital extends Menu {
	private Hospital hospital;

	private enum MenuState {MAIN, CREATE, APPOINTMENT, URGENCY, SURGERY, NURSE_TREATMENT, TECHNICIAN_TREATMENT, TRAINING};
	private MenuState state;

	private boolean first;

	public ManageHospital(Scanner reader, Hospital hospital) {
		super(reader);
		state = MenuState.MAIN;
		first = true;
		this.hospital = hospital;
	}

	@Override
	public void show() {
		switch(state) {
		case MAIN:
			if(first) {
				System.out.println("\n\n" + hospital.getName());
				first = false;
			}
			System.out.println("1. Criar");
			System.out.println("2. Consultas");
			System.out.println("3. Urgencias");
			System.out.println("4. Cirurgias");
			System.out.println("5. Tratamento Enfermeiro");
			System.out.println("6. Tratamentos Tecnico");
			System.out.println("7. Treinos");
			System.out.println("8. Sair");
			break;
		case CREATE:
			System.out.println("1. Medico");
			System.out.println("2. Enfermeiro");
			System.out.println("3. Tecnico");
			System.out.println("4. Cirurgiao");
			System.out.println("5. Atras");
			break;
		case APPOINTMENT:
		case SURGERY:
		case NURSE_TREATMENT:
		case TECHNICIAN_TREATMENT:
		case URGENCY:
		case TRAINING:
			System.out.println("1. Registar");
			System.out.println("2. Listar");
			System.out.println("3. Atras");
			break;
		}
		System.out.print("Opcao: ");
	}

	@Override
	public Menu action() {        
		Menu returnedMenu = null;

        while(returnedMenu == null) {
            String str = reader.nextLine();
            returnedMenu = input(str);
        }

		return returnedMenu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu input(String input) {        
		if(input == null || input.equals("")) {
			System.out.print("Opcao: ");
			return null;
		}

		int option = 0;
		try {
			option = Integer.parseInt(input);
		} catch(NumberFormatException ignore) {
			System.out.println("Input invalido");
			System.out.print("Opcao: ");
			return null;
		}

		switch(state) {
		case MAIN:
			if(option < 1 && option > 7)
				return null;

			if(option == 1)
				state = MenuState.CREATE;
			else if(option == 2)
				state = MenuState.APPOINTMENT;
			else if(option == 3)
				state = MenuState.URGENCY;
			else if(option == 4)
				state = MenuState.SURGERY;
			else if(option == 5)
				state = MenuState.NURSE_TREATMENT;
			else if(option == 6)
				state = MenuState.TECHNICIAN_TREATMENT;
			else if(option == 7)
				state = MenuState.TRAINING;
			else if(option == 8) {
				return new HospitalPicker(reader);
			}

			break;
		case CREATE:
			if(option < 1 && option > 3)
				return null;

			if(option == 1) {
				return new CreatePerson(reader, CreatePerson.CreateType.MEDIC, hospital);
			}
			else if(option == 2) {
				return new CreatePerson(reader, CreatePerson.CreateType.NURSE, hospital);
			}
			else if(option == 3) {
				return new CreatePerson(reader, CreatePerson.CreateType.TECHNICIAN, hospital);
			}
			else if(option == 4) {
				return new CreatePerson(reader, CreatePerson.CreateType.SURGEON, hospital);
			}
			else if(option == 5)
				state = MenuState.MAIN;

			break;
		case APPOINTMENT:
			if(option < 1 && option > 3)
				return null;

			if(option == 1) 
				return new CreateTask(reader, CreateTask.TaskType.APPOINTMENT, CreatePerson.CreateType.MEDIC, hospital);
			else if(option == 2) {
		    	VDMSet tasks = hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance());
		        if(tasks.size() <= 0) {
		            System.out.println("Neste momento nao ha medicos disponiveis");
		        } else {
		            Task[] hArr = new Task[tasks.size()];
		            Iterator<Task> iter = tasks.iterator();
		            int i = 0;
		            while(iter.hasNext())
		                hArr[i++] = iter.next();

		            ListSelectabels<Task> m = new ListSelectabels<>(reader, hArr, this);
		            m.show();
		        }
			}
			else if(option == 3)
				state = MenuState.MAIN;

			break;
		case SURGERY:
			if(option < 1 && option > 3)
				return null;

			if(option == 1)
				return new CreateTask(reader, CreateTask.TaskType.SURGERY, CreatePerson.CreateType.SURGEON, hospital);
			else if(option == 2) {
		    	VDMSet tasks = hospital.getTasksByType(MFES.quotes.SurgeryQuote.getInstance());
		        if(tasks.size() <= 0) {
		            System.out.println("Neste momento nao ha medicos disponiveis");
		        } else {
		            Task[] hArr = new Task[tasks.size()];
		            Iterator<Task> iter = tasks.iterator();
		            int i = 0;
		            while(iter.hasNext())
		                hArr[i++] = iter.next();

		            ListSelectabels<Task> m = new ListSelectabels<>(reader, hArr, this);
		            m.show();
		        }
			}
			else if(option == 3)
				state = MenuState.MAIN;

			break;
		case NURSE_TREATMENT:
			if(option < 1 && option > 3)
				return null;

			if(option == 1)
				return new CreateTask(reader, CreateTask.TaskType.TREATMENT, CreatePerson.CreateType.NURSE, hospital);
			else if(option == 2) {
		    	VDMSet tasks = hospital.getTasksByType(MFES.quotes.OtherQuote.getInstance());
		        if(tasks.size() <= 0) {
		            System.out.println("Neste momento nao ha medicos disponiveis");
		        } else {
		            Task[] hArr = new Task[tasks.size()];
		            Iterator<Task> iter = tasks.iterator();
		            int i = 0;
		            while(iter.hasNext())
		                hArr[i++] = iter.next();

		            ListSelectabels<Task> m = new ListSelectabels<>(reader, hArr, this);
		            m.show();
		        }
			}
			else if(option == 3)
				state = MenuState.MAIN;

			break;
		case TECHNICIAN_TREATMENT:
			if(option < 1 && option > 3)
				return null;

			if(option == 1)
				return new CreateTask(reader, CreateTask.TaskType.TREATMENT, CreatePerson.CreateType.TECHNICIAN, hospital);
			else if(option == 2) {
		    	VDMSet tasks = hospital.getTasksByType(MFES.quotes.OtherQuote.getInstance());
		        if(tasks.size() <= 0) {
		            System.out.println("Neste momento nao ha medicos disponiveis");
		        } else {
		            Task[] hArr = new Task[tasks.size()];
		            Iterator<Task> iter = tasks.iterator();
		            int i = 0;
		            while(iter.hasNext())
		                hArr[i++] = iter.next();

		            ListSelectabels<Task> m = new ListSelectabels<>(reader, hArr, this);
		            m.show();
		        }
			}
			else if(option == 3)
				state = MenuState.MAIN;

			break;
		case URGENCY:
			if(option < 1 && option > 3)
				return null;

			if(option == 1)
				return new CreateTask(reader, CreateTask.TaskType.URGENCY, CreatePerson.CreateType.MEDIC, hospital);
			else if(option == 2) {
		    	VDMSet tasks = hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance());
		        if(tasks.size() <= 0) {
		            System.out.println("Neste momento nao ha medicos disponiveis");
		        } else {
		            Task[] hArr = new Task[tasks.size()];
		            Iterator<Task> iter = tasks.iterator();
		            int i = 0;
		            while(iter.hasNext())
		                hArr[i++] = iter.next();

		            ListSelectabels<Task> m = new ListSelectabels<>(reader, hArr, this);
		            m.show();
		        }
			}
			else if(option == 3)
				state = MenuState.MAIN;

			break;
		case TRAINING:
			if(option < 1 && option > 3)
				return null;

			if(option == 1)
				state = MenuState.MAIN;
			else if(option == 2) {
		    	VDMSet tasks = hospital.getTrainings();
		        if(tasks.size() <= 0) {
		            System.out.println("Neste momento nao ha medicos disponiveis");
		        } else {
		            Task[] hArr = new Task[tasks.size()];
		            Iterator<Task> iter = tasks.iterator();
		            int i = 0;
		            while(iter.hasNext())
		                hArr[i++] = iter.next();

		            ListSelectabels<Task> m = new ListSelectabels<>(reader, hArr, this);
		            m.show();
		        }
			}
			else if(option == 3)
				state = MenuState.MAIN;

			break;
		}
	
		show();
		return null;
	}

	@Override
	public void destroy() {
		
	}

}