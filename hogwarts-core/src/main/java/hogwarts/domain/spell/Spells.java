package hogwarts.domain.spell;

import fj.data.HashMap;
import fj.data.Option;
import hogwarts.domain.misc.Intensity;
import hogwarts.util.ReflectRuntimeException;

public class Spells {
    
    private static HashMap<Class<?>, Spell<?>> cache = HashMap.hashMap();

    @SuppressWarnings("unchecked")
    public static <T extends Spell<T>> Spell<T> getSpell(Class<T> spell) {
        Option<Spell<?>> option = cache.get(spell);
        if(option.isSome())
            return (Spell<T>) option.some();
        
        try {
            T instance = spell.newInstance();
            cache.set(spell, instance);
            return instance;
        } catch (InstantiationException e) {
            throw new ReflectRuntimeException("Cannot create spell <" + spell.getSimpleName() + ">", e);
        } catch (IllegalAccessException e) {
            throw new ReflectRuntimeException("Cannot create spell <" + spell.getSimpleName() + ">", e);
        }
    }
    
    public static Intensity defaultIntensityForLevel(SpellLevel level) {
        switch(level) {
            case Beginner: 
                return Intensity.valueOf(1, Intensity.Unit.Raw);
            case Novice: 
                return Intensity.valueOf(10, Intensity.Unit.Raw);
            case Expert: 
                return Intensity.valueOf(50, Intensity.Unit.Raw);
            case Master:
                return Intensity.valueOf(100, Intensity.Unit.Percent);
        }
        return Intensity.valueOf(0, Intensity.Unit.Raw);
    }
}
