package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SafetyNetHospitalTest {
  private SafetyNetHospital safetyNet = new SafetyNetHospital();
  private Types.Time time1 = new Types.Time(12L, 10L);
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
  private Types.Date date7 = new Types.Date(2017L, 11L, 22L, time7);
  private Types.Time time8 = new Types.Time(12L, 30L);
  private Types.Date date8 = new Types.Date(2017L, 11L, 22L, time8);
  private Schedule schedule4 = new Schedule(Utils.copy(date7), Utils.copy(date8));
  private Types.Time time9 = new Types.Time(12L, 35L);
  private Types.Date date9 = new Types.Date(2017L, 11L, 23L, time9);
  private Types.Time time10 = new Types.Time(12L, 45L);
  private Types.Date date10 = new Types.Date(2017L, 11L, 23L, time10);
  private Schedule schedule5 = new Schedule(Utils.copy(date9), Utils.copy(date10));
  private Patient patient =
      new Patient("Rua 1 Maio", "Rui", "Andrade", "123456789", "223456111", "0987654321");
  private Patient patient2 =
      new Patient("Rua 1 Maio", "Diogo", "Andrade", "123321123", "911112345", "908765123");
  private Patient patient3 =
      new Patient("Rua 1 Maio", "Vitor", "Andrade", "135790864", "912345334", "123432130");
  private Patient patient4 =
      new Patient("Rua 1 Maio", "Simone", "Andrade", "234123765", "931238654", "0987654143");
  private Hospital hospital = new Hospital("Hospital das Camélias", "Rua de Cima", safetyNet);
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
  private Appointment appointment2 = new Appointment(doctor, schedule3, patient4, hospital);
  private Appointment appointment3 = new Appointment(doctor2, schedule3, patient, hospital);
  private Appointment urgencies =
      new Appointment(doctor2, MFES.quotes.HighQuote.getInstance(), schedule, patient2, hospital);
  private Surgery surgery = new Surgery(surgeon, schedule, patient3, hospital);
  private Treatment treatment =
      new Treatment(technician, "Fisioterapia", schedule, patient4, hospital);
  private Object purpose = MFES.quotes.TrainingQuote.getInstance();
  private Training training = new Training(((Object) purpose), schedule3, nurse);
  private Training train = new Training(((Object) purpose), schedule4, doctor);

  private void assertTrue(final Boolean cond) {

    return;
  }

  public void testAddRemoveHospitals() {

    Hospital h1 = null;
    Hospital h2 = null;
    Hospital h3 = null;
    h1 = new Hospital("Hospital dos Lusíadas", "Rua de Cima", safetyNet);
    h2 = new Hospital("Hospital Novo", "Rua 1 de Maio", safetyNet);
    h3 = new Hospital("Hospital da Trofa", "Rua da Trofa", safetyNet);
    IO.print("\n Number of hospitals: ");
    IO.print(safetyNet.getHospitals().size());
    IO.print("\n\n Getting hospitals information \n");
    assertTrue(Utils.equals(h1.getName(), "Hospital dos Lusíadas"));
    assertTrue(Utils.equals(h2.getName(), "Hospital Novo"));
    assertTrue(Utils.equals(h3.getName(), "Hospital da Trofa"));
    assertTrue(Utils.equals(h1.getAddress(), "Rua de Cima"));
    assertTrue(Utils.equals(h2.getAddress(), "Rua 1 de Maio"));
    assertTrue(Utils.equals(h3.getAddress(), "Rua da Trofa"));
    IO.print("\n Removing hospitals \n");
    assertTrue(Utils.equals(safetyNet.getHospitals().size(), 4L));
    safetyNet.removeHospital(h1);
    IO.print("\n Removing hospitals \n");
    assertTrue(Utils.equals(safetyNet.getHospitals().size(), 3L));
    safetyNet.removeHospital(h2);
    assertTrue(Utils.equals(safetyNet.getHospitals().size(), 2L));
    IO.print("\n Number of hospitals: ");
    IO.print(safetyNet.getHospitals().size());
  }

  public void testAddRemoveMedHospital() {

    Agenda agenda1 = null;
    Agenda agenda2 = null;
    Agenda agenda3 = null;
    Agenda agenda4 = null;
    Agenda agenda5 = null;
    IO.print("\n Adding health professionals \n");
    hospital.addMedAssociated(doctor);
    hospital.addMedAssociated(doctor2);
    hospital.addMedAssociated(surgeon);
    hospital.addMedAssociated(nurse);
    hospital.addMedAssociated(technician);
    for (Iterator iterator_20 = hospital.getAgendas().iterator(); iterator_20.hasNext(); ) {
      Agenda a = (Agenda) iterator_20.next();
      if (Utils.equals(a.getHealthProfessional(), doctor)) {
        agenda1 = a;
      } else {
        if (Utils.equals(a.getHealthProfessional(), doctor2)) {
          agenda2 = a;
        } else {
          if (Utils.equals(a.getHealthProfessional(), surgeon)) {
            agenda3 = a;
          } else {
            if (Utils.equals(a.getHealthProfessional(), nurse)) {
              agenda4 = a;
            } else {
              agenda5 = a;
            }
          }
        }
      }
    }
    agenda1.addSchedule(schedule);
    agenda1.addSchedule(schedule3);
    agenda1.addSchedule(schedule4);
    agenda2.addSchedule(schedule);
    agenda2.addSchedule(schedule3);
    agenda3.addSchedule(schedule);
    agenda4.addSchedule(schedule3);
    agenda5.addSchedule(schedule);
    assertTrue(Utils.equals(agenda1.getAgenda().size(), 3L));
    assertTrue(Utils.equals(agenda2.getAgenda().size(), 2L));
    assertTrue(Utils.equals(agenda3.getAgenda().size(), 1L));
    assertTrue(Utils.equals(agenda4.getAgenda().size(), 1L));
    assertTrue(Utils.equals(agenda5.getAgenda().size(), 1L));
    IO.print("\n Total number of doctors: ");
    IO.print(hospital.getMedicalAssociatedByType(MFES.quotes.DoctorQuote.getInstance()).size());
    IO.print("\n Total number of surgeons: ");
    IO.print(hospital.getMedicalAssociatedByType(MFES.quotes.SurgeonQuote.getInstance()).size());
    IO.print("\n Total number of nurses: ");
    IO.print(hospital.getMedicalAssociatedByType(MFES.quotes.NurseQuote.getInstance()).size());
    IO.print("\n Total number of technicians: ");
    IO.print(hospital.getMedicalAssociatedByType(MFES.quotes.TechnicianQuote.getInstance()).size());
    assertTrue(
        Utils.equals(
            hospital.getMedicalAssociatedByType(MFES.quotes.DoctorQuote.getInstance()).size(), 2L));
    assertTrue(
        Utils.equals(
            hospital.getMedicalAssociatedByType(MFES.quotes.SurgeonQuote.getInstance()).size(),
            1L));
    assertTrue(
        Utils.equals(
            hospital.getMedicalAssociatedByType(MFES.quotes.NurseQuote.getInstance()).size(), 1L));
    assertTrue(
        Utils.equals(
            hospital.getMedicalAssociatedByType(MFES.quotes.TechnicianQuote.getInstance()).size(),
            1L));
    IO.print("\n Total number of doctors: ");
    IO.print(hospital.getMedicalAssociatedByType(MFES.quotes.DoctorQuote.getInstance()).size());
    assertTrue(
        Utils.equals(
            hospital.getMedicalAssociatedByType(MFES.quotes.DoctorQuote.getInstance()).size(), 2L));
    IO.print("\n Removing a doctor \n");
    hospital.addTask(appointment);
    hospital.addTraining(train);
    hospital.removeMedAssociated(doctor);
    assertTrue(
        Utils.equals(
            hospital.getMedicalAssociatedByType(MFES.quotes.DoctorQuote.getInstance()).size(), 1L));
    IO.print("\n Total number of doctors: ");
    IO.print(hospital.getMedicalAssociatedByType(MFES.quotes.DoctorQuote.getInstance()).size());
    hospital.addMedAssociated(doctor);
    for (Iterator iterator_21 = hospital.getAgendas().iterator(); iterator_21.hasNext(); ) {
      Agenda a = (Agenda) iterator_21.next();
      if (Utils.equals(a.getHealthProfessional().getCC(), doctor.getCC())) {
        agenda1 = a;
      }
    }
    agenda1.addSchedule(schedule);
    agenda1.addSchedule(schedule3);
    assertTrue(Utils.equals(agenda1.getAgenda().size(), 2L));
  }

  public void testAddRemoveTaskHospital() {

    hospital.addTask(appointment);
    hospital.addTask(appointment2);
    hospital.addTask(appointment3);
    hospital.addTask(urgencies);
    hospital.addTask(surgery);
    hospital.addTask(treatment);
    IO.print("\n\n Total number of appointments: ");
    IO.print(hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance()).size());
    IO.print("\n Total number of urgencies: ");
    IO.print(hospital.getTasksByType(MFES.quotes.UrgenciesQuote.getInstance()).size());
    IO.print("\n Total number of surgeries: ");
    IO.print(hospital.getTasksByType(MFES.quotes.SurgeryQuote.getInstance()).size());
    IO.print("\n Total number of other treatments: ");
    IO.print(hospital.getTasksByType(MFES.quotes.OtherQuote.getInstance()).size());
    assertTrue(
        Utils.equals(
            hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance()).size(), 3L));
    assertTrue(
        Utils.equals(hospital.getTasksByType(MFES.quotes.UrgenciesQuote.getInstance()).size(), 1L));
    assertTrue(
        Utils.equals(hospital.getTasksByType(MFES.quotes.SurgeryQuote.getInstance()).size(), 1L));
    assertTrue(
        Utils.equals(hospital.getTasksByType(MFES.quotes.OtherQuote.getInstance()).size(), 1L));
    IO.print("\n Removing an appointment \n");
    hospital.removeTask(appointment);
    assertTrue(
        Utils.equals(
            hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance()).size(), 2L));
    IO.print("\n Total number of appointments: ");
    IO.print(hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance()).size());
    IO.print("\n Adding an appointment \n");
    hospital.addTask(appointment);
    assertTrue(
        Utils.equals(
            hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance()).size(), 3L));
    IO.print("\n Total number of appointments: ");
    IO.print(hospital.getTasksByType(MFES.quotes.AppointmentQuote.getInstance()).size());
  }

  public void testAddRemoveTrainingHospital() {

    IO.print("\n\n Total number of trainings: ");
    IO.print(
        hospital.getTrainingsByType(MFES.quotes.TrainingQuote.getInstance()).size()
            + hospital.getTrainingsByType(MFES.quotes.AddSkillsQuote.getInstance()).size());
    assertTrue(
        Utils.equals(
            hospital.getTrainingsByType(MFES.quotes.TrainingQuote.getInstance()).size(), 0L));
    assertTrue(
        Utils.equals(
            hospital.getTrainingsByType(MFES.quotes.AddSkillsQuote.getInstance()).size(), 0L));
    IO.print("\n Adding a training \n");
    hospital.addTraining(training);
    assertTrue(
        Utils.equals(
            hospital.getTrainingsByType(MFES.quotes.TrainingQuote.getInstance()).size(), 1L));
    IO.print("\n Total number of trainings: ");
    IO.print(
        hospital.getTrainingsByType(MFES.quotes.TrainingQuote.getInstance()).size()
            + hospital.getTrainingsByType(MFES.quotes.AddSkillsQuote.getInstance()).size());
    IO.print("\n Removing a training \n");
    hospital.removeTraining(training);
    assertTrue(
        Utils.equals(
            hospital.getTrainingsByType(MFES.quotes.TrainingQuote.getInstance()).size(), 0L));
    IO.print("\n\n Total number of trainings: ");
    IO.print(
        hospital.getTrainingsByType(MFES.quotes.TrainingQuote.getInstance()).size()
            + hospital.getTrainingsByType(MFES.quotes.AddSkillsQuote.getInstance()).size());
  }

  public void testGetHospitalsMoreAppointments() {

    IO.print("\n Checking Safety Net Hospitals with more appointments, etc \n");
    assertTrue(
        Utils.equals(
            safetyNet
                .getHospitalsMoreAppointments(MFES.quotes.AppointmentQuote.getInstance())
                .getName(),
            "Hospital das Camélias"));
    assertTrue(
        Utils.equals(
            safetyNet
                .getHospitalsMoreAppointments(MFES.quotes.UrgenciesQuote.getInstance())
                .getName(),
            "Hospital das Camélias"));
    assertTrue(
        Utils.equals(
            safetyNet
                .getHospitalsMoreAppointments(MFES.quotes.SurgeryQuote.getInstance())
                .getName(),
            "Hospital das Camélias"));
    assertTrue(
        Utils.equals(
            safetyNet.getHospitalsMoreAppointments(MFES.quotes.OtherQuote.getInstance()).getName(),
            "Hospital das Camélias"));
  }

  public void testGetMedMoreHospitals() {

    for (Iterator iterator_22 = safetyNet.getHospitals().iterator(); iterator_22.hasNext(); ) {
      Hospital t = (Hospital) iterator_22.next();
      if (!(Utils.equals(t.getName(), "Hospital das Camélias"))) {
        t.addMedAssociated(doctor);
      }
    }
    IO.print("\n Checking Safety Net Doctors that works in more than 1 hospital \n");
    IO.print("\n Number of Doctors: ");
    IO.print(safetyNet.getMedMoreHospitals(MFES.quotes.DoctorQuote.getInstance()).size());
    assertTrue(
        Utils.equals(
            safetyNet.getMedMoreHospitals(MFES.quotes.DoctorQuote.getInstance()).size(), 1L));
    assertTrue(
        Utils.equals(
            safetyNet.getMedMoreHospitals(MFES.quotes.DoctorQuote.getInstance()),
            SetUtil.set(doctor)));
  }

  public void testGetMedAssociatedByPatient() {

    VDMMap mapTest = null;
    IO.print("\n\n Getting Doctors associated by patient by hospital \n");
    mapTest = safetyNet.getMedAssociatedByPatient(patient, MFES.quotes.DoctorQuote.getInstance());
    assertTrue(Utils.equals(((VDMSet) Utils.get(mapTest, hospital)).size(), 2L));
    assertTrue(Utils.equals(((VDMSet) Utils.get(mapTest, hospital)), SetUtil.set(doctor, doctor2)));
  }

  public void testGetMedByHospital() {

    VDMMap mapTest = null;
    IO.print("\n\n Getting Doctors associated by hospital \n");
    mapTest = safetyNet.getMedByHospital(MFES.quotes.DoctorQuote.getInstance());
    assertTrue(Utils.equals(((VDMSet) Utils.get(mapTest, hospital)).size(), 2L));
    assertTrue(Utils.equals(((VDMSet) Utils.get(mapTest, hospital)), SetUtil.set(doctor, doctor2)));
    mapTest = safetyNet.getMedByHospital(MFES.quotes.SurgeonQuote.getInstance());
    assertTrue(Utils.equals(((VDMSet) Utils.get(mapTest, hospital)).size(), 1L));
    assertTrue(Utils.equals(((VDMSet) Utils.get(mapTest, hospital)), SetUtil.set(surgeon)));
  }

  public static void main() {

    SafetyNetHospitalTest safetyNetTest = new SafetyNetHospitalTest();
    IO.print("\n *****Running SafetyNetHospitalTest***** \n");
    safetyNetTest.testAddRemoveHospitals();
    safetyNetTest.testAddRemoveMedHospital();
    safetyNetTest.testAddRemoveTaskHospital();
    safetyNetTest.testAddRemoveTrainingHospital();
    safetyNetTest.testGetHospitalsMoreAppointments();
    safetyNetTest.testGetMedMoreHospitals();
    safetyNetTest.testGetMedAssociatedByPatient();
    safetyNetTest.testGetMedByHospital();
  }

  public SafetyNetHospitalTest() {}

  public String toString() {

    return "SafetyNetHospitalTest{"
        + "safetyNet := "
        + Utils.toString(safetyNet)
        + ", time1 := "
        + Utils.toString(time1)
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
        + ", appointment2 := "
        + Utils.toString(appointment2)
        + ", appointment3 := "
        + Utils.toString(appointment3)
        + ", urgencies := "
        + Utils.toString(urgencies)
        + ", surgery := "
        + Utils.toString(surgery)
        + ", treatment := "
        + Utils.toString(treatment)
        + ", purpose := "
        + Utils.toString(purpose)
        + ", training := "
        + Utils.toString(training)
        + ", train := "
        + Utils.toString(train)
        + "}";
  }
}
