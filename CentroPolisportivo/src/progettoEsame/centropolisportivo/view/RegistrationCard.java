package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.business.LevelBusiness;
import progettoEsame.centropolisportivo.business.RegistrationActivityBusiness;
import progettoEsame.centropolisportivo.business.ScheduleBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.view.actionListener.RegistrationCardActionListener;
import progettoEsame.centropolisportivo.view.actionListener.SessionCheck;

public class RegistrationCard extends JPanel 
{
	private JPanel descriptionPanel;
	private JPanel reviewsPanel;
	private JPanel newReviewPanel;
	private JPanel paymentPanel;
	private JPanel mainPanel;
	
	private JScrollPane scr;
	private JScrollPane scrTextNewReview;
	
	private JLabel nameActivityLabel;
	private JLabel nameActivity;
	private JLabel infoActivityLabel;
	private JLabel priceActivityLabel;
	private JLabel priceActivity;
	private JLabel picActivity;
	private JLabel levels;
	private JLabel schedules;
	private JLabel titlePanel;
	private JLabel newReviewLabel;
	private JLabel msg;
	
	private JTextArea infoActivity;
	private JTextArea newTextReview;
	
	private JButton next;
	private JButton showReviews;
	private JButton newReview;
	
	private ReviewsQueue queueReviews;
	
	private JList<String> schedulesList;
	
	private JComboBox<String> listLevels;
	
	private BufferedImage image;
	
	private GridBagConstraints gbc;//contine tutti i dati dell activity
	private GridBagConstraints gbcReview;//contrine tutte le recensioni
	private GridBagConstraints gbcNewReview;//contiene i campi per una nuova recensione
	
	private int idActivity;
	
	public RegistrationCard(int idActivity)
	{		
		this.idActivity=idActivity;
		
		//inzializzazione dei componenti
		this.descriptionPanel= new JPanel();
		this.reviewsPanel= new JPanel();
		this.newReviewPanel= new JPanel();
		this.mainPanel=new JPanel();
		this.paymentPanel=new JPanel();
		
		this.queueReviews = new ReviewsQueue(this);//dichiara una nuova coda per tutte le recensioni scrite
		
		this.nameActivityLabel = new JLabel(ConstantClass.NAME_ACTIVITY_LABEL);
		this.nameActivity = new JLabel("");
		this.infoActivityLabel = new JLabel(ConstantClass.INFO_ACTIVITY_LABEL);
		this.priceActivityLabel = new JLabel(ConstantClass.PRICE_ACTIVITY_LABEL);
		this.priceActivity=new JLabel("");
		this.levels=new JLabel(ConstantClass.LEVELS_LIST);
		this.schedules = new JLabel(ConstantClass.SCHEDULE_LIST);
		this.titlePanel = new JLabel("Registration");
		this.newReviewLabel = new JLabel(ConstantClass.LABEL_NEW_REVIEW);
		this.msg=new JLabel("");
		
		this.infoActivity = new JTextArea("");		
		this.newTextReview= new JTextArea("");
		
		this.listLevels = new JComboBox<String>();//TODO passare il risultato di tutti i livelli presenti per un activity
		
		this.schedulesList = new JList<String>();
		
		this.next=new JButton(ConstantClass.NEXT_STEP_REGISTRATION);
		this.showReviews=new JButton(ConstantClass.SHOW_REVIEWS);
		this.newReview=new JButton(ConstantClass.NEW_REVIEW);
		
		//caricamento dei dti della activity selezionata
		try
		{
			this.setData(ScheduleBusiness.getInstance().getScheduleByActivity(this.idActivity),LevelBusiness.getInstance().getAllLevels(),ActivityBusiness.getInstance().getActivityById(this.idActivity));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.noNewReview();
		
		//set dei componenti a livello grafico della infoActivity
		this.reviewsPanel.setVisible(false);
		
		this.infoActivity.setPreferredSize(new Dimension(75,50));		
		this.infoActivity.setLineWrap(true); //va a capo con il testo automaticamente
		this.infoActivity.setEditable(false);
		Color defaultColor = this.getBackground();//per non fare sembrare l differenza con lo sfondo
		this.infoActivity.setBackground(defaultColor);
		
		this.scr = new JScrollPane(infoActivity);//rende la JText area scrollabile
        this.scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.scrTextNewReview =new JScrollPane(newTextReview);
        this.scrTextNewReview.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
		this.schedulesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.titlePanel.setFont(new Font("Arial", Font.BOLD, 16));
		
		//set del layout dei pannelli
		//this.setLayout(new BorderLayout());
		
		this.mainPanel.setLayout(new BorderLayout());
		
		this.descriptionPanel.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		
		this.reviewsPanel.setLayout(new GridBagLayout());
		this.gbcReview = new GridBagConstraints();
		
		this.newReviewPanel.setLayout(new GridBagLayout());
		this.gbcNewReview = new GridBagConstraints();
		
		this.paymentPanel.setVisible(false);
		
		/**************************************regitsrazione*****************************/
		//label info activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(20,0,10,0); //padding dell'elemento
		this.descriptionPanel.add(this.titlePanel,gbc);
		
		//label nome activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0); //padding dell'elemento
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.anchor = GridBagConstraints.CENTER; //lo centra		
		this.descriptionPanel.add(this.nameActivityLabel,gbc);
		
		//nome activity
		this.gbc.gridx = 2;
		this.gbc.gridy = 0;
		this.descriptionPanel.add(this.nameActivity,gbc);

		//label info activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.descriptionPanel.add(this.infoActivityLabel,gbc);

		//info activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.ipady=40;//setta la altezza
		this.gbc.ipadx=150;//setta la larghezza
		this.gbc.gridwidth=2;//indica quante colonne puo occupare			
		this.descriptionPanel.add(scr,gbc);
		
		//label prezzo activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.gbc.ipady= 0;
		this.gbc.ipadx= 0;
		this.gbc.gridwidth=1;
		this.descriptionPanel.add(this.priceActivityLabel,gbc);
		
		//prezzo activity
		this.gbc.gridx = 2;
		this.gbc.gridy = 3;
		this.descriptionPanel.add(this.priceActivity,gbc);
		
		//label combobox per i livelli
		this.gbc.gridx = 1;
		this.gbc.gridy = 4;
		this.descriptionPanel.add(this.levels,gbc);

		//combobox per i livelli
		this.gbc.gridx = 2;
		this.gbc.gridy = 4;
		this.descriptionPanel.add(this.listLevels,gbc);
		
		//label per gli orari
		this.gbc.gridx = 1;
		this.gbc.gridy = 5;
		this.descriptionPanel.add(this.schedules,gbc);
		
		//lista orari
		this.gbc.gridx = 2;
		this.gbc.gridy = 5;
		this.descriptionPanel.add(this.schedulesList,gbc);

		//bottone per la registreazione
		this.gbc.gridx = 2;
		this.gbc.gridy = 6;
		this.descriptionPanel.add(this.next,gbc);
		
		//isnerimento dell'immagine
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;	
		this.gbc.gridheight=5;
		this.descriptionPanel.add(this.picActivity,gbc);
		
		//bottone per mostrare le recensioni
		this.gbc.gridx = 0;
		this.gbc.gridy = 6;
		this.gbc.insets = new Insets(20,0,10,0); //padding dell'elemento
		this.descriptionPanel.add(this.showReviews,gbc);		
		
		
		/****************************recensioni*****************************/
		//lista recensioni
		this.gbcReview.gridx = 1;
		this.gbcReview.gridy = 8;
		this.gbcReview.insets = new Insets(15, 13, 0, 0); //padding dell'elemento
		this.gbcReview.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbcReview.anchor = GridBagConstraints.CENTER; //lo centra	
		if(this.queueReviews!=null)
			this.reviewsPanel.add(this.queueReviews,gbcReview);
		else 
			this.reviewsPanel.add(new JLabel("No reviews about thia activity"));
		
		/****************************new Review******************************/
		//label per la nuova recensione
		this.gbcNewReview.gridx = 0;
		this.gbcNewReview.gridy = 0;
		this.newReviewPanel.add(this.newReviewLabel,gbcNewReview);	
		
		//text new review
		this.gbcNewReview.gridx = 0;
		this.gbcNewReview.gridy = 1;
		this.gbcNewReview.ipady=50;//setta la altezza
		this.gbcNewReview.ipadx=400;//setta la larghezza
		this.gbcNewReview.gridwidth=5;//indica quante colonne puo occupare
		this.newReviewPanel.add(this.scrTextNewReview,gbcNewReview);		
		
		//button new review
		this.gbcNewReview.gridx = 0;
		this.gbcNewReview.gridy = 2;
		this.gbcNewReview.ipady=1;//setta la altezza
		this.gbcNewReview.ipadx=0;//setta la larghezza
		this.gbcNewReview.gridwidth=1;//indica quante colonne puo occupare	
		this.gbcNewReview.insets = new Insets(0,0,20,0); //padding dell'elemento
		this.newReviewPanel.add(this.newReview,gbcNewReview);
		

		//action listener
		this.showReviews.addActionListener(new RegistrationCardActionListener(this));
		this.showReviews.setActionCommand(ConstantClass.SHOW_REVIEWS);
		
		this.newReview.addActionListener(new RegistrationCardActionListener(this));
		this.newReview.setActionCommand(ConstantClass.NEW_REVIEW_BUTTON);
		
		this.next.addActionListener(new RegistrationCardActionListener(this));
		this.next.setActionCommand(ConstantClass.NEXT_STEP_REGISTRATION);
		
		//TODO aggiungere il pannello con tutte le review e aggiungerlo al pannello delle review this
		this.mainPanel.add(this.descriptionPanel,BorderLayout.NORTH);	
		this.mainPanel.add(this.newReviewPanel,BorderLayout.SOUTH);	
		
		this.add(this.mainPanel);
	}
	
	private Image getScaledImage(Image srcImg, int w, int h)
	{
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public ArrayList<ArrayList<String>> getData()
	{
		ListModel model=this.schedulesList.getModel();
		ArrayList<String> schedule = new ArrayList<>();
		ArrayList<String> dataField= new ArrayList<>();
		ArrayList<ArrayList<String>> dataRegistration= new ArrayList<>();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 
		int[] selectedIx = this.schedulesList.getSelectedIndices();  
		//serve per prendere tutti gli orari selezioanti
		if(selectedIx.length!=0)
		{
			for(int i=0; i < selectedIx.length; i++)
			{
			     schedule.add(String.valueOf(this.schedulesList.getModel().getElementAt(selectedIx[i])));  
			}
		}
		else schedule.add(null);
		
		//server per pender i dati rimanenti
		try
		{
			dataField.add(SessionCheck.getInstance().getEmail());//id member -------0
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(String.valueOf(listLevels.getSelectedItem()));
		c.setTime(new java.util.Date()); // Prende la data attuale		
		dataField.add(String.valueOf(listLevels.getSelectedItem()));//livello selezionato ----1
		dataField.add(String.valueOf(java.sql.Date.valueOf(sdf.format(c.getTime()))));//data attuale ------2
		dataField.add(String.valueOf(this.idActivity));//id_activity -------3
		c.add(Calendar.DATE, 30); // aggiunge 30 giorni
		dataField.add(String.valueOf(java.sql.Date.valueOf(sdf.format(c.getTime()))));//data di scadenza ------------------4
		
		dataRegistration.add(schedule);//-------0
		dataRegistration.add(dataField);//-------1
		
		return dataRegistration;
	}
	
	
	public void setData(ArrayList<ArrayList<String>> schedules,ArrayList<String> levels,ArrayList<String> valueField)
	{
		DefaultListModel<String> model=new DefaultListModel<String>();
		if(schedules!=null)
		{
			for(int i =0; i<schedules.size();i++)//carico gli oraari
			{
				String foo =schedules.get(i).get(1)+" "+schedules.get(i).get(2);
				model.addElement(foo);
			}
			this.schedulesList=new JList<>(model);
		}
		else System.out.println("Contatta lo sviluppatore, è prensente un errore di codice :)");
		
		for(int i =0; i<levels.size();i++)//carico i livelli
		{
			this.listLevels.addItem(levels.get(i));
		}		
		
		//CARICO I DATI DELL'ACTIVITY
		this.nameActivity.setText(valueField.get(1));
		
		try 
		{
			image = ImageIO.read(new File(valueField.get(4)));
			picActivity= new JLabel(new ImageIcon(getScaledImage(image, 275, 275)));			
		} 
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		this.priceActivity.setText(valueField.get(3));
		
		this.infoActivity.setText(valueField.get(2));
		
	}
	
	
	public void showReviews()
	{
		this.mainPanel.add(this.queueReviews,BorderLayout.CENTER);
		this.showReviews.setActionCommand(ConstantClass.HIDE_REVIEWS);
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}
	
	public void removeReviews()
	{
		this.showReviews.setActionCommand(ConstantClass.SHOW_REVIEWS);
		this.mainPanel.remove(this.queueReviews);
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
	}
	
	private void noNewReview() 
	{
		try
		{
			if((RegistrationActivityBusiness.getInstance().memberJoinedActivity(Session.getInstance().getEmail(), this.idActivity))&&(SessionCheck.getInstance().getTypeUser().equals("member")))
				this.newReviewPanel.setVisible(true);
			else
				this.newReviewPanel.setVisible(false);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getReviewData()
	{
		ArrayList<String> tmp=new ArrayList<>();
		tmp.add(String.valueOf(this.idActivity));
		try
		{
			tmp.add(SessionCheck.getInstance().getEmail());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		tmp.add(this.newTextReview.getText());
		tmp.add("2");
		return tmp;		
	}
	
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		this.gbcNewReview.gridx = 3;
		this.gbcNewReview.gridy = 2;
		this.newReviewPanel.add(this.msg,gbcNewReview);	
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{
		this.gbcNewReview.gridx = 3;
		this.gbcNewReview.gridy = 2;
		this.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
	
	public void showPaymentPanel()
	{
		this.mainPanel.setVisible(false);
		this.paymentPanel=new JPanel();
		this.paymentPanel.add(new PaymentView(Double.parseDouble(this.priceActivity.getText()),this.idActivity,"Activity",this));
		this.add(this.paymentPanel);
		this.paymentPanel.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	public void hidePaymentPanel()
	{
		this.paymentPanel.setVisible(false);
		this.remove(this.paymentPanel);
		this.mainPanel.setVisible(true);
		this.revalidate();
		this.repaint();
	}

}
