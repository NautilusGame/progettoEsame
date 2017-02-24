package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.exception.PaymentException;
import progettoEsame.centropolisportivo.model.Payment;

public class PaymentDAO  {

	private static PaymentDAO instance;

	public static synchronized PaymentDAO getInstance ()
	{
		if(instance==null)
			instance = new PaymentDAO();
		return instance;
	}

	public void insert(Payment newPayment) throws SQLException
	{
		if(newPayment.getConfirmed() == 0)
		{	
			if(newPayment.getActivity() != null)
			{
				String query = "INSERT INTO payment( type, number, amount,confirmed,member_email,activity_id) VALUES ('"+newPayment.getType()+"','"+newPayment.getNumber()+"',"+newPayment.getAmount()+","+newPayment.getConfirmed()+",'"+newPayment.getMember().getEmail()+"',"+newPayment.getActivity().getId()+");";
				DbConnection.getInstance().eseguiAggiornamento(query);
				System.out.println(query);
			}
			else
			{
				String query = "INSERT INTO payment( type, number, amount,confirmed,member_email,event_id) VALUES ('"+newPayment.getType()+"','"+newPayment.getNumber()+"',"+newPayment.getAmount()+","+newPayment.getConfirmed()+",'"+newPayment.getMember().getEmail()+"',"+newPayment.getEvent().getId()+");";
				DbConnection.getInstance().eseguiAggiornamento(query);
				System.out.println(query);
			}
			
		}
		else
		{
			if(newPayment.getActivity() != null)
			{
				String query = "INSERT INTO payment( type, number, amount,confirmed,member_email,center_manager_email,activity_id) VALUES ('"+newPayment.getType()+"','"+newPayment.getNumber()+"',"+newPayment.getAmount()+","+newPayment.getConfirmed()+",'"+newPayment.getMember().getEmail()+"','"+newPayment.getCenterManager().getEmail()+"',"+newPayment.getActivity().getId()+");";
				DbConnection.getInstance().eseguiAggiornamento(query);
			}
			else
			{
				String query = "INSERT INTO payment( type, number, amount,confirmed,member_email,center_manager_email,event_id) VALUES ('"+newPayment.getType()+"','"+newPayment.getNumber()+"',"+newPayment.getAmount()+","+newPayment.getConfirmed()+",'"+newPayment.getMember().getEmail()+"','"+newPayment.getCenterManager().getEmail()+"',"+newPayment.getEvent().getId()+");";
				DbConnection.getInstance().eseguiAggiornamento(query);
			}

		}
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM payment WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Payment newPayment) throws SQLException
	{
		if(newPayment.getActivity() !=null)
		{
			String query = "UPDATE payment SET type='"+newPayment.getType()+"',number='"+newPayment.getNumber()+"',amount = "+newPayment.getAmount()+", confirmed = "+newPayment.getConfirmed()+", member_email='"+newPayment.getMember().getEmail()+"', center_manager_email = '"+newPayment.getCenterManager().getEmail()+"', activity_id = "+newPayment.getActivity().getId()+" WHERE id = "+newPayment.getId()+";";
			System.out.println(query);
			DbConnection.getInstance().eseguiAggiornamento(query);
		}
		
		else
		{
			String query = "UPDATE payment SET type='"+newPayment.getType()+"',number='"+newPayment.getNumber()+"',amount = "+newPayment.getAmount()+", confirmed = "+newPayment.getConfirmed()+", member_email='"+newPayment.getMember().getEmail()+"', center_manager_email = '"+newPayment.getCenterManager().getEmail()+"', event_id = "+newPayment.getEvent().getId()+" WHERE id = "+newPayment.getId()+"; ";
			DbConnection.getInstance().eseguiAggiornamento(query);
		}
	}
	public Payment findById(Integer id) throws SQLException
	{
		Payment payment = new Payment();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM payment WHERE  id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		payment.setId(Integer.parseInt(row[0]));
		payment.setType(row[1]);
		payment.setNumber(row[2]);
		payment.setAmount(Double.parseDouble(row[3]));
		payment.setConfirmed(Integer.parseInt(row[4]));
		payment.setMember(MemberDAO.getInstance().findByEmail(row[5]));
		payment.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[6]));
		payment.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[7])));
		return payment;

	}

	public Payment findByCardNumber(String cardNumber) throws SQLException
	{
		Payment payment = new Payment();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM payment WHERE  number = '"+cardNumber+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		payment.setId(Integer.parseInt(row[0]));
		payment.setType(row[1]);
		payment.setNumber(row[2]);
		payment.setAmount(Double.parseDouble(row[3]));
		payment.setConfirmed(Integer.parseInt(row[4]));
		payment.setMember(MemberDAO.getInstance().findByEmail(row[5]));
		payment.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[6]));
		if(row[7] != null)
		{
			payment.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[7])));
		}
		else
		{
			payment.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[8])));
		}
		return payment;

	}

	public ArrayList<Payment> getAllUnconfirmedPayment() throws SQLException
	{
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery("SELECT * FROM payment WHERE confirmed = 0");
		ArrayList<Payment> unconfirmedPayment = new ArrayList<>();
		if(result.size() == 0) return null;

		for(int i = 0;i<result.size();i++)
		{
			Payment payment = new Payment();
			String[] row = result.get(i);
			payment.setId(Integer.parseInt(row[0]));
			payment.setType(row[1]);
			payment.setNumber(row[2]);
			payment.setAmount(Double.parseDouble(row[3]));
			payment.setConfirmed(Integer.parseInt(row[4]));
			payment.setMember(MemberDAO.getInstance().findByEmail(row[5]));
			payment.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[6]));
			if(row[7] != null)
			{
				payment.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[7])));
			}
			else
			{
				payment.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[8])));
			}
			unconfirmedPayment.add(payment);
		}
		return unconfirmedPayment;
	}
	
	public Payment findByActivityAndMember(int idActivity,String memberEmail)throws SQLException
	{
		String query = "SELECT * FROM payment as p WHERE p.member_email = '"+memberEmail+"' AND p.activity_id = "+idActivity+";";
		System.out.println(query);
		Payment payment = new Payment();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery(query);
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		payment.setId(Integer.parseInt(row[0]));
		payment.setType(row[1]);
		payment.setNumber(row[2]);
		payment.setAmount(Double.parseDouble(row[3]));
		payment.setConfirmed(Integer.parseInt(row[4]));
		payment.setMember(MemberDAO.getInstance().findByEmail(row[5]));
		payment.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[6]));
		if(row[7] != null)
		{
			payment.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[7])));
		}
		else
		{
			payment.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[8])));
		}
		return payment;
	}
	
	public Payment findByEventAndMember(int idEvent,String memberEmail)throws SQLException
	{
		String query = "SELECT * FROM payment as p WHERE p.member_email = '"+memberEmail+"' AND p.event_id = "+idEvent+";";
		Payment payment = new Payment();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery(query);
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		payment.setId(Integer.parseInt(row[0]));
		payment.setType(row[1]);
		payment.setNumber(row[2]);
		payment.setAmount(Double.parseDouble(row[3]));
		payment.setConfirmed(Integer.parseInt(row[4]));
		payment.setMember(MemberDAO.getInstance().findByEmail(row[5]));
		payment.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[6]));
		if(row[7] != null)
		{
			payment.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[7])));
		}
		else
		{
			payment.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[8])));
		}
		return payment;
	}

}

