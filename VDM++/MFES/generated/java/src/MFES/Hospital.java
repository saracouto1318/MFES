package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hospital {
  private VDMSet medicalAssociated;
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

  public void addMedAssociated(final HealthProfessional d) {

    medicalAssociated = SetUtil.union(SetUtil.set(d), Utils.copy(medicalAssociated));
  }

  public void removeMedAssociated(final HealthProfessional d) {

    for (Iterator iterator_1 = tasks.iterator(); iterator_1.hasNext(); ) {
      Task t = (Task) iterator_1.next();
      if (Utils.equals(d, t.getMedAssoc())) {
        removeTask(t);
      }
    }
    for (Iterator iterator_2 = trainings.iterator(); iterator_2.hasNext(); ) {
      Training t = (Training) iterator_2.next();
      if (Utils.equals(d, t.getMedAssoc())) {
        removeTraining(t);
      }
    }
    medicalAssociated = SetUtil.diff(Utils.copy(medicalAssociated), SetUtil.set(d));
  }

  public void addTask(final Task d) {

    if (!(SetUtil.inSet(d.getPatient(), d.getMedAssoc().getPatients()))) {
      d.getMedAssoc().addPatient(d.getPatient());
    }

    tasks = SetUtil.union(SetUtil.set(d), Utils.copy(tasks));
  }

  public void removeTask(final Task d) {

    tasks = SetUtil.diff(Utils.copy(tasks), SetUtil.set(d));
  }

  public void addTraining(final Training d) {

    trainings = SetUtil.union(SetUtil.set(d), Utils.copy(trainings));
  }

  public void removeTraining(final Training d) {

    trainings = SetUtil.diff(Utils.copy(trainings), SetUtil.set(d));
  }

  public VDMSet getTasksByType(final Object s) {

    VDMSet tasksTotal = null;
    tasksTotal = SetUtil.set();
    for (Iterator iterator_3 = tasks.iterator(); iterator_3.hasNext(); ) {
      Task t = (Task) iterator_3.next();
      if (Utils.equals(t.getType(), s)) {
        tasksTotal = SetUtil.union(Utils.copy(tasksTotal), SetUtil.set(t));
      }
    }
    return Utils.copy(tasksTotal);
  }

  public VDMSet getTrainingsByType(final Object s) {

    VDMSet train = null;
    train = SetUtil.set();
    for (Iterator iterator_4 = trainings.iterator(); iterator_4.hasNext(); ) {
      Training t = (Training) iterator_4.next();
      if (Utils.equals(t.getPurpose(), s)) {
        train = SetUtil.union(Utils.copy(train), SetUtil.set(t));
      }
    }
    return Utils.copy(train);
  }

  public VDMSet getMedicalAssociatedByType(final Object type) {

    VDMSet med = null;
    med = SetUtil.set();
    for (Iterator iterator_5 = medicalAssociated.iterator(); iterator_5.hasNext(); ) {
      HealthProfessional d = (HealthProfessional) iterator_5.next();
      if (Utils.equals(d.getType(), type)) {
        med = SetUtil.union(Utils.copy(med), SetUtil.set(d));
      }
    }
    return Utils.copy(med);
  }

  public Boolean overlap(final Schedule t1, final Schedule t2) {

    return t1.overlap(t1, t2);
  }

  public Boolean overlapTask(final Task t1) {

    for (Iterator iterator_6 = tasks.iterator(); iterator_6.hasNext(); ) {
      Task t = (Task) iterator_6.next();
      Boolean andResult_16 = false;

      if (overlap(t.getSchedule(), t1.getSchedule())) {
        Boolean orResult_1 = false;

        if (Utils.equals(t1.getMedAssoc().getCC(), t.getMedAssoc().getCC())) {
          orResult_1 = true;
        } else {
          Boolean orResult_2 = false;

          if (Utils.equals(t1.getPatient().getCC(), t.getPatient().getCC())) {
            orResult_2 = true;
          } else {
            Boolean orResult_3 = false;

            if (Utils.equals(t1.getMedAssoc().getCC(), t.getPatient().getCC())) {
              orResult_3 = true;
            } else {
              orResult_3 = Utils.equals(t1.getPatient().getCC(), t.getMedAssoc().getCC());
            }

            orResult_2 = orResult_3;
          }

          orResult_1 = orResult_2;
        }

        if (orResult_1) {
          andResult_16 = true;
        }
      }

      if (andResult_16) {
        return true;
      }
    }
    for (Iterator iterator_7 = trainings.iterator(); iterator_7.hasNext(); ) {
      Training t = (Training) iterator_7.next();
      Boolean andResult_17 = false;

      if (overlap(t.getSchedule(), t1.getSchedule())) {
        Boolean orResult_4 = false;

        if (Utils.equals(t1.getMedAssoc().getCC(), t.getMedAssoc().getCC())) {
          orResult_4 = true;
        } else {
          orResult_4 = Utils.equals(t1.getPatient().getCC(), t.getMedAssoc().getCC());
        }

        if (orResult_4) {
          andResult_17 = true;
        }
      }

      if (andResult_17) {
        return true;
      }
    }
    for (Iterator iterator_8 = getTasksByType(MFES.quotes.SurgeryQuote.getInstance()).iterator();
        iterator_8.hasNext();
        ) {
      Task t = (Task) iterator_8.next();
      Boolean orResult_5 = false;

      Boolean andResult_18 = false;

      if (overlap(t.getSchedule(), t1.getSchedule())) {
        if (SetUtil.inSet(
            t1.getMedAssoc(), t.getSurgeryPersons(MFES.quotes.SurgeonQuote.getInstance()))) {
          andResult_18 = true;
        }
      }

      if (andResult_18) {
        orResult_5 = true;
      } else {
        orResult_5 =
            SetUtil.inSet(
                t1.getMedAssoc(), t.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()));
      }

      if (orResult_5) {
        return true;
      }
    }
    return false;
  }

  public Boolean overlapTraining(final Training t1) {

    for (Iterator iterator_9 = trainings.iterator(); iterator_9.hasNext(); ) {
      Training t = (Training) iterator_9.next();
      Boolean andResult_19 = false;

      if (overlap(t.getSchedule(), t1.getSchedule())) {
        if (Utils.equals(t1.getMedAssoc().getCC(), t.getMedAssoc().getCC())) {
          andResult_19 = true;
        }
      }

      if (andResult_19) {
        return true;
      }
    }
    for (Iterator iterator_10 = tasks.iterator(); iterator_10.hasNext(); ) {
      Task t = (Task) iterator_10.next();
      Boolean andResult_20 = false;

      if (overlap(t.getSchedule(), t1.getSchedule())) {
        Boolean orResult_6 = false;

        if (Utils.equals(t1.getMedAssoc().getCC(), t.getMedAssoc().getCC())) {
          orResult_6 = true;
        } else {
          orResult_6 = Utils.equals(t1.getMedAssoc().getCC(), t.getPatient().getCC());
        }

        if (orResult_6) {
          andResult_20 = true;
        }
      }

      if (andResult_20) {
        return true;
      }
    }
    for (Iterator iterator_11 = getTasksByType(MFES.quotes.SurgeryQuote.getInstance()).iterator();
        iterator_11.hasNext();
        ) {
      Task t = (Task) iterator_11.next();
      Boolean orResult_7 = false;

      Boolean andResult_21 = false;

      if (overlap(t.getSchedule(), t1.getSchedule())) {
        if (SetUtil.inSet(
            t1.getMedAssoc(), t.getSurgeryPersons(MFES.quotes.SurgeonQuote.getInstance()))) {
          andResult_21 = true;
        }
      }

      if (andResult_21) {
        orResult_7 = true;
      } else {
        orResult_7 =
            SetUtil.inSet(
                t1.getMedAssoc(), t.getSurgeryPersons(MFES.quotes.NurseQuote.getInstance()));
      }

      if (orResult_7) {
        return true;
      }
    }
    return false;
  }

  public Hospital() {}

  public String toString() {

    return "Hospital{"
        + "medicalAssociated := "
        + Utils.toString(medicalAssociated)
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
