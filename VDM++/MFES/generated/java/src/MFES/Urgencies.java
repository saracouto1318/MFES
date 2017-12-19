package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Urgencies extends Task {
  public Object priority;

  public void cg_init_Urgencies_1(final Object p) {

    priority = p;
    return;
  }

  public Urgencies(final Object p) {

    cg_init_Urgencies_1(p);
  }

  public Object getPriority() {

    return priority;
  }

  public void setPriority(final Object p) {

    priority = p;
  }

  public String getType() {

    return "Urgencies";
  }

  public Urgencies() {}

  public String toString() {

    return "Urgencies{" + "priority := " + Utils.toString(priority) + "}";
  }
}
