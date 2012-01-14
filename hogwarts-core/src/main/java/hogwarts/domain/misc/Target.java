package hogwarts.domain.misc;

import hogwarts.util.HasBehavior;

import java.util.Iterator;

import com.google.common.collect.Iterators;

public interface Target extends Iterable<HasBehavior> {
    int count();
    

    public static class F { 
        public static Target targets(final HasBehavior...targets) {
            return new Target() {
                @Override
                public Iterator<HasBehavior> iterator() {
                    return Iterators.forArray(targets);
                }
                @Override
                public int count() {
                    return targets.length;
                }
            };
        }
    };
}
