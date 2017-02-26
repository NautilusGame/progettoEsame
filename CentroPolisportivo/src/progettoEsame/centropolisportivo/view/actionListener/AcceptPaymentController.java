package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import progettoEsame.centropolisportivo.business.PaymentBusiness;
import progettoEsame.centropolisportivo.model.Payment;
import progettoEsame.centropolisportivo.view.AcceptPaymentProposal;
import progettoEsame.centropolisportivo.view.Message;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AcceptPaymentController {

	private static AcceptPaymentController instance;
	private AcceptPaymentProposal acceptPaymentProposalPanel;

	public static synchronized AcceptPaymentController getInstance()
	{
		if(instance == null)
			return new AcceptPaymentController();
		return instance;
	}

	public ArrayList<JPanel> getUnconfirmedPayment(AcceptPaymentProposal acceptPaymentProposalPanel)
	{
		try {
			this.acceptPaymentProposalPanel = acceptPaymentProposalPanel;
			ArrayList<JPanel> unconfirmedPaymentPanel = new ArrayList<>();
			ArrayList<Payment> unconfirmedPayment = PaymentBusiness.getInstance().getAllUnconfirmedPayment();
			if(unconfirmedPayment == null)
			{	
				this.acceptPaymentProposalPanel.removeMessageToPanel();
				this.acceptPaymentProposalPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ACCEPT_PAYMENT_PROPOSAL_NO_PAYMENT));
			}
			else
			{
				for(int i = 0;i<unconfirmedPayment.size();i++)
				{	
					JPanel tmpPanel = new JPanel();
					GridBagConstraints gbc = new GridBagConstraints();
					JButton acceptButton = new JButton(ACCEPT_PAYMENT_PROPOSAL_ACCEPT_BUTTON);
					JButton declineButton = new JButton(ACCEPT_PAYMENT_PROPOSAL_DECLINE_BUTTON); 
					acceptButton.setName(ACCEPT_PAYMENT_PROPOSAL_ACCEPT_BUTTON_NAME);
					declineButton.setName(ACCEPT_PAYMENT_PROPOSAL_DECLINE_BUTTON_NAME);
					acceptButton.setActionCommand(Integer.toString(unconfirmedPayment.get(i).getId()));
					declineButton.setActionCommand(Integer.toString(unconfirmedPayment.get(i).getId()));
					acceptButton.addActionListener(new AcceptPaymentActionListener(acceptPaymentProposalPanel));
					declineButton.addActionListener(new AcceptPaymentActionListener(acceptPaymentProposalPanel));
					tmpPanel.setLayout(new GridBagLayout());
					
					gbc.gridx = 0;
					gbc.gridy = 0;
					tmpPanel.add(new JLabel("Member: " + unconfirmedPayment.get(i).getMember().getName()), gbc);
					gbc.gridx = 1;
					gbc.gridy = 0;
					if(unconfirmedPayment.get(i).getActivity() != null)
					{
						tmpPanel.add(new JLabel("Activity: " + unconfirmedPayment.get(i).getActivity().getName()), gbc);
					}
					else
					{
						tmpPanel.add(new JLabel("Event: " + unconfirmedPayment.get(i).getEvent().getName()), gbc);
					}

					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.anchor = GridBagConstraints.CENTER;
					gbc.fill = GridBagConstraints.BOTH;
					gbc.gridwidth = 2;
					tmpPanel.add(new JLabel("Transition: " + unconfirmedPayment.get(i).getAmount() + "€"), gbc);
					
					gbc = new GridBagConstraints();
					gbc.gridx = 2;
					gbc.gridy = 0;
					tmpPanel.add(acceptButton, gbc);
					
					gbc.gridx = 3;
					gbc.gridy = 0;
					tmpPanel.add(declineButton, gbc);
					
					unconfirmedPaymentPanel.add(tmpPanel);
				}
				return unconfirmedPaymentPanel;
			}
		} catch (SQLException e) {
			this.acceptPaymentProposalPanel.removeMessageToPanel();
			this.acceptPaymentProposalPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ACCEPT_PAYMENT_PROPOSAL_DB_ERROR));
		}
		return null;
	}


}
