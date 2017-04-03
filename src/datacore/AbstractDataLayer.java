package datacore;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import cern.mpe.systems.core.domain.SystemUnderTest;
import cern.mpe.systems.core.domain.relation.SystemRelation;
import cern.mpe.systems.core.service.control.SystemRelationControllerImpl;
import cern.mpe.systems.core.service.control.SystemsControllerImpl;
import cern.mpe.systems.core.service.manage.SystemRelationEngine;
import cern.mpe.systems.core.service.manage.SystemRelationManagerImpl;
import cern.mpe.systems.core.service.manage.SystemsManagerImpl;
import cern.mpe.systems.core.service.provider.SystemRelationProvider;
import cern.mpe.systems.core.service.provider.SystemsProvider;
import filters.Filter;
/**
 * abstract class you need to implement for making a DataLayer
 * DataLayer permit to make initialize all the you need to load data from providers
 * @author Lagache
 */
public abstract class AbstractDataLayer {
	
	protected boolean isFirstLaunch;
    protected SystemsControllerImpl systemController;
    protected SystemRelationControllerImpl relationController;
    protected SystemRelationEngine relationEngine;
	protected SystemsManagerImpl systemsManager;
	protected SystemRelationManagerImpl relationManager;
	protected Collection<SystemsProvider> systemProviders;
	protected List<SystemRelationProvider> relationProviders;
	protected Collection<SystemUnderTest> systemsUnderTest;
	protected Collection<SystemRelation> relationsUnderTest;
	protected Collection<Filter> filters;
	
	public boolean isFirstLaunch() {
		return isFirstLaunch;
	}

	public SystemsControllerImpl getSystemController() {
		return systemController;
	}

	public SystemRelationControllerImpl getRelationController() {
		return relationController;
	}

	public SystemRelationEngine getRelationEngine() {
		return relationEngine;
	}

	public SystemsManagerImpl getSystemsManager() {
		return systemsManager;
	}

	public SystemRelationManagerImpl getRelationManager() {
		return relationManager;
	}

	public Collection<SystemsProvider> getSystemProviders() {
		return systemProviders;
	}

	public List<SystemRelationProvider> getRelationProviders() {
		return relationProviders;
	}

	public Collection<SystemUnderTest> getSystemsUnderTest() {
		return systemsUnderTest;
	}

	public Collection<SystemRelation> getRelationsUnderTest() {
		return relationsUnderTest;
	}

	public Collection<Filter> getFilters() {
		return filters;
	}

	public void setFirstLaunch(boolean isFirstLaunch) {
		this.isFirstLaunch = isFirstLaunch;
	}

	public void setSystemController(SystemsControllerImpl systemController) {
		this.systemController = systemController;
	}

	public void setRelationController(SystemRelationControllerImpl relationController) {
		this.relationController = relationController;
	}

	public void setRelationEngine(SystemRelationEngine relationEngine) {
		this.relationEngine = relationEngine;
	}

	public void setSystemsManager(SystemsManagerImpl systemsManager) {
		this.systemsManager = systemsManager;
	}

	public void setRelationManager(SystemRelationManagerImpl relationManager) {
		this.relationManager = relationManager;
	}

	public void setSystemProviders(Collection<SystemsProvider> systemProviders) {
		this.systemProviders = systemProviders;
	}

	public void setRelationProviders(List<SystemRelationProvider> relationProviders) {
		this.relationProviders = relationProviders;
	}

	public void setSystemsUnderTest(Collection<SystemUnderTest> systemsUnderTest) {
		this.systemsUnderTest = systemsUnderTest;
	}

	public void setRelationsUnderTest(Collection<SystemRelation> relationsUnderTest) {
		this.relationsUnderTest = relationsUnderTest;
	}
	
	
	public void setFilters(Collection<Filter> filters) {
		this.filters = filters;
	}

	/**
	 * Instantiate here {@link SystemsManager} and {@link SystemsProvider}
	 */
	protected abstract void setUpSystems();
	
	/**
	 * Instantiate here {@link SystemRelationEngine} and {@link SystemRelationProvider}
	 */
	protected abstract void setUpSystemRelations();
	
	/**
	 * Instantiate here {@link filters} 
	 */
	protected abstract void setUpFilters();

	
	/**
	 * Call all the other class of the DataLayer
	 * Return a collection of easy to display items
	 * 
	 * @return Collection<StandardDisplayItem>
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IOException
	 */
	public Collection<?> getData() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException
	{
		if(isFirstLaunch){
			this.setUpSystems();
			this.setUpSystemRelations();
			this.setUpFilters();
			this.isFirstLaunch = false;
		}
		
		systemsUnderTest = systemController.getAllSystemsUnderTest();
		
		for(Filter filter : filters){
			systemsUnderTest = filter.filter(systemsUnderTest);
		}
		
		return this.convertToDisplayList(systemsUnderTest);
	}
	
	/**
	 * Define the way the return data need to looks like
	 * @param <T>
	 * 
	 * @param the list of SystemUnderTest
	 * @return a collection of ?
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	protected abstract Collection<?> convertToDisplayList(Collection<SystemUnderTest> listSystemsUnderTest) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException;

}





