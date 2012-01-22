package hogwarts.domain.misc;

public class HitPoints {

    private int maxHitPoints;
    private int amount;
    
    public HitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        this.amount = maxHitPoints;
    }
    
    public boolean isNegativeOrZero() {
        return amount<=0;
    }
    
    public boolean isPositive() {
        return amount>0;
    }
    
    public int get() {
        return amount;
    }
    
    public void modify(int delta) {
        amount += delta;
        amount = Math.min(amount, maxHitPoints);
    }

    public void set(int i) {
        this.amount = i;
    }
}
