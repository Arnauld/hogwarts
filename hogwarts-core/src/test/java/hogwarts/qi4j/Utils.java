package hogwarts.qi4j;

import org.qi4j.bootstrap.Assembler;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.SingletonAssembler;

public class Utils {
    public static SingletonAssembler asSingletonAssembler(final Assembler assembler) {
        return new SingletonAssembler() {
            @Override
            public void assemble(ModuleAssembly assembly) throws AssemblyException {
                assembler.assemble(assembly);
            }
        };

    }
}
