package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Person {
  public String address;
  public String firstName;
  public String lastName;

  public void cg_init_Person_1(final String a, final String fn, final String ln) {

    address = a;
    firstName = fn;
    lastName = ln;
    return;
  }

  public Person(final String a, final String fn, final String ln) {

    cg_init_Person_1(a, fn, ln);
  }

  public String getAddress() {

    return address;
  }

  public String getName() {

    return firstName + " " + lastName;
  }

  public String getFirstName() {

    return firstName;
  }

  public String getLastName() {

    return lastName;
  }

  public Person() {}

  public String toString() {

    return "Person{"
        + "address := "
        + Utils.toString(address)
        + ", firstName := "
        + Utils.toString(firstName)
        + ", lastName := "
        + Utils.toString(lastName)
        + "}";
  }
}
