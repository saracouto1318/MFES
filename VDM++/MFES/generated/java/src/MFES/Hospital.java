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
    for (Iterator iterator_4 = agenda.iterator(); iterator_4.hasNext(); ) {
      Agenda a2 = (Agenda) iterator_4.next();
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

    for (Iterator iterator_5 = tasks.iterator(); iterator_5.hasNext(); ) {
      Task t = (Task) iterator_5.next();
      if (Utils.equals(d, t.getMedAssoc())) {
        removeTask(t);
      }
    }
    for (Iterator iterator_6 = trainings.iterator(); iterator_6.hasNext(); ) {
      Training t = (Training) iterator_6.next();
      if (Utils.equals(d, t.getMedAssoc())) {
        removeTraining(t);
      }
    }
    for (Iterator iterator_7 = agenda.iterator(); iterator_7.hasNext(); ) {
      Agenda a = (Agenda) iterator_7.next();
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
    if (Utils.equals(d.getType(), MFES.quotes.SurgeryQuote.getInstance())) {
      removeScheduleSurgery(d);
    }

    for (Iterator iterator_8 = agenda.iterator(); iterator_8.hasNext(); ) {
      Agenda a = (Agenda) iterator_8.next();
      if (Utils.equals(a.getHealthProfessional().getCC(), d.getMedAssoc().getCC())) {
        a.removeSchedule(d.getSchedule());
      }
    }
  }

  public void removeScheduleSurgery(final Surgery s) {

    for (Iterator iterator_9 = agenda.iterator(); iterator_9.hasNext(); ) {
      Agenda a = (Agenda) iterator_9.next();
      for (Iterator iterator_10 =
              s.getSurgeryPersons(MFES.quotes.SurgeonQuote.getInstance()).iterator();
          iterator_10.hasNext();
          ) {
        HealthProfessional surg = (HealthProfessional) iterator_10.next();
        if (Utils.equals(a.getHealthProfessional().getCC(), surg.getCC())) {
          a.removeSchedule(s.getSchedule());
        }
      }
      for (Iterator iterator_11 =
              s.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()).iterator();
          iterator_11.hasNext();
          ) {
        HealthProfessional surg = (HealthProfessional) iterator_11.next();
        if (Utils.equals(a.getHealthProfessional().getCC(), surg.getCC())) {
          a.removeSchedule(s.getSchedule());
        }
      }
    }
  }

  public void addScheduleSurgery(final Surgery s) {

    for (Iterator iterator_12 = agenda.iterator(); iterator_12.hasNext(); ) {
      Agenda a = (Agenda) iterator_12.next();
      for (Iterator iterator_13 =
              s.getSurgeryPersons(MFES.quotes.SurgeonQuote.getInstance()).iterator();
          iterator_13.hasNext();
          ) {
        HealthProfessional surg = (HealthProfessional) iterator_13.next();
        if (Utils.equals(a.getHealthProfessional().getCC(), surg.getCC())) {
          a.addSchedule(s.getSchedule());
        }
      }
      for (Iterator iterator_14 =
              s.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()).iterator();
          iterator_14.hasNext();
          ) {
        HealthProfessional surg = (HealthProfessional) iterator_14.next();
        if (Utils.equals(a.getHealthProfessional().getCC(), surg.getCC())) {
          a.addSchedule(s.getSchedule());
        }
      }
    }
  }

  public void removeTask(final Task d) {

    if (Utils.equals(d.getType(), MFES.quotes.SurgeryQuote.getInstance())) {
      addScheduleSurgery(d);
    }

    for (Iterator iterator_15 = agenda.iterator(); iterator_15.hasNext(); ) {
      Agenda a = (Agenda) iterator_15.next();
      if (Utils.equals(a.getHealthProfessional(), d.getMedAssoc())) {
        a.addSchedule(d.getSchedule());
      }
    }
    tasks = SetUtil.diff(Utils.copy(tasks), SetUtil.set(d));
  }

  public void addTraining(final Training d) {

    for (Iterator iterator_16 = agenda.iterator(); iterator_16.hasNext(); ) {
      Agenda a = (Agenda) iterator_16.next();
      if (Utils.equals(a.getHealthProfessional(), d.getMedAssoc())) {
        a.removeSchedule(d.getSchedule());
      }
    }
    trainings = SetUtil.union(SetUtil.set(d), Utils.copy(trainings));
  }

  public void removeTraining(final Training d) {

    for (Iterator iterator_17 = agenda.iterator(); iterator_17.hasNext(); ) {
      Agenda a = (Agenda) iterator_17.next();
      if (Utils.equals(a.getHealthProfessional(), d.getMedAssoc())) {
        a.addSchedule(d.getSchedule());
      }
    }
    trainings = SetUtil.diff(Utils.copy(trainings), SetUtil.set(d));
  }

  public VDMSet getTasksByType(final Object s) {

    VDMSet tasksTotal = null;
    tasksTotal = SetUtil.set();
    for (Iterator iterator_18 = tasks.iterator(); iterator_18.hasNext(); ) {
      Task t = (Task) iterator_18.next();
      if (Utils.equals(t.getType(), s)) {
        tasksTotal = SetUtil.union(Utils.copy(tasksTotal), SetUtil.set(t));
      }
    }
    return Utils.copy(tasksTotal);
  }

  public VDMSet getTrainingsByType(final Object s) {

    VDMSet train = null;
    train = SetUtil.set();
    for (Iterator iterator_19 = trainings.iterator(); iterator_19.hasNext(); ) {
      Training t = (Training) iterator_19.next();
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
    for (Iterator iterator_20 = medicalAssociated.iterator(); iterator_20.hasNext(); ) {
      HealthProfessional d = (HealthProfessional) iterator_20.next();
      if (Utils.equals(d.getType(), type)) {
        med = SetUtil.union(Utils.copy(med), SetUtil.set(d));
      }
    }
    return Utils.copy(med);
  }

  public VDMSet getMedicalAssociatedBySpecialty(final String spec) {

    VDMSet med = null;
    med = SetUtil.set();
    for (Iterator iterator_21 = medicalAssociated.iterator(); iterator_21.hasNext(); ) {
      HealthProfessional d = (HealthProfessional) iterator_21.next();
      for (Iterator iterator_22 = d.getSpecialties().iterator(); iterator_22.hasNext(); ) {
        Specialty s = (Specialty) iterator_22.next();
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
