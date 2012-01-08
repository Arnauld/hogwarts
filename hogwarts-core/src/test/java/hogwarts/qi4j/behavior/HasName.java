package hogwarts.qi4j.behavior;

import hogwarts.qi4j.behavior.property.NameProperty;

import org.qi4j.api.property.Immutable;

public interface HasName {
    @Immutable
    NameProperty name();
}
