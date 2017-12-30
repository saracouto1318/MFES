package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TrainingTest {
  private HealthProfessional doctor =
      new HealthProfessional(
          "Rua de Cima",
          "Ana",
          "Marques",
          "123432156",
          "921349076",
          "111111222",
          MFES.quotes.DoctorQuote.getInstance());
  private Object purpose = MFES.quotes.TrainingQuote.getInstance();
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
  private Training training = new Training(((Object) purpose), schedule, doctor);

  private void assertTrue(final Boolean cond) {

    return;
  }

  public void testGetsSets() {

    IO.print("\n Testing Training gets and sets \n");
    assertTrue(Utils.equals(training.getPurpose(), MFES.quotes.TrainingQuote.getInstance()));
    assertTrue(Utils.equals(training.getMedAssoc().getCC(), "123432156"));
    training.setPurpose(MFES.quotes.AddSkillsQuote.getInstance());
    assertTrue(Utils.equals(training.getPurpose(), MFES.quotes.AddSkillsQuote.getInstance()));
    assertTrue(Utils.equals(training.getSchedule().getScheduleStart().year, 2017L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleStart().month, 12L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleStart().day, 25L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleStart().time.hour, 12L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleStart().time.min, 10L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleEnd().year, 2017L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleEnd().month, 12L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleEnd().day, 25L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleEnd().time.hour, 12L));
    assertTrue(Utils.equals(training.getSchedule().getScheduleEnd().time.min, 30L));
  }

  public static void main() {

    TrainingTest trainingTest = new TrainingTest();
    IO.print("\n *****Running TrainingTest***** \n");
    trainingTest.testGetsSets();
  }

  public TrainingTest() {}

  public String toString() {

    return "TrainingTest{"
        + "doctor := "
        + Utils.toString(doctor)
        + ", purpose := "
        + Utils.toString(purpose)
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
        + ", training := "
        + Utils.toString(training)
        + "}";
  }
}
