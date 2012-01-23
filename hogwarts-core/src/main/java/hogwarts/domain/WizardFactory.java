package hogwarts.domain;

import hogwarts.domain.behavior.CasterMixin;
import hogwarts.domain.behavior.HasHitPoints;
import hogwarts.domain.behavior.HasWandMixin;
import hogwarts.domain.behavior.LivingCreatureMixin;
import hogwarts.domain.misc.HitPoints;
import hogwarts.util.HasBehaviorBasicMixin;

import org.qi4j.api.composite.TransientBuilder;
import org.qi4j.api.composite.TransientBuilderFactory;
import org.qi4j.api.composite.TransientComposite;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.SingletonAssembler;

public class WizardFactory {

    public interface WizardComposite extends Wizard, HasHitPoints, TransientComposite {
    }

    //

    private TransientBuilderFactory defaultTransientBuilderFactory;

    public Wizard create(String name) {
        TransientBuilder<WizardComposite> wizardBuilder = newDefaultWizardBuilder();
        WizardComposite prototype = wizardBuilder.prototype();
        prototype.name().set(name);
        prototype.spellBook().set(new SpellBook());
        prototype.hitPoints().set(new HitPoints(100));

        Wizard wizard = wizardBuilder.newInstance();
        return wizard;
    }

    private TransientBuilder<WizardComposite> newDefaultWizardBuilder() {
        if (defaultTransientBuilderFactory == null)
            defaultTransientBuilderFactory = initializeDefaultFactory();
        return defaultTransientBuilderFactory.newTransientBuilder(WizardComposite.class);
    }

    protected TransientBuilderFactory initializeDefaultFactory() {
        SingletonAssembler singletonAssembler = new SingletonAssembler() {
            @SuppressWarnings("unchecked")
            @Override
            public void assemble(ModuleAssembly module) throws AssemblyException {
                module.transients(WizardComposite.class)//
                        .withMixins(
                                CasterMixin.class, 
                                LivingCreatureMixin.class, 
                                HasWandMixin.class,
                                HasBehaviorBasicMixin.class);
            }
        };
        return singletonAssembler.transientBuilderFactory();
    }
}
