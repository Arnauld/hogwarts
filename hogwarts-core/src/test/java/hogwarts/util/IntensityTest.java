package hogwarts.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import hogwarts.domain.Intensity;

import org.junit.Test;

public class IntensityTest {

    @Test
    public void valueOf() {
        for(int i=-100;i<+101;i++) {
            assertThat(Intensity.valueOf(i).getValue(), equalTo(i));
        }
    }
}
