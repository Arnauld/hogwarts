package hogwarts.domain.spell;

import fj.data.HashMap;
import fj.data.Option;
import hogwarts.domain.Spell;
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
}
