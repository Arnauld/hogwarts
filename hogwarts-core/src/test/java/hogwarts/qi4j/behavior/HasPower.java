package hogwarts.qi4j.behavior;

import hogwarts.qi4j.behavior.property.PowerProperty;

import org.qi4j.api.property.Immutable;

public interface HasPower {
    @Immutable
    PowerProperty power();
}
