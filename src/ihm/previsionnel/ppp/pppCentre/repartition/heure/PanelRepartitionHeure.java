package ihm.previsionnel.ppp.pppCentre.repartition.heure;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;

import java.awt.Color;

import javax.swing.JPanel;

import ihm.previsionnel.ppp.pppCentre.repartition.PanelRepartition;

public class PanelRepartitionHeure extends JPanel{
	private PanelRepartition panelMere;
	//private PanelRepartitionHGauche panelRepartitionHGauche;
	private PanelRepartitionHDroite panelRepartitionHDroite;

	public PanelRepartitionHeure(PanelRepartition panelMere){
		this.panelMere = panelMere;
		this.setLayout(new GridLayout(1,1));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		//this.panelRepartitionHGauche = new PanelRepartitionHGauche(this);
		this.panelRepartitionHDroite = new PanelRepartitionHDroite();

		//this.add(this.panelRepartitionHGauche);
		this.add(this.panelRepartitionHDroite);
	}

	/*public HashMap<String, Integer> getNbSemaines() {
		return this.panelRepartitionHGauche.getNbSemaines();
	}*/
	
}