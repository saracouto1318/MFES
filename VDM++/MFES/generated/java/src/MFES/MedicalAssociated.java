package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MedicalAssociated extends Person {
  public String medicalNumber;
  public VDMSet specialties;

  public void cg_init_MedicalAssociated_1(final String s) {

    medicalNumber = s;
    specialties = SetUtil.set();
    return;
  }

  public MedicalAssociated(final String s) {

    cg_init_MedicalAssociated_1(s);
  }

  public String getMedicalNumber() {

    return medicalNumber;
  }

  public VDMSet getSpecialties() {

    return Utils.copy(specialties);
  }

  public VDMSet removeSpecialty(final Specialty s) {

    return SetUtil.diff(Utils.copy(specialties), SetUtil.set(s));
  }

  public VDMSet addSpecialty(final Specialty s) {

    return SetUtil.union(Utils.copy(specialties), SetUtil.set(s));
  }

  public String getType() {

    return "";
  }

  public MedicalAssociated() {}

  public String toString() {

    return "MedicalAssociated{"
        + "medicalNumber := "
        + Utils.toString(medicalNumber)
        + ", specialties := "
        + Utils.toString(specialties)
        + "}";
  }
}
