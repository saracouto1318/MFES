package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Patient extends Person {
  private String healthNumber;

  public void cg_init_Patient_1(
      final String a,
      final String fn,
      final String ln,
      final String c,
      final String pn,
      final String n) {

    healthNumber = n;
    cg_init_Person_1(a, fn, ln, c, pn);
  }

  public Patient(
      final String a,
      final String fn,
      final String ln,
      final String c,
      final String pn,
      final String n) {

    cg_init_Patient_1(a, fn, ln, c, pn, n);
  }

  public String getHealthNumber() {

    return healthNumber;
  }

  public Patient() {}

  public String toString() {

    return "Patient{" + "healthNumber := " + Utils.toString(healthNumber) + "}";
  }
}
