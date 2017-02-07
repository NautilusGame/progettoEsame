package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.actionListener.SessionCheck;
import progettoEsame.centropolisportivo.view.actionListener.TemplateActionListener;
import progettoEsame.centropolisportivo.view.actionListener.TemplateMenuListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class Template extends JPanel {

	private MainFrame mf;
	
	private JPanel panelNord;//Panel eddicato al banner e al menu nella sezione nord del template
	private JPanel panelOvest;//Panel dedicato a tutte le voci di menu aggiuntive per ogni entità
	private JPanel panelSud;//panel eddicato alla zona per i messaggi di errori
	private JPanel panelCenter;//pannello per richiamare tutte le pagine
	
	private JMenuBar menuBar;//menu comune a tutte e tre le entità
	private JMenu home;//per ritornare alla home
	private JMenu flyer;//per sfogliare il catalogo
	private JMenu profile;//per andare al profilo
	private JMenu logout;//per effettuare il logout
	private JMenu info;
	private JMenu inf1o;
	
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

	private BufferedImage image;
	private JLabel picLabel;
	
	//inizio del cotruttore della view
	public Template (MainFrame mf){
		this.setLayout(new BorderLayout());
		
		this.mf=mf;
		
		//inizializzazione di tutti i panel
		this.panelNord=new JPanel();
		this.panelOvest=new JPanel();
		this.panelSud=new JPanel();
		this.panelCenter=new JPanel();
		
		this.panelNord.setLayout(new GridLayout(2, 1));
		
		//caricamento del banner del sito trimite immagine
		try {                
			image = ImageIO.read(new File("ciao.jpg"));//TODO inserire il banner
			picLabel= new JLabel(new ImageIcon(image));
			
			//TODO aggiungere il richiamo alla home quado si preme sull'immagine
			
			this.panelNord.add(picLabel);
		} 
		catch (IOException ex) {
			this.addMessageToPanel(Message.getInstance().printErrorMsg("Immagine non trovata"));
		}
		
		
		//creazione del menu generico uguale per tutti
		this.menuBar = new JMenuBar();
		this.home = new JMenu("Home");
		this.flyer = new JMenu("Catalogo");
		this.profile = new JMenu("Profile");
		this.logout = new JMenu("Logout");
		this.info = new JMenu("Info");
		
		//this.home.setPreferredSize(new Dimension(80,20));
		this.menuBar.setBorderPainted(true);
		
		this.home.setActionCommand(ConstantClass.HOME_ACTION_CMD);
		this.flyer.setActionCommand(ConstantClass.FLYER_ACTION_CMD);
		this.profile.setActionCommand(ConstantClass.PROFILE_ACTION_CMD);
		this.logout.setActionCommand(ConstantClass.LOGOUT_ACTION_CMD);
		this.info.setActionCommand(ConstantClass.INFO_ACTION_CMD);
		
		this.home.addMenuListener(new TemplateMenuListener(this,this.mf) );
		this.flyer.addMenuListener(new TemplateMenuListener(this,this.mf) );
		this.profile.addMenuListener(new TemplateMenuListener(this,this.mf) );
		this.logout.addMenuListener(new TemplateMenuListener(this,this.mf) );
		this.info.addMenuListener(new TemplateMenuListener(this,this.mf) );

		this.menuBar.add(home);
		this.menuBar.add(flyer);
		this.menuBar.add(profile);
		this.menuBar.add(logout);
		this.menuBar.add(info);

		this.panelNord.add(menuBar);
		

		//si verifica quale tipo di utente ha fatto l'accesso e si aggiunge il menu dedicato
		if (this.getTypeUser().equals("member")){
			this.addMenuMember();
		}
		else if (this.getTypeUser().equals("trainer")){
			this.addMenuTraienr();
		}
		else{
			this.addMenuCenterManager();
		}
		
		//aggiunta di tutti i pannelli creati al panel template
		this.add(panelNord, BorderLayout.NORTH);
		this.add(panelOvest, BorderLayout.WEST);
		this.add(panelSud, BorderLayout.SOUTH);
		this.add(panelCenter, BorderLayout.CENTER);
		
	}	
	
	/*
	 * Funzioni per l'aggiunta del menu laterale in base al tipo di utente che ha effettuato il login
	 */
	
	//TODO cambiare le voci dei menu dedicati per ogni utente
	public void addMenuMember(){
		
		this.panelOvest.setLayout(new GridLayout(5, 1));	
		
		this.member1=new JButton("Info1");
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
		
		this.panelOvest.add(member1);
		this.panelOvest.add(member2);
		this.panelOvest.add(member3);
		this.panelOvest.add(member4);
		this.panelOvest.add(member5);
	}
	
	public void addMenuTraienr(){
		
		this.panelOvest.setLayout(new GridLayout(5, 1));	
		
		this.trainer1=new JButton("Info2");
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
		this.panelOvest.add(trainer2);
		this.panelOvest.add(trainer3);
		this.panelOvest.add(trainer4);
		this.panelOvest.add(trainer5);
	}	
	
	public void addMenuCenterManager(){
		
		this.panelOvest.setLayout(new GridLayout(5, 1));	
		
		this.centerManager1=new JButton("Info3");
		this.centerManager2=new JButton("Info3");
		this.centerManager3=new JButton("Info3");
		this.centerManager4=new JButton("Info3");
		this.centerManager5=new JButton("Info3");
		
		this.centerManager1.setActionCommand(CENTERMANAGER_MENU_1);
		this.centerManager2.setActionCommand(CENTERMANAGER_MENU_2);
		this.centerManager3.setActionCommand(CENTERMANAGER_MENU_3);
		this.centerManager4.setActionCommand(CENTERMANAGER_MENU_4);
		this.centerManager5.setActionCommand(CENTERMANAGER_MENU_5);
		
		this.centerManager1.addActionListener(new TemplateActionListener(this));
		this.centerManager2.addActionListener(new TemplateActionListener(this));
		this.centerManager3.addActionListener(new TemplateActionListener(this));
		this.centerManager4.addActionListener(new TemplateActionListener(this));
		this.centerManager5.addActionListener(new TemplateActionListener(this));
		
		this.panelOvest.add(centerManager1);
		this.panelOvest.add(centerManager2);
		this.panelOvest.add(centerManager3);
		this.panelOvest.add(centerManager4);
		this.panelOvest.add(centerManager5);
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
	
	public void setPage(JPanel newPage)
	{
		this.panelCenter.add(newPage);
		this.revalidate();
		this.repaint();
	}
	
	
	
	//TODO per avere tutte le voci distribuite bisogna prendere la larghezza e divedrla per il numero di voi cosi da avere tutte la stessa grndezza
}
