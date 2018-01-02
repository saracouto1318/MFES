package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Types {
  public static Number daysOfMonth(final Number month, final Number year) {

    Boolean orResult_4 = false;

    if (Utils.equals(month, 1L)) {
      orResult_4 = true;
    } else {
      Boolean orResult_5 = false;

      if (Utils.equals(month, 3L)) {
        orResult_5 = true;
      } else {
        Boolean orResult_6 = false;

        if (Utils.equals(month, 5L)) {
          orResult_6 = true;
        } else {
          Boolean orResult_7 = false;

          if (Utils.equals(month, 7L)) {
            orResult_7 = true;
          } else {
            Boolean orResult_8 = false;

            if (Utils.equals(month, 8L)) {
              orResult_8 = true;
            } else {
              Boolean orResult_9 = false;

              if (Utils.equals(month, 10L)) {
                orResult_9 = true;
              } else {
                orResult_9 = Utils.equals(month, 12L);
              }

              orResult_8 = orResult_9;
            }

            orResult_7 = orResult_8;
          }

          orResult_6 = orResult_7;
        }

        orResult_5 = orResult_6;
      }

      orResult_4 = orResult_5;
    }

    if (orResult_4) {
      return 31L;

    } else {
      Boolean orResult_10 = false;

      if (Utils.equals(month, 4L)) {
        orResult_10 = true;
      } else {
        Boolean orResult_11 = false;

        if (Utils.equals(month, 6L)) {
          orResult_11 = true;
        } else {
          Boolean orResult_12 = false;

          if (Utils.equals(month, 9L)) {
            orResult_12 = true;
          } else {
            orResult_12 = Utils.equals(month, 11L);
          }

          orResult_11 = orResult_12;
        }

        orResult_10 = orResult_11;
      }

      if (orResult_10) {
        return 30L;

      } else {
        if (Utils.equals(month, 2L)) {
          Boolean orResult_13 = false;

          Boolean andResult_59 = false;

          if (Utils.equals(Utils.mod(year.longValue(), 4L), 0L)) {
            if (!(Utils.equals(Utils.mod(year.longValue(), 100L), 0L))) {
              andResult_59 = true;
            }
          }

          if (andResult_59) {
            orResult_13 = true;
          } else {
            orResult_13 = Utils.equals(Utils.mod(year.longValue(), 400L), 0L);
          }

          if (orResult_13) {
            return 29L;
          }
        }
      }
    }

    return 28L;
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

    Boolean andResult_60 = false;

    if (t.hour.longValue() >= 0L) {
      Boolean andResult_61 = false;

      if (t.hour.longValue() < 24L) {
        Boolean andResult_62 = false;

        if (t.min.longValue() >= 0L) {
          if (t.min.longValue() < 60L) {
            andResult_62 = true;
          }
        }

        if (andResult_62) {
          andResult_61 = true;
        }
      }

      if (andResult_61) {
        andResult_60 = true;
      }
    }

    return andResult_60;
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

    Boolean andResult_66 = false;

    if (d.month.longValue() <= 12L) {
      if (d.day.longValue() <= daysOfMonth(d.month, d.year).longValue()) {
        andResult_66 = true;
      }
    }

    return andResult_66;
  }
}
