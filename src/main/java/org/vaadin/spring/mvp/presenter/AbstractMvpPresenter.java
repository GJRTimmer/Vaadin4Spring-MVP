package org.vaadin.spring.mvp.presenter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.mvp.MvpPresenter;
import org.vaadin.spring.mvp.MvpView;

import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public abstract class AbstractMvpPresenter<V extends MvpView> implements MvpPresenter {
	
	private final V view;
	private final EventBus eventBus;
		
	public AbstractMvpPresenter(final V view, final EventBus eventBus) {
		this.view =  view;
		this.eventBus =  eventBus;
	}
	
	
	public V getView() {
		return view;
	}

	public EventBus getEventBus() {
		return eventBus;
	}
	
	@Override
	public Component getViewComponent() {	
		return (Component) getView();
	}
	
	@PostConstruct
	@Override
	public void postConstruct() {
		eventBus.subscribe(this);
	}
	
	@PreDestroy
	@Override
	public void preDestroy() {
		eventBus.unsubscribe(this);
	}

}
