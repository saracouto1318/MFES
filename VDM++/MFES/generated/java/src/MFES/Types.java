package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Types {
  public static Number daysOfMonth(final Number month, final Number year) {

    Boolean orResult_2 = false;

    if (Utils.equals(month, 1L)) {
      orResult_2 = true;
    } else {
      Boolean orResult_3 = false;

      if (Utils.equals(month, 3L)) {
        orResult_3 = true;
      } else {
        Boolean orResult_4 = false;

        if (Utils.equals(month, 5L)) {
          orResult_4 = true;
        } else {
          Boolean orResult_5 = false;

          if (Utils.equals(month, 7L)) {
            orResult_5 = true;
          } else {
            Boolean orResult_6 = false;

            if (Utils.equals(month, 8L)) {
              orResult_6 = true;
            } else {
              Boolean orResult_7 = false;

              if (Utils.equals(month, 10L)) {
                orResult_7 = true;
              } else {
                orResult_7 = Utils.equals(month, 12L);
              }

              orResult_6 = orResult_7;
            }

            orResult_5 = orResult_6;
          }

          orResult_4 = orResult_5;
        }

        orResult_3 = orResult_4;
      }

      orResult_2 = orResult_3;
    }

    if (orResult_2) {
      return 31L;

    } else {
      Boolean orResult_8 = false;

      if (Utils.equals(month, 4L)) {
        orResult_8 = true;
      } else {
        Boolean orResult_9 = false;

        if (Utils.equals(month, 6L)) {
          orResult_9 = true;
        } else {
          Boolean orResult_10 = false;

          if (Utils.equals(month, 9L)) {
            orResult_10 = true;
          } else {
            orResult_10 = Utils.equals(month, 11L);
          }

          orResult_9 = orResult_10;
        }

        orResult_8 = orResult_9;
      }

      if (orResult_8) {
        return 30L;

      } else {
        if (Utils.equals(month, 2L)) {
          Boolean orResult_11 = false;

          Boolean andResult_48 = false;

          if (Utils.equals(Utils.mod(year.longValue(), 4L), 0L)) {
            if (!(Utils.equals(Utils.mod(year.longValue(), 100L), 0L))) {
              andResult_48 = true;
            }
          }

          if (andResult_48) {
            orResult_11 = true;
          } else {
            orResult_11 = Utils.equals(Utils.mod(year.longValue(), 400L), 0L);
          }

          if (orResult_11) {
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

    Boolean andResult_49 = false;

    if (t.hour.longValue() >= 0L) {
      Boolean andResult_50 = false;

      if (t.hour.longValue() < 24L) {
        Boolean andResult_51 = false;

        if (t.min.longValue() >= 0L) {
          if (t.min.longValue() < 60L) {
            andResult_51 = true;
          }
        }

        if (andResult_51) {
          andResult_50 = true;
        }
      }

      if (andResult_50) {
        andResult_49 = true;
      }
    }

    return andResult_49;
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

    Boolean andResult_55 = false;

    if (d.month.longValue() <= 12L) {
      if (d.day.longValue() <= daysOfMonth(d.month, d.year).longValue()) {
        andResult_55 = true;
      }
    }

    return andResult_55;
  }
}
