package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Task {
  protected Schedule schedule;
  protected Patient patient;
  protected Hospital hospital;
  protected HealthProfessional medicalAssoc;
  protected Object taskType;

  public void cg_init_Task_1(
      final HealthProfessional med,
      final Schedule s,
      final Patient p,
      final Hospital h,
      final Object t) {

    schedule = s;
    patient = p;
    hospital = h;
    taskType = t;
    medicalAssoc = med;
    return;
  }

  public Task(
      final HealthProfessional med,
      final Schedule s,
      final Patient p,
      final Hospital h,
      final Object t) {

    cg_init_Task_1(med, s, p, h, t);
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

  public Object getType() {

    return taskType;
  }

  public HealthProfessional getMedAssoc() {

    return medicalAssoc;
  }

  public void setSchedule(final Schedule s) {

    for (Iterator iterator_25 = hospital.getAgendas().iterator(); iterator_25.hasNext(); ) {
      Agenda a = (Agenda) iterator_25.next();
      if (Utils.equals(a.getHealthProfessional().getCC(), medicalAssoc.getCC())) {
        a.addSchedule(schedule);
        a.removeSchedule(s);
      }
    }
    schedule = s;
  }

  public VDMSet getSurgeryPersons(final Object t) {

    return SetUtil.set();
  }

  public Task() {}

  public String toString() {
    return "Tarefa "
        + Utils.toString(hospital)
        + " de "
        + Utils.toString(patient)
        + " para "
        + Utils.toString(medicalAssoc)
        + " tipo "
        + Utils.toString(taskType);
  }
}
