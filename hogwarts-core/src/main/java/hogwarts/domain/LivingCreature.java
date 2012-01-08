package hogwarts.domain;

import fj.Effect;
import fj.F;

public interface LivingCreature extends Targetable {
    void die();
    boolean isAlive();
    void heal(int amount);
    
    public static Effect<LivingCreature> kill = new Effect<LivingCreature>() {
        @Override
        public void e(LivingCreature creature) {
            creature.die();
        }
    };
    
    public static F<Integer,Effect<LivingCreature>> heal = new F<Integer,Effect<LivingCreature>>() {
        @Override
        public Effect<LivingCreature> f(final Integer amount) {
            return new Effect<LivingCreature>() {
                @Override
                public void e(LivingCreature creature) {
                    creature.heal(amount);
                }
            };
        }
    };
}
