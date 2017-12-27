package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Schedule {
  private Types.Date startHour;
  private Types.Date endHour;

  public void cg_init_Schedule_1(final Types.Date d, final Types.Date d2) {

    startHour = Utils.copy(d);
    endHour = Utils.copy(d2);
    return;
  }

  public Schedule(final Types.Date d, final Types.Date d2) {

    cg_init_Schedule_1(Utils.copy(d), Utils.copy(d2));
  }

  public void setSchedule(final Types.Date d1, final Types.Date d2) {

    startHour = Utils.copy(d1);
    endHour = Utils.copy(d2);
  }

  public Types.Date getScheduleStart() {

    return Utils.copy(startHour);
  }

  public Types.Date getScheduleEnd() {

    return Utils.copy(endHour);
  }

  public Boolean overlap(final Schedule d1, final Schedule d2) {

    Boolean orResult_8 = false;

    Boolean andResult_31 = false;

    if (lessThan(d1.startHour, d2.startHour)) {
      if (greaterThan(d1.endHour, d2.startHour)) {
        andResult_31 = true;
      }
    }

    if (andResult_31) {
      orResult_8 = true;
    } else {
      Boolean andResult_32 = false;

      if (!(lessThan(d1.startHour, d2.startHour))) {
        if (lessThan(d1.startHour, d2.endHour)) {
          andResult_32 = true;
        }
      }

      orResult_8 = andResult_32;
    }

    if (orResult_8) {
      return true;
    }

    return false;
  }

  public static Boolean lessThan(final Types.Date d1, final Types.Date d2) {

    if (d1.year.longValue() < d2.year.longValue()) {
      return true;

    } else {
      if (d1.year.longValue() > d2.year.longValue()) {
        return false;
      }
    }

    if (d1.month.longValue() < d2.month.longValue()) {
      return true;

    } else {
      if (d1.month.longValue() > d2.month.longValue()) {
        return false;
      }
    }

    if (d1.day.longValue() < d2.day.longValue()) {
      return true;

    } else {
      if (d1.day.longValue() > d2.day.longValue()) {
        return false;
      }
    }

    if (d1.time.hour.longValue() < d2.time.hour.longValue()) {
      return true;

    } else {
      if (d1.time.hour.longValue() > d2.time.hour.longValue()) {
        return false;
      }
    }

    return d1.time.min.longValue() < d2.time.min.longValue();
  }

  public static Boolean greaterThan(final Types.Date d1, final Types.Date d2) {

    if (d1.year.longValue() < d2.year.longValue()) {
      return false;

    } else {
      if (d1.year.longValue() > d2.year.longValue()) {
        return true;
      }
    }

    if (d1.month.longValue() < d2.month.longValue()) {
      return false;

    } else {
      if (d1.month.longValue() > d2.month.longValue()) {
        return true;
      }
    }

    if (d1.day.longValue() < d2.day.longValue()) {
      return false;

    } else {
      if (d1.day.longValue() > d2.day.longValue()) {
        return true;
      }
    }

    if (d1.time.hour.longValue() < d2.time.hour.longValue()) {
      return false;

    } else {
      if (d1.time.hour.longValue() > d2.time.hour.longValue()) {
        return true;
      }
    }

    return d1.time.min.longValue() > d2.time.min.longValue();
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
