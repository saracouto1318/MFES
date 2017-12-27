package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class NurseQuote {
  private static int hc = 0;
  private static NurseQuote instance = null;

  public NurseQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static NurseQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new NurseQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof NurseQuote;
  }

  public String toString() {

    return "<Nurse>";
  }
}
