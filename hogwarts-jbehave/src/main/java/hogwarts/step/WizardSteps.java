package hogwarts.step;

import org.jbehave.core.annotations.Given;

public class WizardSteps {
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
    }
}
