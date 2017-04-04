package debugprovidertest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;

import cern.mpe.systems.core.domain.relation.SystemRelation;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemsProvider;
import debugprovider.RandomGenRelations;
import debugprovider.RandomGenSystems;

public class RandomGenRelationsTest {

	@Test
	public void genAllRelations() {
		RandomGenSystems test = new RandomGenSystems();
		Collection<SystemsProvider> systemProviders = new ArrayList<SystemsProvider>();
		systemProviders.add(test);
		SystemsManagerImpl systemsManager = new SystemsManagerImpl();
		systemsManager.setSystemsProviders(systemProviders);
		systemsManager.init();
		
		RandomGenRelations randGen = new RandomGenRelations();
		randGen.genAllRelations(systemsManager);
		int i = 0;
		for(SystemRelation rel : randGen.relations){
			i++;
			System.out.println(i+" "+rel);
		}
		assertTrue(randGen.relations.size() == RandomGenRelations.nbRel);
	}

}
