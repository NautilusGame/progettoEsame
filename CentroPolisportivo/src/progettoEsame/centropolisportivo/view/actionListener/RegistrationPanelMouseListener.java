package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import progettoEsame.centropolisportivo.view.PdfFromPanel;

public class RegistrationPanelMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		PdfFromPanel.getInstance().GeneratePdfFromPanel(RegistrationPanelController.getInstance().getPDFsubscription());

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
