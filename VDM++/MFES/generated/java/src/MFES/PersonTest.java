package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PersonTest {
  private Patient patient =
      new Patient("Rua 1 Maio", "Rui", "Andrade", "123456789", "223456111", "0987654321");
  private HealthProfessional doctor =
      new HealthProfessional(
          "Rua de Cima",
          "Ana",
          "Marques",
          "123432156",
          "921349076",
          "111111222",
          MFES.quotes.DoctorQuote.getInstance());
  private HealthProfessional surgeon =
      new HealthProfessional(
          "Rua 2",
          "Diogo",
          "Viana",
          "234512389",
          "921349134",
          "111111232",
          MFES.quotes.SurgeonQuote.getInstance());
  private HealthProfessional nurse =
      new HealthProfessional(
          "Rua de Baixo",
          "Lisete",
          "Antunes",
          "123444654",
          "921378643",
          "111222333",
          MFES.quotes.NurseQuote.getInstance());
  private HealthProfessional technician =
      new HealthProfessional(
          "Rua Antero Marques",
          "InÃªs",
          "Pinto",
          "123432151",
          "921348765",
          "123432578",
          MFES.quotes.TechnicianQuote.getInstance());

  private void assertTrue(final Boolean cond) {

    return;
  }

  public void testGetInformation() {

    IO.print("\n Getting patient informations \n");
    assertTrue(Utils.equals(patient.getHealthNumber(), "0987654321"));
    assertTrue(Utils.equals(patient.getCC(), "123456789"));
    assertTrue(
        Utils.equals(
            patient.getInfo(),
            "Name: "
                + "Rui"
                + " "
                + "Andrade"
                + "\nAddress: "
                + "Rua 1 Maio"
                + "\nPhone Number: "
                + "223456111"
                + "\nCC: "
                + "123456789"));
    IO.print("\n Getting doctor informations \n");
    assertTrue(Utils.equals(doctor.getMedicalNumber(), "111111222"));
    assertTrue(Utils.equals(doctor.getCC(), "123432156"));
    assertTrue(
        Utils.equals(
            doctor.getInfo(),
            "Name: "
                + "Ana"
                + " "
                + "Marques"
                + "\nAddress: "
                + "Rua de Cima"
                + "\nPhone Number: "
                + "921349076"
                + "\nCC: "
                + "123432156"));
    assertTrue(Utils.equals(doctor.getType(), MFES.quotes.DoctorQuote.getInstance()));
    IO.print("\n Getting surgeon informations \n");
    assertTrue(Utils.equals(surgeon.getMedicalNumber(), "111111232"));
    assertTrue(Utils.equals(surgeon.getCC(), "234512389"));
    assertTrue(
        Utils.equals(
            surgeon.getInfo(),
            "Name: "
                + "Diogo"
                + " "
                + "Viana"
                + "\nAddress: "
                + "Rua 2"
                + "\nPhone Number: "
                + "921349134"
                + "\nCC: "
                + "234512389"));
    assertTrue(Utils.equals(surgeon.getType(), MFES.quotes.SurgeonQuote.getInstance()));
    IO.print("\n Getting nurse informations \n");
    assertTrue(Utils.equals(nurse.getMedicalNumber(), "111222333"));
    assertTrue(Utils.equals(nurse.getCC(), "123444654"));
    assertTrue(
        Utils.equals(
            nurse.getInfo(),
            "Name: "
                + "Lisete"
                + " "
                + "Antunes"
                + "\nAddress: "
                + "Rua de Baixo"
                + "\nPhone Number: "
                + "921378643"
                + "\nCC: "
                + "123444654"));
    assertTrue(Utils.equals(nurse.getType(), MFES.quotes.NurseQuote.getInstance()));
    IO.print("\n Getting technician informations \n");
    assertTrue(Utils.equals(technician.getMedicalNumber(), "123432578"));
    assertTrue(Utils.equals(technician.getCC(), "123432151"));
    assertTrue(
        Utils.equals(
            technician.getInfo(),
            "Name: "
                + "InÃªs"
                + " "
                + "Pinto"
                + "\nAddress: "
                + "Rua Antero Marques"
                + "\nPhone Number: "
                + "921348765"
                + "\nCC: "
                + "123432151"));
    assertTrue(Utils.equals(technician.getType(), MFES.quotes.TechnicianQuote.getInstance()));
  }

  public void testAddRemovePatient() {

    IO.print("\n Number of patients: ");
    IO.print(doctor.getPatients().size());
    assertTrue(Utils.equals(doctor.getPatients().size(), 0L));
    IO.print("\n Adding a patient \n");
    doctor.addPatient(patient);
    IO.print("\n Number of patients: ");
    IO.print(doctor.getPatients().size());
    assertTrue(Utils.equals(doctor.getPatients().size(), 1L));
    IO.print("\n Removing a patient \n");
    doctor.removePatient(patient);
    IO.print("\n Number of patients: ");
    IO.print(doctor.getPatients().size());
    assertTrue(Utils.equals(doctor.getPatients().size(), 0L));
    IO.print("\n Adding a patient \n");
    assertTrue(Utils.equals(surgeon.getPatients().size(), 0L));
    surgeon.addPatient(patient);
    IO.print("\n Number of patients: ");
    IO.print(surgeon.getPatients().size());
    assertTrue(Utils.equals(surgeon.getPatients().size(), 1L));
  }

  public void testAddRemoveSpecialty() {

    Specialty specialty1 = new Specialty("General");
    Specialty specialty2 = new Specialty("Cardio");
    IO.print("\n Number of specialties: ");
    IO.print(doctor.getSpecialties().size());
    assertTrue(Utils.equals(doctor.getSpecialties().size(), 0L));
    IO.print("\n Adding a specialty \n");
    doctor.addSpecialty(specialty1);
    IO.print("\n Number of specialties: ");
    IO.print(doctor.getSpecialties().size());
    assertTrue(Utils.equals(specialty1.getName(), "General"));
    assertTrue(Utils.equals(doctor.getSpecialties().size(), 1L));
    assertTrue(Utils.equals(doctor.getSpecialties(), SetUtil.set(specialty1)));
    IO.print("\n Adding a specialty \n");
    doctor.addSpecialty(specialty2);
    IO.print("\n Number of specialties: ");
    IO.print(doctor.getSpecialties().size());
    assertTrue(Utils.equals(specialty2.getName(), "Cardio"));
    assertTrue(Utils.equals(doctor.getSpecialties().size(), 2L));
    assertTrue(Utils.equals(doctor.getSpecialties(), SetUtil.set(specialty1, specialty2)));
    IO.print("\n Removing a specialty \n");
    doctor.removeSpecialty(specialty1);
    IO.print("\n Number of specialties: ");
    IO.print(doctor.getSpecialties().size());
    assertTrue(Utils.equals(doctor.getSpecialties().size(), 1L));
    assertTrue(Utils.equals(doctor.getSpecialties(), SetUtil.set(specialty2)));
  }

  public static void main() {

    PersonTest personTest = new PersonTest();
    IO.print("\n *****Running PersonTest***** \n");
    personTest.testGetInformation();
    personTest.testAddRemovePatient();
    personTest.testAddRemoveSpecialty();
  }

  public PersonTest() {}

  public String toString() {

    return "PersonTest{"
        + "patient := "
        + Utils.toString(patient)
        + ", doctor := "
        + Utils.toString(doctor)
        + ", surgeon := "
        + Utils.toString(surgeon)
        + ", nurse := "
        + Utils.toString(nurse)
        + ", technician := "
        + Utils.toString(technician)
        + "}";
  }
}
