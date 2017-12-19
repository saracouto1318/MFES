package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class HospitalTreatment extends Task {
  public OtherMedicalAssociated technician;
  public String name;

  public void cg_init_HospitalTreatment_1(final String n) {

    name = n;
    return;
  }

  public HospitalTreatment(final String n) {

    cg_init_HospitalTreatment_1(n);
  }

  public String getName() {

    return name;
  }

  public void setTechnician(final OtherMedicalAssociated t) {

    technician = t;
    return;
  }

  public OtherMedicalAssociated getTechnician() {

    return technician;
  }

  public String getTechnicianName() {

    return technician.getName();
  }

  public String getType() {

    return "Hospital Treatment";
  }

  public HospitalTreatment() {}

  public String toString() {

    return "HospitalTreatment{"
        + "technician := "
        + Utils.toString(technician)
        + ", name := "
        + Utils.toString(name)
        + "}";
  }
}
