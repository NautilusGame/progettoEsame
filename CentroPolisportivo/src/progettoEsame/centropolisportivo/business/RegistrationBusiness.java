package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;

import progettoEsame.centropolisportivo.dao.RegistrationDAO;
import progettoEsame.centropolisportivo.exception.RegistrationException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.model.Trainer;

import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class RegistrationBusiness {

	private static RegistrationBusiness instance;

	public static synchronized RegistrationBusiness getInstance()
	{
		if(instance == null)
			return new RegistrationBusiness();
		return instance;
	}

	
	
	public void insert(Registration newRegistration) throws SQLException
	{
		RegistrationDAO.getInstance().insert(newRegistration);
	}
	
	public ArrayList<Registration> getAllRegisteredRegistration(String memberEmail)throws SQLException
	{
		return Registration.getAllRegisteredRegistration(memberEmail);
	}
	
	public Registration findById(int id)throws SQLException
	{
		return Registration.findById(id);
	}

}
