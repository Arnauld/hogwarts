package hogwarts.domain;

import fj.data.Option;
import hogwarts.util.HasBehavior;

public interface Wand extends HasBehavior {
    Option<HasBehavior> holdBy();
}
