package hogwarts.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class WandFactoryTest {
    
    @Test
    public void create() {
        WandFactory factory = new WandFactory();
        Wand wand = factory.create(11, "holly wood", "phoenix feather");
        assertThat(wand, notNullValue());
        assertThat(wand.length().get(), equalTo(11));
        assertThat(wand.material().get(), equalTo("holly wood"));
        assertThat(wand.coreMaterial().get(), equalTo("phoenix feather"));
    }
}
