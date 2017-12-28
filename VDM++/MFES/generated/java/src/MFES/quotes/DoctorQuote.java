package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DoctorQuote {
  private static int hc = 0;
  private static DoctorQuote instance = null;

  public DoctorQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static DoctorQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new DoctorQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof DoctorQuote;
  }

  public String toString() {

    return "<Doctor>";
  }
}
