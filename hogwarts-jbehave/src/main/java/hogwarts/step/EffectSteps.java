package hogwarts.step;

import static org.hamcrest.text.StringContains.containsString;
import hogwarts.story.Context;
import jbehave.Steps;

import org.hamcrest.MatcherAssert;
import org.jbehave.core.annotations.Then;

@Steps
public class EffectSteps {

    @Then("a spark should occur")
    public void sparkOccur() {
        String log = Context.get().getEnvironmentLog();
        MatcherAssert.assertThat(log, containsString("spark is pulsating at"));
    }
}
