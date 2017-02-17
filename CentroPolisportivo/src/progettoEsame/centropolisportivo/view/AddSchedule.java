package progettoEsame.centropolisportivo.view;

import java.util.ArrayList;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import progettoEsame.centropolisportivo.view.actionListener.AddScheduleMouseListener;

public class AddSchedule extends JPanel {

	private String[] dayName;
	private String[][] schedule;
	private JTable scheduleTable;
	private JScrollPane tableScrollPane;
	private JList<String> selectedSchedule;
	private JScrollPane selectedScheduleScrollPane;
	private DefaultListModel<String> listModel;
	public AddSchedule(int activityId)
	{
		this.fillDayNameArray();
		this.fillScheduleArray();
		this.scheduleTable = new JTable(schedule,dayName);
		// Settare header...
		this.scheduleTable.setModel(new ScheduleTableModel(schedule));
		this.listModel = new DefaultListModel<>();
		this.selectedSchedule = new JList<>(this.listModel);
		this.selectedScheduleScrollPane = new JScrollPane(this.selectedSchedule);
		this.scheduleTable.setRowSelectionAllowed(false);
		this.scheduleTable.setRowHeight(90);
		this.scheduleTable.addMouseListener(new AddScheduleMouseListener(this));
		this.tableScrollPane = new JScrollPane(this.scheduleTable);
		this.add(tableScrollPane);
		this.add(selectedScheduleScrollPane);


	}

	private void fillScheduleArray()
	{
		this.schedule = new String[13][6];
		for(int i = 0;i<ADD_SCHEDULE_NUMBER_OF_DAY;i++)
		{
			for(int j = ADD_SCHEDULE_START_TIME;j<ADD_SCHEDULE_FINISH_TIME;j++)
			{
				this.schedule[j-ADD_SCHEDULE_START_TIME][i] = j + ":00-" + (j+1) + ":00";
			}
		}
	}


	private void fillDayNameArray()
	{
		this.dayName = new String[6];
		this.dayName[0] = "Monday";
		this.dayName[1] = "Tuesday";
		this.dayName[2] = "Wednesday";
		this.dayName[3] = "Thursday";
		this.dayName[4] = "Friday";
		this.dayName[5] = "Saturday";
	}

	public ArrayList<String> getSelectedCell()
	{
		ArrayList<String> dataArray = new ArrayList<>();
		dataArray.add((String)this.scheduleTable.getValueAt(this.scheduleTable.getSelectedRow(), this.scheduleTable.getSelectedColumn()));
		dataArray.add(String.valueOf(this.scheduleTable.getSelectedColumn()));
		dataArray.add(String.valueOf(this.scheduleTable.getSelectedRow()));
		return dataArray;
	}
	
	public void addScheduleToList(String newSchedule)
	{
		this.listModel.addElement(newSchedule);
	}

}
