package hogwarts.domain.behavior;

import fj.Effect;
import hogwarts.domain.misc.Intensity;
import hogwarts.environment.Color;

public interface CanGlow {
    void glow(final Color color, final Intensity intensity);
    
    public static class F {
        public static Effect<CanGlow> glow(final Color CanGlow, final Intensity intensity) {
            return new Effect<CanGlow> () {
                public void e(CanGlow who) {
                    who.glow(CanGlow, intensity);
                }
            };
        }
    }
}
