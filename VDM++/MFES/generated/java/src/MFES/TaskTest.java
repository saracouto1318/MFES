package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TaskTest {
  private SafetyNetHospital safetyNet = new SafetyNetHospital();
  private Types.Time time1 = new Types.Time(12L, 10L);
  private Types.Date date = new Types.Date(2017L, 11L, 25L, time1);
  private Types.Date d = new Types.Date(2017L, 2L, 25L, time1);
  private Types.Date date1 = new Types.Date(2017L, 12L, 25L, time1);
  private Types.Time time2 = new Types.Time(12L, 30L);
  private Types.Date date2 = new Types.Date(2017L, 12L, 25L, time2);
  private Schedule schedule = new Schedule(Utils.copy(date1), Utils.copy(date2));
  private Types.Time time3 = new Types.Time(12L, 15L);
  private Types.Date date3 = new Types.Date(2017L, 12L, 25L, time3);
  private Types.Time time4 = new Types.Time(12L, 35L);
  private Types.Date date4 = new Types.Date(2017L, 12L, 25L, time4);
  private Schedule schedule2 = new Schedule(Utils.copy(date3), Utils.copy(date4));
  private Types.Time time5 = new Types.Time(12L, 40L);
  private Types.Date date5 = new Types.Date(2017L, 12L, 25L, time5);
  private Types.Time time6 = new Types.Time(12L, 50L);
  private Types.Date date6 = new Types.Date(2017L, 12L, 25L, time6);
  private Schedule schedule3 = new Schedule(Utils.copy(date5), Utils.copy(date6));
  private Patient patient =
      new Patient("Rua 1 Maio", "Rui", "Andrade", "123456789", "223456111", "0987654321");
  private Patient patient2 =
      new Patient("Rua 1 Maio", "Diogo", "Andrade", "123321123", "911112345", "908765123");
  private Patient patient3 =
      new Patient("Rua 1 Maio", "Vitor", "Andrade", "135790864", "912345334", "123432130");
  private Patient patient4 =
      new Patient("Rua 1 Maio", "Simone", "Andrade", "234123765", "931238654", "0987654143");
  private Hospital hospital = new Hospital("Hospital dos Lusíadas", "Rua de Cima", safetyNet);
  private HealthProfessional doctor =
      new HealthProfessional(
          "Rua de Cima",
          "Ana",
          "Marques",
          "123432156",
          "921349076",
          "111111222",
          MFES.quotes.DoctorQuote.getInstance());
  private HealthProfessional doctor2 =
      new HealthProfessional(
          "Rua de Cima",
          "Anabela",
          "Marques",
          "123432157",
          "921349077",
          "111111223",
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
  private HealthProfessional secSurgeon =
      new HealthProfessional(
          "Rua 2",
          "Diana",
          "Viana",
          "234512390",
          "921349135",
          "111111235",
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
          "Rua de Baixo",
          "Luís",
          "Antunes",
          "123444655",
          "921377654",
          "111222345",
          MFES.quotes.TechnicianQuote.getInstance());
  private Appointment appointment = new Appointment(doctor, schedule, patient, hospital);
  private Appointment urgencies =
      new Appointment(doctor2, MFES.quotes.HighQuote.getInstance(), schedule, patient2, hospital);
  private Surgery surgery = new Surgery(surgeon, schedule3, patient3, hospital);
  private Treatment treatment =
      new Treatment(technician, "Fisioterapia", schedule, patient4, hospital);
  private Medicament medicament = new Medicament("Brufen");
  private Prescription prescription = new Prescription("123");

  private void assertTrue(final Boolean cond) {

    return;
  }

  public void testGetsSetsTask() {

    IO.print("\n Getting appointment informations \n");
    assertTrue(Utils.equals(appointment.getPatient().getCC(), "123456789"));
    assertTrue(Utils.equals(appointment.getHospital().getName(), "Hospital dos Lusíadas"));
    assertTrue(Utils.equals(appointment.getType(), MFES.quotes.AppointmentQuote.getInstance()));
    assertTrue(Utils.equals(urgencies.getType(), MFES.quotes.UrgenciesQuote.getInstance()));
    assertTrue(Utils.equals(surgery.getType(), MFES.quotes.SurgeryQuote.getInstance()));
    assertTrue(Utils.equals(treatment.getType(), MFES.quotes.OtherQuote.getInstance()));
    IO.print("\n Getting tasks informations \n");
    assertTrue(Utils.equals(appointment.getMedAssoc().getCC(), "123432156"));
    assertTrue(
        Utils.equals(
            appointment.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()).size(), 0L));
    assertTrue(Utils.equals(urgencies.getMedAssoc().getCC(), "123432157"));
    assertTrue(Utils.equals(surgery.getMedAssoc().getCC(), "234512389"));
    IO.print("\n Checking schedules \n");
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.min, 10L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.min, 30L));
    assertTrue(
        Utils.equals(
            appointment
                .getSchedule()
                .lessThan(
                    appointment.getSchedule().getScheduleStart(),
                    appointment.getSchedule().getScheduleEnd()),
            true));
    appointment.getSchedule().setSchedule(Utils.copy(date3), Utils.copy(date4));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.min, 15L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.min, 35L));
    appointment.setSchedule(schedule2);
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.min, 15L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.min, 35L));
  }

  public void testAppointment() {

    IO.print("\n Checking appointment priority \n");
    assertTrue(Utils.equals(appointment.getPriority(), MFES.quotes.MediumQuote.getInstance()));
    assertTrue(Utils.equals(urgencies.getPriority(), MFES.quotes.HighQuote.getInstance()));
    urgencies.setPriority(MFES.quotes.LowQuote.getInstance());
    assertTrue(Utils.equals(urgencies.getPriority(), MFES.quotes.LowQuote.getInstance()));
    IO.print("\n Checking appointment prescriptions \n");
    IO.print("\n Number of prescriptions: ");
    IO.print(appointment.getPrescriptions().size() + urgencies.getPrescriptions().size());
    assertTrue(Utils.equals(appointment.getPrescriptions().size(), 0L));
    assertTrue(Utils.equals(urgencies.getPrescriptions().size(), 0L));
    IO.print("\n\n Getting prescription code and medicament name \n");
    assertTrue(Utils.equals(medicament.getName(), "Brufen"));
    assertTrue(Utils.equals(prescription.getCode(), "123"));
    assertTrue(Utils.equals(prescription.getMedicaments().size(), 0L));
    IO.print("\n Adding medicament \n");
    IO.print("\n Number of medicaments: ");
    IO.print(prescription.getMedicaments().size());
    prescription.addMedicament(medicament);
    assertTrue(Utils.equals(prescription.getMedicaments().size(), 1L));
    assertTrue(Utils.equals(prescription.getMedicaments(), SetUtil.set(medicament)));
    IO.print("\n\n Removing medicament \n");
    IO.print("\n Number of medicaments: ");
    IO.print(prescription.getMedicaments().size());
    prescription.removeMedicament(medicament);
    assertTrue(Utils.equals(prescription.getMedicaments().size(), 0L));
    assertTrue(Utils.empty(prescription.getMedicaments()));
    IO.print("\n Adding a prescription \n");
    IO.print("\n Number of prescriptions: ");
    IO.print(appointment.getPrescriptions().size() + urgencies.getPrescriptions().size());
    appointment.addPrescription(prescription);
    urgencies.addPrescription(prescription);
    assertTrue(Utils.equals(appointment.getPrescriptions().size(), 1L));
    assertTrue(Utils.equals(urgencies.getPrescriptions().size(), 1L));
    IO.print("\n\n Removing a prescription \n");
    IO.print("\n Number of prescriptions: ");
    IO.print(appointment.getPrescriptions().size() + urgencies.getPrescriptions().size());
    appointment.removePrescription(prescription);
    urgencies.removePrescription(prescription);
    assertTrue(Utils.equals(appointment.getPrescriptions().size(), 0L));
    assertTrue(Utils.equals(urgencies.getPrescriptions().size(), 0L));
  }

  public void testSurgery() {

    IO.print("\n Checking surgery informations \n");
    assertTrue(
        Utils.equals(surgery.getSurgeryPersons(MFES.quotes.SurgeonQuote.getInstance()).size(), 0L));
    surgery.addSecondaryDoctor(secSurgeon);
    assertTrue(
        Utils.equals(surgery.getSurgeryPersons(MFES.quotes.SurgeonQuote.getInstance()).size(), 1L));
    surgery.removeSecondaryDoctor(secSurgeon);
    assertTrue(
        Utils.equals(surgery.getSurgeryPersons(MFES.quotes.SurgeonQuote.getInstance()).size(), 0L));
    assertTrue(
        Utils.equals(surgery.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()).size(), 0L));
    surgery.addOther(nurse);
    assertTrue(
        Utils.equals(surgery.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()).size(), 1L));
    surgery.removeOther(nurse);
    assertTrue(
        Utils.equals(surgery.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()).size(), 0L));
    assertTrue(Utils.equals(surgery.getMainDoctor().getCC(), "234512389"));
    surgery.setMainDoctor(secSurgeon);
    assertTrue(Utils.equals(surgery.getMainDoctor().getCC(), "234512390"));
  }

  public void testTreatment() {

    IO.print("\n Checking treatment informations \n");
    assertTrue(Utils.equals(treatment.getName(), "Fisioterapia"));
    assertTrue(Utils.equals(treatment.getMed().getCC(), "123444655"));
  }

  public static void main() {

    TaskTest taskTest = new TaskTest();
    IO.print("\n *****Running TaskTest***** \n");
    taskTest.testGetsSetsTask();
    taskTest.testAppointment();
    taskTest.testSurgery();
    taskTest.testTreatment();
  }

  public TaskTest() {}

  public String toString() {

    return "TaskTest{"
        + "safetyNet := "
        + Utils.toString(safetyNet)
        + ", time1 := "
        + Utils.toString(time1)
        + ", date := "
        + Utils.toString(date)
        + ", d := "
        + Utils.toString(d)
        + ", date1 := "
        + Utils.toString(date1)
        + ", time2 := "
        + Utils.toString(time2)
        + ", date2 := "
        + Utils.toString(date2)
        + ", schedule := "
        + Utils.toString(schedule)
        + ", time3 := "
        + Utils.toString(time3)
        + ", date3 := "
        + Utils.toString(date3)
        + ", time4 := "
        + Utils.toString(time4)
        + ", date4 := "
        + Utils.toString(date4)
        + ", schedule2 := "
        + Utils.toString(schedule2)
        + ", time5 := "
        + Utils.toString(time5)
        + ", date5 := "
        + Utils.toString(date5)
        + ", time6 := "
        + Utils.toString(time6)
        + ", date6 := "
        + Utils.toString(date6)
        + ", schedule3 := "
        + Utils.toString(schedule3)
        + ", patient := "
        + Utils.toString(patient)
        + ", patient2 := "
        + Utils.toString(patient2)
        + ", patient3 := "
        + Utils.toString(patient3)
        + ", patient4 := "
        + Utils.toString(patient4)
        + ", hospital := "
        + Utils.toString(hospital)
        + ", doctor := "
        + Utils.toString(doctor)
        + ", doctor2 := "
        + Utils.toString(doctor2)
        + ", surgeon := "
        + Utils.toString(surgeon)
        + ", secSurgeon := "
        + Utils.toString(secSurgeon)
        + ", nurse := "
        + Utils.toString(nurse)
        + ", technician := "
        + Utils.toString(technician)
        + ", appointment := "
        + Utils.toString(appointment)
        + ", urgencies := "
        + Utils.toString(urgencies)
        + ", surgery := "
        + Utils.toString(surgery)
        + ", treatment := "
        + Utils.toString(treatment)
        + ", medicament := "
        + Utils.toString(medicament)
        + ", prescription := "
        + Utils.toString(prescription)
        + "}";
  }
}
