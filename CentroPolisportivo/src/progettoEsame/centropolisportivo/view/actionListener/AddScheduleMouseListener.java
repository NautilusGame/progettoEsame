package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;

import progettoEsame.centropolisportivo.view.AddSchedule;
import progettoEsame.centropolisportivo.view.Message;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AddScheduleMouseListener implements MouseListener {

	private AddSchedule addSchedulePanel;
	private static ArrayList<String> weekDay;

	public AddScheduleMouseListener(AddSchedule addSchedulePanel) {
		this.addSchedulePanel = addSchedulePanel;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource().getClass().equals(JLabel.class))
		{
			JLabel tmp = (JLabel)arg0.getSource();
			if(tmp.getName().equals(ADD_SCHEDULE_SCHEDULE_IMAGE_NAME))
			{
				addSchedulePanel.makeScheduleSectionVisible();
			}
		}
		else
		{
			if(arg0.getSource().getClass().equals(JList.class))
			{
				ArrayList<String> listContent = addSchedulePanel.getSelectedSchedule();
				for(int i = 0;i<listContent.size();i++)
				{
					String newSelectedSchedule = addSchedulePanel.getSelectedItem();
					System.out.println(newSelectedSchedule);
					if(newSelectedSchedule.equals(listContent.get(i)))
					{
						addSchedulePanel.removeScheduleFromList(i);
						System.out.println(i);
					} 
				}
			}
			else
			{
				this.fillDayNameArray();
				ArrayList<String> listContent = addSchedulePanel.getSelectedSchedule();
				boolean deleted = false;
				String newSelectedSchedule = weekDay.get(Integer.parseInt(addSchedulePanel.getSelectedCell().get(1))) +  "  " + addSchedulePanel.getSelectedCell().get(0);
				for(int i = 0;i<listContent.size();i++)
				{
					if(newSelectedSchedule.equals(listContent.get(i)))
					{
						addSchedulePanel.removeScheduleFromList(i);
						deleted = true;
					} 
				}
				if(addSchedulePanel.getNumberOfSelectedSchedule()<3)
				{
					if(!deleted)
					{
						addSchedulePanel.addScheduleToList(weekDay.get(Integer.parseInt(addSchedulePanel.getSelectedCell().get(1))) +  "  " + addSchedulePanel.getSelectedCell().get(0));
					}
				}
				else
				{
					addSchedulePanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_SCHEDULE_MAX_CHOOSE));
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void fillDayNameArray()
	{
		this.weekDay = new ArrayList<>();
		this.weekDay.add("Monday");
		this.weekDay.add("Tuesday");
		this.weekDay.add("Wednesday");
		this.weekDay.add("Thursday");
		this.weekDay.add("Friday");
		this.weekDay.add("Saturday");
	}

}
