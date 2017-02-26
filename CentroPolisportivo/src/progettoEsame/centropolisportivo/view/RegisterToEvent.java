package progettoEsame.centropolisportivo.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.model.Csrc;
import progettoEsame.centropolisportivo.model.Event;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.view.actionListener.RegisterToEventActionListener;
import progettoEsame.centropolisportivo.view.actionListener.RegisterToEventController;
import progettoEsame.centropolisportivo.view.actionListener.RegisterToEventMouseListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class RegisterToEvent extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel;
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JLabel costLabel;
	private JLabel typeLabel;
	private JLabel titlePage;
	private ImageIcon imageIcon;
	private JLabel img;
	private ImageIcon csrcImageIcon;
	private JLabel csrcImg;
	private GridBagConstraints gbc;
	private JLabel msg;
	private JFileChooser csrc;
	private JButton enrolButton;
	private int uploadFileResult;
	private int eventID;
	private JLabel invisibleLabel;
	private JLabel fileName;
	private JPanel mainPanel;
	private JPanel paymentPanel;
	public RegisterToEvent(int eventId)
	{
		this.eventID = eventId;
		RegisterToEventController.getInstance().init(eventId, this);
		
		
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
		gbc.gridwidth = 2;
		this.mainPanel.add(typeLabel, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.mainPanel.add(nameLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.mainPanel.add(costLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.mainPanel.add(startDateLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.mainPanel.add(endDateLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.mainPanel.add(csrcImg, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.mainPanel.add(fileName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		this.mainPanel.add(enrolButton, gbc);
		this.add(mainPanel);
		this.add(paymentPanel);
		
	}
	
	public void init(Event e)
	{
		this.mainPanel = new JPanel(new GridBagLayout());
		this.paymentPanel = new JPanel();
		this.paymentPanel.setVisible(false);
		this.titlePage = new JLabel(REGISTER_TO_COMPETITION_TITLE_PAGE);
		this.csrc = new JFileChooser();
		this.imageIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/default.png").getImage().getScaledInstance(300, 170, Image.SCALE_DEFAULT));
		this.img = new JLabel();
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		this.img.setIcon(imageIcon);
		this.csrcImageIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/698654-icon-138-certificate-128.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		this.csrcImg = new JLabel();
		this.csrcImg.setIcon(csrcImageIcon);
		this.csrc = new JFileChooser();
		this.gbc = new GridBagConstraints();
		this.msg = new JLabel();repaint();
		this.fileName = new JLabel();
		this.invisibleLabel = new JLabel();
		this.enrolButton = new JButton(REGISTER_TO_COMPETITION_ENROL_BUTTON);
		this.enrolButton.setActionCommand(REGISTER_TO_COMPETITION_ENROL_BUTTON_ACTION_CMD);
		this.enrolButton.addActionListener(new RegisterToEventActionListener(this));
		this.csrcImg.addMouseListener(new RegisterToEventMouseListener(this));
		this.typeLabel = new JLabel("Type: " + e.getType());
		this.nameLabel = new JLabel("Name: " + e.getName());
		this.startDateLabel = new JLabel("Start date: " + e.getStartDate().toString());
		this.endDateLabel = new JLabel("End date: " + e.getFinishDate().toString());
		if(e.isFree())
		{
			this.costLabel = new JLabel("Cost: Free");
		}
		else
		{
			this.costLabel = new JLabel("Cost: " + Double.toString(e.getCost()));
		}

		if(!e.getType().equals("Competition"))
		{
			this.csrcImg.setVisible(false);
			this.csrc.setEnabled(false);
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
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 8;
		this.mainPanel.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
	
	public void openFileChooser()
	{
		this.uploadFileResult = this.csrc.showDialog(null, "Upload");
	}
	
	public String getCsrcPath()
	{
		if(this.uploadFileResult == JFileChooser.APPROVE_OPTION)
		{
			if(this.csrc.getSelectedFile().getName().endsWith("pdf"))
			{
				String path = this.csrc.getSelectedFile().getPath();
				path = path.replace('\\','/');
				return path;
			}
			else
			{
				return REGISTER_TO_COMPETITION_FILE_ERROR_MSG;
			}
		}
		else
		{
			return REGISTER_TO_COMPETITION_CANCEL_CHOOSE;
		}
	}
	public void setPathToCSRCLabel(String path)
	{
		File a = new File(path);
		a.renameTo(new File("../progettoEsame/image/CSRC/" + a.getName()));
		path = "../progettoEsame/image/CSRC/" + a.getName();
		this.invisibleLabel.setText(path);
		this.fileName.setText(a.getName());
	}
	
	public String getPathFromImageLabel()
	{
		return this.invisibleLabel.getText();
	}
	
	public Registration getRegistration()
	{
		Registration newRegistration = new Registration();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		newRegistration.setDate(Date.valueOf(sdf.format(new java.util.Date())));
		try {
			newRegistration.setEvent(EventBusiness.getInstance().findById(this.eventID));
		} catch (SQLException e) {
			this.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_TO_COMPETITION_DB_ERROR));
		}
		return newRegistration;
	}
	
	public Csrc getCSRC()
	{
		Csrc newCsrc = new Csrc();
		newCsrc.setPath(this.invisibleLabel.getText());
		try {
			newCsrc.setEvent(EventBusiness.getInstance().findById(this.eventID));
		} catch (SQLException e) {
			this.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_TO_COMPETITION_DB_ERROR));
		}
		return newCsrc;
	}
	
	public void makeInvisibleMainPanel(Double amount, int id)
	{
		this.mainPanel.setVisible(false);
		this.paymentPanel.add(new PaymentView(amount,id,"Event",this));
		this.paymentPanel.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	

	public void makeVisibleMainPanel()
	{
		this.mainPanel.setVisible(true);
		this.remove(this.paymentPanel);
		this.paymentPanel.setVisible(false);
		this.revalidate();
		this.repaint();
	}

}
