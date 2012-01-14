package hogwarts.domain.behavior;

import hogwarts.domain.Wand;
import fj.Effect;
import fj.F;
import fj.data.Option;

public interface HasWand {
    Option<Wand> getWand();
    
    public static F<Effect<Wand>, Effect<HasWand>> applyEffectOnWand = new F<Effect<Wand>, Effect<HasWand>>() {
        public Effect<HasWand> f(final Effect<Wand> wandEffect) {
            return new Effect<HasWand>() {
                @Override
                public void e(HasWand a) {
                    a.getWand().foreach(wandEffect);
                }
            };
        };
    };
}
