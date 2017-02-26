package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import progettoEsame.centropolisportivo.view.ActivityCard;
import progettoEsame.centropolisportivo.view.AddSchedule;
import progettoEsame.centropolisportivo.view.ConstantClass;
import progettoEsame.centropolisportivo.view.RegistrationCard;
import progettoEsame.centropolisportivo.view.Template;

public class ActivityCardActionListener implements ActionListener
{
	private Template template;
	private ActivityCard ac;
	
	public ActivityCardActionListener(Template template,ActivityCard ac)
	{
		this.template=template;
		this.ac=ac;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals(ConstantClass.ACTIVITY_REGISTER_BUTTON))
		{
			this.template.removePage();
			this.template.revalidate();
			this.template.repaint();
			RegistrationCard rc = new RegistrationCard(this.ac.getIdActivity());
			this.template.setPage(rc);
		}	
		if(e.getActionCommand().equals(ConstantClass.ACTIVITY_SCHEDULE_BUTTON))
		{
			this.template.removePage();
			this.template.revalidate();
			this.template.repaint();
			AddSchedule addSchedule = new AddSchedule(this.ac.getIdActivity());
			this.template.setPage(addSchedule);
		}	
	}

}
