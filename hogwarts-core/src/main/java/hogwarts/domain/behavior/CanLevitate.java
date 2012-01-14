package hogwarts.domain.behavior;

import hogwarts.domain.misc.Intensity;
import fj.Effect;

public interface CanLevitate {
    void levitate(final Intensity intensity);
    
    public static class F {
        public static Effect<CanLevitate> levitate(final Intensity intensity) {
            return new Effect<CanLevitate>() {
                @Override
                public void e(CanLevitate a) {
                    a.levitate(intensity);
                }
            };
        }
    }
}
