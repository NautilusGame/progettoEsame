package progettoEsame.centropolisportivo.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.business.TrainerBusiness;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Event;
import progettoEsame.centropolisportivo.view.actionListener.AddEventActionListener;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class AddEvent extends JPanel{

	private static final long serialVersionUID = 1L;
	private ImageIcon image;
	private JLabel img;
	private JLabel competitionNameLabel;
	private JLabel startDateLabel;
	private JLabel finishDateLabel;
	private JLabel costLabel;
	private JLabel titlePage;
	private JComboBox<String> combobox;
	private JCheckBox isFreeButton;
	private JTextField competitionNameTextField;
	private JTextField costTextField;
	private JCalendar startDatePicker;
	private JCalendar endDatePicker;
	private GridBagConstraints gbc;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private String[] items = {"Competition","Stage"};
	private JButton insertButton;
	private JLabel msg;
	public AddEvent()
	{
		this.setLayout(new BorderLayout());
		this.msg = new JLabel();
		this.mainPanel = new JPanel(new GridBagLayout());
		this.scrollPane = new JScrollPane(this.mainPanel);
		this.competitionNameLabel = new JLabel(ADD_EVENT_NAME_EVENT_LABEL);
		this.startDateLabel = new JLabel(ADD_EVENT_START_DATE_EVENT_LABEL);
		this.finishDateLabel = new JLabel(ADD_EVENT_END_DATE_EVENT_LABEL);
		this.costLabel = new JLabel(ADD_EVENT_COST_EVENT_LABEL);
		this.isFreeButton = new JCheckBox(ADD_EVENT_FREE_EVENT_LABEL);
		this.titlePage = new JLabel(ADD_EVENT_TITLE_PAGE_EVENT_LABEL);
		this.gbc = new GridBagConstraints();
		this.insertButton = new JButton(ADD_EVENT_INSERT_BUTTON_EVENT);
		this.combobox = new JComboBox<String>(this.items);
		this.competitionNameTextField = new JTextField(15);
		this.costTextField = new JTextField(5);
		this.startDatePicker = new JCalendar();
		this.endDatePicker = new JCalendar();
		this.image = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/default.png").getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT));
		this.img = new JLabel();
		this.scrollPane.setBorder(null);
		this.makePriceInvisible();
		this.isFreeButton.setSelected(true);
		this.isFreeButton.setActionCommand(ADD_EVENT_IS_FREE_EVENT_ACTION_CMD);
		this.isFreeButton.addActionListener(new AddEventActionListener(this));
		this.insertButton.setActionCommand(ADD_EVENT_INSERT_BUTTON_EVENT_ACTION_CMD);
		this.insertButton.addActionListener(new AddEventActionListener(this));

		this.img.setIcon(image);
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.mainPanel.add(this.titlePage, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		this.mainPanel.add(this.img,gbc);


		gbc.gridx = 0;
		gbc.gridy = 2;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.mainPanel.add(this.combobox,gbc);


		gbc.gridx = 0;
		gbc.gridy = 3;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.mainPanel.add(this.isFreeButton,gbc);


		gbc.gridx = 0;
		gbc.gridy = 4;
		this.mainPanel.add(this.competitionNameLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.mainPanel.add(this.competitionNameTextField,gbc);


		gbc.gridx = 0;
		gbc.gridy = 5;
		this.mainPanel.add(this.startDateLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.mainPanel.add(this.startDatePicker,gbc);


		gbc.gridx = 0;
		gbc.gridy = 6;
		this.mainPanel.add(this.finishDateLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		this.mainPanel.add(this.endDatePicker,gbc);


		gbc.gridx = 0;
		gbc.gridy = 7;
		this.mainPanel.add(this.costLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		this.mainPanel.add(this.costTextField,gbc);


		gbc.gridx = 1;
		gbc.gridy = 8;
		this.mainPanel.add(this.insertButton,gbc);

		this.add(scrollPane,BorderLayout.CENTER);

	}

	public void makePriceVisible()
	{
		this.costLabel.setVisible(true);
		this.costTextField.setVisible(true);
	}


	public void makePriceInvisible()
	{
		this.costLabel.setVisible(false);
		this.costTextField.setVisible(false);
	}
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		this.add(this.msg,BorderLayout.SOUTH);
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
	public Event getEventData()
	{
		try {
			Event newEvent = new Event();
			newEvent.setTrainer(TrainerBusiness.getInstance().findByEmail(Session.getInstance().getEmail()));
			newEvent.setName(this.competitionNameTextField.getText());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			newEvent.setStartDate(Date.valueOf(sdf.format(this.startDatePicker.getDate())));
			newEvent.setFinishDate(Date.valueOf(sdf.format(this.endDatePicker.getDate())));
			newEvent.setType((String)this.combobox.getSelectedItem());
			newEvent.setFree(this.isFreeButton.isSelected());
			return newEvent;
		} catch (SQLException | SessionException e) {
			this.removeMessageToPanel();
			this.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_DB_ERROR));
		}
		return null;
	}
	
	public String getCost()
	{
		return this.costTextField.getText();
	}

}
