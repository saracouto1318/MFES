package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Appointment extends Task {
  public Doctor doctor;
  public VDMSet prescriptions;

  public void cg_init_Appointment_1(final Doctor d) {

    doctor = d;
    prescriptions = SetUtil.set();
    return;
  }

  public Appointment(final Doctor d) {

    cg_init_Appointment_1(d);
  }

  public Doctor getDoctorAppointment() {

    return doctor;
  }

  public VDMSet getPrescriptions() {

    return Utils.copy(prescriptions);
  }

  public Prescription getPrescription(final String code) {

    Prescription prescription = null;
    for (Iterator iterator_1 = prescriptions.iterator(); iterator_1.hasNext(); ) {
      Prescription p = (Prescription) iterator_1.next();
      if (p.compare(code)) {
        prescription = p;
      }
    }
    return prescription;
  }

  public VDMSet addPrescription(final Prescription p) {

    return SetUtil.union(Utils.copy(prescriptions), SetUtil.set(p));
  }

  public VDMSet removePrescription(final Prescription p) {

    return SetUtil.diff(Utils.copy(prescriptions), SetUtil.set(p));
  }

  public String getType() {

    return "Appointment";
  }

  public Appointment() {}

  public String toString() {

    return "Appointment{"
        + "doctor := "
        + Utils.toString(doctor)
        + ", prescriptions := "
        + Utils.toString(prescriptions)
        + "}";
  }
}
