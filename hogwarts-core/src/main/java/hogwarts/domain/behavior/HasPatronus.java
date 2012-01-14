package hogwarts.domain.behavior;

import fj.Effect;
import fj.F;
import fj.data.Option;
import hogwarts.environment.Shape;

public interface HasPatronus {
    Option<Shape> getPatronus();
    
    public static F<Effect<Shape>, Effect<HasPatronus>> applyEffectOnPatronus = new F<Effect<Shape>, Effect<HasPatronus>>() {
        public Effect<HasPatronus> f(final Effect<Shape> effect) {
            return new Effect<HasPatronus>() {
                @Override
                public void e(HasPatronus a) {
                    a.getPatronus().foreach(effect);
                }
            };
        };
    };
}
