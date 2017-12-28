package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Appointment extends Task {
  private VDMSet prescriptions;
  private Object priority;

  public void cg_init_Appointment_2(
      final HealthProfessional d,
      final Object p,
      final Schedule s,
      final Patient pat,
      final Hospital h) {

    medicalAssoc = d;
    priority = p;
    prescriptions = SetUtil.set();
    cg_init_Task_1(d, s, pat, h, MFES.quotes.UrgenciesQuote.getInstance());
  }

  public void cg_init_Appointment_1(
      final HealthProfessional d, final Schedule s, final Patient p, final Hospital h) {

    medicalAssoc = d;
    priority = MFES.quotes.MediumQuote.getInstance();
    prescriptions = SetUtil.set();
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

  public VDMSet getPrescriptions() {

    return Utils.copy(prescriptions);
  }

  public void setPriority(final Object p) {

    priority = p;
  }

  public void addPrescription(final Prescription p) {

    prescriptions = SetUtil.union(Utils.copy(prescriptions), SetUtil.set(p));
  }

  public void removePrescription(final Prescription p) {

    prescriptions = SetUtil.diff(Utils.copy(prescriptions), SetUtil.set(p));
  }

  public Appointment() {}

  public String toString() {

    return "Appointment{"
        + "prescriptions := "
        + Utils.toString(prescriptions)
        + ", priority := "
        + Utils.toString(priority)
        + "}";
  }
}
