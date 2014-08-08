Vaadin4Spring-MVP
=================

Simple implementation of MVP pattern.

This project depends on vaadin4spring add-on. <https://github.com/peholmst/vaadin4spring>

## Building 
git clone <https://github.com/markoradinovic/Vaadin4Spring-MVP.git>
mvn clean install

## Maven
Add this repository to your `pom.xml` file.

```java
<repositories>
    <repository>
		<id>github-snapshots</id>
		<name>Vaadin4Spring MVP</name>			
		<url>https://raw.github.com/markoradinovic/mvn-repo/snapshot</url>
		<releases>
			<enabled>false</enabled>
		</releases>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
	</repository>
    <repository>
		<id>github</id>
		<name>Vaadin4Spring MVP</name>			
		<url>https://raw.github.com/markoradinovic/mvn-repo/master</url>
		<releases>
			<enabled>true</enabled>
		</releases>
		<snapshots>
			<enabled>false</enabled>
		</snapshots>
	</repository>
</repositories>
```
Add the following dependency to your `pom.xml` file:
```java
<dependency>
	<groupId>org.vaadin.spring</groupId>
	<artifactId>spring-vaadin-mvp</artifactId>
	<version>0.0.2-SNAPSHOT</version>
</dependency>
```

## Quick start
Create presenter
```java
@UIScope
@VaadinView(name="/home")
public class HomePresenter extends AbstractMvpPresenterView<HomePresenter.HomeView> implements HomePresenterHandlers {
	
	public interface HomeView extends MvpView, MvpHasPresenterHandlers<HomePresenterHandlers> {
		public void initView();
		public void showMessage(String message);
	}
	
	private int count = 0;
	
	@Autowired
	public HomePresenter(HomeView view, EventBus eventBus) {
		super(view, eventBus);
		getView().setPresenterHandlers(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {		
		getView().initView();
		count=0;
	}

	@Override
	public ClickListener getDemoBtnClickListener() {		
		return new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				count++;
				getView().showMessage("Button was clicked " + count + " times");				
			}
		};
	}	
}
```

Create interface HomePresenterHandlers
```java
public interface HomePresenterHandlers extends MvpPresenterHandlers {	
	public ClickListener getDemoBtnClickListener();
}
```

Create View
```java
@UIScope
@VaadinComponent
public class HomeViewImpl extends AbstractMvpView implements HomeView  {

	private HomePresenterHandlers mvpPresenterHandlers;
	
	
	CssLayout content;
	Button button;
	
	@Override
	public void postConstruct() {	
		super.postConstruct();
		
		content = new CssLayout();
		content.setWidth("100%");
		setCompositionRoot(content);
				
		final CssLayout wrap = new CssLayout();
		wrap.setWidth("100%");
		content.addComponent(wrap);
		
		final Label caption = new Label("This is Home View");
		caption.addStyleName(ValoTheme.LABEL_H1);
		wrap.addComponent(caption);
		
		button = new Button("Click me");		
		wrap.addComponent(button);
		
				
	}
	
	@Override
	public void setPresenterHandlers(HomePresenterHandlers mvpPresenterHandlers) {
		this.mvpPresenterHandlers = mvpPresenterHandlers;
		button.addClickListener(this.mvpPresenterHandlers.getDemoBtnClickListener());
		
	}

	@Override
	public void initView() {
		while ( content.getComponentCount() > 1) {
			if (content.getComponent(content.getComponentCount() - 1) instanceof Label) {
				content.removeComponent(content.getComponent(content.getComponentCount() - 1));
			}
		}
		
	}

	@Override
	public void showMessage(String message) {
		final Label caption = new Label(message);
		caption.addStyleName(ValoTheme.LABEL_H2);
		content.addComponent(caption);
		
	}

}
```