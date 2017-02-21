package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.business.CsrcBusiness;
import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.business.PaymentBusiness;
import progettoEsame.centropolisportivo.business.RegistrationBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.model.Csrc;
import progettoEsame.centropolisportivo.model.Payment;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.PaymentView;
import progettoEsame.centropolisportivo.view.RegisterToEvent;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class PaymentActionListener implements ActionListener {

	private PaymentView paymentViewPanel;
	private Object registrationPanel;

	public PaymentActionListener(PaymentView paymentViewPanel, Object registrationPanel) {
		this.paymentViewPanel = paymentViewPanel;
		this.registrationPanel = registrationPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Payment newPayment = this.paymentViewPanel.getPayment();
		if(arg0.getActionCommand().equals(PAYMENT_COMBO_TYPE_ACTION_CMD))
		{
			if(paymentViewPanel.getSelectedType().equals("Cash"))
			{
				this.paymentViewPanel.makeInvisibleCardNumber();
			}
			else
			{
				this.paymentViewPanel.makeVisibleCardNumber();
				paymentViewPanel.removeMessageToPanel();
				paymentViewPanel.setIcon(paymentViewPanel.getSelectedType());
			}
		}

		if(arg0.getActionCommand().equals(PAYMENT_INSERT_BUTTON_ACTION_CMD))
		{
			if(newPayment.getNumber().equals("") && !newPayment.getType().equals("Cash"))
			{
				paymentViewPanel.removeMessageToPanel();
				this.paymentViewPanel.addMessageToPanel(Message.getInstance().printErrorMsg(PAYMENT_CARD_NUMBER_BLANK));
			}
			else if(newPayment.getNumber().length()<13 && !newPayment.getType().equals("Cash"))
			{
				this.paymentViewPanel.addMessageToPanel(Message.getInstance().printErrorMsg(PAYMENT_CARD_NUMBER_CIPHER));
			}

			paymentViewPanel.makeInvisibleMainPanel();

		}
		if(arg0.getActionCommand().equals(PAYMENT_CARD_YES_BUTTON_ACTION_CMD))
		{
			paymentViewPanel.makeVisibleMainPanel();
			try {
				PaymentBusiness.getInstance().insert(newPayment);
				RegistrationBusiness.getInstance().insert((Registration)Session.getInstance().getFromSession("newRegistration"));
				CsrcBusiness.getInstance().insert((Csrc)Session.getInstance().getFromSession("newCsrc"));
				this.paymentViewPanel.addMessageToPanel(Message.getInstance().printSuccessMsg(REGISTER_TO_COMPETITION_SUCCESS));
				
				paymentViewPanel.removeMessageToPanel();
				this.paymentViewPanel.addMessageToPanel(Message.getInstance().printSuccessMsg(PAYMENT_SUCCESS_MSG));

			} catch (SQLException e) {
				paymentViewPanel.removeMessageToPanel();
				this.paymentViewPanel.addMessageToPanel(Message.getInstance().printErrorMsg(PAYMENT_DB_ERROR));
				e.printStackTrace();
			}
		}
		else if(arg0.getActionCommand().equals(PAYMENT_CARD_NO_BUTTON_ACTION_CMD))
		{
			if(newPayment.getActivity() != null)
			{
				System.out.println("Ciao");
				
				/*CHIAMA FUNZIONE CHE RITORNA ALLA PAGINA INIZIALE PER ACTIVITY E FAI IL CASTING DEL REGISTRATION PANEL*/
			}
			else
			{
				try {
					if(EventBusiness.getInstance().findById(paymentViewPanel.getid()).getType().equals("Competition"))
					{
						RegisterToEvent registerToCompetition = (RegisterToEvent)this.registrationPanel;
						this.paymentViewPanel.makeVisibleMainPanel();
						registerToCompetition.makeVisibleMainPanel();
					}
				} catch (SQLException e) {
					paymentViewPanel.removeMessageToPanel();
					this.paymentViewPanel.addMessageToPanel(Message.getInstance().printErrorMsg(PAYMENT_DB_ERROR));
				}
			}
		}

	}

}
