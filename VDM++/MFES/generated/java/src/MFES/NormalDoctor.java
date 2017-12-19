package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class NormalDoctor extends Doctor {
  public VDMSet patients;

  public void cg_init_NormalDoctor_1() {

    patients = SetUtil.set();
    return;
  }

  public NormalDoctor() {

    cg_init_NormalDoctor_1();
  }

  public VDMSet addPatient(final Patient p) {

    return SetUtil.union(Utils.copy(patients), SetUtil.set(p));
  }

  public VDMSet removePatient(final Patient p) {

    return SetUtil.diff(Utils.copy(patients), SetUtil.set(p));
  }

  public String getType() {

    return "Normal Doctor";
  }

  public String toString() {

    return "NormalDoctor{" + "patients := " + Utils.toString(patients) + "}";
  }
}
