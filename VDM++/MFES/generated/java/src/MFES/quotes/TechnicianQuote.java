package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TechnicianQuote {
  private static int hc = 0;
  private static TechnicianQuote instance = null;

  public TechnicianQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TechnicianQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TechnicianQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TechnicianQuote;
  }

  public String toString() {

    return "<Technician>";
  }
}
