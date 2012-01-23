package hogwarts.configuration;

import hogwarts.domain.WandFactory;
import hogwarts.domain.WizardFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    
    @Bean
    public WandFactory wandFactory () {
        return new WandFactory();
    }

    @Bean
    public WizardFactory wizardFactory() {
        return new WizardFactory();
    }
}
