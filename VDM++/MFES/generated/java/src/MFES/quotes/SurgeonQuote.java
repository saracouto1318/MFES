package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SurgeonQuote {
  private static int hc = 0;
  private static SurgeonQuote instance = null;

  public SurgeonQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SurgeonQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SurgeonQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SurgeonQuote;
  }

  public String toString() {

    return "<Surgeon>";
  }
}
