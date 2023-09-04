package management.auto.car;

import java.time.LocalDate;

public class Bus extends Car {
    private int seatCount;

    @Override
    public double calcInsuranceCost(boolean discount) {
        double baseCost = (LocalDate.now().getYear() - this.getConstructionYear()) * 200L
                + (isDiesel() ? 1000 : 0)
                + (getKmCnt() >= 100000 && getKmCnt() < 200000 ? 500 : 0)
                + (getKmCnt() >= 200000 ? 1000 : 0);

        if (discount) {
            double discountAmount = baseCost * 0.10;
            return baseCost - discountAmount;
        } else {
            return baseCost;
        }
    }

}
