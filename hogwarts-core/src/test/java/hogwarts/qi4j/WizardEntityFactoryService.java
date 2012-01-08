package hogwarts.qi4j;

import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.service.ServiceComposite;

@Mixins({WizardEntityFactoryMixin.class})
public interface WizardEntityFactoryService extends WizardEntityFactory, ServiceComposite {

}
