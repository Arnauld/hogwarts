package hogwarts.qi4j;

import hogwarts.qi4j.domain.SpellBook;
import hogwarts.qi4j.domain.Wizard;

import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.injection.scope.Structure;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.unitofwork.UnitOfWorkFactory;

public class WizardEntityFactoryMixin implements WizardEntityFactory {

    @Structure UnitOfWorkFactory uowf;
    
    @Override
    public Wizard create(String name, SpellBook book) {
        UnitOfWork uow = uowf.currentUnitOfWork();
        EntityBuilder<Wizard> builder = uow.newEntityBuilder(Wizard.class);
        
        Wizard prototype = builder.instance();
        prototype.name().set(name);
        return builder.newInstance();
    }
}
