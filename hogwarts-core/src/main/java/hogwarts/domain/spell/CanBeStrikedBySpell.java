package hogwarts.domain.spell;

import fj.Effect;

public interface CanBeStrikedBySpell {
    <T extends Spell<T>> void strikedBySpell(Spell<T> spell);
    
    public static class F {
        public static <T extends Spell<T>> Effect<CanBeStrikedBySpell> strikedBySpell(final Spell<T> spell) {
            return new Effect<CanBeStrikedBySpell> () {
                public void e(CanBeStrikedBySpell who) {
                    who.strikedBySpell(spell);
                }
            };
        }
    }
}
