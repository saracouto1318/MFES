package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Patient extends Person {
  private String healthNumber;

  public void cg_init_Patient_1(final String n) {

    healthNumber = n;
    return;
  }

  public Patient(final String n) {

    cg_init_Patient_1(n);
  }

  public String getHealthNumber() {

    return healthNumber;
  }

  public Patient() {}

  public String toString() {

    return "Patient{" + "healthNumber := " + Utils.toString(healthNumber) + "}";
  }
}
