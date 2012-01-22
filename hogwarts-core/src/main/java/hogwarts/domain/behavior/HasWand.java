package hogwarts.domain.behavior;

import org.qi4j.api.common.Optional;

import fj.Effect;
import fj.F;
import fj.data.Option;
import hogwarts.domain.Wand;
import hogwarts.domain.WandProperty;

public interface HasWand {

    @Optional
    WandProperty wand();
    
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
