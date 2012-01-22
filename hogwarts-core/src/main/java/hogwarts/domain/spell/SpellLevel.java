package hogwarts.domain.spell;

public enum SpellLevel {
    Beginner,
    Novice,
    Expert,
    Master;

    public boolean isHigherThan(SpellLevel other) {
        return ordinal()>other.ordinal();
    }
}
