package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Types {
  public static Number daysOfMonth(final Number month) {

    Boolean orResult_9 = false;

    if (Utils.equals(month, 1L)) {
      orResult_9 = true;
    } else {
      Boolean orResult_10 = false;

      if (Utils.equals(month, 3L)) {
        orResult_10 = true;
      } else {
        Boolean orResult_11 = false;

        if (Utils.equals(month, 5L)) {
          orResult_11 = true;
        } else {
          Boolean orResult_12 = false;

          if (Utils.equals(month, 7L)) {
            orResult_12 = true;
          } else {
            Boolean orResult_13 = false;

            if (Utils.equals(month, 8L)) {
              orResult_13 = true;
            } else {
              Boolean orResult_14 = false;

              if (Utils.equals(month, 10L)) {
                orResult_14 = true;
              } else {
                orResult_14 = Utils.equals(month, 12L);
              }

              orResult_13 = orResult_14;
            }

            orResult_12 = orResult_13;
          }

          orResult_11 = orResult_12;
        }

        orResult_10 = orResult_11;
      }

      orResult_9 = orResult_10;
    }

    if (orResult_9) {
      return 31L;

    } else {
      Boolean orResult_15 = false;

      if (Utils.equals(month, 4L)) {
        orResult_15 = true;
      } else {
        Boolean orResult_16 = false;

        if (Utils.equals(month, 6L)) {
          orResult_16 = true;
        } else {
          Boolean orResult_17 = false;

          if (Utils.equals(month, 9L)) {
            orResult_17 = true;
          } else {
            orResult_17 = Utils.equals(month, 11L);
          }

          orResult_16 = orResult_17;
        }

        orResult_15 = orResult_16;
      }

      if (orResult_15) {
        return 30L;

      } else {
        return 28L;
      }
    }
  }

  public Types() {}

  public String toString() {

    return "Types{}";
  }

  public static class Time implements Record {
    public Number hour;
    public Number min;

    public Time(final Number _hour, final Number _min) {

      hour = _hour;
      min = _min;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Time)) {
        return false;
      }

      Time other = ((Time) obj);

      return (Utils.equals(hour, other.hour)) && (Utils.equals(min, other.min));
    }

    public int hashCode() {

      return Utils.hashCode(hour, min);
    }

    public Time copy() {

      return new Time(hour, min);
    }

    public String toString() {

      return "mk_Types`Time" + Utils.formatFields(hour, min);
    }
  }

  public static Boolean inv_Time(final Time t) {

    Boolean andResult_47 = false;

    if (t.hour.longValue() >= 0L) {
      Boolean andResult_48 = false;

      if (t.hour.longValue() < 24L) {
        Boolean andResult_49 = false;

        if (t.min.longValue() >= 0L) {
          if (t.min.longValue() < 60L) {
            andResult_49 = true;
          }
        }

        if (andResult_49) {
          andResult_48 = true;
        }
      }

      if (andResult_48) {
        andResult_47 = true;
      }
    }

    return andResult_47;
  }

  public static class Date implements Record {
    public Number year;
    public Number month;
    public Number day;
    public Time time;

    public Date(final Number _year, final Number _month, final Number _day, final Time _time) {

      year = _year;
      month = _month;
      day = _day;
      time = _time != null ? Utils.copy(_time) : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(year, other.year))
          && (Utils.equals(month, other.month))
          && (Utils.equals(day, other.day))
          && (Utils.equals(time, other.time));
    }

    public int hashCode() {

      return Utils.hashCode(year, month, day, time);
    }

    public Date copy() {

      return new Date(year, month, day, time);
    }

    public String toString() {

      return "mk_Types`Date" + Utils.formatFields(year, month, day, time);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_53 = false;

    if (d.month.longValue() <= 12L) {
      if (d.day.longValue() <= daysOfMonth(d.month).longValue()) {
        andResult_53 = true;
      }
    }

    return andResult_53;
  }
}
