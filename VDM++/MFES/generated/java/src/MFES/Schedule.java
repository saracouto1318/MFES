package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Schedule {
  public Date startHour;
  public Date endHour;

  public void cg_init_Schedule_1(final Date d) {

    startHour = d;
    return;
  }

  public Schedule(final Date d) {

    cg_init_Schedule_1(d);
  }

  public Date setEndHour(final Date d) {

    endHour = d;
    return endHour;
  }

  public Date setStartHour(final Date d) {

    startHour = d;
    return startHour;
  }

  public Schedule setSchedule(final Date d1, final Date d2) {

    startHour = d1;
    endHour = d2;
    return this;
  }

  public Date getScheduleStart() {

    return startHour;
  }

  public Date getScheduleEnd() {

    return endHour;
  }

  public Schedule() {}

  public String toString() {

    return "Schedule{"
        + "startHour := "
        + Utils.toString(startHour)
        + ", endHour := "
        + Utils.toString(endHour)
        + "}";
  }
}
