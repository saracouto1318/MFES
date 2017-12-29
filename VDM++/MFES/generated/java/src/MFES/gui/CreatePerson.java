package MFES.gui;

import java.util.Scanner;

import MFES.HealthProfessional;
import MFES.Hospital;
import MFES.Patient;
import MFES.Person;

public class CreatePerson extends Menu {
    private static enum CreateState {CC, FIRSTNAME, LASTNAME, ADDRESS, PHONENUMBER, MEDICALNUMBER, HEALTHNUMBER};
    private CreateState state;

    public static enum CreateType {MEDIC, NURSE, TECHNICIAN, SURGEON, PATIENT};
    private CreateType type;

    private Hospital hospital;

    private String cc = null;
    private String firstName = null;
    private String lastName = null;
    private String address = null;
    private String phoneNumber = null;
    private String medicalNumber = null;
    private String healthNumber = null;

    private Person person = null;

    public CreatePerson(Scanner reader, CreateType type, Hospital hospital) {
        super(reader);
        state = CreateState.CC;
        this.type = type;
        this.hospital = hospital;
    }

	@Override
	public void show() {
		switch(state) {
        case CC:
            System.out.println("\n\nCriar");
            System.out.print("CC: ");
            break;
        case FIRSTNAME:
            System.out.print("Primeiro Nome: ");
            break;
        case LASTNAME:
            System.out.print("Ultimo Nome: ");
            break;
        case ADDRESS:
            System.out.print("Morada: ");
            break;
        case PHONENUMBER:
            System.out.print("Telemovel: ");
            break;
        case MEDICALNUMBER:
            System.out.print("Numero de medico: ");
            break;
        case HEALTHNUMBER:
            System.out.print("Numero de saude: ");
            break;
        }
	}

	@Override
	public Menu action() {
        Menu returnedMenu = null;

        while(!endCondition(returnedMenu)) {
            String str = reader.nextLine();
            returnedMenu = input(str);
        }

        createPerson();
		return returnedMenu;
	}

	@Override
	public Menu input(String input) {
        if(input == null || input.equals("")) {
            show();
            return null;
        }

        switch(state) {
        case CC:
            cc = input;
            state = CreateState.FIRSTNAME;
            break;
        case FIRSTNAME:
            firstName = input;
            state = CreateState.LASTNAME;
            break;
        case LASTNAME:
            lastName = input;
            state = CreateState.ADDRESS;
            break;
        case ADDRESS:
            address = input;
            state = CreateState.PHONENUMBER;
            break;
        case PHONENUMBER:
            phoneNumber = input;
            if(type == CreateType.PATIENT)
                state = CreateState.HEALTHNUMBER;
            else
                state = CreateState.MEDICALNUMBER;
            break;
        case MEDICALNUMBER:
            medicalNumber = input;
            return new HealthProfessionalPage(reader);
        case HEALTHNUMBER:
            healthNumber = input;
            return new HealthProfessionalPage(reader);
        }

        show();
		return null;
	}

	@Override
	public void destroy() {}
    
    private boolean endCondition(Menu returnedMenu) {
        return firstName != null && 
            lastName != null &&
            address != null &&
            phoneNumber != null &&
            ((type != CreateType.PATIENT && medicalNumber != null) || 
            (type == CreateType.PATIENT && healthNumber != null));
    }

    private Person createPerson() {
        HealthProfessional h = null;

        switch(type) {
        case MEDIC:
            h = new HealthProfessional(address, firstName, lastName, cc, phoneNumber, medicalNumber, "MEDIC");
            hospital.addMedAssociated(h);
        case NURSE:
            h = new HealthProfessional(address, firstName, lastName, cc, phoneNumber, medicalNumber, "NURSE");
            hospital.addMedAssociated(h);
        case TECHNICIAN:
            h = new HealthProfessional(address, firstName, lastName, cc, phoneNumber, medicalNumber, "TECHNICIAN");
            hospital.addMedAssociated(h);
        case SURGEON:
            h = new HealthProfessional(address, firstName, lastName, cc, phoneNumber, medicalNumber, "SURGEON");
            hospital.addMedAssociated(h);
        case PATIENT:
            person = new Patient(address, firstName, lastName, cc, phoneNumber, healthNumber);
        }

        person = h;
        return person;
    }

    public Person getPerson() {
        return person;
    }
}