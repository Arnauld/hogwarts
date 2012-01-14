package hogwarts.domain.behavior;

import hogwarts.domain.misc.Intensity;
import fj.Effect;

public interface LivingCreature {
    void die();
    boolean isAlive();
    void heal(final Intensity intensity);
    
    

    public static class F {
        public static Effect<LivingCreature> kill() {
            return new Effect<LivingCreature>() {
                @Override
                public void e(LivingCreature creature) {
                    creature.die();
                }
            };
        }
        
        public static Effect<LivingCreature> heal(final Intensity intensity) {
            return new Effect<LivingCreature>() {
                @Override
                public void e(LivingCreature creature) {
                    creature.heal(intensity);
                }
            };
        }
    }
}
