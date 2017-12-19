package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Training {
  public VDMSet medicalAssociated;
  public Object purpose;
  public Schedule schedule;

  public void cg_init_Training_1(final Object p, final Schedule s) {

    purpose = p;
    schedule = s;
    medicalAssociated = SetUtil.set();
    return;
  }

  public Training(final Object p, final Schedule s) {

    cg_init_Training_1(p, s);
  }

  public Schedule getSchedule() {

    return schedule;
  }

  public Object getPurpose() {

    return purpose;
  }

  public VDMSet addMedicalAssociated(final MedicalAssociated m) {

    return SetUtil.union(Utils.copy(medicalAssociated), SetUtil.set(m));
  }

  public VDMSet removeMedicalAssociated(final MedicalAssociated m) {

    return SetUtil.diff(Utils.copy(medicalAssociated), SetUtil.set(m));
  }

  public void setSchedule(final Schedule s) {

    schedule = s;
  }

  public void setPurpose(final Object p) {

    purpose = p;
  }

  public Training() {}

  public String toString() {

    return "Training{"
        + "medicalAssociated := "
        + Utils.toString(medicalAssociated)
        + ", purpose := "
        + Utils.toString(purpose)
        + ", schedule := "
        + Utils.toString(schedule)
        + "}";
  }
}
