package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Appointment extends Task {
  private Object priority;

  public void cg_init_Appointment_2(
      final HealthProfessional d,
      final Object p,
      final Schedule s,
      final Patient pat,
      final Hospital h) {

    medicalAssoc = d;
    priority = p;
    cg_init_Task_1(d, s, pat, h, MFES.quotes.UrgenciesQuote.getInstance());
  }

  public void cg_init_Appointment_1(
      final HealthProfessional d, final Schedule s, final Patient p, final Hospital h) {

    medicalAssoc = d;
    priority = MFES.quotes.MediumQuote.getInstance();
    cg_init_Task_1(d, s, p, h, MFES.quotes.AppointmentQuote.getInstance());
  }

  public Appointment(
      final HealthProfessional d, final Schedule s, final Patient p, final Hospital h) {

    cg_init_Appointment_1(d, s, p, h);
  }

  public Appointment(
      final HealthProfessional d,
      final Object p,
      final Schedule s,
      final Patient pat,
      final Hospital h) {

    cg_init_Appointment_2(d, p, s, pat, h);
  }

  public Object getPriority() {

    return priority;
  }

  public void setPriority(final Object p) {

    priority = p;
  }

  public Appointment() {}

}
