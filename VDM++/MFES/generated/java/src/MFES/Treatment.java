package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Treatment extends Task {
  public HealthProfessional med;
  public String name;

  public void cg_init_Treatment_1(
      final HealthProfessional m,
      final String n,
      final Schedule s,
      final Patient p,
      final Hospital h) {

    name = n;
    med = m;
    cg_init_Task_1(m, s, p, h, MFES.quotes.OtherQuote.getInstance());
  }

  public Treatment(
      final HealthProfessional m,
      final String n,
      final Schedule s,
      final Patient p,
      final Hospital h) {

    cg_init_Treatment_1(m, n, s, p, h);
  }

  public String getName() {

    return name;
  }

  public HealthProfessional getMed() {

    return med;
  }

  public Treatment() {}
}
