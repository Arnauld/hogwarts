package hogwarts.qi4j.behavior;

import hogwarts.domain.property.NameProperty;

import org.qi4j.api.property.Immutable;

public interface HasName {
    @Immutable
    NameProperty name();
}
