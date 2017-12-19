package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Task {
  public Schedule schedule;
  public Patient patient;
  public Hospital hospital;

  public void cg_init_Task_1(final Schedule s, final Patient p, final Hospital h) {

    schedule = s;
    patient = p;
    hospital = h;
    return;
  }

  public Task(final Schedule s, final Patient p, final Hospital h) {

    cg_init_Task_1(s, p, h);
  }

  public Schedule getSchedule() {

    return schedule;
  }

  public Patient getPatient() {

    return patient;
  }

  public Hospital getHospital() {

    return hospital;
  }

  public void setSchedule(final Schedule s) {

    schedule = s;
  }

  public void setPatient(final Patient s) {

    patient = s;
  }

  public void setHospital(final Hospital s) {

    hospital = s;
  }

  public String getType() {

    return "";
  }

  public Task() {}

  public String toString() {

    return "Task{"
        + "schedule := "
        + Utils.toString(schedule)
        + ", patient := "
        + Utils.toString(patient)
        + ", hospital := "
        + Utils.toString(hospital)
        + "}";
  }
}
