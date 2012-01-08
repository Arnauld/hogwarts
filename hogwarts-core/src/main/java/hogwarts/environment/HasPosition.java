package hogwarts.environment;

public interface HasPosition {
    
    /**
     * Due to magical and parallel world it is possible for a same object
     * to have different positions depending on the environment.
     * 
     * @param environment
     * @return
     */
    Position getPosition(Environment env);
}
