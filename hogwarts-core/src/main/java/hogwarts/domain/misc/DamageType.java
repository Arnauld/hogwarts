package hogwarts.domain.misc;

import hogwarts.util.New;

import java.util.Set;

public class DamageType {
    public enum Flag {
        Physical,
        Magical,
        Fire,
        Water,
        Air,
        Earth,
        Dark,
        Light
    }
    
    private Set<Flag> flags;
    public DamageType(Flag...flags) {
        this.flags = New.immutableSet(flags);
    }
    
    public Set<Flag> getFlags() {
        return flags;
    }
    
    public boolean hasFlag(Flag flag) {
        return flags.contains(flag);
    }
    
}
