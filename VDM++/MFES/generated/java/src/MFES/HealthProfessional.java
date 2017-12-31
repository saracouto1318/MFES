package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class HealthProfessional extends Person {
  private String medicalNumber;
  private VDMSet specialties;
  private VDMSet patients;
  private Object type;

  public void cg_init_HealthProfessional_1(
      final String a,
      final String fn,
      final String ln,
      final String c,
      final String pn,
      final String s,
      final Object t) {

    medicalNumber = s;
    type = t;
    specialties = SetUtil.set();
    patients = SetUtil.set();
    cg_init_Person_1(a, fn, ln, c, pn);
  }

  public HealthProfessional(
      final String a,
      final String fn,
      final String ln,
      final String c,
      final String pn,
      final String s,
      final Object t) {

    cg_init_HealthProfessional_1(a, fn, ln, c, pn, s, t);
  }

  public String getMedicalNumber() {

    return medicalNumber;
  }

  public VDMSet getSpecialties() {

    return Utils.copy(specialties);
  }

  public VDMSet getPatients() {

    return Utils.copy(patients);
  }

  public Object getType() {

    return type;
  }

  public void removeSpecialty(final Specialty s) {

    specialties = SetUtil.diff(Utils.copy(specialties), SetUtil.set(s));
  }

  public void addSpecialty(final Specialty s) {

    specialties = SetUtil.union(Utils.copy(specialties), SetUtil.set(s));
  }

  public void addPatient(final Patient p) {

    patients = SetUtil.union(Utils.copy(patients), SetUtil.set(p));
  }

  public void removePatient(final Patient p) {

    patients = SetUtil.diff(Utils.copy(patients), SetUtil.set(p));
  }

  public HealthProfessional() {}

  public String toString() {

    return Utils.toString(medicalNumber)
    		+ " - "
    		+ Utils.toString(firstName)
    		+ " " 
    		+ Utils.toString(lastName);
  }
}
