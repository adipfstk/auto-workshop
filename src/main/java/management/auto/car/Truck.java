package management.auto.car;

public class Truck extends Car {
    private int tonnage;

    @Override
    public double calcInsuranceCost(boolean discount) {
        return this.getConstructionYear() * 300L
                + (isDiesel() ? 500 : 0)
                + (getKmCnt() >= 800000 ? 700 : 0)
                * (discount ? 5 / 100.0 : 1);
    }
}
