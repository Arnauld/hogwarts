package hogwarts.util;

import org.qi4j.api.injection.scope.This;

import fj.data.Option;

public class HasBehaviorBasicMixin implements HasBehavior {

    private @This Object composite;
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> Option<T> as(Class<T> requiredType) {
        if(requiredType.isInstance(composite))
            return Option.some((T)composite);
        return Option.none();
    }
}
