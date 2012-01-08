package hogwarts.util;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import fj.Effect;
import fj.F;
import hogwarts.environment.Color;

import org.junit.Before;
import org.junit.Test;

public class MethodsTest {

    private SomeBehavior behavior;

    public interface SomeBehavior {
        void markIt();

        void colorize(Color color);

        void electrize(int powerIntensity);
    }

    @Before
    public void setup() {
        behavior = mock(SomeBehavior.class);
    }

    @Test
    public void asEffect_valid_method() {
        Effect<SomeBehavior> effect = Methods.asEffect(SomeBehavior.class, "markIt");
        effect.e(behavior);
        verify(behavior).markIt();
        verifyNoMoreInteractions(behavior);
    }
    
    @Test(expected=ReflectRuntimeException.class)
    public void asEffect_missing_method() {
        Methods.asEffect(SomeBehavior.class, "unmarkIt");
        fail("An exception should have been raised");
    }
    
    @Test(expected=ReflectRuntimeException.class)
    public void asEffect_invalid_method_argument() {
        Methods.asEffect(SomeBehavior.class, "colorize");
        fail("An exception should have been raised");
    }
    
    @Test
    public void asEffectF_valid_method() {
        F<Color,Effect<SomeBehavior>> effectF = Methods.asEffectF(SomeBehavior.class, "colorize", Color.class);
        effectF.f(Color.Green).e(behavior);
        verify(behavior).colorize(Color.Green);
        verifyNoMoreInteractions(behavior);
    }
    
    @Test
    public void asEffectF_valid_method_ex2() {
        F<Integer,Effect<SomeBehavior>> effectF = Methods.asEffectF(SomeBehavior.class, "electrize", Integer.class);
        effectF.f(14).e(behavior);
        verify(behavior).electrize(14);
        verifyNoMoreInteractions(behavior);
    }

    @Test(expected=ReflectRuntimeException.class)
    public void asEffectF_missing_method() {
        Methods.asEffectF(SomeBehavior.class, "unmarkIt", String.class);
        fail("An exception should have been raised");
    }
    
    @Test(expected=ReflectRuntimeException.class)
    public void asEffectF_invalid_method_argument() {
        Methods.asEffectF(SomeBehavior.class, "colorize", Integer.class);
        fail("An exception should have been raised");
    }
}
