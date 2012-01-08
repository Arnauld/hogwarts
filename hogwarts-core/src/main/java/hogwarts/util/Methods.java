package hogwarts.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fj.Effect;
import fj.F;

public class Methods {

    public static <T, ARG> F<ARG, Effect<T>> asEffectF(Class<T> clazz, String methodName, Class<ARG> arg) {
        try {
            final Method method = clazz.getMethod(methodName, arg);
            return new F<ARG, Effect<T>>() {
                public fj.Effect<T> f(final ARG arg) {
                    return new Effect<T>() {
                        public void e(T a) {
                            try {
                                method.invoke(a, arg);
                            } catch (IllegalArgumentException e) {
                                throw new ReflectRuntimeException("Error during <" + method + "> invocation", e);
                            } catch (IllegalAccessException e) {
                                throw new ReflectRuntimeException("Error during <" + method + "> invocation", e);
                            } catch (InvocationTargetException e) {
                                throw new ReflectRuntimeException("Error during <" + method + "> invocation", e);
                            }
                        };
                    };
                }
            };
        } catch (SecurityException e) {
            throw new ReflectRuntimeException("Error during <" + methodName + "> retrieval", e);
        } catch (NoSuchMethodException e) {
            throw new ReflectRuntimeException("Error during <" + methodName + "> retrieval", e);
        }
    }

    public static <T> Effect<T> asEffect(Class<T> clazz, String methodName) {
        try {
            final Method method = clazz.getMethod(methodName);
            return new Effect<T>() {
                public void e(T a) {
                    try {
                        method.invoke(a);
                    } catch (IllegalArgumentException e) {
                        throw new ReflectRuntimeException("Error during <" + method + "> invocation", e);
                    } catch (IllegalAccessException e) {
                        throw new ReflectRuntimeException("Error during <" + method + "> invocation", e);
                    } catch (InvocationTargetException e) {
                        throw new ReflectRuntimeException("Error during <" + method + "> invocation", e);
                    }
                };
            };
        } catch (SecurityException e) {
            throw new ReflectRuntimeException("Error during <" + methodName + "> retrieval", e);
        } catch (NoSuchMethodException e) {
            throw new ReflectRuntimeException("Error during <" + methodName + "> retrieval", e);
        }
    }
}
