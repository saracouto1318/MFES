package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Surgery extends Task {
  public Surgeon mainDoctor;
  public VDMSet secondaryDoctors;
  public VDMSet other;

  public void cg_init_Surgery_1(final Surgeon s) {

    mainDoctor = s;
    other = SetUtil.set();
    secondaryDoctors = SetUtil.set();
    return;
  }

  public Surgery(final Surgeon s) {

    cg_init_Surgery_1(s);
  }

  public VDMSet addSecondaryDoctor(final Surgeon s) {

    return SetUtil.union(Utils.copy(secondaryDoctors), SetUtil.set(s));
  }

  public VDMSet removeSecondaryDoctor(final Surgeon s) {

    return SetUtil.diff(Utils.copy(secondaryDoctors), SetUtil.set(s));
  }

  public VDMSet addOther(final OtherMedicalAssociated s) {

    return SetUtil.union(Utils.copy(other), SetUtil.set(s));
  }

  public VDMSet removeOther(final OtherMedicalAssociated s) {

    return SetUtil.diff(Utils.copy(other), SetUtil.set(s));
  }

  public void setMainDoctor(final Surgeon s) {

    mainDoctor = s;
  }

  public Surgeon getMainDoctor() {

    return mainDoctor;
  }

  public VDMSet getSecondaryDoctors() {

    return Utils.copy(secondaryDoctors);
  }

  public VDMSet getOthers() {

    return Utils.copy(other);
  }

  public String getType() {

    return "Surgery";
  }

  public Surgery() {}

  public String toString() {

    return "Surgery{"
        + "mainDoctor := "
        + Utils.toString(mainDoctor)
        + ", secondaryDoctors := "
        + Utils.toString(secondaryDoctors)
        + ", other := "
        + Utils.toString(other)
        + "}";
  }
}
