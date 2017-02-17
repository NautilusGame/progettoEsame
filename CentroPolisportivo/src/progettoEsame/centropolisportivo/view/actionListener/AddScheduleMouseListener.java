package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.view.AddSchedule;

public class AddScheduleMouseListener implements MouseListener {

	private AddSchedule addSchedulePanel;
	private static ArrayList<String> weekDay;
	
	public AddScheduleMouseListener(AddSchedule addSchedulePanel) {
		this.addSchedulePanel = addSchedulePanel;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.fillDayNameArray();
		addSchedulePanel.addScheduleToList(weekDay.get(Integer.parseInt(addSchedulePanel.getSelectedCell().get(1))) +  "  " + addSchedulePanel.getSelectedCell().get(0));

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
