package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
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
			String query = "INSERT INTO payment( type, number, member_email) VALUES ('"+newPayment.getType()+"','"+newPayment.getNumber()+"','"+newPayment.getMember().getEmail()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM payment WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Payment newPayment) throws SQLException
	{
		String query = "UPDATE payment SET type='"+newPayment.getType()+"',number='"+newPayment.getNumber()+"',member_email="+newPayment.getMember().getEmail()+"'; ";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Payment findByEmail(Integer id) throws SQLException
	{
		Payment payment = new Payment();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM payment WHERE  id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		payment.setId(Integer.parseInt(row[0]));
		payment.setType(row[1]);
		payment.setNumber(row[2]);
		payment.setMember(MemberDAO.getInstance().findByEmail(row[3]));
		return payment;

	}

}

