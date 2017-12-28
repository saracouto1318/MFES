package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class HighQuote {
  private static int hc = 0;
  private static HighQuote instance = null;

  public HighQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static HighQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new HighQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof HighQuote;
  }

  public String toString() {

    return "<High>";
  }
}
