package org.vaadin.spring.mvp;

import java.io.Serializable;

public interface MvpView extends Serializable {
	
	public void postConstruct();
	
	public void preDestroy();

}
