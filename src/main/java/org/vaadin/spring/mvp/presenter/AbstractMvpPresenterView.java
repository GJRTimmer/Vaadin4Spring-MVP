package org.vaadin.spring.mvp.presenter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.mvp.MvpPresenterView;
import org.vaadin.spring.mvp.MvpView;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;

/**
 * <p>
 * 	Abstract implementation of MvpPresenterView. You should extend this class;
 * </p>
 * <pre>
 * <code>
 * 	{@literal @}UIScope
 * 	{@literal @}VaadinView(name="/home")
 * 	public class HomePresenter extends AbstractMvpPresenterView<HomePresenter.HomeView> {
 *
 *		public interface HomeView extends MvpView {
 *			void initView();
 *		}
 *	
 *		{@literal @}Autowired
 *		public HomePresenter(HomeView view, EventBus eventBus) {
 *			super(view, eventBus);
 *		}
 *
 *		{@literal @}Override
 *		public void enter(ViewChangeEvent event) {
 *			getView().initView();
 *		
 *		}
 * }
 *
 * </code>
 * </pre>
 * 
 * 
 *  
 * @author Marko Radinovic
 *
 * @param <V>
 */
@SuppressWarnings("serial")
public abstract class AbstractMvpPresenterView<V extends MvpView> implements MvpPresenterView {
	
	private final V view;
	private final EventBus eventBus;
		
	public AbstractMvpPresenterView(final V view, final EventBus eventBus) {
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
	
	@Override
	public abstract void enter(ViewChangeEvent event);
	
	@PreDestroy
	@Override
	public void preDestroy() {
		eventBus.unsubscribe(this);
	}

}
