package hogwarts.domain.behavior;

import hogwarts.domain.misc.Intensity;

import org.qi4j.api.injection.scope.This;

public class LivingCreatureMixin implements LivingCreature {
    
    private @This HasHitPoints hasHitPoints;
    
    @Override
    public void die() {
        hasHitPoints.hitPoints().get().set(0);
    }
    
    @Override
    public void heal(Intensity intensity) {
        hasHitPoints.hitPoints().get().modify(intensity.getValue());
    }
    
    @Override
    public boolean isAlive() {
        return hasHitPoints.hitPoints().get().isPositive();
    }
    
}
