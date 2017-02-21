package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.ReviewDAO;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Review;

public class ReviewBusiness 
{
	private static ReviewBusiness instance;
	private ArrayList<Review> allReviews;
	
	public static synchronized ReviewBusiness getInstance()
	{
		if(instance == null)
			return new ReviewBusiness();
		return instance;
	}

	public String getNameMemberByReview(String email) throws SQLException
	{
		Member member=Member.findByEmail(email);
		return member.getName();
	}

	public ArrayList<Review> getAllReviews()throws SQLException
	{
		this.allReviews = Review.getAllReviews();
		return this.allReviews;		
	}
	
	public boolean insertNewReview(ArrayList<String> newReviewValues)
	{
		Activity a= new Activity();
		Member m= new Member();
		m.setEmail(newReviewValues.get(1));
		a.setId(Integer.parseInt(newReviewValues.get(0)));
		Review tmp=new Review(a,m,newReviewValues.get(2),Integer.parseInt(newReviewValues.get(3)));
		try
		{
			ReviewDAO.getInstance().insert(tmp);
			return true;
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			//e.printStackTrace();
			return false;
		}
	}
	
	

}
