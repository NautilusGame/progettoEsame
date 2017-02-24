package progettoEsame.centropolisportivo.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.model.Schedule;
import progettoEsame.centropolisportivo.model.TempAlterRegistration;
import progettoEsame.centropolisportivo.view.actionListener.ModifyRegistrationActionListener;
import progettoEsame.centropolisportivo.view.actionListener.ModifyRegistrationController;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class ModifyRegistration extends JPanel {

	private int id;
	private ImageIcon activityImage;
	private JLabel labelImage;
	private JLabel registrationName;
	private JLabel activityDescription;
	private JLabel registrationCost;
	private JComboBox<String> levels;
	private JList<String> schedule;
	private GridBagConstraints gbc;
	private JButton modifyButton;
	private JLabel msg;
	private JPanel mainPanel;
	private JScrollPane mainScrollPane;
	public ModifyRegistration(int id)
	{
		this.id = id;
		this.init();
	}

	private void init()
	{
		JPanel descriptionPanel = new JPanel(new GridLayout(4, 1));
		this.mainPanel = new JPanel(new GridBagLayout());
		this.mainScrollPane = new JScrollPane(this.mainPanel);
		this.msg = new JLabel();
		ArrayList<Level> levelArray = new ArrayList<>();
		ArrayList<Schedule> scheduleArray = new ArrayList<>();
		this.modifyButton = new JButton(MODIFY_PANEL_MODIFY_BUTTON);
		this.modifyButton.addActionListener(new ModifyRegistrationActionListener(this));
		this.schedule = new JList<>();
		this.schedule.setModel(new DefaultListModel<>());
		this.levels = new JComboBox<>();
		this.gbc = new GridBagConstraints();
		Registration selectedRegistration = ModifyRegistrationController.getInstance().getRegistration(this.id);
		this.labelImage = new JLabel();
		Activity tmpActivity = selectedRegistration.getActivity();
		this.registrationName = new JLabel("Name: " + tmpActivity.getName());
		this.activityDescription = new JLabel("Description: " + tmpActivity.getDescription());
		this.registrationCost = new JLabel("Cost: " + selectedRegistration.getCost());
		this.activityImage = new ImageIcon(new ImageIcon(tmpActivity.getImagePath()).getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT));
		this.labelImage.setIcon(activityImage);
		DefaultListModel<String> listModel = (DefaultListModel)(schedule.getModel());
		levelArray = ModifyRegistrationController.getInstance().getLevels();
		scheduleArray = ModifyRegistrationController.getInstance().getSchedule(tmpActivity.getId());
		for(int i = 0;i<levelArray.size();i++)
		{
			this.levels.addItem(levelArray.get(i).getName());
			if(levelArray.get(i).getName().equals(selectedRegistration.getLevel().getName()))
				this.levels.setSelectedIndex(i);
		}
		
		for(int i = 0;i<scheduleArray.size();i++)
		{
			listModel.addElement(scheduleArray.get(i).getDay() + " " + scheduleArray.get(i).getTime());
		}
		
		descriptionPanel.add(registrationName);
		descriptionPanel.add(activityDescription);
		descriptionPanel.add(registrationCost);
		
		this.gbc = new GridBagConstraints();
		
		
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.mainPanel.add(labelImage, gbc);
		
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.mainPanel.add(descriptionPanel, gbc);
		

		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.mainPanel.add(levels, gbc);
		

		this.gbc.gridx = 0;
		this.gbc.gridy = 3;
		this.mainPanel.add(schedule, gbc);
		
		
		this.gbc.gridx = 0;
		this.gbc.gridy = 4;
		this.mainPanel.add(modifyButton, gbc);
		
		this.add(this.mainScrollPane);
		
	}
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		this.gbc.gridx = 0;
		this.gbc.gridy = 6;
		this.mainPanel.add(this.msg, gbc);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 10;
		this.mainPanel.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
	public int getId()
	{
		return this.id;
	}
	
	public String getLevelName()
	{
		return (String)this.levels.getSelectedItem();
	}
	
	public ArrayList<String> getSelectedSchedule()
	{
		ListModel model = schedule.getModel();
		ArrayList<String> listContent = new ArrayList<>();
		for(int i = 0;i<model.getSize();i++)
		{
			listContent.add((String)model.getElementAt(i));
		}
		return listContent;
	}
}
