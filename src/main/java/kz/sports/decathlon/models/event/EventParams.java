package kz.sports.decathlon.models.event;

import java.math.BigDecimal;

public class EventParams {

    private BigDecimal A;
    private BigDecimal B;
    private BigDecimal C;
    private BigDecimal manualAdjustment;

    public EventParams(String a, String b, String c) {
        prepare(a, b, c, "0");
    }

    public EventParams(String a, String b, String c, String manualAdjustment) {
        /*
        * "NOTE: Points for manual times in events up to 400 metres are calculated eitherby  adding
        *   the  standard  adjustment  factor  to  the  time  (i.e.  0.24  sec.  for  eventsbelow
        *   400 metres, 0.14 sec. for 400 metres) or by subtracting the factor from the"b" parameter.
        *   There are no adjustments for events above 400 metres."
        * IAAF Scoring Tables For Combined Events;
        * */
        prepare(a, b, c, manualAdjustment);
    }

    private void prepare(String a, String b, String c, String manualAdjustment) {
        this.manualAdjustment = new BigDecimal(manualAdjustment);
        A = new BigDecimal(a);
        B = new BigDecimal(b).subtract(this.manualAdjustment);
        C = new BigDecimal(c);
    }

    public BigDecimal getA() {
        return A;
    }

    public BigDecimal getB() {
        return B;
    }

    public BigDecimal getC() {
        return C;
    }

}
