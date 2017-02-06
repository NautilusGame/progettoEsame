package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Review;

public class ReviewDAO  {

	private static ReviewDAO instance;

	public static synchronized ReviewDAO getInstance ()
	{
		if(instance==null)
			instance = new ReviewDAO();
		return instance;
	}

	public void insert(Review newReview) throws SQLException
	{
			String query = "INSERT INTO review(activity_id, member_email, text, rate) VALUES ("+newReview.getActivity().getId()+",'"+newReview.getMember().getEmail()+"','"+newReview.getText()+"',"+newReview.getRate()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Activity activity, Member member) throws SQLException
	{
		String query = "DELETE FROM review WHERE  activity_id= "+activity.getId()+" AND member_email='"+member.getEmail()+"';";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Review newReview) throws SQLException
	{
		String query ="UPDATE review SET activity_id="+newReview.getActivity().getId()+",member_email='"+newReview.getMember().getEmail()+"',text='"+newReview.getText()+"',rate="+newReview.getRate()+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public Review findById(Activity activity, Member member) throws SQLException
	{
		Review review = new Review();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM review WHERE  activity_id= "+activity.getId()+" AND member_email='"+member.getEmail()+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		review.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[0])));
		review.setMember(MemberDAO.getInstance().findByEmail(row[1]));
		review.setText(row[2]);
		review.setRate(Integer.parseInt(row[3]));

	
		return review;

	}

}
