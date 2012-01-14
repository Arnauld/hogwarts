package hogwarts.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class New {

    public static <T> HashSet<T> hashSet(T...values) {
        HashSet<T> set = new HashSet<T>();
        for(T value : values)
            set.add(value);
        return set;
    }

    public static <T> Set<T> immutableSet(T...values) {
        return Collections.unmodifiableSet(hashSet(values));
    }

}
