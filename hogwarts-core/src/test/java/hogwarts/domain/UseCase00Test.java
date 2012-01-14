package hogwarts.domain;

import static hogwarts.domain.spell.Spells.getSpell;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hogwarts.domain.behavior.Caster;
import hogwarts.domain.spell.AvadaKedavra;
import hogwarts.domain.spell.Incantation;
import hogwarts.domain.spell.Spell;
import hogwarts.environment.Environment;
import hogwarts.util.Parameter.Value;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UseCase00Test {

    private Environment environment;
    private Incantation<AvadaKedavra> incantation;
    private Wizard voldemort;
    private Wizard harry;

    @Before
    @SuppressWarnings("unchecked")
    public void setup() {
        voldemort = mock(Wizard.class);
        harry = mock(Wizard.class);
        environment = mock(Environment.class);
        incantation = mock(Incantation.class);
        when(voldemort.incanteSpell(Mockito.any(Spell.class), (Value[]) Mockito.any()))
                .thenReturn(incantation);
        
    }

    @Test
    public void wizard_cast_spell() {
        Incantation<AvadaKedavra> incantation = 
                voldemort.incanteSpell(getSpell(AvadaKedavra.class), Caster.F.target(harry));
        incantation.performCast(environment);
    }
}
