package progettoEsame.centropolisportivo.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.view.actionListener.AddLevelActionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;


public class AddNewLevel extends JPanel{
	
	private JLabel titlePage;
	private JLabel levelNameLabel;
	private JLabel descriptionLabel;
	private JTextField levelNameTextField;
	private JTextArea levelDescriptionTextArea;
	private JScrollPane descriptionScrollPane;
	private JButton insertButton;
	private GridBagConstraints gbc;
	public AddNewLevel()
	{
		this.setSize(new Dimension(400, 400));
		this.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.gbc.insets = new Insets(35, 13, 0, 0);
		this.titlePage = new JLabel(ADD_LEVEL_TITLE_PAGE);
		this.levelNameLabel = new JLabel(ADD_LEVEL_LEVEL_NAME);
		this.descriptionLabel = new JLabel(ADD_LEVEL_DESCRIPTION);
		this.insertButton = new JButton(ADD_LEVEL_INSERT_BUTTON_TEXT);
		this.levelNameTextField = new JTextField(15);
		this.levelDescriptionTextArea = new JTextArea(10,2);
	    this.levelDescriptionTextArea.setMargin(new Insets(10, 15, 20, 20));
		this.descriptionScrollPane = new JScrollPane(this.levelDescriptionTextArea);
		this.titlePage.setFont(new Font(Font.SANS_SERIF, 10, 30));
		this.insertButton.addActionListener(new AddLevelActionListener(this));
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.add(titlePage, gbc);
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.add(levelNameLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.add(levelNameTextField, gbc);
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.add(descriptionLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.add(descriptionScrollPane, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.add(insertButton, gbc);
		
	}
	
	public void addMessageToPanel(JLabel msg)
	{
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(msg,gbc);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(new JLabel(""),gbc);
		this.revalidate();
		this.repaint();
	}
	
	
	public Level getLevelData()
	{
		Level newLevel = new Level(null, this.levelNameTextField.getText(), this.levelDescriptionTextArea.getText());
		return newLevel;
	}
}
