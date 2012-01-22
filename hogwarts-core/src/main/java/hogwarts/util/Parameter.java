package hogwarts.util;

public class Parameter<T> {
    
    public static <T> Parameter<T> create(String name, Class<T> type) {
        return new Parameter<T>(name, type);
    }
    
    private final String name;
    private final Class<T> type;
    
    public Parameter(String name, Class<T> type) {
        super();
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public Class<T> getType() {
        return type;
    }

    public Value newValue(T value) {
        return new Value(value);
    }


    public class Value {
        public final T value;

        public Value(T value) {
            super();
            this.value = value;
        }
        public Parameter<T> getParameter() {
            return Parameter.this;
        }
    }

}
