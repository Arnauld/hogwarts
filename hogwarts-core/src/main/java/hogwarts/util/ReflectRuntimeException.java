package hogwarts.util;

public class ReflectRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 4835080893735904637L;

    public ReflectRuntimeException() {
        super();
    }

    public ReflectRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectRuntimeException(String message) {
        super(message);
    }

    public ReflectRuntimeException(Throwable cause) {
        super(cause);
    }

}
