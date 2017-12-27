package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Prescription {
  private VDMSet medicaments;
  private String code;

  public void cg_init_Prescription_1(final String c) {

    code = c;
    medicaments = SetUtil.set();
    return;
  }

  public Prescription(final String c) {

    cg_init_Prescription_1(c);
  }

  public String getCode() {

    return code;
  }

  public void addMedicament(final Medicament m) {

    medicaments = SetUtil.union(SetUtil.set(m), Utils.copy(medicaments));
  }

  public void removeMedicament(final Medicament m) {

    medicaments = SetUtil.diff(Utils.copy(medicaments), SetUtil.set(m));
  }

  public VDMSet getMedicaments() {

    return Utils.copy(medicaments);
  }

  public Prescription() {}

  public String toString() {

    return "Prescription{"
        + "medicaments := "
        + Utils.toString(medicaments)
        + ", code := "
        + Utils.toString(code)
        + "}";
  }
}