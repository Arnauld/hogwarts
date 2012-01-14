package hogwarts.domain.behavior;

import fj.Effect;
import hogwarts.domain.misc.Damage;

public interface CanBeDamaged {
    void damage(Damage damage);
    
    public static class F {
        public static Effect<CanBeDamaged> damage(final Damage damage) {
            return new Effect<CanBeDamaged> () {
                public void e(CanBeDamaged who) {
                    who.damage(damage);
                }
            };
        }
    }
}
