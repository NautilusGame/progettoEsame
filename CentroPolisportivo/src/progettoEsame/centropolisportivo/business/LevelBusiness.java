package progettoEsame.centropolisportivo.business;

import static progettoEsame.centropolisportivo.business.ConstantClass.EMAIL_REGISTRATION_ERROR_MSG;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.CenterManagerDAO;
import progettoEsame.centropolisportivo.exception.LevelException;
import progettoEsame.centropolisportivo.exception.RegistrationException;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.model.Member;
import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class LevelBusiness {
	
	private static LevelBusiness instance;
	private static ArrayList<Level> alLevels;
	
	public static synchronized LevelBusiness getInstance()
	{
		if(instance == null)
			return new LevelBusiness();
		return instance;
	}
	
	public void checkLevelData(Level level)throws LevelException,SQLException,SessionException
	{
		Level tmpLevel = Level.findByName(level.getName());
		if(tmpLevel  !=null)
		{
			throw new LevelException(LEVEL_NAME_YET_EXIST);
		}
		level.setCenterManager(CenterManagerDAO.getInstance().findByEmail(Session.getInstance().getEmail()));
		Level.insert(level);
	}
	
	public void insert(Level newLevel) throws SQLException
	{
		Level.insert(newLevel);
	}
	
	public ArrayList<String> getAllLevels() throws SQLException
	{
		ArrayList<Level> levels= Level.getAllLevels();
		ArrayList<String> levelsName= new ArrayList<>();
		
		for (int i=0; i<levels.size(); i++)
		{
			levelsName.add(levels.get(i).getName());//1
		}
	
		return levelsName;
	}	
	
	public ArrayList<Level> getLevels() throws SQLException
	{
		return Level.getAllLevels();
	}
	
	public Level findLevelByName(String levelName)throws SQLException
	{
		return Level.findByName(levelName);
	}

}
