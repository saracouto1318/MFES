package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  private Number year;
  private Number month;
  private Number day;
  private Number hour;
  private Number minutes;

  public void cg_init_Date_1(
      final Number y, final Number m, final Number d, final Number h, final Number min) {

    year = y;
    month = m;
    day = d;
    hour = h;
    minutes = min;
    return;
  }

  public Date(final Number y, final Number m, final Number d, final Number h, final Number min) {

    cg_init_Date_1(y, m, d, h, min);
  }

  public Number getYear() {

    return year;
  }

  public Number getMonth() {

    return month;
  }

  public Number getDay() {

    return day;
  }

  public Number getHour() {

    return hour;
  }

  public Number getMin() {

    return minutes;
  }

  public Boolean compareDateLess(final Date date) {

    Boolean andResult_26 = false;

    if (year.longValue() < date.getYear().longValue()) {
      Boolean andResult_27 = false;

      if (month.longValue() < date.getMonth().longValue()) {
        Boolean andResult_28 = false;

        if (day.longValue() < date.getDay().longValue()) {
          Boolean andResult_29 = false;

          if (hour.longValue() < date.getHour().longValue()) {
            if (minutes.longValue() < date.getMin().longValue()) {
              andResult_29 = true;
            }
          }

          if (andResult_29) {
            andResult_28 = true;
          }
        }

        if (andResult_28) {
          andResult_27 = true;
        }
      }

      if (andResult_27) {
        andResult_26 = true;
      }
    }

    return andResult_26;
  }

  public Boolean compareDate(final Date date) {

    Boolean andResult_30 = false;

    if (Utils.equals(date.getYear(), year)) {
      Boolean andResult_31 = false;

      if (Utils.equals(date.getMonth(), month)) {
        Boolean andResult_32 = false;

        if (Utils.equals(date.getDay(), day)) {
          Boolean andResult_33 = false;

          if (Utils.equals(hour, date.getHour())) {
            if (Utils.equals(minutes, date.getMin())) {
              andResult_33 = true;
            }
          }

          if (andResult_33) {
            andResult_32 = true;
          }
        }

        if (andResult_32) {
          andResult_31 = true;
        }
      }

      if (andResult_31) {
        andResult_30 = true;
      }
    }

    return andResult_30;
  }

  public Date() {}

  public String toString() {

    return "Date{"
        + "year := "
        + Utils.toString(year)
        + ", month := "
        + Utils.toString(month)
        + ", day := "
        + Utils.toString(day)
        + ", hour := "
        + Utils.toString(hour)
        + ", minutes := "
        + Utils.toString(minutes)
        + "}";
  }
}
