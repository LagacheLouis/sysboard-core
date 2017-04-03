package debugprovidertest;

import static org.junit.Assert.*;

import java.util.Collection;
import org.junit.Test;
import cern.mpe.systems.core.domain.SystemUnderTest;
import debugprovider.RandomGenSystems;

public class RandomGenSystemsTest {

	@Test
	public void getAllSystems() {
		RandomGenSystems testgen = new RandomGenSystems();
		Collection<SystemUnderTest> list = testgen.getAllSystems();
		assertTrue(list.size()==RandomGenSystems.nbsys);
	}

}
