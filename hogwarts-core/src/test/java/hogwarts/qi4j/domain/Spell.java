package hogwarts.qi4j.domain;

import org.qi4j.api.value.Value;

import hogwarts.qi4j.behavior.HasName;
import hogwarts.qi4j.behavior.HasPower;

public interface Spell extends HasName, HasPower, Value {

}
