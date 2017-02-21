package progettoEsame.centropolisportivo.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class ScheduleTableModel extends DefaultTableModel {
	
	private String[][] data;
	private String[] colName;
	
	public ScheduleTableModel(String[][] data, String[] colName) {
		this.data = data;
		this.colName = colName;
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
	
	@Override
	public String getColumnName(int col) {
	    return colName[col];
	}
	

}
