package hogwarts.domain;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.List;

public enum KnownWizard {
    LordVoldemort("Lord Voldemort", "Voldemort", "You-Know-Who", "He-Who-Must-Not-Be-Named", "the Dark Lord"),
    LilyPotter("Lily Potter", "Harry's mum"),
    HarryPotter("Harry Potter", "Harry"),
    HermioneGranger("Hermione Granger", "Hermione", "Miss Granger");
    
    private List<String> aliases;
    private KnownWizard(String...aliases) {
        this.aliases = unmodifiableList(asList(aliases));
    }
    
    public List<String> aliases() {
        return aliases;
    }
    
    public boolean matchesAlias(String alias) {
        return aliases.contains(alias);
    }
    
    public static KnownWizard lookupByAlias(String alias) {
        for(KnownWizard wizard : values()) {
            if(wizard.matchesAlias(alias))
                return wizard;
        }
        return null;
    }
}
