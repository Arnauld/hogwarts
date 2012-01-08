package hogwarts.util;

import fj.data.Option;

public interface HasBehavior {
    <T> Option<T> as(Class<T> requiredType);
}
