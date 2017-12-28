package MFES.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AddSkillsQuote {
  private static int hc = 0;
  private static AddSkillsQuote instance = null;

  public AddSkillsQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AddSkillsQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AddSkillsQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AddSkillsQuote;
  }

  public String toString() {

    return "<AddSkills>";
  }
}
