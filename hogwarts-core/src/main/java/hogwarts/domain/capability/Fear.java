package hogwarts.domain.capability;

import fj.Effect;
import hogwarts.domain.Intensity;


public class Fear {
    public interface CauseOf {
        void changeFearOn(Aware target, Intensity intensity);
    }
    
    public static Effect<CauseOf> changeFearOnF(final Aware target, final Intensity intensity) {
        return new Effect<Fear.CauseOf>() {
            @Override
            public void e(CauseOf causeOf) {
                causeOf.changeFearOn(target, intensity);
            }
        };
    }
    
    public interface Aware {
        int getFearAgainst(CauseOf origin);
        void modifyFearAgainst(CauseOf origin, Intensity.Modifier modifier);
    }
    
    public static Effect<Aware> modifyFearAgainstF(final CauseOf origin, final Intensity.Modifier modifier) {
        return new Effect<Fear.Aware>() {
            @Override
            public void e(Aware aware) {
                aware.modifyFearAgainst(origin, modifier);
            }
        };
    }

}
