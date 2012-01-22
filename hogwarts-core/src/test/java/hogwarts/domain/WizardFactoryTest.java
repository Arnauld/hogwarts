package hogwarts.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import hogwarts.domain.spell.Lumos;

import org.junit.Test;

public class WizardFactoryTest {

    @Test
    public void create() {
        WizardFactory factory = new WizardFactory();
        Wizard wizard = factory.create("Ron");
        assertThat(wizard, notNullValue());
        assertThat(wizard.name().get(), equalTo("Ron"));
        assertThat(wizard.isAlive(), is(true));
        assertThat(wizard.getWand().isNone(), is(true));
        assertThat(wizard.spellBook().get(), notNullValue());
        try {
            wizard.incanteSpell(new Lumos());
            fail("By default wizard has an empty spellbook");
        }
        catch(Exception e) {
            assertThat(e.getMessage(), equalTo("Attempt to cast an unknown spell: Lumos"));
        }
    }
}
