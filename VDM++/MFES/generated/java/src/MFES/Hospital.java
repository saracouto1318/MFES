package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hospital {
  public VDMSet medicalAssociated;
  public String name;
  public String address;
  public VDMSet tasks;

  public void cg_init_Hospital_1(final String n, final String a) {

    name = n;
    address = a;
    medicalAssociated = SetUtil.set();
    tasks = SetUtil.set();
    return;
  }

  public Hospital(final String n, final String a) {

    cg_init_Hospital_1(n, a);
  }

  public String getName() {

    return name;
  }

  public String getAddress() {

    return address;
  }

  public VDMSet getMedicalAssociated() {

    return Utils.copy(medicalAssociated);
  }

  public VDMSet getTasks() {

    return Utils.copy(tasks);
  }

  public MedicalAssociated getMedAssociated(final String n) {

    MedicalAssociated medical = null;
    for (Iterator iterator_2 = medicalAssociated.iterator(); iterator_2.hasNext(); ) {
      MedicalAssociated m = (MedicalAssociated) iterator_2.next();
      if (Utils.equals(m.getName(), n)) {
        medical = m;
      }
    }
    return medical;
  }

  public VDMSet addMedAssociated(final MedicalAssociated d) {

    return SetUtil.union(SetUtil.set(d), Utils.copy(medicalAssociated));
  }

  public VDMSet removeMedAssociated(final MedicalAssociated d) {

    return SetUtil.diff(Utils.copy(medicalAssociated), SetUtil.set(d));
  }

  public VDMSet addTask(final Task d) {

    return SetUtil.union(SetUtil.set(d), Utils.copy(tasks));
  }

  public VDMSet removeTask(final Task d) {

    return SetUtil.diff(Utils.copy(tasks), SetUtil.set(d));
  }

  public VDMSet getAppointments() {

    VDMSet tasks2 = null;
    for (Iterator iterator_3 = tasks.iterator(); iterator_3.hasNext(); ) {
      Task t = (Task) iterator_3.next();
      if (Utils.equals(t.getType(), "Appointment")) {
        tasks2 = SetUtil.union(Utils.copy(tasks2), SetUtil.set(t));
      }
    }
    return Utils.copy(tasks2);
  }

  public VDMSet getSurgeries() {

    VDMSet tasks2 = null;
    for (Iterator iterator_4 = tasks.iterator(); iterator_4.hasNext(); ) {
      Task t = (Task) iterator_4.next();
      if (Utils.equals(t.getType(), "Surgery")) {
        tasks2 = SetUtil.union(Utils.copy(tasks2), SetUtil.set(t));
      }
    }
    return Utils.copy(tasks2);
  }

  public VDMSet getOther() {

    VDMSet tasks2 = null;
    for (Iterator iterator_5 = tasks.iterator(); iterator_5.hasNext(); ) {
      Task t = (Task) iterator_5.next();
      if (Utils.equals(t.getType(), "Other")) {
        tasks2 = SetUtil.union(Utils.copy(tasks2), SetUtil.set(t));
      }
    }
    return Utils.copy(tasks2);
  }

  public VDMSet getDoctors() {

    VDMSet doctors = null;
    for (Iterator iterator_6 = medicalAssociated.iterator(); iterator_6.hasNext(); ) {
      MedicalAssociated d = (MedicalAssociated) iterator_6.next();
      if (Utils.equals(d.getType(), "Doctor")) {
        doctors = SetUtil.union(Utils.copy(doctors), SetUtil.set(d));
      }
    }
    return Utils.copy(doctors);
  }

  public VDMSet geNormalDoctors() {

    VDMSet doctors = null;
    for (Iterator iterator_7 = medicalAssociated.iterator(); iterator_7.hasNext(); ) {
      MedicalAssociated d = (MedicalAssociated) iterator_7.next();
      if (Utils.equals(d.getType(), "Normal Doctor")) {
        doctors = SetUtil.union(Utils.copy(doctors), SetUtil.set(d));
      }
    }
    return Utils.copy(doctors);
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
        + "}";
  }
}
