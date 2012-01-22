package hogwarts.step;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;

public class DarkMagicSteps {
    @Given("$personName has $quantity horcruxes")
    @Alias("$personName has $quantity horcrux")
    public void associateHorcrux(String personName, int quantity) {}
}
