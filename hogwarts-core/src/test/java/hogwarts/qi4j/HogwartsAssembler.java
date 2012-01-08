package hogwarts.qi4j;

import org.qi4j.api.entity.association.AssociationMixin;
import org.qi4j.api.property.PropertyMixin;
import org.qi4j.bootstrap.Assembler;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;

public class HogwartsAssembler implements Assembler {

    @SuppressWarnings("unchecked")
    @Override
    public void assemble(ModuleAssembly module) throws AssemblyException {
        module.entities(SpellBookEntity.class).withMixins(
                PropertyMixin.class, AssociationMixin.class);
    }
}
