package debugprovider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.service.provider.SystemsProvider;
import cern.mpe.systems.domain.mps.BeamInterlock;
import cern.mpe.systems.domain.mps.BeamLossMonitorCrate;
import cern.mpe.systems.domain.mps.FastMagnetCurrentChangeMonitor;
import cern.mpe.systems.domain.mps.PoweringInterlock;
import cern.mpe.systems.domain.mps.SafeMachineParameter;
import cern.mpe.systems.domain.mps.SisServer;
import cern.mpe.systems.domain.mps.SisSubtree;
import cern.mpe.systems.domain.mps.WarmMagnetInterlock;
import cern.mpe.systems.domain.mps.key.BeamInterlockKey;
import cern.mpe.systems.domain.mps.key.BeamLossMonitorCrateKey;
import cern.mpe.systems.domain.mps.key.FastMagnetCurrentChangeMonitorKey;
import cern.mpe.systems.domain.mps.key.PoweringInterlockKey;
import cern.mpe.systems.domain.mps.key.SafeMachineParameterKey;
import cern.mpe.systems.domain.mps.key.SisComponentKey;
import cern.mpe.systems.domain.mps.key.WarmMagnetInterlockKey;


/**
 * @author Lagache
 * Permit to return a random set of system for testing
 *
 */
public class RandomGenSystems implements SystemsProvider {
	
	public static int nbsys = 30;
	
    public Collection<SystemUnderTest> getAllSystems() {

            Random random = new Random();
            HashSet<SystemUnderTest> systemsUnderTest = new HashSet<>();
            for (int i=0; i<nbsys; i++) {
                    int choice = random.nextInt(8);
                    switch (choice) {
                    case 0 :
                            systemsUnderTest.add(new BeamInterlock(
                                            BeamInterlockKey.ofId((long)i),"BeamInterlock "+(i+1), Collections.emptySet()));
                            break;
                    case 1 :
                            systemsUnderTest.add(new BeamLossMonitorCrate(
                                            BeamLossMonitorCrateKey.ofId((long)i), "BeamLossMonitorCrate "+(i+1), Collections.emptySet()));
                            break;
                    case 2 :
                            systemsUnderTest.add(new FastMagnetCurrentChangeMonitor(
                                            FastMagnetCurrentChangeMonitorKey.ofId((long)i), "FastMagnetCurrentChangeMonitor "+(i+1), Collections.emptySet()));
                            break;
                    case 3 :
                            systemsUnderTest.add(new PoweringInterlock(
                                            PoweringInterlockKey.ofId((long)i), "PoweringInterlock "+(i+1), Collections.emptySet()));
                            break;
                    case 4 :
                            systemsUnderTest.add(new SafeMachineParameter(
                                            SafeMachineParameterKey.ofId((long)i),"SafeMachineParameter "+(i+1), Collections.emptySet()));
                            break;
                    case 5 :
                            systemsUnderTest.add(new SisServer(
                                            SisComponentKey.ofId((long)i),"SisServer "+(i+1), Collections.emptySet()));
                            break;
                    case 6 :
                            systemsUnderTest.add(new SisSubtree(
                                            SisComponentKey.ofId((long)i), "SisSubtree "+(i+1), Collections.emptySet()));
                            break;
                    case 7 :
                            systemsUnderTest.add(new WarmMagnetInterlock(
                                            WarmMagnetInterlockKey.ofId((long)i), "WarmMagnetInterlock "+(i+1), Collections.emptySet()));
                            break;
                    }

            }
            return systemsUnderTest;

    }
    
}
