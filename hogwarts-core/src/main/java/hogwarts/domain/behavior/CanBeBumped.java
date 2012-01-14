package hogwarts.domain.behavior;

import hogwarts.domain.misc.Intensity;
import fj.Effect;

/**
 */
public interface CanBeBumped {
    void bump(Intensity intensity);

    public static class F {
        public static Effect<CanBeBumped> bump(final Intensity intensity) {
            return new Effect<CanBeBumped>() {
                @Override
                public void e(CanBeBumped a) {
                    a.bump(intensity);
                }
            };
        }
    }
}
