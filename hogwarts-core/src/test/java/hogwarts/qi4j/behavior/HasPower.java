package hogwarts.qi4j.behavior;

import hogwarts.domain.property.PowerProperty;

import org.qi4j.api.property.Immutable;

public interface HasPower {
    @Immutable
    PowerProperty power();
}
