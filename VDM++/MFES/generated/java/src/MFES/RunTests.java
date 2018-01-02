package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class RunTests {
  public static void main() {

    TaskTest taskTest = new TaskTest();
    PersonTest personTest = new PersonTest();
    TrainingTest trainingTest = new TrainingTest();
    SafetyNetHospitalTest safetyNetTest = new SafetyNetHospitalTest();
    personTest.main();
    taskTest.main();
    trainingTest.main();
    safetyNetTest.main();
    IO.print("\n\n ====== All TaskTest run successfully ====== \n\n");
    IO.print("\n\n ====== All TrainingTest run successfully ====== \n\n");
    IO.print("\n\n ====== All PersonTest run successfully ====== \n\n");
    IO.print("\n\n ====== All SafetyNetHospitalTest run successfully ====== \n\n");
  }

  public RunTests() {}

  public String toString() {

    return "RunTests{}";
  }
}
