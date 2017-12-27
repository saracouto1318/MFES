package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AppointmentQuote {
  private static int hc = 0;
  private static AppointmentQuote instance = null;

  public AppointmentQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AppointmentQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AppointmentQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AppointmentQuote;
  }

  public String toString() {

    return "<Appointment>";
  }
}
