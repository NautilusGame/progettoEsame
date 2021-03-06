package progettoEsame.centropolisportivo.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.view.actionListener.AddActivityActionListener;
import progettoEsame.centropolisportivo.view.actionListener.AddActivityController;
import progettoEsame.centropolisportivo.view.actionListener.AddActivityMouseListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;


public class AddActivity extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel titlePage;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JLabel priceLabel;
	private JLabel img;
	private JLabel roomLabel;
	private JLabel activityTypeLabel;
	private JLabel invisibleImagePath;
	private JLabel msg;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextArea activityDescriptionTextArea;
	private JScrollPane descriptionScrollPane;
	private JScrollPane roomScrollPane;
	private JScrollPane activityTypeScrollPane;
	private JButton insertButton;
	private GridBagConstraints gbc;
	private ImageIcon image;
	private JList <String> room;
	private JList <String> activityType;
	private JFileChooser imageChooser;
	private int uploadImageResult;
	private DefaultListModel<String> demoListModel;
	private JScrollPane mainScrollPane;
	private JPanel mainPanel;
	
	public AddActivity()
	{
		this.mainPanel = new JPanel(new GridBagLayout());
		this.mainScrollPane = new JScrollPane();
		this.gbc = new GridBagConstraints();
		this.msg = new JLabel();
		this.imageChooser = new JFileChooser();
		this.imageChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg"));
		this.titlePage = new JLabel(ADD_ACTIVITY_TITLE_PAGE);
		this.invisibleImagePath = new JLabel();
		this.invisibleImagePath.setVisible(false);
		this.nameLabel = new JLabel(ADD_ACTIVITY_NAME_ACTIVITY);
		this.descriptionLabel = new JLabel(ADD_ACTIVITY_DESCRIPTION_ACTIVITY);
		this.priceLabel = new JLabel(ADD_ACTIVITY_PRICE_ACTIVITY);
		this.roomLabel = new JLabel(ADD_ACTIVITY_ROOM_ACTIVITY);
		this.activityTypeLabel = new JLabel(ADD_ACTIVITY_ACTIVITY_TYPE);
		this.nameTextField = new JTextField(15);
		this.priceTextField = new JTextField(15);
		this.activityDescriptionTextArea = new JTextArea(10,15);
		this.descriptionScrollPane = new JScrollPane(this.activityDescriptionTextArea);
		this.imageChooser = new JFileChooser();
		this.insertButton = new JButton(ADD_ACTIVITY_INSERT_BUTTON);
		AddActivityController.getInstance().addRooms(this);
		this.roomScrollPane = new JScrollPane(this.room);
		if(room != null)
		{
			room.setSelectedIndex(0);
			room.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			room.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		}

		AddActivityController.getInstance().addType(this);
		if(activityType != null)
		{

			activityType.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			activityType.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			activityType.setSelectedIndex(0);
		}
		this.activityTypeScrollPane= new JScrollPane(this.activityType);
		this.insertButton.setActionCommand(ADD_ACTIVITY_INSERT_BUTTON_ACTION_CMD);
		this.insertButton.addActionListener(new AddActivityActionListener(this));
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		this.image = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/default.png").getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
		this.img  = new JLabel();
		this.img.setBounds(0, 0, 10, 10);
		this.img.setIcon(image);
		this.img.setName(ADD_ACTIVITY_IMAGE_ACTION_CMD);
		this.img.addMouseListener(new AddActivityMouseListener(this));
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.mainPanel.add(this.titlePage, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		this.mainPanel.add(img, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.mainPanel.add(this.nameLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.mainPanel.add(this.nameTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.mainPanel.add(this.descriptionLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.mainPanel.add(this.descriptionScrollPane, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.mainPanel.add(this.priceLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.mainPanel.add(this.priceTextField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.mainPanel.add(this.roomLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.mainPanel.add(this.roomScrollPane, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		this.mainPanel.add(this.activityTypeLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		this.mainPanel.add(this.activityTypeScrollPane, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		this.mainPanel.add(this.insertButton, gbc);

		this.mainScrollPane.setViewportView(this.mainPanel);
		this.mainScrollPane.setPreferredSize(new Dimension(500, 700));
		this.mainScrollPane.setBorder(null);
		this.add(this.mainScrollPane);

	}

	public void openFileChooser()
	{
		this.uploadImageResult = this.imageChooser.showDialog(null,"Upload");
	}

	public String getImagePath()
	{
		if(this.uploadImageResult == JFileChooser.APPROVE_OPTION)
		{
			if(this.imageChooser.getSelectedFile().getName().endsWith("jpg") || this.imageChooser.getSelectedFile().getName().endsWith("png") || this.imageChooser.getSelectedFile().getName().endsWith("gif") || this.imageChooser.getSelectedFile().getName().endsWith("jpeg"))
			{
				String path = this.imageChooser.getSelectedFile().getPath();
				path = path.replace('\\','/');
				return path;
			}
			else
			{
				return ADD_ACTIVITY_IMAGE_ERROR_MSG;
			}
		}
		else
		{
			return ADD_ACTIVITY_CANCEL_CHOOSE;
		}
	}

	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 8;
		this.mainPanel.add(this.msg,gbc);
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}

	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		this.mainPanel.remove(this.msg);
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}

	public void setPathToImageLabel(String path)
	{
		File a = new File(path);
		a.renameTo(new File("../progettoEsame/image/ActivityImage/" + a.getName()));
		path = "../progettoEsame/image/ActivityImage/" + a.getName();
		this.invisibleImagePath.setText(path);
	}

	public String getPathFromImageLabel()
	{
		return this.invisibleImagePath.getText();
	}

	public void setRoomListModel(ArrayList<String> model)
	{
		this.demoListModel = new DefaultListModel<String>();
		for(int i = 0;i<model.size();i++)
		{
			this.demoListModel.addElement(model.get(i));
		}
		this.room = new JList<>(demoListModel);
	}
	public void setActivityTypeListModel(ArrayList<String> model)
	{
		this.demoListModel = new DefaultListModel<String>();
		for(int i = 0;i<model.size();i++)
		{
			this.demoListModel.addElement(model.get(i));
		}
		this.activityType = new JList<>(demoListModel);
	}

	public ArrayList<String> getActivityData()
	{
		if(activityType == null || room == null)
		{
			return null;
		}
		else
		{
			ArrayList<String> newActivity = new ArrayList<>();
			try {
				newActivity.add(activityType.getSelectedValue()); //0
				newActivity.add(Session.getInstance().getEmail()); //1
				newActivity.add(room.getSelectedValue()); //2
				newActivity.add(this.nameTextField.getText()); //3
				newActivity.add(this.activityDescriptionTextArea.getText()); //4
				newActivity.add(this.priceTextField.getText()); //5
				newActivity.add(this.getPathFromImageLabel());//6
				return newActivity;
			} 
			catch (SessionException e) {

			}
		}
		return null;
	}
	
	public void resetImage(String path)
	{
		this.image = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
		this.img.setIcon(image);
	}

}
