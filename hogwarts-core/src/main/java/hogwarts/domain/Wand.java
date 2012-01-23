package hogwarts.domain;

import hogwarts.util.HasBehavior;

import org.qi4j.api.property.Immutable;
import org.qi4j.api.property.Property;

public interface Wand extends HasBehavior {
    
    @Immutable
    Property<Integer> length();
    
    @Immutable
    Property<String> material();

    @Immutable
    Property<String> coreMaterial();
}
