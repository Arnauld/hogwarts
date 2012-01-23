package hogwarts.domain;

import hogwarts.environment.Environment;
import hogwarts.environment.HasPosition;
import hogwarts.environment.effect.Spark;

import java.util.Collection;

public class WizardMeeting {

    private Collection<Wizard> wizards;
    private HasPosition hasPosition;
    
    public WizardMeeting(HasPosition hasPosition, Collection<Wizard> wizards) {
        this.wizards = wizards;
        this.hasPosition = hasPosition;
    }
    
    public Collection<Wizard> getWizards() {
        return wizards;
    }
    
    public void execute(Environment env) {
        int wandCount = 0;
        for(Wizard wiz : wizards) {
            if(wiz.getWand().isSome())
                wandCount++;
        }
        
        if(wandCount>=2) {
            Spark spark = env.getEffectFactory().createSpark();
            spark.makePulsating(hasPosition);
        }
    }
}
