package org.vaadin.spring.mvp;

import java.io.Serializable;

import com.vaadin.ui.Component;

public interface MvpPresenter extends Serializable {

	public void postConstruct();
	
	public void preDestroy();
	
	public Component getViewComponent();
	
}
