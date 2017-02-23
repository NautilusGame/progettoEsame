package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.business.ConstantClass.SUCCES_LOGIN_MSG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.business.RegistrationActivityBusiness;
import progettoEsame.centropolisportivo.business.ReviewBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.ConstantClass;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.RegistrationCard;

public class RegistrationCardActionListener implements ActionListener
{

	private RegistrationCard card;
	
	
	public RegistrationCardActionListener(RegistrationCard card)
	{
		this.card=card;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals(ConstantClass.SHOW_REVIEWS))
		{
			this.card.showReviews();
		}
		else if(e.getActionCommand().equals(ConstantClass.HIDE_REVIEWS))
		{
			this.card.removeReviews();
		}
		else if(e.getActionCommand().equals(ConstantClass.NEW_REVIEW_BUTTON))
		{
			if(ReviewBusiness.getInstance().insertNewReview(this.card.getReviewData()))
			{
				this.card.removeMessageToPanel();
				this.card.addMessageToPanel(Message.getInstance().printSuccessMsg(ConstantClass.SUCCESS_INSERT_REVIEW));
			}
			else 
			{
				this.card.removeMessageToPanel();
				this.card.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.ERROR_TO_INSERT_REVIEW));
			}				
		}
		else if(e.getActionCommand().equals(ConstantClass.NEXT_STEP_REGISTRATION))
		{
			Session.getInstance().saveOnSession("dataRegistration", this.card.getData());
			this.card.showPaymentPanel();
		}
	}

}
