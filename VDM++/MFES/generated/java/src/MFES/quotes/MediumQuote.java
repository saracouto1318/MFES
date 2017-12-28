package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MediumQuote {
  private static int hc = 0;
  private static MediumQuote instance = null;

  public MediumQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MediumQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MediumQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MediumQuote;
  }

  public String toString() {

    return "<Medium>";
  }
}
