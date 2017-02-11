package progettoEsame.centropolisportivo.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AddActivity extends JPanel {

	private JLabel titlePage;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JLabel priceLabel;
	private JLabel img;
	private JLabel roomLabel;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextArea activityDescriptionTextArea;
	private JScrollPane descriptionScrollPane;
	private JScrollPane roomScrollPane;
	private JButton insertButton;
	private GridBagConstraints gbc;
	private ImageIcon image;
	private JList<ArrayList<String>> room;
	private JFileChooser imageChooser;
	public AddActivity()
	{
		this.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.titlePage = new JLabel(ADD_ACTIVITY_TITLE_PAGE);
		this.nameLabel = new JLabel(ADD_ACTIVITY_NAME_ACTIVITY);
		this.descriptionLabel = new JLabel(ADD_ACTIVITY_DESCRIPTION_ACTIVITY);
		this.priceLabel = new JLabel(ADD_ACTIVITY_PRICE_ACTIVITY);
		this.roomLabel = new JLabel(ADD_ACTIVITY_ROOM_ACTIVITY);
		this.nameTextField = new JTextField(15);
		this.priceTextField = new JTextField(15);
		this.activityDescriptionTextArea = new JTextArea(10,15);
		this.descriptionScrollPane = new JScrollPane(this.activityDescriptionTextArea);
		this.imageChooser = new JFileChooser();
		this.room = new JList<ArrayList<String>>();
		this.roomScrollPane = new JScrollPane(this.room);
		this.insertButton = new JButton(ADD_ACTIVITY_INSERT_BUTTON);
		room.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		room.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		this.image = new ImageIcon(new ImageIcon("../progettoEsame/image/default.png").getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
		this.img  = new JLabel();
		this.img.setBounds(0, 0, 10, 10);
		this.img.setIcon(image);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(this.titlePage, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(img, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(this.nameLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(this.nameTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(this.descriptionLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(this.descriptionScrollPane, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(this.priceLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(this.priceTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(this.roomLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.add(this.roomScrollPane, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		this.add(this.insertButton, gbc);


	}


}
