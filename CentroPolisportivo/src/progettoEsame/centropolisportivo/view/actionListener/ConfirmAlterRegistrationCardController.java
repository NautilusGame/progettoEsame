package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.business.ConfirmAlterRegistrationBusiness;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.TempAlterRegistration;
import progettoEsame.centropolisportivo.view.ActivityCard;
import progettoEsame.centropolisportivo.view.ConfirmAlterRegistrationCard;
import progettoEsame.centropolisportivo.view.Template;

public class ConfirmAlterRegistrationCardController
{

	private static ConfirmAlterRegistrationCardController instance;
	private static Template template;
	
	public static synchronized ConfirmAlterRegistrationCardController getInstance()
	{
		if(instance == null)
			return new ConfirmAlterRegistrationCardController();
		return instance;
	}
	
	public void setTemplate(Template template)
	{
		this.template=template;
	}
	
	public ArrayList<ConfirmAlterRegistrationCard> getAllTempAlterRegistration() throws SQLException
	{
		ArrayList<TempAlterRegistration> tmpAllRequest=ConfirmAlterRegistrationBusiness.getInstance().getAllRequests();
		ArrayList<ConfirmAlterRegistrationCard> allRequestsCard = new ArrayList<ConfirmAlterRegistrationCard>();
		ArrayList<String> valueActivityPanel;
		
		if(tmpAllRequest!=null)
		{
			for(int i=0;i<tmpAllRequest.size();i++)
			{
				System.out.println(tmpAllRequest.get(i).getId());
				ConfirmAlterRegistrationCard tmp=new ConfirmAlterRegistrationCard(tmpAllRequest.get(i));
				allRequestsCard.add(tmp);
			}
			return allRequestsCard;
		}
		else 
			return null;
	}
}
