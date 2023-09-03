package management.auto.car;

public class Bus extends Car {
    private int seatCount;

    @Override
    public double calcInsuranceCost(boolean discount) {
        return (this.getConstructionYear() * 200L
                + (isDiesel() ? 1000 : 0)
                + (getKmCnt() >= 100000 && getKmCnt() < 200000 ? 500 : 0)
                + (getKmCnt() >= 200000 ? 1000 : 0))
                * (discount ? 10 / 100.0 : 1);
    }
}
