package hogwarts.domain;

import hogwarts.environment.HasPosition;
import hogwarts.util.HasBehavior;

public interface Targetable extends HasBehavior, HasPosition {
    void strikedBySpell(Spell<?> spell);
}
