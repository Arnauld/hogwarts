package hogwarts.domain.behavior;

import hogwarts.domain.Wand;
import fj.data.Option;

public abstract class HasWandMixin implements HasWand {


    @Override
    public Option<Wand> getWand() {
        Wand wand = wand().get();
        if(wand==null)
            return Option.none();
        else
            return Option.some(wand);
    }
}
