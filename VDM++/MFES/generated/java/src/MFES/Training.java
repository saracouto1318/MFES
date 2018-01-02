package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Training {
  private HealthProfessional medicalAssociated;
  private Object purpose;
  private Schedule schedule;

  public void cg_init_Training_1(final Object p, final Schedule s, final HealthProfessional h) {

    purpose = p;
    schedule = s;
    medicalAssociated = h;
    return;
  }

  public Training(final Object p, final Schedule s, final HealthProfessional h) {

    cg_init_Training_1(p, s, h);
  }

  public Schedule getSchedule() {

    return schedule;
  }

  public Object getPurpose() {

    return purpose;
  }

  public HealthProfessional getMedAssoc() {

    return medicalAssociated;
  }

  public void setPurpose(final Object p) {

    purpose = p;
  }

  public Training() {}

  public String toString() {

    return "Treino para"
        + Utils.toString(medicalAssociated)
        + Utils.toString(schedule);
  }
}
