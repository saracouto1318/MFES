package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TaskTest {
  private SafetyNetHospital safetyNet = new SafetyNetHospital();
  private Types.Time time1 = new Types.Time(12L, 10L);
  private Types.Date date = new Types.Date(2017L, 11L, 25L, time1);
  private Types.Date d = new Types.Date(2017L, 2L, 25L, time1);
  private Types.Date d2 = new Types.Date(2016L, 2L, 25L, time1);
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
  private Types.Time time7 = new Types.Time(12L, 10L);
  private Types.Date date7 = new Types.Date(2018L, 11L, 22L, time7);
  private Types.Time time8 = new Types.Time(12L, 30L);
  private Types.Date date8 = new Types.Date(2018L, 11L, 22L, time8);
  private Schedule schedule4 = new Schedule(Utils.copy(date7), Utils.copy(date8));
  private Types.Time time9 = new Types.Time(12L, 35L);
  private Types.Date date9 = new Types.Date(2017L, 11L, 22L, time9);
  private Types.Time time10 = new Types.Time(12L, 45L);
  private Types.Date date10 = new Types.Date(2017L, 11L, 22L, time10);
  private Schedule schedule5 = new Schedule(Utils.copy(date9), Utils.copy(date10));
  private Patient patient =
      new Patient("Rua 1 Maio", "Rui", "Andrade", "123456789", "223456111", "0987654321");
  private Patient patient2 =
      new Patient("Rua 1 Maio", "Diogo", "Andrade", "123321123", "911112345", "908765123");
  private Patient patient3 =
      new Patient("Rua 1 Maio", "Vitor", "Andrade", "135790864", "912345334", "123432130");
  private Patient patient4 =
      new Patient("Rua 1 Maio", "Simone", "Andrade", "234123765", "931238654", "0987654143");
  private Hospital hospital = new Hospital("Hospital dos LusÃ­adas", "Rua de Cima", safetyNet);
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
          "LuÃ­s",
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

  private void assertTrue(final Boolean cond) {

    return;
  }

  public void testGetsSetsTask() {

    Agenda agenda1 = null;
    Agenda agenda2 = null;
    Agenda agenda3 = null;
    Agenda agenda4 = null;
    Agenda agenda5 = null;
    Agenda agenda6 = null;
    hospital.addMedAssociated(doctor);
    hospital.addMedAssociated(doctor2);
    hospital.addMedAssociated(surgeon);
    hospital.addMedAssociated(technician);
    hospital.addMedAssociated(nurse);
    hospital.addMedAssociated(secSurgeon);
    for (Iterator iterator_29 = hospital.getAgendas().iterator(); iterator_29.hasNext(); ) {
      Agenda a = (Agenda) iterator_29.next();
      if (Utils.equals(a.getHealthProfessional(), doctor)) {
        agenda1 = a;
      } else {
        if (Utils.equals(a.getHealthProfessional(), doctor2)) {
          agenda2 = a;
        } else {
          if (Utils.equals(a.getHealthProfessional(), surgeon)) {
            agenda3 = a;
          } else {
            if (Utils.equals(a.getHealthProfessional(), secSurgeon)) {
              agenda5 = a;
            } else {
              if (Utils.equals(a.getHealthProfessional(), nurse)) {
                agenda6 = a;
              } else {
                agenda4 = a;
              }
            }
          }
        }
      }
    }
    assertTrue(Utils.empty(hospital.getAgenda(doctor).getAgenda()));
    assertTrue(Utils.equals(hospital.getAgenda(doctor).getAgenda().size(), 0L));
    agenda1.addSchedule(schedule);
    agenda1.addSchedule(schedule3);
    assertTrue(Utils.equals(agenda1.getHealthProfessional().getCC(), doctor.getCC()));
    assertTrue(Utils.equals(agenda1.getAgenda().size(), 2L));
    agenda2.addSchedule(schedule);
    assertTrue(Utils.equals(agenda2.getAgenda().size(), 1L));
    agenda3.addSchedule(schedule3);
    assertTrue(Utils.equals(agenda3.getAgenda().size(), 1L));
    agenda4.addSchedule(schedule);
    assertTrue(Utils.equals(agenda4.getAgenda().size(), 1L));
    agenda4.removeSchedule(schedule);
    assertTrue(Utils.equals(agenda4.getAgenda().size(), 0L));
    agenda4.addSchedule(schedule);
    assertTrue(Utils.equals(agenda4.getAgenda().size(), 1L));
    agenda5.addSchedule(schedule3);
    assertTrue(Utils.equals(agenda5.getAgenda().size(), 1L));
    agenda6.addSchedule(schedule3);
    assertTrue(Utils.equals(agenda6.getAgenda().size(), 1L));
    hospital.addTask(appointment);
    hospital.addTask(urgencies);
    hospital.addTask(surgery);
    hospital.addTask(treatment);
    IO.print("\n Getting appointment informations \n");
    assertTrue(Utils.equals(appointment.getPatient().getCC(), "123456789"));
    assertTrue(Utils.equals(appointment.getHospital().getName(), "Hospital dos LusÃ­adas"));
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
    appointment.setSchedule(schedule3);
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleStart().time.min, 40L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().year, 2017L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().month, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().day, 25L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.hour, 12L));
    assertTrue(Utils.equals(appointment.getSchedule().getScheduleEnd().time.min, 50L));
  }

  public void testAppointment() {

    IO.print("\n Checking appointment priority \n");
    assertTrue(Utils.equals(appointment.getPriority(), MFES.quotes.MediumQuote.getInstance()));
    assertTrue(Utils.equals(urgencies.getPriority(), MFES.quotes.HighQuote.getInstance()));
    urgencies.setPriority(MFES.quotes.LowQuote.getInstance());
    assertTrue(Utils.equals(urgencies.getPriority(), MFES.quotes.LowQuote.getInstance()));
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
    assertTrue(Utils.equals(surgery.getMedAssoc().getCC(), "234512389"));
    surgery.setMainDoctor(secSurgeon);
    assertTrue(Utils.equals(surgery.getMedAssoc().getCC(), "234512390"));
  }

  public void testTreatment() {

    IO.print("\n Checking treatment informations \n");
    IO.print("\n Checking schedule functions \n");
    assertTrue(Utils.equals(treatment.getName(), "Fisioterapia"));
    assertTrue(Utils.equals(treatment.getMed().getCC(), "123444655"));
  }

  public void testScheduleFunctions() {

    Schedule sch = null;
    Schedule sch1 = null;
    Schedule sch2 = null;
    Types.Date dateNew = null;
    Types.Date dateNew2 = null;
    dateNew = new Types.Date(2017L, 10L, 25L, time1);
    dateNew2 = new Types.Date(2017L, 10L, 25L, time2);
    sch = new Schedule(Utils.copy(dateNew), Utils.copy(dateNew2));
    dateNew = new Types.Date(2017L, 10L, 26L, time1);
    dateNew2 = new Types.Date(2017L, 10L, 26L, time2);
    sch1 = new Schedule(Utils.copy(dateNew), Utils.copy(dateNew2));
    dateNew = new Types.Date(2017L, 11L, 26L, time1);
    dateNew2 = new Types.Date(2017L, 11L, 26L, time2);
    sch2 = new Schedule(Utils.copy(dateNew), Utils.copy(dateNew2));
    IO.print("\n Checking schedule functions \n");
    assertTrue(
        appointment
            .getSchedule()
            .lessThan(
                appointment.getSchedule().getScheduleStart(),
                appointment.getSchedule().getScheduleEnd()));
    assertTrue(
        appointment
            .getSchedule()
            .greaterThan(
                appointment.getSchedule().getScheduleEnd(),
                appointment.getSchedule().getScheduleStart()));
    assertTrue(
        appointment
            .getSchedule()
            .lessThan(appointment.getSchedule().getScheduleStart(), schedule4.getScheduleStart()));
    assertTrue(!(schedule4.lessThan(schedule4.getScheduleStart(), schedule5.getScheduleStart())));
    assertTrue(sch.lessThan(sch.getScheduleStart(), schedule.getScheduleStart()));
    assertTrue(!(sch1.lessThan(sch1.getScheduleStart(), sch.getScheduleStart())));
    assertTrue(!(schedule3.lessThan(schedule3.getScheduleStart(), sch2.getScheduleStart())));
    assertTrue(sch.lessThan(sch.getScheduleStart(), sch1.getScheduleStart()));
    assertTrue(
        appointment
            .getSchedule()
            .greaterThan(schedule4.getScheduleStart(), schedule5.getScheduleStart()));
    assertTrue(
        !(appointment
            .getSchedule()
            .greaterThan(
                appointment.getSchedule().getScheduleStart(), schedule4.getScheduleStart())));
    assertTrue(!(sch.greaterThan(sch.getScheduleStart(), schedule.getScheduleStart())));
    assertTrue(sch1.greaterThan(sch1.getScheduleStart(), sch.getScheduleStart()));
    assertTrue(schedule.greaterThan(schedule.getScheduleStart(), sch.getScheduleStart()));
    assertTrue(!(sch.greaterThan(sch.getScheduleStart(), sch1.getScheduleStart())));
    IO.print("\n Checking overlap \n");
    assertTrue(schedule.overlap(schedule, schedule2));
  }

  public static void main() {

    TaskTest taskTest = new TaskTest();
    IO.print("\n\n *****Running TaskTest***** \n");
    taskTest.testGetsSetsTask();
    taskTest.testAppointment();
    taskTest.testSurgery();
    taskTest.testTreatment();
    taskTest.testScheduleFunctions();
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
        + ", d2 := "
        + Utils.toString(d2)
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
        + ", time7 := "
        + Utils.toString(time7)
        + ", date7 := "
        + Utils.toString(date7)
        + ", time8 := "
        + Utils.toString(time8)
        + ", date8 := "
        + Utils.toString(date8)
        + ", schedule4 := "
        + Utils.toString(schedule4)
        + ", time9 := "
        + Utils.toString(time9)
        + ", date9 := "
        + Utils.toString(date9)
        + ", time10 := "
        + Utils.toString(time10)
        + ", date10 := "
        + Utils.toString(date10)
        + ", schedule5 := "
        + Utils.toString(schedule5)
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
        + "}";
  }
}
