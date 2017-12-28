package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Person {
  protected String address;
  protected String firstName;
  protected String lastName;
  protected String cc;
  protected String phoneNumber;

  public void cg_init_Person_1(
      final String a, final String fn, final String ln, final String c, final String pn) {

    address = a;
    firstName = fn;
    lastName = ln;
    cc = c;
    phoneNumber = pn;
    return;
  }

  public Person(final String a, final String fn, final String ln, final String c, final String pn) {

    cg_init_Person_1(a, fn, ln, c, pn);
  }

  public String getCC() {

    return cc;
  }

  public String getInfo() {

    return "Name: "
        + firstName
        + " "
        + lastName
        + "\nAddress: "
        + address
        + "\nPhone Number: "
        + phoneNumber
        + "\nCC: "
        + cc;
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
        + ", cc := "
        + Utils.toString(cc)
        + ", phoneNumber := "
        + Utils.toString(phoneNumber)
        + "}";
  }
}
