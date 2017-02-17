package progettoEsame.centropolisportivo.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class ScheduleTableModel extends AbstractTableModel {
	
	private String[][] data;
	
	public ScheduleTableModel(String[][] data) {
		this.data = data;
	}
	@Override
	public int getColumnCount() {
		return ADD_SCHEDULE_NUMBER_OF_DAY;
	}

	@Override
	public int getRowCount() {
		return ADD_SCHEDULE_FINISH_TIME - ADD_SCHEDULE_START_TIME;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	public boolean isCellEditable(int row,int col)
	{
		return false;
	}
	
	@Override
	public Class getColumnClass(int columnIndex) {
		
		return super.getColumnClass(columnIndex);
	}
	

}
