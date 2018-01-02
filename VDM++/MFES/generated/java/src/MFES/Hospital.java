package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hospital {
  private VDMSet medicalAssociated;
  private VDMSet agenda;
  private String name;
  private String address;
  private VDMSet tasks;
  private VDMSet trainings;
  private SafetyNetHospital safetyNet;

  public void cg_init_Hospital_1(final String n, final String a, final SafetyNetHospital s) {

    name = n;
    address = a;
    safetyNet = s;
    medicalAssociated = SetUtil.set();
    tasks = SetUtil.set();
    trainings = SetUtil.set();
    agenda = SetUtil.set();
    safetyNet.addHospital(this);
    return;
  }

  public Hospital(final String n, final String a, final SafetyNetHospital s) {

    cg_init_Hospital_1(n, a, s);
  }

  public String getName() {

    return name;
  }

  public String getAddress() {

    return address;
  }

  public VDMSet getTasks() {

    return Utils.copy(tasks);
  }

  public VDMSet getTrainings() {

    return Utils.copy(trainings);
  }

  public VDMSet getAgendas() {

    return Utils.copy(agenda);
  }

  public Agenda getAgenda(final HealthProfessional h) {

    Agenda a1 = null;
    for (Iterator iterator_3 = agenda.iterator(); iterator_3.hasNext(); ) {
      Agenda a2 = (Agenda) iterator_3.next();
      if (Utils.equals(a2.getHealthProfessional(), h)) {
        a1 = a2;
      }
    }
    return a1;
  }

  public void removeAgenda(final Agenda a) {

    agenda = SetUtil.diff(Utils.copy(agenda), SetUtil.set(a));
  }

  public void addMedAssociated(final HealthProfessional d) {

    Agenda agendaNew = null;
    agendaNew = new Agenda(d);
    medicalAssociated = SetUtil.union(SetUtil.set(d), Utils.copy(medicalAssociated));
    agenda = SetUtil.union(Utils.copy(agenda), SetUtil.set(agendaNew));
  }

  public void removeMedAssociated(final HealthProfessional d) {

    for (Iterator iterator_4 = tasks.iterator(); iterator_4.hasNext(); ) {
      Task t = (Task) iterator_4.next();
      if (Utils.equals(d, t.getMedAssoc())) {
        removeTask(t);
      }
    }
    for (Iterator iterator_5 = trainings.iterator(); iterator_5.hasNext(); ) {
      Training t = (Training) iterator_5.next();
      if (Utils.equals(d, t.getMedAssoc())) {
        removeTraining(t);
      }
    }
    for (Iterator iterator_6 = agenda.iterator(); iterator_6.hasNext(); ) {
      Agenda a = (Agenda) iterator_6.next();
      if (Utils.equals(a.getHealthProfessional().getCC(), d.getCC())) {
        removeAgenda(a);
      }
    }
    medicalAssociated = SetUtil.diff(Utils.copy(medicalAssociated), SetUtil.set(d));
  }

  public void addTask(final Task d) {

    if (!(SetUtil.inSet(d.getPatient(), d.getMedAssoc().getPatients()))) {
      d.getMedAssoc().addPatient(d.getPatient());
    }

    tasks = SetUtil.union(SetUtil.set(d), Utils.copy(tasks));
    for (Iterator iterator_7 = agenda.iterator(); iterator_7.hasNext(); ) {
      Agenda a = (Agenda) iterator_7.next();
      if (Utils.equals(a.getHealthProfessional().getCC(), d.getMedAssoc().getCC())) {
        a.removeSchedule(d.getSchedule());
      }
    }
  }

  public void removeTask(final Task d) {

    for (Iterator iterator_8 = agenda.iterator(); iterator_8.hasNext(); ) {
      Agenda a = (Agenda) iterator_8.next();
      if (Utils.equals(a.getHealthProfessional(), d.getMedAssoc())) {
        a.addSchedule(d.getSchedule());
      }
    }
    tasks = SetUtil.diff(Utils.copy(tasks), SetUtil.set(d));
  }

  public void addTraining(final Training d) {

    for (Iterator iterator_9 = agenda.iterator(); iterator_9.hasNext(); ) {
      Agenda a = (Agenda) iterator_9.next();
      if (Utils.equals(a.getHealthProfessional(), d.getMedAssoc())) {
        a.removeSchedule(d.getSchedule());
      }
    }
    trainings = SetUtil.union(SetUtil.set(d), Utils.copy(trainings));
  }

  public void removeTraining(final Training d) {

    for (Iterator iterator_10 = agenda.iterator(); iterator_10.hasNext(); ) {
      Agenda a = (Agenda) iterator_10.next();
      if (Utils.equals(a.getHealthProfessional(), d.getMedAssoc())) {
        a.addSchedule(d.getSchedule());
      }
    }
    trainings = SetUtil.diff(Utils.copy(trainings), SetUtil.set(d));
  }

  public VDMSet getTasksByType(final Object s) {

    VDMSet tasksTotal = null;
    tasksTotal = SetUtil.set();
    for (Iterator iterator_11 = tasks.iterator(); iterator_11.hasNext(); ) {
      Task t = (Task) iterator_11.next();
      if (Utils.equals(t.getType(), s)) {
        tasksTotal = SetUtil.union(Utils.copy(tasksTotal), SetUtil.set(t));
      }
    }
    return Utils.copy(tasksTotal);
  }

  public VDMSet getTrainingsByType(final Object s) {

    VDMSet train = null;
    train = SetUtil.set();
    for (Iterator iterator_12 = trainings.iterator(); iterator_12.hasNext(); ) {
      Training t = (Training) iterator_12.next();
      if (Utils.equals(t.getPurpose(), s)) {
        train = SetUtil.union(Utils.copy(train), SetUtil.set(t));
      }
    }
    return Utils.copy(train);
  }

  public VDMSet getMedicalAssociated() {

    return Utils.copy(medicalAssociated);
  }

  public VDMSet getMedicalAssociatedByType(final Object type) {

    VDMSet med = null;
    med = SetUtil.set();
    for (Iterator iterator_13 = medicalAssociated.iterator(); iterator_13.hasNext(); ) {
      HealthProfessional d = (HealthProfessional) iterator_13.next();
      if (Utils.equals(d.getType(), type)) {
        med = SetUtil.union(Utils.copy(med), SetUtil.set(d));
      }
    }
    return Utils.copy(med);
  }

  public VDMSet getMedicalAssociatedBySpecialty(final String spec) {

    VDMSet med = null;
    med = SetUtil.set();
    for (Iterator iterator_14 = medicalAssociated.iterator(); iterator_14.hasNext(); ) {
      HealthProfessional d = (HealthProfessional) iterator_14.next();
      for (Iterator iterator_15 = d.getSpecialties().iterator(); iterator_15.hasNext(); ) {
        Specialty s = (Specialty) iterator_15.next();
        if (Utils.equals(s.getName(), spec)) {
          med = SetUtil.union(Utils.copy(med), SetUtil.set(d));
        }
      }
    }
    return Utils.copy(med);
  }

  public Hospital() {}

  public String toString() {

    return "Hospital{"
        + "medicalAssociated := "
        + Utils.toString(medicalAssociated)
        + ", agenda := "
        + Utils.toString(agenda)
        + ", name := "
        + Utils.toString(name)
        + ", address := "
        + Utils.toString(address)
        + ", tasks := "
        + Utils.toString(tasks)
        + ", trainings := "
        + Utils.toString(trainings)
        + ", safetyNet := "
        + Utils.toString(safetyNet)
        + "}";
  }
}
