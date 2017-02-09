package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;

import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class CenterManagerRegistrationProposalActionListener implements ActionListener {

	public CenterManagerRegistrationProposalActionListener() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if((((JButton)e.getSource()).getName()).equals(ACCEPT_BUTTON_MEMBER_NAME))
		{
			Member newMember;
			try {
				newMember = Member.findByEmail(e.getActionCommand());
				newMember.setCenterManager(CenterManager.findByEmail(SessionCheck.getInstance().getEmail()));
				newMember.setConfirmed(1);
				Member.update(newMember);
				
			} catch (SQLException | SessionException e1) {
				e1.printStackTrace();
			}
		}
		else if((((JButton)e.getSource()).getName()).equals(DECLINE_BUTTON_MEMBER_NAME))
		{
			Member newMember;
			try {
				newMember = Member.findByEmail(e.getActionCommand());
				newMember.setConfirmed(2);
				newMember.setCenterManager(CenterManager.findByEmail(SessionCheck.getInstance().getEmail()));
				Member.update(newMember);
			} catch (SQLException | SessionException e1) {
				e1.printStackTrace();
			}
		}
		else if((((JButton)e.getSource()).getName()).equals(ACCEPT_BUTTON_TRAINER_NAME))
		{
			Trainer newTrainer;
			try {
				newTrainer = Trainer.findByEmail(e.getActionCommand());
				newTrainer.setConfirmed(1);
				newTrainer.setCenterManager(CenterManager.findByEmail(SessionCheck.getInstance().getEmail()));
				Trainer.update(newTrainer);
			} catch (SQLException | SessionException e1) {
				e1.printStackTrace();
			}
		}
		else if((((JButton)e.getSource()).getName()).equals(DECLINE_BUTTON_TRAINER_NAME))
		{
			Trainer newTrainer;
			try {
				newTrainer = Trainer.findByEmail(e.getActionCommand());
				newTrainer.setConfirmed(2);
				newTrainer.setCenterManager(CenterManager.findByEmail(SessionCheck.getInstance().getEmail()));
				Trainer.update(newTrainer);
			} catch (SQLException | SessionException e1) {
				e1.printStackTrace();
			}
		}
	}

}
