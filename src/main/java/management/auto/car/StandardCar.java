package management.auto.car;

public class StandardCar extends Car {
    private boolean isManual;

    @Override
    public double calcInsuranceCost(boolean discount) {
        return (this.getConstructionYear() * 100L
                + (isDiesel() ? 500 : 0)
                + (getKmCnt() >= 200000 ? 500 : 0))
                * (discount ? 5 / 100.0 : 1);
    }
}
