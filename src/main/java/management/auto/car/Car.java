package management.auto.car;

public abstract class Car {
    public static int id;
    private long kmCnt;
    private int constructionYear;
    private boolean diesel;

    public long getKmCnt() {
        return kmCnt;
    }

    public void setKmCnt(long kmCnt) {
        this.kmCnt = kmCnt;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public boolean isDiesel() {
        return diesel;
    }

    public void setDiesel(boolean diesel) {
        this.diesel = diesel;
    }

    public abstract double calcInsuranceCost(boolean discount);
}
