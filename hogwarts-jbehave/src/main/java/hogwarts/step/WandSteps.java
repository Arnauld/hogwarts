package hogwarts.step;

import hogwarts.domain.Wand;
import hogwarts.domain.WandFactory;
import hogwarts.domain.behavior.HasWand;
import hogwarts.story.Context;
import hogwarts.story.Key;
import jbehave.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Steps
public class WandSteps {
    
    private static Logger logger = LoggerFactory.getLogger(WandSteps.class);
    
    @Autowired
    private WandFactory wandFactory;
    
    /**
     * Ollivander is widely considered the best wandmaker in the world, 
     * and many wizards and witches buy their wands from him.
     * 
     * @param length length of the wand
     * @param material material of the wand
     * @param coreMaterial material used as core of the wand
     */
    @Given("a wand $length\" long made of $material, with $core core")
    public void ollivanderCraftsWand(int length, String material, String coreMaterial) {
        logger.debug("Creating wand {}\" long made of {}, with {} core", new Object[] { length, material, coreMaterial});
        Wand wand = wandFactory.create(length, material, coreMaterial);
        Context.get().put(Key.Wand, wand);
    }
    
    /**
     * Ollivander is widely considered the best wandmaker in the world, 
     * and many wizards and witches buy their wands from him.
     * 
     * @param key is a numerical accessor indicating the n-th wand defined. Accepted values
     *    are first, second, third, fourth, fifth, sixth, seventh, eighth, ninth and tenth.
     * @param who the person in the context to whom the wand will be assigned.
     * @see NumericalAccessorKey
     */
    @When("Ollivander gives the $accessorKey wand to $who")
    public void ollivanderAssignsWand(String key, String who) {
        NumericalAccessorKey accessorKey = NumericalAccessorKey.lookupIgnoringCase(key);
        if(accessorKey==null)
            throw new IllegalArgumentException("Not recognized numerical accessor key <" + key + ">");
        
        Wand wand = Context.get().get(Key.Wand, accessorKey);
        HasWand hasWand = Context.get().getNamed(who);
        hasWand.wand().set(wand);
    }
}
