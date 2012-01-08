package hogwarts.qi4j;

import org.qi4j.api.Qi4j;
import org.qi4j.api.service.ServiceComposite;
import org.qi4j.api.structure.Application;
import org.qi4j.api.structure.Application.Mode;
import org.qi4j.api.structure.Module;
import org.qi4j.bootstrap.ApplicationAssembler;
import org.qi4j.bootstrap.ApplicationAssembly;
import org.qi4j.bootstrap.ApplicationAssemblyFactory;
import org.qi4j.bootstrap.Assembler;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.Energy4Java;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.entitystore.memory.MemoryEntityStoreService;
import org.qi4j.spi.Qi4jSPI;
import org.qi4j.spi.structure.ApplicationModelSPI;
import org.qi4j.spi.structure.ApplicationSPI;
import org.qi4j.spi.uuid.UuidIdentityGeneratorService;

public abstract class EntityBootstrap {
    protected Qi4j api;
    protected Qi4jSPI spi;

    protected Energy4Java qi4j;
    protected ApplicationModelSPI applicationModel;
    protected ApplicationSPI application;
    private Mode mode;

    public EntityBootstrap() {
        this(Application.Mode.test);
    }
    
    public EntityBootstrap(Mode mode) {
        this.mode = mode;
    }

    public void start() throws Exception {
        qi4j = new Energy4Java();
        applicationModel = newApplication();
        if (applicationModel == null) {
            // An AssemblyException has occurred that the Test wants to check
            // for.
            return;
        }
        application = applicationModel.newInstance(qi4j.spi());
        initApplication(application);
        api = spi = qi4j.spi();
        application.activate();
    }
    
    public Application getApplication() {
        return application;
    }
    
    public Module getDefaultModule () {
        // Assume only one module
        return application.findModule( "Layer 1", "Module 1" );
    }

    protected ApplicationModelSPI newApplication() throws AssemblyException {
        ApplicationAssembler assembler = createApplicationAssembler();
        if (assembler == null) {
            assembler = createDefaultApplicationAssembler();
        }
        try {
            return qi4j.newApplicationModel(assembler);
        } catch (AssemblyException e) {
            assemblyException(e);
            return null;
        }
    }
    
    protected Class<? extends ServiceComposite> getEntityStoreService() {
        return MemoryEntityStoreService.class;
    }

    protected ApplicationAssembler createApplicationAssembler() {
        return null;
    }

    private ApplicationAssembler createDefaultApplicationAssembler() {
        return new ApplicationAssembler() {
            public ApplicationAssembly assemble(ApplicationAssemblyFactory applicationFactory) throws AssemblyException {
                return applicationFactory.newApplicationAssembly(new Assembler() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void assemble(ModuleAssembly module) throws AssemblyException {
                        module.layer().application().setMode(mode);
                        module.services( getEntityStoreService());
                        module.services( UuidIdentityGeneratorService.class );
                        EntityBootstrap.this.assemble(module);
                    }
                });
            }
        };
    }

    protected void assemble(ModuleAssembly module) throws AssemblyException {
    }

    /**
     * This method is called when there was an AssemblyException in the creation
     * of the Qi4j application model.
     * <p/>
     * Override this method to catch valid failures to place into satisfiedBy
     * suites.
     * 
     * @param exception
     *            the exception thrown.
     * @throws AssemblyException
     *             The default implementation of this method will simply
     *             re-throw the exception.
     */
    protected void assemblyException(AssemblyException exception) throws AssemblyException {
        throw exception;
    }

    protected void initApplication(Application app) {
    }
    
    public void shutdown() throws Exception {
        if (application != null) {
            application.passivate();
        }
    }
}
