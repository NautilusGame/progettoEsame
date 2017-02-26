package progettoEsame.centropolisportivo.business;



import java.awt.Dimension;


import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.view.MainFrame;

public class Launcher {

	public static void main(String[] args) {
		DbConnection.connetti("sports_center", "root", "");
		MainFrame mf = new MainFrame("Sports_Center",new Dimension(700, 700));
		mf.paintFrame();
	}

}
