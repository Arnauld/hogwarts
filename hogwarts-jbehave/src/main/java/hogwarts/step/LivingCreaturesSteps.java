package hogwarts.step;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Then;

public class LivingCreaturesSteps {
    @Then("$livingName is alive")
    @Alias("$livingName is still alive")
    public void checkIsAlive(String livingName) {}

    @Then("$livingName is dead")
    @Alias("$livingName is suddenly dead")
    public void checkIsDied(String livingName) {}
}
