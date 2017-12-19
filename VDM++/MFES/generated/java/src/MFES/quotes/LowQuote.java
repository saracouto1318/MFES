package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LowQuote {
  private static int hc = 0;
  private static LowQuote instance = null;

  public LowQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static LowQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new LowQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof LowQuote;
  }

  public String toString() {

    return "<Low>";
  }
}
