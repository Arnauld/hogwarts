package hogwarts.domain.behavior;

import hogwarts.domain.misc.HitPointsProperty;

import org.qi4j.api.property.Immutable;

public interface HasHitPoints {

    @Immutable
    HitPointsProperty hitPoints();
}
