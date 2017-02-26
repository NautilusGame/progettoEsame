package progettoEsame.centropolisportivo.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progettoEsame.centropolisportivo.view.actionListener.AddActivityTypeActionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AddActivityType extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel titlePage;
	private JLabel activityTypeNameLabel;
	private JLabel msg;
	private JTextField activityTypeNameField;
	private JButton insertButton;
	private GridBagConstraints gbc;
	
	public AddActivityType() {
		this.setLayout(new GridBagLayout());
		this.activityTypeNameLabel = new JLabel(ADD_ACTIVITY_TYPE_NAME_LABEL);
		this.msg = new JLabel();
		this.activityTypeNameField = new JTextField(15);
		this.insertButton = new JButton(ADD_ACTIVITY_TYPE_INSERT_BUTTON);
		this.titlePage = new JLabel(ADD_ACTIVITY_TYPE_TITLE_PAGE);
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		this.gbc = new GridBagConstraints();
		this.insertButton.addActionListener(new AddActivityTypeActionListener(this));
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.add(titlePage, gbc);
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.add(activityTypeNameLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.add(activityTypeNameField, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.add(insertButton, gbc);
	}
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(this.msg,gbc);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 4;
		this.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
	
	
	public String getActivityTypeDate()
	{
		return this.activityTypeNameField.getText();
	}
}
