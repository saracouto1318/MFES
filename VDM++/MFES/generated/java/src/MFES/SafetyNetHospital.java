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
    for (Iterator iterator_16 = hospitals.iterator(); iterator_16.hasNext(); ) {
      Hospital h = (Hospital) iterator_16.next();
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
    for (Iterator iterator_17 = hospitals.iterator(); iterator_17.hasNext(); ) {
      Hospital h = (Hospital) iterator_17.next();
      VDMSet med = null;
      VDMSet list = null;
      med = h.getMedicalAssociatedByType(((Object) t));
      list = SetUtil.diff(Utils.copy(hospitals), SetUtil.set(h));
      for (Iterator iterator_18 = med.iterator(); iterator_18.hasNext(); ) {
        HealthProfessional m = (HealthProfessional) iterator_18.next();
        for (Iterator iterator_19 = list.iterator(); iterator_19.hasNext(); ) {
          Hospital l = (Hospital) iterator_19.next();
          Boolean andResult_27 = false;

          if (Utils.equals(m.getType(), t)) {
            Boolean andResult_28 = false;

            if (SetUtil.inSet(m, l.getMedicalAssociatedByType(((Object) t)))) {
              if (!(SetUtil.inSet(m, doctors))) {
                andResult_28 = true;
              }
            }

            if (andResult_28) {
              andResult_27 = true;
            }
          }

          if (andResult_27) {
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
    for (Iterator iterator_20 = hospitals.iterator(); iterator_20.hasNext(); ) {
      Hospital h = (Hospital) iterator_20.next();
      for (Iterator iterator_21 = h.getMedicalAssociatedByType(((Object) t)).iterator();
          iterator_21.hasNext();
          ) {
        HealthProfessional m = (HealthProfessional) iterator_21.next();
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
    for (Iterator iterator_22 = hospitals.iterator(); iterator_22.hasNext(); ) {
      Hospital h = (Hospital) iterator_22.next();
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
