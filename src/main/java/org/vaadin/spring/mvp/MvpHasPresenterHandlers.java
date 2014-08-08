package org.vaadin.spring.mvp;

/**
 * Interface meant to be implemented by the {@link MvpView} so that MvpView can access presenter methods.
 * 
 * @author Marko Radinovic
 *
 * @param <H>
 */
public interface MvpHasPresenterHandlers<H extends MvpPresenterHandlers> {
	
	public void setPresenterHandlers(H mvpPresenterHandlers);
}
