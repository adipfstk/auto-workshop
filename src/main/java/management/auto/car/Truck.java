package management.auto.car;

import java.time.LocalDate;

public class Truck extends Car {
    private int tonnage;

    @Override
    public double calcInsuranceCost(boolean discount) {
        double baseCost = (LocalDate.now().getYear() - this.getConstructionYear()) * 300L
                + (isDiesel() ? 500 : 0)
                + (getKmCnt() >= 800000 ? 700 : 0);

        if (discount) {
            double discountAmount = baseCost * 0.05;
            return baseCost - discountAmount;
        } else {
            return baseCost;
        }
    }

}
