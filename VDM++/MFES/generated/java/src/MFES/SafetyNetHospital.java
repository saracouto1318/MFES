package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SafetyNetHospital {
  private VDMSet hospitals;

  public void cg_init_SafetyNetHospital_1() {

    hospitals = SetUtil.set();
    return;
  }

  public SafetyNetHospital() {

    cg_init_SafetyNetHospital_1();
  }

  public void addHospital(final Hospital h) {

    hospitals = SetUtil.union(Utils.copy(hospitals), SetUtil.set(h));
  }

  public void removeHospital(final Hospital h) {

    hospitals = SetUtil.diff(Utils.copy(hospitals), SetUtil.set(h));
  }

  public VDMSet getHospitals() {

    return Utils.copy(hospitals);
  }

  public Hospital getHospitalsMoreAppointments(final Object t) {

    Number max = 0L;
    Hospital hosp = null;
    max = -1L;
    for (Iterator iterator_23 = hospitals.iterator(); iterator_23.hasNext(); ) {
      Hospital h = (Hospital) iterator_23.next();
      if (h.getTasksByType(((Object) t)).size() > max.longValue()) {
        max = h.getTasksByType(((Object) t)).size();
        hosp = h;
      }
    }
    return hosp;
  }

  public VDMSet getMedMoreHospitals(final Object t) {

    VDMSet doctors = null;
    doctors = SetUtil.set();
    for (Iterator iterator_24 = hospitals.iterator(); iterator_24.hasNext(); ) {
      Hospital h = (Hospital) iterator_24.next();
      VDMSet med = null;
      VDMSet list = null;
      med = h.getMedicalAssociatedByType(((Object) t));
      list = SetUtil.diff(Utils.copy(hospitals), SetUtil.set(h));
      for (Iterator iterator_25 = med.iterator(); iterator_25.hasNext(); ) {
        HealthProfessional m = (HealthProfessional) iterator_25.next();
        for (Iterator iterator_26 = list.iterator(); iterator_26.hasNext(); ) {
          Hospital l = (Hospital) iterator_26.next();
          Boolean andResult_29 = false;

          if (Utils.equals(m.getType(), t)) {
            Boolean andResult_30 = false;

            if (SetUtil.inSet(m, l.getMedicalAssociatedByType(((Object) t)))) {
              if (!(SetUtil.inSet(m, doctors))) {
                andResult_30 = true;
              }
            }

            if (andResult_30) {
              andResult_29 = true;
            }
          }

          if (andResult_29) {
            doctors = SetUtil.union(Utils.copy(doctors), SetUtil.set(m));
          }
        }
      }
    }
    return Utils.copy(doctors);
  }

  public VDMMap getMedAssociatedByPatient(final Patient p, final Object t) {

    VDMMap maps = null;
    VDMSet med = null;
    maps = MapUtil.map();
    med = SetUtil.set();
    for (Iterator iterator_27 = hospitals.iterator(); iterator_27.hasNext(); ) {
      Hospital h = (Hospital) iterator_27.next();
      for (Iterator iterator_28 = h.getMedicalAssociatedByType(((Object) t)).iterator();
          iterator_28.hasNext();
          ) {
        HealthProfessional m = (HealthProfessional) iterator_28.next();
        if (SetUtil.inSet(p, m.getPatients())) {
          med = SetUtil.union(Utils.copy(med), SetUtil.set(m));
        }
      }
      maps = MapUtil.munion(Utils.copy(maps), MapUtil.map(new Maplet(h, Utils.copy(med))));
      med = SetUtil.set();
    }
    return Utils.copy(maps);
  }

  public VDMMap getMedByHospital(final Object t) {

    VDMMap maps = null;
    maps = MapUtil.map();
    for (Iterator iterator_29 = hospitals.iterator(); iterator_29.hasNext(); ) {
      Hospital h = (Hospital) iterator_29.next();
      maps =
          MapUtil.munion(
              Utils.copy(maps),
              MapUtil.map(new Maplet(h, h.getMedicalAssociatedByType(((Object) t)))));
    }
    return Utils.copy(maps);
  }

  public String toString() {

    return "SafetyNetHospital{" + "hospitals := " + Utils.toString(hospitals) + "}";
  }
}
