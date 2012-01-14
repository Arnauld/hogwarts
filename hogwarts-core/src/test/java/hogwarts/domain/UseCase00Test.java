package hogwarts.domain;

import static hogwarts.domain.spell.Spells.getSpell;
import static org.mockito.Mockito.mock;
import hogwarts.domain.behavior.Caster;
import hogwarts.domain.spell.AvadaKedavra;
import hogwarts.domain.spell.Incantation;
import hogwarts.environment.Environment;

import org.junit.Before;
import org.junit.Test;

public class UseCase00Test {

    private Environment environment;

    @Before
    public void setup() {
        environment = mock(Environment.class);
    }

    @Test
    public void wizard_cast_spell() {
        Wizard voldemort = mock(Wizard.class);
        Wizard harry = mock(Wizard.class);
        
        Incantation<AvadaKedavra> incantation = 
                voldemort.incanteSpell(getSpell(AvadaKedavra.class), Caster.F.target(harry));
        //incantation.performCast(environment);
    }
}
