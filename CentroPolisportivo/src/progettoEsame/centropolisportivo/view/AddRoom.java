package progettoEsame.centropolisportivo.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import progettoEsame.centropolisportivo.view.actionListener.AddRoomActionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AddRoom extends JPanel{
	
	private JLabel titlePage;
	private JLabel roomNameLabel;
	private JLabel descriptionLabel;
	private JLabel capabilityLabel;
	private JLabel msg;
	private JTextField roomNameTextField;
	private JTextField capabilityTextField;
	private JTextArea roomDescriptionTextArea;
	private JScrollPane descriptionScrollPane;
	private JButton insertButton;
	private GridBagConstraints gbc;
	
	public AddRoom() {
		this.setLayout(new GridBagLayout());
		this.titlePage = new JLabel(ADD_ROOM_TITLE_PAGE);
		this.roomNameLabel = new JLabel(ADD_ROOM_ROOM_LABEL);
		this.descriptionLabel = new JLabel(ADD_ROOM_DESCRIPTION_LABEL);
		this.capabilityLabel = new JLabel(ADD_ROOM_CAPABILITY_LABEL);
		this.msg = new JLabel();
		this.roomNameTextField = new JTextField(15);
		this.capabilityTextField = new JTextField(15);
		this.roomDescriptionTextArea = new JTextArea(10,15);
		this.descriptionScrollPane = new JScrollPane(this.roomDescriptionTextArea);
		this.insertButton = new JButton(ADD_ROOM_INSERT_BUTTON);
		this.gbc = new GridBagConstraints();
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		this.insertButton.addActionListener(new AddRoomActionListener(this));
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.add(titlePage, gbc);
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.add(roomNameLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.add(roomNameTextField, gbc);
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.add(capabilityLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.add(capabilityTextField, gbc);
		this.gbc.gridx = 0;
		this.gbc.gridy = 3;
		this.add(descriptionLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.add(descriptionScrollPane, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 4;
		this.add(insertButton, gbc);
	}
	
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 6;
		this.add(this.msg,gbc);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 6;
		this.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
	
	
	public ArrayList<String> getRoomData()
	{
		ArrayList<String> newRoom = new ArrayList<>();
		newRoom.add(this.roomNameTextField.getText()); //0
		newRoom.add(this.roomDescriptionTextArea.getText());//1
		newRoom.add(this.capabilityTextField.getText());//2
		return newRoom;
	}

}
