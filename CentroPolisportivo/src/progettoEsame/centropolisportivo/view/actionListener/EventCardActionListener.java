package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import progettoEsame.centropolisportivo.view.ActivityCard;
import progettoEsame.centropolisportivo.view.AddSchedule;
import progettoEsame.centropolisportivo.view.ConstantClass;
import progettoEsame.centropolisportivo.view.EventCard;
import progettoEsame.centropolisportivo.view.RegisterToEvent;
import progettoEsame.centropolisportivo.view.RegistrationCard;
import progettoEsame.centropolisportivo.view.Template;

public class EventCardActionListener implements ActionListener {
	private Template template;
	private EventCard ec; 
	
	public EventCardActionListener(Template template,EventCard ac)
	{
		this.template=template;
		this.ec=ac;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
			this.template.removePage();
			RegisterToEvent rte = new RegisterToEvent(this.ec.getIdEvent());
			this.template.setPage(rte);
			this.template.revalidate();
			this.template.repaint();
		
	}


}
