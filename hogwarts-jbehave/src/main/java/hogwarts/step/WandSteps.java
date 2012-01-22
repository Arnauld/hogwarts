package hogwarts.step;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

public class WandSteps {

    @Given("a wand $length\" long made of $material, with $core core")
    public void defineWand(int length, String material, String coreMaterial) {
        
    }
    
    /**
     * Ollivander was widely considered the best wandmaker in the world, 
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
    }
}
