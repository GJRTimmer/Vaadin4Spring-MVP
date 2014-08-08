package org.vaadin.spring.mvp.view;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.vaadin.spring.mvp.MvpView;

import com.vaadin.ui.CustomComponent;

/**
 * Simple MvpView implementation using CustomComponent
 *  
 * @author Marko Radinovic
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractMvpView extends CustomComponent implements MvpView {

	@PostConstruct
	@Override
	public void postConstruct() {		
		
	}

	@PreDestroy
	@Override
	public void preDestroy() {
		
	}		

}
