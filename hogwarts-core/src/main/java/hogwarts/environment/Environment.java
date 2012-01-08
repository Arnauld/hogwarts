package hogwarts.environment;




public class Environment {
    
    public EffectFactory getEffectFactory () {
        return new EffectFactory(this);
    }
    
    public void log(String message) {
        System.out.println(message);
    }
}
