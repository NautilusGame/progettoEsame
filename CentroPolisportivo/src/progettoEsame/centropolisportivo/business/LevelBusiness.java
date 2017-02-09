package progettoEsame.centropolisportivo.business;

import static progettoEsame.centropolisportivo.business.ConstantClass.EMAIL_REGISTRATION_ERROR_MSG;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.dao.CenterManagerDAO;
import progettoEsame.centropolisportivo.exception.LevelException;
import progettoEsame.centropolisportivo.exception.RegistrationException;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.model.Member;
import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class LevelBusiness {
	
	private static LevelBusiness instance;

	public static synchronized LevelBusiness getInstance()
	{
		if(instance == null)
			return new LevelBusiness();
		return instance;
	}
	
	public Boolean checkLevelData(Level level)throws LevelException,SQLException,SessionException
	{
		Level tmpLevel = Level.findByName(level.getName());
		if(tmpLevel  !=null)
		{
			throw new LevelException(LEVEL_NAME_YET_EXIST);
		}
		level.setCenterManager(CenterManagerDAO.getInstance().findByEmail(Session.getInstance().getEmail()));
		Level.insert(level);
		return true;
	}

}
