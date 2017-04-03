package other;

import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.domain.relation.AbstractSystemRelation;
import cern.mpe.systems.core.domain.relation.UnspecifiedSystemRelation;

/**
 * A basic oriented relation
 * @author Lagache
 *
 */
public class BasicSystemRelation extends AbstractSystemRelation {

private static final long serialVersionUID = 1L;

/**
* Constructs an instance of {@link UnspecifiedSystemRelation}
* 
* @param source the source {@link SystemUnderTest}
* @param target the target {@link SystemUnderTest}
*/
public BasicSystemRelation(SystemUnderTest source, SystemUnderTest target) {
super(source, target);
}

}