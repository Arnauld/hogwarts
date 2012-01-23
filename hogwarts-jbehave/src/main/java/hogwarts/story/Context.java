package hogwarts.story;

import hogwarts.environment.Environment;
import hogwarts.step.NumericalAccessorKey;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;

public class Context {

    private static ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        protected Context initialValue() {
            return new Context();
        }
    };
    public static Context get () {
        return threadLocal.get();
    }
    
    private final ArrayListMultimap<Object, Object> transientData = ArrayListMultimap.create();
    private final Map<String,Object> namedObjects = new HashMap<String, Object>();
    private Environment environment;
    private ByteArrayOutputStream logBytesStream;
    
    protected Environment createEnvironment() {
        logBytesStream = new ByteArrayOutputStream(1024);
        PrintStream stream;
        try {
            stream = new PrintStream(logBytesStream, true, "UTF8");
        } catch (UnsupportedEncodingException e) {
            throw utf8NotSupported(e);
        }
        Environment environment = new Environment();
        environment.setLogStream(stream);
        return environment;
    }

    public <T> void put(Object key, T value) {
        transientData.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> Collection<T> get(Object key) {
        return (Collection<T>) transientData.get(key);
    }
    
    @SuppressWarnings("unchecked")
    public <T> Collection<T> get(Object key, Class<T> requiredType) {
        return (Collection<T>) transientData.get(key);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, NumericalAccessorKey accessorKey) {
        return (T) transientData.get(key).get(accessorKey.asIndex());
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, NumericalAccessorKey accessorKey, Class<T> requiredType) {
        return (T) transientData.get(key).get(accessorKey.asIndex());
    }

    public void setNamed(String name, Object what) {
        namedObjects.put(name, what);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getNamed(String name) {
        return (T)namedObjects.get(name);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getNamed(String name, Class<T> requiredType) {
        return (T)namedObjects.get(name);
    }

    public Environment getEnvironment() {
        if(environment==null)
            environment = createEnvironment();
        return environment;
    }

    public String getEnvironmentLog() {
        if(logBytesStream!=null) {
            try {
                return logBytesStream.toString("UTF8");
            } catch (UnsupportedEncodingException e) {
                throw utf8NotSupported(e);
            }
        }
        return null;
    }

    private static RuntimeException utf8NotSupported(UnsupportedEncodingException e) {
        return new RuntimeException("UTF8 is not supported... we are in trouble!", e);
    }

}
