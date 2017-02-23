package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.PaymentDAO;
import progettoEsame.centropolisportivo.model.Payment;

public class PaymentBusiness {
	
	private static PaymentBusiness instance;

	public static synchronized PaymentBusiness getInstance()
	{
		if(instance == null)
			return new PaymentBusiness();
		return instance;
	}
	
	public void insert(Payment newPayment) throws SQLException
	{
		Payment.insert(newPayment);
	}
	
	public ArrayList<Payment> getAllUnconfirmedPayment() throws SQLException
	{
		return Payment.getAllUnconfirmedPayment();
		
	}
	
	public Payment findById(int id) throws SQLException
	{
		return Payment.findById(id);
	}
	
	public void update(Payment newPayment) throws SQLException
	{
		Payment.update(newPayment);
	}

	public Payment findByActivityAndMember(int idActivity, String memberEmail)throws SQLException
	{
		return Payment.findByActivityAndMember(idActivity, memberEmail);
	}
	
	public Payment findByEventAndMember(int idEvent, String memberEmail)throws SQLException
	{
		return Payment.findByEventAndMember(idEvent, memberEmail);
	}
}
