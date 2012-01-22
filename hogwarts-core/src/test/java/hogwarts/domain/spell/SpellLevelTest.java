package hogwarts.domain.spell;

import static hogwarts.domain.spell.SpellLevel.Beginner;
import static hogwarts.domain.spell.SpellLevel.Expert;
import static hogwarts.domain.spell.SpellLevel.Master;
import static hogwarts.domain.spell.SpellLevel.Novice;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class SpellLevelTest {

    @Test
    public void isHigherThan_Beginner() {
        assertThat(Beginner.isHigherThan(Beginner), is(false));
        assertThat(Beginner.isHigherThan(Novice), is(false));
        assertThat(Beginner.isHigherThan(Expert), is(false));
        assertThat(Beginner.isHigherThan(Master), is(false));
    }
    @Test
    public void isHigherThan_Novice() {
        assertThat(Novice.isHigherThan(Beginner), is(true));
        assertThat(Novice.isHigherThan(Novice), is(false));
        assertThat(Novice.isHigherThan(Expert), is(false));
        assertThat(Novice.isHigherThan(Master), is(false));
    }
    @Test
    public void isHigherThan_Expert() {
        assertThat(Expert.isHigherThan(Beginner), is(true));
        assertThat(Expert.isHigherThan(Novice), is(true));
        assertThat(Expert.isHigherThan(Expert), is(false));
        assertThat(Expert.isHigherThan(Master), is(false));
    }
    @Test
    public void isHigherThan_Master() {
        assertThat(Master.isHigherThan(Beginner), is(true));
        assertThat(Master.isHigherThan(Novice), is(true));
        assertThat(Master.isHigherThan(Expert), is(true));
        assertThat(Master.isHigherThan(Master), is(false));
    }
}
