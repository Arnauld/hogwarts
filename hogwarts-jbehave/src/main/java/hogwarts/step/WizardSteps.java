package hogwarts.step;

import java.util.Collection;

import hogwarts.domain.Wizard;
import hogwarts.domain.WizardFactory;
import hogwarts.domain.WizardMeeting;
import hogwarts.environment.Environment;
import hogwarts.environment.HasPosition;
import hogwarts.environment.Position;
import hogwarts.story.Context;
import hogwarts.story.Key;
import jbehave.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;

@Steps
public class WizardSteps {
    
    @Autowired
    private WizardFactory wizardFactory;
    
    /**
     * Create a new wizard with the given name in the current context.
     * 
     * Wizard is then set as current one and is available through several 
     * aliases: wizard, a wizard, the wizard... 
     * 
     * The Wizard's name can be used afterward to retrieve the wizard
     * from the context. Well known wizards such as Lord Voldemort,
     * Harry Potter, Hermione are created with predefined capabilities
     * and known spells.
     * 
     * @param wizardName the name of the wizard to be created, can be 
     *        one of the well known ones
     */
    @Given("a wizard named $wizardName")
    public void defineCurrentWizard(String wizardName) {
        Wizard wizard = wizardFactory.create(wizardName);
        Context.get().put(Key.Wizard, wizard);
        Context.get().setNamed(wizardName, wizard);
    }
    
    /**
     * All wizards currently defined in context meet together.
     * Sometimes, it happens that magical things are triggered during such
     * meeting due to the high concentration of magic.
     */
    @When("the wizards meet")
    public void wizardsMeetings() {
        Collection<Wizard> collection = Context.get().get(Key.Wizard);
        HasPosition hasPosition = new HasPosition() {
            @Override
            public Position getPosition(Environment env) {
                return new Position() {
                    @Override
                    public String getPositionLabel() {
                        return "Quidditch World Cup";
                    }
                };
            }
        };
        WizardMeeting meeting = new WizardMeeting(hasPosition, collection);
        meeting.execute(Context.get().getEnvironment());
    }
}
