package hogwarts.domain;

import hogwarts.util.HasBehaviorBasicMixin;

import org.qi4j.api.composite.TransientBuilder;
import org.qi4j.api.composite.TransientBuilderFactory;
import org.qi4j.api.composite.TransientComposite;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.SingletonAssembler;

public class WandFactory {
    public interface WandComposite extends Wand, TransientComposite {
    }

    private TransientBuilderFactory defaultTransientBuilderFactory;

    public Wand create(int length, String material, String coreMaterial) {
        TransientBuilder<WandComposite> wandBuilder = newWandBuilder();
        WandComposite prototype = wandBuilder.prototype();
        prototype.length().set(length);
        prototype.material().set(material);
        prototype.coreMaterial().set(coreMaterial);
        
        Wand wand = wandBuilder.newInstance();
        return wand;
    }

    private TransientBuilder<WandComposite> newWandBuilder() {
        if (defaultTransientBuilderFactory == null)
            defaultTransientBuilderFactory = initializeDefaultFactory();
        return defaultTransientBuilderFactory.newTransientBuilder(WandComposite.class);
    }

    protected TransientBuilderFactory initializeDefaultFactory() {
        SingletonAssembler singletonAssembler = new SingletonAssembler() {
            @SuppressWarnings("unchecked")
            @Override
            public void assemble(ModuleAssembly module) throws AssemblyException {
                module.transients(WandComposite.class)//
                        .withMixins(
                                HasBehaviorBasicMixin.class);
            }
        };
        return singletonAssembler.transientBuilderFactory();
    }
}
