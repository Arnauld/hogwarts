package hogwarts.environment;

import java.io.PrintStream;

public class Environment {
    
    private PrintStream logStream = System.out;
    
    public EffectFactory getEffectFactory () {
        return new EffectFactory(this);
    }
    
    public void setLogStream(PrintStream logStream) {
        this.logStream = logStream;
    }
    
    public void log(String message) {
        logStream.println(message);
    }

}
