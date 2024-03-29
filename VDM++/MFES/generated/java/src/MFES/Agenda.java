package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Agenda {
  private HealthProfessional healthProfessional;
  private VDMSet agenda;

  public void cg_init_Agenda_1(final HealthProfessional h) {

    healthProfessional = h;
    agenda = SetUtil.set();
    return;
  }

  public Agenda(final HealthProfessional h) {

    cg_init_Agenda_1(h);
  }

  public HealthProfessional getHealthProfessional() {

    return healthProfessional;
  }

  public VDMSet getAgenda() {

    return Utils.copy(agenda);
  }

  public void addSchedule(final Schedule s) {

    agenda = SetUtil.union(Utils.copy(agenda), SetUtil.set(s));
  }

  public void removeSchedule(final Schedule s) {

    for (Iterator iterator_30 = agenda.iterator(); iterator_30.hasNext(); ) {
      Schedule sch = (Schedule) iterator_30.next();
      if (overlap(s, sch)) {
        agenda = SetUtil.diff(Utils.copy(agenda), SetUtil.set(sch));
        return;
      }
    }
  }

  public Boolean overlap(final Schedule t1, final Schedule t2) {

    return t1.overlap(t1, t2);
  }

  public Agenda() {}

  public String toString() {

    return "Agenda "
        + Utils.toString(healthProfessional)
        + " - "
        + Utils.toString(agenda);
  }
}
