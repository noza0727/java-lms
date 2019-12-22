package Library;

import java.util.*;

class LibraryBook {
    private static final double MILLISEC_TO_DAY = 1.0 / 1000 / 60 / 60 / 24;
    private static final double CHARGE_PER_DAY = 500;
    private static final double MAX_CHARGE = 500000;

    private GregorianCalendar dueDate;
    private double chargePerDay;
    private double maximumCharge;

    public LibraryBook(GregorianCalendar dueDate, double chargePerDay, double maximumCharge) {
        this.dueDate = dueDate;
        this.chargePerDay = chargePerDay;
        this.maximumCharge = maximumCharge;
    }

    public double computeCharge(GregorianCalendar returnDate) {
        double charge = 0.0;
        long dueTime = dueDate.getTimeInMillis();
        long returnTime = returnDate.getTimeInMillis();
        long diff = returnTime - dueTime;
        if (diff > 0) {
            charge = chargePerDay * diff * MILLISEC_TO_DAY;
            if (charge > maximumCharge) {
                charge = maximumCharge;
            }
        }
        return charge;
    }


}