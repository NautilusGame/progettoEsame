package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.business.ReviewBusiness;
import progettoEsame.centropolisportivo.model.Review;
import progettoEsame.centropolisportivo.view.ReviewCard;

public class ReviewCardController
{

	private static ReviewCardController instance;

	
	public static synchronized ReviewCardController getInstance()
	{
		if(instance == null)
			return new ReviewCardController();
		return instance;
	}

	public ArrayList<ReviewCard> getAllReviewCard(int id) throws SQLException
	{
		ArrayList<Review> tmpAllReviews= ReviewBusiness.getInstance().getAllReviewsByActivity(id);
		ArrayList<ReviewCard> allReviewsCard = new ArrayList<ReviewCard>();
		
		if(tmpAllReviews!=null)
		{
			for(int i=0;i<tmpAllReviews.size();i++)
			{
				ArrayList<String> valueReviewPanel = new ArrayList<>();
				String name=ReviewBusiness.getInstance().getNameMemberByReview(tmpAllReviews.get(i).getMember().getEmail());//prende il nome di cha scritto la recensione 
				valueReviewPanel.add(name);//Passare nome utente che ha scritto la review
				valueReviewPanel.add(tmpAllReviews.get(i).getText());
				valueReviewPanel.add(String.valueOf(tmpAllReviews.get(i).getRate()));
				ReviewCard tmp=new ReviewCard(valueReviewPanel);
				allReviewsCard.add(tmp);
			}
			return allReviewsCard;
		}
		else 
			return null;
	}
}
