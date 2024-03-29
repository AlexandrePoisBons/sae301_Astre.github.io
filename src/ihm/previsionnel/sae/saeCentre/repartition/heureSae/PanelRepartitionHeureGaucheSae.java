package ihm.previsionnel.sae.saeCentre.repartition.heureSae;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import metier.Heure;
import metier.Module;
import metier.TypeHeure;

public class PanelRepartitionHeureGaucheSae extends JPanel implements FocusListener, ActionListener {

	private PanelRepH panelMere;
	private ArrayList<JTextField> ensJTextField;

	private Module module;

	private int sommeHSae = 0;
	private int sommeHTut = 0;
	private int totalSomme = 0;

	public PanelRepartitionHeureGaucheSae(PanelRepH panelRepH, Module m) {
		this.panelMere = panelRepH;
		this.module = m;
		this.ensJTextField = new ArrayList<JTextField>();


		JPanel panelHC	= new JPanel();
		
		for(int cpt=0; cpt < 4; cpt++) {
			JTextField jtf = new JTextField(3);
			this.ensJTextField.add(jtf);
			if(cpt>1){
				jtf.setEditable(false);
			}
		}

		panelHC.setLayout(new GridBagLayout());
		GridBagConstraints gbcH = new GridBagConstraints();


		gbcH.gridx = 1;
		gbcH.gridy = 0;
		gbcH.insets = new Insets(10, 0, 2, 5);
		panelHC.add(new JLabel("h Sae"), gbcH);
		gbcH.gridx = 2;
		panelHC.add(new JLabel("h Tut"), gbcH);


		gbcH.gridx = 0;
		gbcH.gridy = 1;
		gbcH.insets = new Insets(0, 0, 2, 0);
		panelHC.add(new JLabel("Total promo (eqtd)"), gbcH);
		gbcH.gridy = 2;
		panelHC.add(new JLabel("Total affect (eqtd)"), gbcH);
		gbcH.insets = new Insets(0, 0, 2, 0);
		gbcH.gridx = 1;
		gbcH.gridy = 1;
		panelHC.add(this.ensJTextField.get(0), gbcH);
		gbcH.gridx = 2;
		panelHC.add(this.ensJTextField.get(1), gbcH);
		gbcH.gridy = 2;
		gbcH.gridx = 1;
		gbcH.insets = new Insets(0, 0, 2, 0);
		panelHC.add(this.ensJTextField.get(2), gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 0, 2, 0);
		panelHC.add(this.ensJTextField.get(3), gbcH);

		this.add(panelHC);
		//implémentation des listener

		int hTut = 0;
		int hSae = 0;
		for (Heure h : this.module.getHeures()) {
			if(h.getTypeHeure().getNomTypeHeure().equals("TUT")){
				hTut += h.getDuree();
			}
			if(h.getTypeHeure().getNomTypeHeure().equals("SAE")) {
				hSae += h.getDuree();
			}
		}
		if(hSae != 0 || hTut != 0){
			this.setHeureAffecte(hSae, hTut);
			this.actualiserSomme();
		}
		

		for(int i=0;i<this.ensJTextField.size()/2;i++){
			this.ensJTextField.get(i).addActionListener(this);
			this.ensJTextField.get(i).addFocusListener(this);
		}

		if ( this.module != null )
			this.initValues();

		this.setVisible(true);
	}


	private void initValues() {
		HashMap<TypeHeure, HashMap<String,Integer>> map = this.panelMere.getHeuresParTypesHeures(this.module);

		if ( map != null )
			for (TypeHeure typeHeure : map.keySet()) {
				switch (typeHeure.getNomTypeHeure()) {
					case "SAE"  -> {
						this.ensJTextField.get(0).setText(""+map.get(typeHeure).get("nb_heures"));
					} case "TUT"  -> {
						this.ensJTextField.get(1).setText(""+map.get(typeHeure).get("nb_heures"));
					}
				}
			}
	}

	public HashMap<String, HashMap<String,Integer>> getDataHeuresTypesHeures() {
		HashMap<String, HashMap<String,Integer>> map = new HashMap<>();

		HashMap<String,Integer> temp = new HashMap<>();
		temp.put("nb_heures", this.getIntVal(this.ensJTextField.get(0)) );
		map.put("SAE", temp);

		temp = new HashMap<>();
		temp.put("nb_heures", this.getIntVal(this.ensJTextField.get(1)) );
		map.put("TUT", temp);

		return map;
	}

	public int getIntVal(JTextField txt) {
		int nb;
		try {
			nb = Integer.parseInt(txt.getText());
		} catch(NumberFormatException e) { nb=0; }

		return nb;
	}


	public void setLabelErreur(String message) { this.panelMere.setLabelErreur(message); }
	//méthode pour gérer la couleur du cadre en fonction de la validité de la saisie
	public void setCouleurErreur(boolean b, JTextField txt) {
		if (b) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		} else {
			txt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		}
	}


	public HashMap<String, Integer> getNbSemaines() {
		HashMap<String, Integer> map = new HashMap<>();
	
		try { map.put("SAE", Integer.parseInt(this.ensJTextField.get(0).getText())); }
		catch (NumberFormatException e) { map.put("SAE", 0); }

		try { map.put("TUT", Integer.parseInt(this.ensJTextField.get(1).getText())); }
		catch (NumberFormatException e) { map.put("TUT", 0); }

		return map;
	}

	public void setSommePromo(int somme) {
		this.panelMere.setSommePromo(somme);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.ensJTextField.get(0)) {
			//pour que le focus passe au champ suivant quand  l'utilisteur clique sur "entrée"
			this.ensJTextField.get(0).transferFocus();
		}
		else if(e.getSource() == this.ensJTextField.get(1)) {

			this.ensJTextField.get(1).transferFocusBackward();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == this.ensJTextField.get(0)) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if(this.panelMere.estChiffre(this.ensJTextField.get(0).getText())==false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.ensJTextField.get(0));
				}
				else {
					if (this.ensJTextField.get(0).getText().equals("") || Integer.parseInt(this.ensJTextField.get(0).getText()) < 0)
						this.ensJTextField.get(0).setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.ensJTextField.get(0));
					this.sommeHSae = Integer.parseInt(this.ensJTextField.get(0).getText());
				}
			} catch (Exception err) {}
		}

		if (e.getSource() == this.ensJTextField.get(1)) {
			try {
				// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
				// somme
				if(this.panelMere.estChiffre(this.ensJTextField.get(1).getText())==false) {
					this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
					this.setCouleurErreur(true, this.ensJTextField.get(1));
				}
				else {
					if (this.ensJTextField.get(1).getText().equals("")|| Integer.parseInt(this.ensJTextField.get(1).getText()) < 0)
						this.ensJTextField.get(1).setText("0");
					this.setLabelErreur("");
					this.setCouleurErreur(false, this.ensJTextField.get(1));
					this.sommeHTut = Integer.parseInt(this.ensJTextField.get(1).getText());
				}
			} catch (Exception err) {}
		}
		this.totalSomme = this.sommeHSae + this.sommeHTut;
		this.setSommePromo(this.totalSomme);
	}
	@Override
	public void focusGained(FocusEvent e) {
		// ré-affichage du label erreur si il y a une erreur dans un des txtField
		if (e.getSource() == this.ensJTextField.get(0)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(0).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(0));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(0));
			}
		}

		if (e.getSource() == this.ensJTextField.get(1)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(1).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(1));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(1));
			}
		}
	}

	public void setHeureAffecte(int hSae, int hTut) {
		this.ensJTextField.get(2).setText("" + hSae);
		this.ensJTextField.get(3).setText("" + hTut);
		this.repaint();
		this.revalidate();
	}


	public void actualiserSomme() {
		int somme = 0;
		for(int i=2;i<this.ensJTextField.size();i++) {
			somme += Integer.parseInt(this.ensJTextField.get(i).getText());
		}
		this.panelMere.setSommeAffecte(somme);
	}


}
