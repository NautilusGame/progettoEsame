package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.actionListener.SessionCheck;
import progettoEsame.centropolisportivo.view.actionListener.TemplateActionListener;
import progettoEsame.centropolisportivo.view.actionListener.TemplateFixedMenuListener;


import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class Template extends JPanel {

	private MainFrame mf;

	private JPanel panelNord;//Panel eddicato al banner e al menu nella sezione nord del template
	private JPanel panelOvest;//Panel dedicato a tutte le voci di menu aggiuntive per ogni entità
	private JPanel panelSud;//panel eddicato alla zona per i messaggi di errori
	private JScrollPane panelCenter;//pannello per richiamare tutte le pagine
	//private JPanel panelCenter;
	private JPanel fixedMenu;//pannello per il menu comune

	/*menu comune a tute le enetità*/
	private JButton flyer;//per sfogliare il catalogo
	private JButton profile;//per andare al profilo
	private JButton logout;//per effettuare il logout
	private JButton info;
	private JButton loginRegisterButton;

	private Flyer flyerPage= new Flyer(this);
	/*
	 * Pulsanti dedicati per ogni entita nel menu laterale
	 */
	private JButton member1;
	private JButton member2;
	private JButton member3;
	private JButton member4;
	private JButton member5;

	private JButton trainer1;
	private JButton trainer2;
	private JButton trainer3;
	private JButton trainer4;
	private JButton trainer5;

	private JButton centerManager1;
	private JButton centerManager2;
	private JButton centerManager3;
	private JButton centerManager4;
	private JButton centerManager5;
	private JButton centerManager6;
	private JButton centerManager7;

	private BufferedImage image;
	private JLabel picLabel;
	//inizio del cotruttore della view
	public Template (MainFrame mf)
	{

		this.setLayout(new BorderLayout());

		this.mf=mf;
		//inizializzazione di tutti i panel
		this.panelNord=new JPanel();
		this.panelOvest=new JPanel(new BoxLayout(this.panelOvest, BoxLayout.Y_AXIS));
		this.panelSud=new JPanel();
		this.panelCenter = new JScrollPane();
		this.fixedMenu=new JPanel();


		this.panelNord.setLayout(new GridLayout(2, 1));
		this.fixedMenu.setLayout(new GridLayout(1, 6));
		//caricamento del banner del sito trimite immagine
		this.panelCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.panelCenter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		try 
		{                
			image = ImageIO.read(new File("image/SystemImage/banner"));//TODO inserire il banner
			picLabel= new JLabel(new ImageIcon(image));
			this.panelNord.add(picLabel);
		} 
		catch (IOException ex) 
		{
		}


		//creazione del menu generico uguale per tutti
		this.flyer = new JButton("Catalogo");
		this.profile = new JButton("Profile");
		this.logout = new JButton("Logout");
		this.info = new JButton("Info");
		this.loginRegisterButton= new JButton("Login/Register");

		this.flyer.setActionCommand(ConstantClass.FLYER_ACTION_CMD);
		this.profile.setActionCommand(ConstantClass.PROFILE_ACTION_CMD);
		this.logout.setActionCommand(ConstantClass.LOGOUT_ACTION_CMD);
		this.info.setActionCommand(ConstantClass.INFO_ACTION_CMD);
		this.loginRegisterButton.setActionCommand(ConstantClass.LOGIN_REGISTER_BUTTON);

		this.flyer.addActionListener(new TemplateFixedMenuListener(this,this.mf));
		this.profile.addActionListener(new TemplateFixedMenuListener(this,this.mf));
		this.logout.addActionListener(new TemplateFixedMenuListener(this,this.mf));
		this.info.addActionListener(new TemplateFixedMenuListener(this,this.mf));
		this.loginRegisterButton.addActionListener(new TemplateFixedMenuListener(this,this.mf));

		this.fixedMenu.add(flyer);
		this.fixedMenu.add(info);

		this.panelNord.add(this.fixedMenu);
		//si verifica quale tipo di utente ha fatto l'accesso e si aggiunge il menu dedicato
		if(this.getSessionStatus())
		{
			this.fixedMenu.add(this.profile);
			this.fixedMenu.add(this.logout);
			this.remove(this.loginRegisterButton);
			if (this.getTypeUser().equals("member")){
				this.addMenuMember();
			}
			else if (this.getTypeUser().equals("trainer")){
				this.addMenuTraienr();
			}
			else if (this.getTypeUser().equals("centerManager")){
				this.addMenuCenterManager();
			}
			this.add(panelOvest, BorderLayout.WEST);
		}
		else 
			this.fixedMenu.add(this.loginRegisterButton);

		this.setPage(flyerPage);
		//aggiunta di tutti i pannelli creati al panel template
		this.add(panelNord, BorderLayout.NORTH);
		this.add(panelSud, BorderLayout.SOUTH);
		//this.add(panelCenter, BorderLayout.CENTER);

	}	

	/*
	 * Funzioni per l'aggiunta del menu laterale in base al tipo di utente che ha effettuato il login
	 */

	//TODO cambiare le voci dei menu dedicati per ogni utente
	public void addMenuMember(){

		this.panelOvest.setLayout(new GridLayout(5, 1));	

		this.member1=new JButton("Registration Panel");
		this.member2=new JButton("Info1");
		this.member3=new JButton("Info1");
		this.member4=new JButton("Info1");
		this.member5=new JButton("Info1");

		this.member1.setActionCommand(MEMBER_MENU_1);
		this.member2.setActionCommand(MEMBER_MENU_2);
		this.member3.setActionCommand(MEMBER_MENU_3);
		this.member4.setActionCommand(MEMBER_MENU_4);
		this.member5.setActionCommand(MEMBER_MENU_5);

		this.member1.addActionListener(new TemplateActionListener(this));
		this.member2.addActionListener(new TemplateActionListener(this));
		this.member3.addActionListener(new TemplateActionListener(this));
		this.member4.addActionListener(new TemplateActionListener(this));
		this.member5.addActionListener(new TemplateActionListener(this));

		//Codice nel caso si debbano aggiungere ltri bottoni 
		this.panelOvest.add(member1);
		/*is.panelOvest.add(member2);
				this.panelOvest.add(member3);
				this.panelOvest.add(member4);
				this.panelOvest.add(member5);*/
	}

	public void addMenuTraienr(){

		this.panelOvest.setLayout(new GridLayout(5, 1));	

		this.trainer1=new JButton("Add Event");
		this.trainer2=new JButton("Info2");
		this.trainer3=new JButton("Info2");
		this.trainer4=new JButton("Info2");
		this.trainer5=new JButton("Info2");

		this.trainer1.setActionCommand(TRAINER_MENU_1);
		this.trainer2.setActionCommand(TRAINER_MENU_2);
		this.trainer3.setActionCommand(TRAINER_MENU_3);
		this.trainer4.setActionCommand(TRAINER_MENU_4);
		this.trainer5.setActionCommand(TRAINER_MENU_5);

		this.trainer1.addActionListener(new TemplateActionListener(this));
		this.trainer2.addActionListener(new TemplateActionListener(this));
		this.trainer3.addActionListener(new TemplateActionListener(this));
		this.trainer4.addActionListener(new TemplateActionListener(this));
		this.trainer5.addActionListener(new TemplateActionListener(this));

		this.panelOvest.add(trainer1);
		/*this.panelOvest.add(trainer2);
		this.panelOvest.add(trainer3);
		this.panelOvest.add(trainer4);
		this.panelOvest.add(trainer5);*/
	}	

	public void addMenuCenterManager(){

		this.panelOvest.setLayout(new GridLayout(7, 1));	

		this.centerManager1=new JButton("Accept registration");
		this.centerManager2=new JButton("Add activity");
		this.centerManager3=new JButton("Add level");
		this.centerManager4=new JButton("Add room");
		this.centerManager5=new JButton("Add activity type");
		this.centerManager6=new JButton("Accept payment");
		this.centerManager7=new JButton("Accept modify request");

		this.centerManager1.setActionCommand(CENTERMANAGER_MENU_1);
		this.centerManager2.setActionCommand(CENTERMANAGER_MENU_2);
		this.centerManager3.setActionCommand(CENTERMANAGER_MENU_3);
		this.centerManager4.setActionCommand(CENTERMANAGER_MENU_4);
		this.centerManager5.setActionCommand(CENTERMANAGER_MENU_5);
		this.centerManager6.setActionCommand(CENTERMANAGER_MENU_6);
		this.centerManager7.setActionCommand(CENTERMANAGER_MENU_7);

		this.centerManager1.addActionListener(new TemplateActionListener(this));
		this.centerManager2.addActionListener(new TemplateActionListener(this));
		this.centerManager3.addActionListener(new TemplateActionListener(this));
		this.centerManager4.addActionListener(new TemplateActionListener(this));
		this.centerManager5.addActionListener(new TemplateActionListener(this));
		this.centerManager6.addActionListener(new TemplateActionListener(this));
		this.centerManager7.addActionListener(new TemplateActionListener(this));

		this.panelOvest.add(centerManager1);
		this.panelOvest.add(centerManager2);
		this.panelOvest.add(centerManager3);
		this.panelOvest.add(centerManager4);
		this.panelOvest.add(centerManager5);
		this.panelOvest.add(centerManager6);
		this.panelOvest.add(centerManager7);

	}

	public void addMessageToPanel(JLabel msg){
		this.panelSud.add(msg);
		this.revalidate();
		this.repaint();
	}

	public void removeMessageToPanel(){
		this.panelSud.add(new JLabel(""));
		this.revalidate();
		this.repaint();
	}

	public String getTypeUser(){
		return SessionCheck.getInstance().getTypeUser();
	}

	public boolean getSessionStatus(){
		return SessionCheck.getInstance().getStatusSession();
	}

	public void setPage(JPanel newPage)
	{
		this.panelCenter = new JScrollPane(newPage);
		this.panelCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.panelCenter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	public void removePage()
	{
		try
		{
			this.remove(this.panelCenter);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	//TODO per avere tutte le voci distribuite bisogna prendere la larghezza e divedrla per il numero di voi cosi da avere tutte la stessa grndezza
}
