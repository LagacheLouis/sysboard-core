package filters;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import cern.mpe.systems.core.domain.SystemUnderTest;

/**
 *	An abstract filter for building basic filter
 * @author Lagache
 *
 */
public abstract class Filter {
	
	
	/**
	 * @return FilterView
	 * Return the view of the filter
	 * 
	 */
	public FilterView getView() {
		String className = this.getClass().getName() + "View";
		FilterView instance = null;
		try {
			instance = (FilterView) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * Initialize the view of the filter 
	 */
	public void callInitialize() {
		this.getView().initialize();
	}
	
	
	/**
	 * @return String
	 * return the user entries from the view
	 * 
	 */
	public Object callGetValueFromView() {
		return this.getView().getValueFromView();
	}
	
	/**
	 * @param listSystemsUnderTest
	 * @return Collection of SystemUnderTest
	 * 
	 * Define the how the data need to be filtered
	 */
	public abstract Collection<SystemUnderTest> filter(Collection<SystemUnderTest> listSystemsUnderTest);
}
