package hogwarts.domain;

import java.util.Iterator;

import com.google.common.collect.Iterators;

import fj.F;

public interface SpellTarget extends Iterable<Targetable> {

    public static F<Targetable,SpellTarget> singleTarget = new F<Targetable, SpellTarget>() {
        
        @Override
        public SpellTarget f(final Targetable target) {
            return new SpellTarget() {
                @Override
                public Iterator<Targetable> iterator() {
                    return Iterators.forArray(target);
                }
            };
        }
    };
}
