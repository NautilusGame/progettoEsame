package progettoEsame.centropolisportivo.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.model.Activity;



public class PdfFromPanel extends JPanel {
	private String path;
	
	private static PdfFromPanel instance;
	private static JPanel printPanel;
	
	public static synchronized PdfFromPanel getInstance()
	{
		if(instance == null)
		{
			return new PdfFromPanel();
		}
		return instance;
	}
	
	
	public void GeneratePdfFromPanel(JPanel panel)
	{
		this.printPanel = panel;
		Document document = new Document();
		JFileChooser fileChooser= new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	
		int returnValue = fileChooser.showOpenDialog(null);	

		if (returnValue == JFileChooser.APPROVE_OPTION) 
		{
			path = fileChooser.getSelectedFile().getAbsolutePath();
		}
		
		try
		{
			PdfWriter.getInstance(document, new FileOutputStream(path+"\\test.pdf"));
		    document.open();
		    BufferedImage image=createImage(panel);
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ImageIO.write(image, "png", baos);
		    Image iTextImage = Image.getInstance(baos.toByteArray());
		    iTextImage.scaleAbsolute(this.printPanel.getWidth()/2, this.printPanel.getHeight()/2);
		    document.add(iTextImage);
		    document.close();
		
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public BufferedImage createImage(JPanel panel) {

	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    return bi;
	}
	
	
	

}




