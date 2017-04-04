package debugprovider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.domain.relation.SystemRelation;
import cern.mpe.systems.core.service.manage.SystemsManager;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemRelationProvider;
import other.BasicSystemRelation;

/**
 * @author Lagache
 * A relation provider to return a random set of system relations for testing
 *
 */
public class RandomGenRelations implements SystemRelationProvider{
	
	public static int nbRel = 50;
	
	public Collection<SystemRelation> relations;
	public int nbRelcorrect;
	
	/**
	 * @param systemManager
	 * Generate random generation between the system of the given system manager
	 * 
	 */
	public void genAllRelations(SystemsManagerImpl systemManager) {
        Random random = new Random();
        relations = new HashSet<>();
        Collection<SystemUnderTest> systems = systemManager.getAllSystemsUnderTest();
        
        if(nbRel>systems.size()*systems.size()-systems.size())
        	nbRelcorrect = systems.size()*systems.size()-systems.size();
        else
        	nbRelcorrect = nbRel;
        
        while (relations.size() < nbRelcorrect) {
        	SystemRelation newRel = null;
    		newRel = null;
        	int id1 = random.nextInt(systemManager.getAllSystemsUnderTest().size());
        	int id2 = random.nextInt(systemManager.getAllSystemsUnderTest().size());
        	while(id2 == id1)
        		id2 = random.nextInt(systemManager.getAllSystemsUnderTest().size());
        	newRel = new BasicSystemRelation((SystemUnderTest)systems.toArray()[id1],(SystemUnderTest)systems.toArray()[id2]);
        	relations.add((SystemRelation)newRel);
        }
    }
	
	
    @Override
    public Collection<SystemRelation> getAllSystemRelations(SystemsManager systemsManager) {
        Collection<SystemRelation> relationsToReturn = new HashSet<>();
        for (SystemUnderTest systemUnderTest : systemsManager.getAllSystemsUnderTest()) {
        	Collection<SystemRelation> findedRelations = findRelations(systemUnderTest);
            relationsToReturn.addAll(findedRelations);
        }
        return relationsToReturn;
    }

    private Collection<SystemRelation> findRelations(SystemUnderTest system) {
    	Collection<SystemRelation> findedRelations = new HashSet<>();
        for (SystemRelation relation : relations) {
            if (relation.getSource().equals(system) || relation.getTarget().equals(system)) {
                findedRelations.add(relation);
            }
        }
        return findedRelations;
    }

}
