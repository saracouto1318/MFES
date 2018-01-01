package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Surgery extends Task {
  private VDMSet secondaryDoctors;
  private VDMSet other;

  public void cg_init_Surgery_1(
      final HealthProfessional s, final Schedule sch, final Patient p, final Hospital h) {

    medicalAssoc = s;
    other = SetUtil.set();
    secondaryDoctors = SetUtil.set();
    cg_init_Task_1(s, sch, p, h, MFES.quotes.SurgeryQuote.getInstance());
  }

  public Surgery(
      final HealthProfessional s, final Schedule sch, final Patient p, final Hospital h) {

    cg_init_Surgery_1(s, sch, p, h);
  }

  public void addSecondaryDoctor(final HealthProfessional s) {

    removeScheduleAuxiliaries(s);
    secondaryDoctors = SetUtil.union(Utils.copy(secondaryDoctors), SetUtil.set(s));
  }

  public void removeSecondaryDoctor(final HealthProfessional s) {

    addScheduleAuxiliaries(s);
    secondaryDoctors = SetUtil.diff(Utils.copy(secondaryDoctors), SetUtil.set(s));
  }

  public void addOther(final HealthProfessional s) {

    removeScheduleAuxiliaries(s);
    other = SetUtil.union(Utils.copy(other), SetUtil.set(s));
  }

  public void removeOther(final HealthProfessional s) {

    addScheduleAuxiliaries(s);
    other = SetUtil.diff(Utils.copy(other), SetUtil.set(s));
  }

  public void setMainDoctor(final HealthProfessional s) {

    medicalAssoc = s;
  }

  public VDMSet getSurgeryPersons(final Object t) {

    VDMSet med = null;
    if (Utils.equals(t, MFES.quotes.SurgeonQuote.getInstance())) {
      med = Utils.copy(secondaryDoctors);
    } else {
      med = Utils.copy(other);
    }

    return Utils.copy(med);
  }

  public void addScheduleAuxiliaries(final HealthProfessional s) {

    for (Iterator iterator_20 = hospital.getAgendas().iterator(); iterator_20.hasNext(); ) {
      Agenda a = (Agenda) iterator_20.next();
      if (Utils.equals(a.getHealthProfessional().getCC(), s.getCC())) {
        a.addSchedule(schedule);
      }
    }
  }

  public void removeScheduleAuxiliaries(final HealthProfessional s) {

    for (Iterator iterator_21 = hospital.getAgendas().iterator(); iterator_21.hasNext(); ) {
      Agenda a = (Agenda) iterator_21.next();
      if (Utils.equals(a.getHealthProfessional().getCC(), s.getCC())) {
        a.removeSchedule(schedule);
      }
    }
  }

  public Surgery() {}

  public String toString() {

    return "Surgery{"
        + "secondaryDoctors := "
        + Utils.toString(secondaryDoctors)
        + ", other := "
        + Utils.toString(other)
        + "}";
  }
}
