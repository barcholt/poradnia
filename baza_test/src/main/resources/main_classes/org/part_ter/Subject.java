/**
 * 
 */
package org.part_ter;

/**
 * @author bartek
 *
 */

public interface Subject<TEntity> {

	boolean addObservers(ObserverClass obs);
	boolean deleteObservers(ObserverClass obs);
	boolean notifyObservers();
	
}
