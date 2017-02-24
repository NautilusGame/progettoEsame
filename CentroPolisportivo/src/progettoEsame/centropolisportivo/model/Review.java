package progettoEsame.centropolisportivo.model;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.ActivityDAO;
import progettoEsame.centropolisportivo.dao.ReviewDAO;

public class Review  {

	private Activity activity;
	private Member member;
	private String text;
	private Integer rate;	

	public Review() {
		super();
	}

	public Review(Activity activity, Member member, String text) {
		this.activity = activity;
		this.member = member;
		this.text = text;
	}

	public Review(Activity activity, Member member, String text, Integer rate) {

		this.activity = activity;
		this.member = member;
		this.text = text;
		this.rate = rate;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public static ArrayList<Review> getAllReviewsByActivity(int id)throws SQLException
	{
		return ReviewDAO.getInstance().getAllReviewsByActivity(id);
	}
	
	public static void insertReviev(Review newReview) throws SQLException
	{
		ReviewDAO.getInstance().insert(newReview);
	}
}
