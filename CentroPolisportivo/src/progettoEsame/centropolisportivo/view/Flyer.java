package progettoEsame.centropolisportivo.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Flyer extends JPanel{
	private JTabbedPane tabPane;
	
	public Flyer(Template template)
	{
		this.tabPane = new JTabbedPane();
		tabPane.addTab("ActivityFlyer", new ActivityFlyer(template));
		tabPane.addTab("EventFlyer",new EventFlyer(template));
		this.add(tabPane);
	}

}
