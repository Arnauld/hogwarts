package hogwarts.domain.behavior;

import fj.Effect;
import hogwarts.domain.misc.Intensity;

public interface CanBeRepaired {
    void repair(Intensity intensity);
    
    public static class F {
        public static Effect<CanBeRepaired> repair(final Intensity intensity) {
            return new Effect<CanBeRepaired> () {
                public void e(CanBeRepaired who) {
                    who.repair(intensity);
                }
            };
        }
    }
}
