package hogwarts.environment;

public interface Animation {
    void animate(Environment environement);
    
    public static final Animation None = new Animation() {
        @Override
        public void animate(Environment environment) {
        }
    };
}
