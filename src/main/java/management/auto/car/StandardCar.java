package management.auto.car;

import java.time.LocalDate;

public class StandardCar extends Car {
    private boolean isManual;

    @Override
    public double calcInsuranceCost(boolean discount) {
        double baseCost = (LocalDate.now().getYear() - this.getConstructionYear()) * 100L
                + (isDiesel() ? 500 : 0)
                + (getKmCnt() >= 200000 ? 500 : 0);

        if (discount) {
            double discountAmount = baseCost * 0.05;
            return baseCost - discountAmount;
        } else {
            return baseCost;
        }
    }

}
