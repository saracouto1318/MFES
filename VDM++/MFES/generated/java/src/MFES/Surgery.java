package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Surgery extends Task {
  private VDMSet secondaryDoctors;
  private VDMSet other;

  public void cg_init_Surgery_1(
      final HealthProfessional s, final Schedule sch, final Patient p, final Hospital h) {

    medicalAssoc = s;
    other = SetUtil.set();
    secondaryDoctors = SetUtil.set();
    cg_init_Task_1(s, sch, p, h, MFES.quotes.SurgeryQuote.getInstance());
  }

  public Surgery(
      final HealthProfessional s, final Schedule sch, final Patient p, final Hospital h) {

    cg_init_Surgery_1(s, sch, p, h);
  }

  public void addSecondaryDoctor(final HealthProfessional s) {

    secondaryDoctors = SetUtil.union(Utils.copy(secondaryDoctors), SetUtil.set(s));
  }

  public void removeSecondaryDoctor(final HealthProfessional s) {

    secondaryDoctors = SetUtil.diff(Utils.copy(secondaryDoctors), SetUtil.set(s));
  }

  public void addOther(final HealthProfessional s) {

    other = SetUtil.union(Utils.copy(other), SetUtil.set(s));
  }

  public void removeOther(final HealthProfessional s) {

    other = SetUtil.diff(Utils.copy(other), SetUtil.set(s));
  }

  public void setMainDoctor(final HealthProfessional s) {

    medicalAssoc = s;
  }

  public HealthProfessional getMainDoctor() {

    return medicalAssoc;
  }

  public VDMSet getSurgeryPersons(final Object t) {

    VDMSet med = null;
    if (Utils.equals(t, MFES.quotes.SurgeonQuote.getInstance())) {
      med = Utils.copy(secondaryDoctors);
    } else {
      med = Utils.copy(other);
    }

    return Utils.copy(med);
  }

  public Surgery() {}

  public String toString() {

    return "Surgery{"
        + "secondaryDoctors := "
        + Utils.toString(secondaryDoctors)
        + ", other := "
        + Utils.toString(other)
        + "}";
  }
}
