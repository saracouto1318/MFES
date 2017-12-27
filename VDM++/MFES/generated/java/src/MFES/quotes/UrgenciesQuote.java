package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UrgenciesQuote {
  private static int hc = 0;
  private static UrgenciesQuote instance = null;

  public UrgenciesQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static UrgenciesQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new UrgenciesQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof UrgenciesQuote;
  }

  public String toString() {

    return "<Urgencies>";
  }
}
