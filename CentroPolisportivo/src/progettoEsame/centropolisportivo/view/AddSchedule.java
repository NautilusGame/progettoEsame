package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;

import progettoEsame.centropolisportivo.view.actionListener.AddScheduleActionListener;
import progettoEsame.centropolisportivo.view.actionListener.AddScheduleController;
import progettoEsame.centropolisportivo.view.actionListener.AddScheduleMouseListener;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AddSchedule extends JPanel{

	private JLabel titlePage;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JLabel img;
	private JLabel scheduleImg;
	private JLabel roomLabel;
	private JLabel activityTypeLabel;
	private JLabel msg;
	private JLabel nameLabelField;
	private JLabel descriptionLabelField;
	private JLabel roomLabelField;
	private JLabel activityTypeLabelField;
	private ImageIcon image;
	private ImageIcon scheduleImage;
	private String[] dayName;
	private String[][] schedule;
	private JTable scheduleTable;
	private JScrollPane tableScrollPane;
	private JList<String> selectedSchedule;
	private JScrollPane selectedScheduleScrollPane;
	private DefaultListModel<String> listModel;
	private GridBagConstraints gbc;
	private JPanel scheduleSection;
	private JPanel mainSection;
	private JButton insertButton;
	private int activityID;
	private JPanel mainPanel;
	private JScrollPane mainScrollPane;
	public AddSchedule(int activityId)
	{
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		this.mainScrollPane = new JScrollPane();
		this.mainScrollPane.setViewportView(this.mainPanel);
		this.mainScrollPane.setBorder(null);
		this.setLayout(new BorderLayout());
		this.activityID = activityId;
		this.mainSection = new JPanel();
		this.mainSection.setLayout(new GridBagLayout());
		this.fillDayNameArray();
		this.fillScheduleArray();
		this.scheduleSection = new JPanel();
		this.scheduleSection.setLayout(new BoxLayout(this.scheduleSection, BoxLayout.Y_AXIS));
		this.insertButton = new JButton(ADD_SCHEDULE_INSERT_BUTTON);
		this.gbc = new GridBagConstraints();
		this.scheduleTable = new JTable(schedule, dayName);
		this.scheduleTable.setModel(new ScheduleTableModel(schedule,dayName));
		this.listModel = new DefaultListModel<>();
		this.selectedSchedule = new JList<>(this.listModel);
		this.selectedScheduleScrollPane = new JScrollPane(this.selectedSchedule);
		this.scheduleTable.setRowSelectionAllowed(false);
		this.scheduleTable.setRowHeight(90);
		this.scheduleTable.addMouseListener(new AddScheduleMouseListener(this));
		this.tableScrollPane = new JScrollPane(this.scheduleTable);
		this.selectedSchedule.addMouseListener(new AddScheduleMouseListener(this));
		this.titlePage = new JLabel(ADD_SCHEDULE_TITLE_PAGE);
		this.nameLabel = new JLabel(ADD_SCHEDULE_NAME_LABEL);
		this.descriptionLabel = new JLabel(ADD_SCHEDULE_DESCRIPTION_LABEL);
		this.roomLabel = new JLabel(ADD_SCHEDULE_ROOM_LABEL);
		this.activityTypeLabel = new JLabel(ADD_SCHEDULE_ACTIVITY_TYPE_LABEL);
		this.msg = new JLabel();
		this.img  = new JLabel();
		this.img.setBounds(0, 0, 10, 10);
		this.img.setIcon(image);
		this.scheduleImage = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/calendar.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		this.scheduleImg  = new JLabel();
		this.scheduleImg.setBounds(0, 0, 10, 10);
		this.scheduleImg.setIcon(scheduleImage);
		this.scheduleImg.setName(ADD_SCHEDULE_SCHEDULE_IMAGE_NAME);
		this.scheduleImg.addMouseListener(new AddScheduleMouseListener(this));
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		AddScheduleController.getInstance().init(this, activityId);
		this.insertButton.setActionCommand(ADD_SCHEDULE_INSERT_BUTTON_ATION_CMD);
		this.insertButton.addActionListener(new AddScheduleActionListener(this));
		this.scheduleSection.add(tableScrollPane);
		this.scheduleSection.add(selectedScheduleScrollPane);
		this.scheduleSection.add(this.insertButton);
		this.scheduleSection.setVisible(false);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.mainSection.add(this.titlePage, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		this.mainSection.add(img, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.mainSection.add(this.nameLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.mainSection.add(this.nameLabelField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.mainSection.add(this.descriptionLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.mainSection.add(this.descriptionLabelField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.mainSection.add(this.roomLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.mainSection.add(this.roomLabelField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.mainSection.add(this.activityTypeLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.mainSection.add(this.activityTypeLabelField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		this.mainSection.add(this.scheduleImg, gbc);
		this.mainPanel.add(mainSection);
		this.mainPanel.add(this.scheduleSection);
		this.add(mainScrollPane,BorderLayout.CENTER);


	}
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 10;
		this.add(this.msg,gbc);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 10;
		this.remove(this.msg);
		this.revalidate();
		this.repaint();
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
	
	public ArrayList<String> getSelectedSchedule()
	{
		ListModel model = selectedSchedule.getModel();
		ArrayList<String> listContent = new ArrayList<>();
		for(int i = 0;i<model.getSize();i++)
		{
			listContent.add((String)model.getElementAt(i));
		}
		return listContent;
	}
	
	public void removeScheduleFromList(int index)
	{
		((DefaultListModel)selectedSchedule.getModel()).remove(index);
		this.repaint();
		this.revalidate();
	}
	
	public int getNumberOfSelectedSchedule()
	{
		ListModel model = selectedSchedule.getModel();
		return model.getSize();
	}
	public String getSelectedItem()
	{
		return selectedSchedule.getSelectedValue();
	}
	
	public void setInitialString(String name, String description, String room, String activityType,String path)
	{
		this.nameLabelField = new JLabel(name);
		this.descriptionLabelField = new JLabel(description);
		this.roomLabelField = new JLabel(room);
		this.activityTypeLabelField = new JLabel(activityType);
		this.image = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(500,150, Image.SCALE_DEFAULT));
		this.img.setIcon(image);
	}
	
	public void makeScheduleSectionVisible()
	{
		this.scheduleSection.setVisible(true);
		this.revalidate();
		this.repaint();
		
	}
	

	public void makeScheduleSectionInvisible()
	{
		this.scheduleSection.setVisible(false);
		this.revalidate();
		this.repaint();
		
	}
	
	public int getActivityID()
	{
		return this.activityID;
	}
	
	public boolean isScheduleSectionVisible()
	{
		return this.scheduleSection.isVisible();
	}
}
