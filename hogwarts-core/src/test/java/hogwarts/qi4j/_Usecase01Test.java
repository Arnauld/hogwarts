package hogwarts.qi4j;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.StringContains.containsString;
import static org.junit.Assert.fail;
import fj.data.Option;
import hogwarts.qi4j.domain.Spell;
import hogwarts.qi4j.domain.SpellBook;

import java.util.ArrayList;

import org.junit.Test;
import org.qi4j.api.composite.TransientBuilder;
import org.qi4j.api.composite.TransientBuilderFactory;
import org.qi4j.api.composite.TransientComposite;
import org.qi4j.api.entity.EntityBuilder;
import org.qi4j.api.property.Property;
import org.qi4j.api.structure.Module;
import org.qi4j.api.unitofwork.UnitOfWork;
import org.qi4j.api.value.ValueBuilder;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.SingletonAssembler;

public class _Usecase01Test {

    @Test
    public void case_voyage() {
        SingletonAssembler singletonAssembler = new SingletonAssembler() {
            @SuppressWarnings("unchecked")
            @Override
            public void assemble(ModuleAssembly module) throws AssemblyException {
                module.transients(VoyageComposite.class);
            }
        };
        TransientBuilderFactory factory = singletonAssembler.transientBuilderFactory();

        TransientBuilder<VoyageComposite> voyageBuilder = factory.newTransientBuilder(VoyageComposite.class);
        VoyageComposite prototype = voyageBuilder.prototype();
        prototype.bookedCargoSize().set(12.0);
        prototype.capacity().set(37.0);
        
        Voyage voyage = voyageBuilder.newInstance();
        assertThat(voyage, notNullValue());
    }
    
    public interface Voyage {
        Property<Double> capacity();
        Property<Double> bookedCargoSize();
    }

    public interface VoyageComposite extends Voyage, TransientComposite {
    }
    
    @Test
    public void case_spell () throws Exception {
        EntityBootstrap bootstrap = new EntityBootstrap() {
            @SuppressWarnings("unchecked")
            @Override
            protected void assemble(ModuleAssembly module) throws AssemblyException {
                module.entities(SpellBookEntity.class);
                module.values(SpellValue.class);
            }
        };
        bootstrap.start();
        Module module = bootstrap.getDefaultModule();
        
        ValueBuilder<Spell> valueBuilder = module.valueBuilderFactory().newValueBuilder(Spell.class);
        Spell prototype = valueBuilder.prototype();
        prototype.name().set("Expelliarmus");
        prototype.power().set(23);
        
        Spell expelliarmus = valueBuilder.newInstance();
        assertThat(expelliarmus.name().get(), equalTo("Expelliarmus"));
        assertThat(expelliarmus.power().get(), equalTo(23));
        
        try {
            expelliarmus.name().set("bouhh");
            fail("Name should be immmutable");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage(), containsString("immutable"));
        }
    }
    
    @Test
    public void case_spellbook () throws Exception {
        EntityBootstrap bootstrap = new EntityBootstrap() {
            @SuppressWarnings("unchecked")
            @Override
            protected void assemble(ModuleAssembly module) throws AssemblyException {
                module.entities(SpellBookEntity.class);
                module.values(SpellValue.class);
            }
        };
        bootstrap.start();
        Module module = bootstrap.getDefaultModule();
        UnitOfWork uow = module.unitOfWorkFactory().newUnitOfWork();
        EntityBuilder<SpellBookEntity> entityBuilder = uow.newEntityBuilder(SpellBookEntity.class);
        entityBuilder.instance().spells().set(new ArrayList<Spell>());
        
        SpellBook spellBook = entityBuilder.newInstance();
        assertThat(spellBook, notNullValue());
        
        ValueBuilder<Spell> valueBuilder = module.valueBuilderFactory().newValueBuilder(Spell.class);
        Spell prototype = valueBuilder.prototype();
        prototype.name().set("Expelliarmus");
        prototype.power().set(23);
        
        Spell expelliarmus = valueBuilder.newInstance();
        assertThat(expelliarmus, notNullValue());
        assertThat(expelliarmus.name().get(), equalTo("Expelliarmus"));
        assertThat(expelliarmus.power().get(), equalTo(23));
        
        spellBook.spells().get().add(expelliarmus);
        Option<Spell> spellOpt = spellBook.findSpell("Expelliarmus");
        assertThat(spellOpt, notNullValue());
        assertThat(spellOpt.isSome(), is(true));
        assertThat(spellOpt.some(), equalTo(expelliarmus));
        
    }

}
