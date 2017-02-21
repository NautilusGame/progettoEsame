package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.control.ComboBox;
import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.business.CenterManagerBusiness;
import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.business.MemberBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Payment;
import progettoEsame.centropolisportivo.view.actionListener.PaymentActionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;



public class PaymentView extends JPanel{

	private JLabel cardNumberLabel;
	private JTextField cardNumberTextField;
	private ImageIcon cardImageIcon;
	private JLabel cardImage;
	private JLabel titlePage;
	private GridBagConstraints gbc; 
	private JComboBox<String> comboBoxType;
	private String[] value = {"Cash","Postepay","Credit Card"};
	private JButton payButton;
	private int id;
	private double paymentAmount;
	private JLabel msg;
	private JPanel confirmDialog;
	private JButton yesButton;
	private JButton noButton;
	private JLabel areYouSure; 
	private JPanel mainPanel;
	private String typology;
	public PaymentView(double paymentAmount,int id, String typology, Object registrationView)
	{
		this.typology = typology;
		this.setLayout(new GridBagLayout());
		this.mainPanel = new JPanel(new GridBagLayout());
		this.cardNumberLabel = new JLabel(PAYMENT_CARD_NUMBER_LABEL);
		this.titlePage = new JLabel(PAYMENT_TITLE_PAGE);
		this.cardNumberTextField = new JTextField(15);
		this.payButton = new JButton(PAYMENT_PAY_BUTTON);
		this.gbc = new GridBagConstraints();
		this.cardImage = new JLabel();
		this.comboBoxType = new JComboBox<>(value);
		this.cardImageIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/cash-1296584_960_720.png").getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT));
		this.cardImage.setIcon(cardImageIcon);
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		this.payButton.setActionCommand(PAYMENT_INSERT_BUTTON_ACTION_CMD);
		this.payButton.addActionListener(new PaymentActionListener(this,registrationView));
		this.comboBoxType.setActionCommand(PAYMENT_COMBO_TYPE_ACTION_CMD);
		this.comboBoxType.addActionListener(new PaymentActionListener(this,registrationView));
		this.confirmDialog = new JPanel();
		this.yesButton = new JButton(PAYMENT_CARD_YES_BUTTON);
		this.noButton = new JButton(PAYMENT_CARD_NO_BUTTON);
		this.areYouSure = new JLabel(PAYMENT_CARD_ARE_YOU_SURE_LABEL);
		this.confirmDialog.add(areYouSure);
		this.confirmDialog.add(yesButton);
		this.confirmDialog.add(noButton);
		this.confirmDialog.setVisible(false);
		this.yesButton.setActionCommand(PAYMENT_CARD_YES_BUTTON_ACTION_CMD);
		this.noButton.setActionCommand(PAYMENT_CARD_NO_BUTTON_ACTION_CMD);
		this.yesButton.addActionListener(new PaymentActionListener(this,registrationView));
		this.noButton.addActionListener(new PaymentActionListener(this,registrationView));
		this.msg = new JLabel();
		this.makeInvisibleCardNumber();
		this.id = id;
		this.paymentAmount = paymentAmount;

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.mainPanel.add(this.titlePage, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		this.mainPanel.add(this.cardImage,gbc);


		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.mainPanel.add(this.comboBoxType,gbc);


		gbc.gridx = 0;
		gbc.gridy = 3;
		this.mainPanel.add(this.cardNumberLabel,gbc);


		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.CENTER;
		this.mainPanel.add(this.cardNumberTextField,gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		this.mainPanel.add(this.payButton,gbc);
		this.add(mainPanel);
		this.add(confirmDialog);
	}

	public String getSelectedType()
	{
		return (String)this.comboBoxType.getSelectedItem();
	}

	public void setIcon(String type)
	{
		if(type.equals("Cash"))
		{
			this.cardImageIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/cash-1296584_960_720.png").getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT));
			this.cardImage.setIcon(cardImageIcon);
			this.repaint();
		}
		if(type.equals("Postepay"))
		{

			this.cardImageIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/logo_postepay.jpg").getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT));
			this.cardImage.setIcon(cardImageIcon);
			this.repaint();
		}

		if(type.equals("Credit Card"))
		{

			this.cardImageIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/8blm_trvsigcm_v_250x158.png").getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT));
			this.cardImage.setIcon(cardImageIcon);
			this.repaint();
		}
	}

	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		this.gbc.gridx = 1;
		this.gbc.gridy = 6;
		this.add(this.msg,gbc);
		this.revalidate();
		this.repaint();
	}

	public void removeMessageToPanel()
	{

		gbc.gridx = 1;
		gbc.gridy = 6;
		this.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
	public Payment getPayment()
	{
		Payment newPayment = new Payment();
		try {
			newPayment.setNumber(this.cardNumberTextField.getText());
			newPayment.setType((String)this.comboBoxType.getSelectedItem());
			newPayment.setAmount(this.paymentAmount);
			newPayment.setConfirmed(0);
			newPayment.setMember(MemberBusiness.getInstance().findByEmail(Session.getInstance().getEmail()));
			newPayment.setCenterManager(null);
			if(this.typology.equals("Activity"))
			{
				newPayment.setActivity(ActivityBusiness.getInstance().findByID(this.id));
			}
			else
			{
				newPayment.setEvent(EventBusiness.getInstance().findById(this.id));
			}

		} catch (SQLException | SessionException e) {
			this.removeMessageToPanel();
			this.addMessageToPanel(Message.getInstance().printErrorMsg(PAYMENT_DB_ERROR));
		}
		return newPayment;
	}

	public void makeVisibleCardNumber()
	{
		this.cardNumberLabel.setVisible(true);
		this.cardNumberTextField.setVisible(true);
		this.repaint();
	}


	public void makeInvisibleCardNumber()
	{
		this.cardNumberLabel.setVisible(false);
		this.cardNumberTextField.setVisible(false);
		this.repaint();
	}

	public void makeInvisibleMainPanel()
	{
		this.mainPanel.setVisible(false);
		this.confirmDialog.setVisible(true);
	}

	public void makeVisibleMainPanel()
	{
		this.mainPanel.setVisible(true);
		this.confirmDialog.setVisible(false);
	}
	public int getid()
	{
		return this.id;
	}
}
