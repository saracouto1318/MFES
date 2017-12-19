package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TrainingQuote {
  private static int hc = 0;
  private static TrainingQuote instance = null;

  public TrainingQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TrainingQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TrainingQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TrainingQuote;
  }

  public String toString() {

    return "<Training>";
  }
}
