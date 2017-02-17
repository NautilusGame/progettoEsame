package progettoEsame.centropolisportivo.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRenderer extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 6703872492730589499L;
    public JTable tab;
    private int rowz;
    private int col;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
    	System.out.println(" isSelected: " + isSelected);
    	System.out.println("Row: " + row + " Rowz: " + rowz);
    	System.out.println("Columns: " + column + " Col " + col);
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ScheduleTableModel model = (ScheduleTableModel)tab.getModel();
        if (tab.isRowSelected(row) && tab.isColumnSelected(column)) {
        	
            cell.setBackground(Color.BLACK);
        }
        else
        {
        	cell.setBackground(table.getBackground());
        }
        return cell;
    }
    
    public void setRowz(int rowz)
    {
    	this.rowz = rowz;
    }
    

    public void setCol(int col)
    {
    	this.col = col;
    }

}
