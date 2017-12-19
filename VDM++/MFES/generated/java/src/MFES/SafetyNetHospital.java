package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SafetyNetHospital {
  public VDMSet hospitals;

  public void cg_init_SafetyNetHospital_1() {

    return;
  }

  public SafetyNetHospital() {

    cg_init_SafetyNetHospital_1();
  }

  public VDMSet addHospital(final Hospital h) {

    return SetUtil.union(Utils.copy(hospitals), SetUtil.set(h));
  }

  public VDMSet removeHospital(final Hospital h) {

    return SetUtil.diff(Utils.copy(hospitals), SetUtil.set(h));
  }

  public Number numHospitals() {

    return hospitals.size();
  }

  public Hospital getHospitalsMoreAppointments() {

    Number max = 0L;
    Hospital hosp = null;
    max = 0L;
    for (Iterator iterator_8 = hospitals.iterator(); iterator_8.hasNext(); ) {
      Hospital h = (Hospital) iterator_8.next();
      if (h.getAppointments().size() > max.longValue()) {
        max = h.getAppointments().size();
        hosp = h;
      }
    }
    return hosp;
  }

  public VDMSet getDoctorsMoreHospitals() {

    VDMSet doctors = null;
    for (Iterator iterator_9 = hospitals.iterator(); iterator_9.hasNext(); ) {
      Hospital h = (Hospital) iterator_9.next();
      VDMSet med = null;
      VDMSet list = null;
      med = h.getMedicalAssociated();
      list = SetUtil.diff(Utils.copy(hospitals), SetUtil.set(h));
      for (Iterator iterator_10 = med.iterator(); iterator_10.hasNext(); ) {
        MedicalAssociated m = (MedicalAssociated) iterator_10.next();
        for (Iterator iterator_11 = list.iterator(); iterator_11.hasNext(); ) {
          Hospital l = (Hospital) iterator_11.next();
          Boolean andResult_34 = false;

          if (SetUtil.inSet(m, l.getMedicalAssociated())) {
            Boolean andResult_35 = false;

            if (Utils.equals(m.getType(), "Doctor")) {
              if (!(SetUtil.inSet(m, doctors))) {
                andResult_35 = true;
              }
            }

            if (andResult_35) {
              andResult_34 = true;
            }
          }

          if (andResult_34) {
            doctors = SetUtil.union(Utils.copy(doctors), SetUtil.set(m));
          }
        }
      }
    }
    return Utils.copy(doctors);
  }

  public String toString() {

    return "SafetyNetHospital{" + "hospitals := " + Utils.toString(hospitals) + "}";
  }
}
