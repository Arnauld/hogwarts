package hogwarts.step;

import hogwarts.story.Context;

import java.util.Arrays;
import java.util.List;

import jbehave.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

@Steps
public class CastSpellSteps {
    /**
     * Define the target of any further action to be the specified entity.
     * 
     * Target is then set as current one and is available through several
     * aliases: target, a target, the target... 
     * 
     * The entity can be a wizard, a person or anything else such as an
     * elephant or a door... The entity is retrieved from the current 
     * context using the given name, and thus one must ensure it is defined
     * first unless it is a well known entity. If it is a well known entity, 
     * the entity is created (if needed) and put in the context.
     * 
     * @param entityName the name of the entity to define as target 
     *        for the next actions.
     */
    @Given("a target named $targetName")
    public void defineTarget(String targetName) {
        Context context = Context.get();
        Object named = context.getNamed(targetName);
        for(String variant : variantsOf("target"))
            context.put(variant, named);
    }

    private static List<String> variantsOf(String string) {
        return Arrays.asList(string,"a " + string, "the "+string);
    }

    /**
     * Bless <code>named</code> with the given spell.
     * @param named identifier of the person that is blessed
     * @param spellName the bless spell invoked
     */
    @Given("$named blessed with $spellName")
    public void bless(String targetName, String spellName) {}

    @When("the wizard cast the spell $spellName")
    public void cast(String spellName) {}

    @Given("$entityName that counterspells $spellFilterName")
    public void activateSpellShield(String entityName, String filterName) {}
}
