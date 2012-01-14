package hogwarts.domain.misc;

import org.qi4j.api.property.Immutable;

public interface Damage {

    @Immutable
    DamageTypeProperty damageType();
    
    @Immutable
    IntensityProperty intensity();

}
