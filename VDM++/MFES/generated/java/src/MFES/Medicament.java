package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Medicament {
  private String name;

  public void cg_init_Medicament_1(final String n) {

    name = n;
    return;
  }

  public Medicament(final String n) {

    cg_init_Medicament_1(n);
  }

  public String getName() {

    return name;
  }

  public Medicament() {}

  public String toString() {

    return "Medicament{" + "name := " + Utils.toString(name) + "}";
  }
}
