package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.exception.ActivityTypeException;
import progettoEsame.centropolisportivo.model.ActivityType;
import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class ActivityTypeBusiness {
	
	private static ActivityTypeBusiness instance;

	public static synchronized ActivityTypeBusiness getInstance()
	{
		if(instance == null)
			return new ActivityTypeBusiness();
		return instance;
	}
	
	public void insert(ActivityType newActivityType) throws SQLException, ActivityTypeException
	{
		if(ActivityType.findByType(newActivityType.getType()) == null)
		{
			ActivityType.insert(newActivityType);
		}
		else
		{
			throw new ActivityTypeException(ACTIVITY_TYPE_IS_PRESENT);
		}
	}

}
