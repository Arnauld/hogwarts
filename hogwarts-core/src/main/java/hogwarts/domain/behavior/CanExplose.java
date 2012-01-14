package hogwarts.domain.behavior;

import hogwarts.domain.misc.Intensity;
import fj.Effect;

public interface CanExplose {
    void explose(final Intensity intensity);
    
    public static class F {
        public static Effect<CanExplose> explose(final Intensity intensity) {
            return new Effect<CanExplose> () {
                public void e(CanExplose who) {
                    who.explose(intensity);
                }
            };
        }
    }
}
