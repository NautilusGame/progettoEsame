package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import progettoEsame.centropolisportivo.business.CenterManagerBusiness;
import progettoEsame.centropolisportivo.business.PaymentBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Payment;
import progettoEsame.centropolisportivo.view.AcceptPaymentProposal;
import progettoEsame.centropolisportivo.view.Message;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AcceptPaymentActionListener implements ActionListener {

	private AcceptPaymentProposal acceptPaymentPanel;
	
	public AcceptPaymentActionListener(AcceptPaymentProposal acceptPaymentPanel) {
		this.acceptPaymentPanel = acceptPaymentPanel;
	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(((JButton)arg0.getSource()).getName().equals(ACCEPT_PAYMENT_PROPOSAL_ACCEPT_BUTTON_NAME))
		{
			Payment newPayment;
			try {
				newPayment = PaymentBusiness.getInstance().findById(Integer.parseInt(arg0.getActionCommand()));
				System.out.println(newPayment.getId());
				newPayment.setCenterManager(CenterManagerBusiness.getInstance().findByEmail(Session.getInstance().getEmail()));
				newPayment.setConfirmed(1);
				Payment.update(newPayment);
			} catch (NumberFormatException | SQLException | SessionException e) {
				e.printStackTrace();
				this.acceptPaymentPanel.removeMessageToPanel();
				this.acceptPaymentPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ACCEPT_PAYMENT_PROPOSAL_DB_ERROR));
			}
		}
		
		else if(((JButton)arg0.getSource()).getName().equals(ACCEPT_PAYMENT_PROPOSAL_DECLINE_BUTTON_NAME))
		{
			Payment newPayment;
			try {
				newPayment = PaymentBusiness.getInstance().findById(Integer.parseInt(arg0.getActionCommand()));
				newPayment.setCenterManager(CenterManagerBusiness.getInstance().findByEmail(Session.getInstance().getEmail()));
				newPayment.setConfirmed(2);
				Payment.update(newPayment);
			} catch (NumberFormatException | SQLException | SessionException e) {
				this.acceptPaymentPanel.removeMessageToPanel();
				this.acceptPaymentPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ACCEPT_PAYMENT_PROPOSAL_DB_ERROR));
			}
		}
		
		

	}

}
