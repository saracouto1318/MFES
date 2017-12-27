package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SurgeryQuote {
  private static int hc = 0;
  private static SurgeryQuote instance = null;

  public SurgeryQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SurgeryQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SurgeryQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SurgeryQuote;
  }

  public String toString() {

    return "<Surgery>";
  }
}
