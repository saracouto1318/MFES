package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Specialty {
  private String name;

  public void cg_init_Specialty_1(final String n) {

    name = n;
    return;
  }

  public Specialty(final String n) {

    cg_init_Specialty_1(n);
  }

  public String getName() {

    return name;
  }

  public Specialty() {}

  public String toString() {
    return "Specialty " 
    		+ name;
  }
}
