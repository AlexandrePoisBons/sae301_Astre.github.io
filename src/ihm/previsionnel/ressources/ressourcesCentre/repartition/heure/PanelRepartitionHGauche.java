package ihm.previsionnel.ressources.ressourcesCentre.repartition.heure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import metier.TypeHeure;
import metier.Module;

public class PanelRepartitionHGauche extends JPanel implements FocusListener, ActionListener{

	private PanelRepartitionHeure panelMere;

	private JPanel                panelHeure;
	private ArrayList<JTextField> ensJTextField;

	private Module module;

	public PanelRepartitionHGauche(PanelRepartitionHeure panelMere, Module m){
		this.panelMere = panelMere;
		this.module = m;

		this.panelHeure = new JPanel();
		this.ensJTextField = new ArrayList<JTextField>();

		this.setLayout(new GridLayout(2, 1));
		this.panelHeure.setLayout(new BorderLayout());

		JPanel panelHN 	= new JPanel();
		JPanel panelHC	= new JPanel();
		JPanel panelB 	= new JPanel();
		for(int cpt=0; cpt < 6; cpt++){
			JTextField jtf = new JTextField(3);
			this.ensJTextField.add(jtf);
		}

		panelHN.setLayout(new GridLayout(1, 3));
		panelHC.setLayout(new GridBagLayout());
		GridBagConstraints gbcH = new GridBagConstraints();
		panelB.setLayout(new GridLayout(2, 1));


		gbcH.gridx = 0;
		gbcH.gridy = 0;
		panelHC.add(new JLabel("nbSem"), gbcH);
		gbcH.gridx = 1;
		panelHC.add(new JLabel("nbH"), gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(new JLabel("nbSem"), gbcH);
		gbcH.insets = new Insets(0, 0, 5, 0);
		gbcH.gridx = 3;
		panelHC.add(new JLabel("nbH"), gbcH);
		gbcH.gridx = 4;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(new JLabel("nbSem"), gbcH);
		gbcH.insets = new Insets(0, 0, 5, 0);
		gbcH.gridx = 5;
		panelHC.add(new JLabel("nbH"), gbcH);

		gbcH.gridx = 0;
		gbcH.gridy = 1;
		gbcH.insets = new Insets(0, 0, 5, 0);
		panelHC.add(this.ensJTextField.get(0), gbcH);
		gbcH.gridx = 1;
		panelHC.add(this.ensJTextField.get(1), gbcH);
		gbcH.gridx = 2;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(this.ensJTextField.get(2), gbcH);
		gbcH.gridx = 3;
		gbcH.insets = new Insets(0, 0, 5, 0);
		panelHC.add(this.ensJTextField.get(3), gbcH);
		gbcH.gridx = 4;
		gbcH.insets = new Insets(0, 7, 5, 0);
		panelHC.add(this.ensJTextField.get(4), gbcH);
		gbcH.gridx = 5;
		gbcH.insets = new Insets(0, 0, 5, 10);
		panelHC.add(this.ensJTextField.get(5), gbcH);

		panelHN.add(new JLabel("CM", JLabel.CENTER));
		panelHN.add(new JLabel("TD", JLabel.CENTER));
		panelHN.add(new JLabel("TP", JLabel.CENTER));

		panelB.add(new JLabel("Total promo (eqtd)", JLabel.RIGHT	));
		panelB.add(new JLabel("Total affect (eqtd)", JLabel.RIGHT	));

		this.panelHeure.add(panelHN, BorderLayout.NORTH);
		this.panelHeure.add(panelHC, BorderLayout.CENTER);

		for(int i=0;i<6;i++){
			this.ensJTextField.get(i).addActionListener(this);
			this.ensJTextField.get(i).addFocusListener(this);
		}

		this.add(this.panelHeure);
		this.add(panelB);

		if ( this.module != null )
			this.initValues();

	}

	public void initValues() {
		HashMap<TypeHeure, HashMap<String,Integer>> map = this.panelMere.getHeuresParTypesHeures(this.module);
		if ( map != null )
			for (TypeHeure typeHeure : map.keySet()) {
				switch (typeHeure.getNomTypeHeure()) {
					case "CM"  -> {
						this.ensJTextField.get(0).setText(""+map.get(typeHeure).get("nb_semaines"));
						this.ensJTextField.get(1).setText(""+map.get(typeHeure).get("nb_heures"));
					} case "TD"  -> {
						this.ensJTextField.get(2).setText(""+map.get(typeHeure).get("nb_semaines"));
						this.ensJTextField.get(3).setText(""+map.get(typeHeure).get("nb_heures"));
					} case "TP"  -> {
						this.ensJTextField.get(4).setText(""+map.get(typeHeure).get("nb_semaines"));
						this.ensJTextField.get(5).setText(""+map.get(typeHeure).get("nb_heures"));
					}
				}
			}

	}


	public HashMap<String, HashMap<String,Integer>> getDataHeuresTypesHeures() {
		HashMap<String, HashMap<String,Integer>> map = new HashMap<>();

		HashMap<String,Integer> temp = new HashMap<>();
		temp.put("nb_semaines", this.getIntVal(this.ensJTextField.get(0)) );
		temp.put("nb_heures", this.getIntVal(this.ensJTextField.get(1)) );
		map.put("CM", temp);

		temp = new HashMap<>();
		temp.put("nb_semaines", this.getIntVal(this.ensJTextField.get(2)) );
		temp.put("nb_heures", this.getIntVal(this.ensJTextField.get(3)) );
		map.put("TD", temp);

		temp = new HashMap<>();
		temp.put("nb_semaines", this.getIntVal(this.ensJTextField.get(4)) );
		temp.put("nb_heures", this.getIntVal(this.ensJTextField.get(5)) );
		map.put("TP", temp);

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

	public HashMap<String, Integer> getNbSemaines(){
		HashMap<String, Integer> map = new HashMap<>();

		try { map.put("CM",Integer.parseInt(this.ensJTextField.get(0).getText())); }
		catch(NumberFormatException e) { map.put("CM", 0); }
		try { map.put("TD",Integer.parseInt(this.ensJTextField.get(2).getText())); }
		catch(NumberFormatException e) { map.put("TD", 0); }
		try { map.put("TP",Integer.parseInt(this.ensJTextField.get(4).getText())); }
		catch(NumberFormatException e) { map.put("TP", 0); }

		return map;
	}

	public int getNbHeureSemaine(int i) {
		return Integer.parseInt(this.ensJTextField.get(i).getText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ensJTextField.get(0)) {
			// pour que le focus passe au champ suivant quand l'utilisteur clique sur
			// "entrée"
			this.ensJTextField.get(0).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(1)) {

			this.ensJTextField.get(1).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(2)) {
			this.ensJTextField.get(2).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(3)) {
			this.ensJTextField.get(3).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(4)) {
			this.ensJTextField.get(4).transferFocus();
		} else if (e.getSource() == this.ensJTextField.get(5)) {
			this.ensJTextField.get(5).transferFocus();
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		try {
			// Addition des heures saisies dans les champs
			int somme = -1; // avant: 0
			if (e.getSource() == this.ensJTextField.get(0)) {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if(this.panelMere.estChiffre(this.ensJTextField.get(0).getText())==false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(0));
					}
					else {
						if (this.ensJTextField.get(0).getText().equals("")|| Integer.parseInt(this.ensJTextField.get(0).getText())<0)
							this.ensJTextField.get(0).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(0));
						if (this.ensJTextField.get(1).getText().equals("0")||this.ensJTextField.get(1).getText().equals("")){
							somme += Integer.parseInt(this.ensJTextField.get(0).getText());
							this.panelMere.setSommeCM();
							this.panelMere.setSommeTotal();
						}
						
						else {
							somme = Integer.parseInt(this.ensJTextField.get(1).getText())
									* Integer.parseInt(this.ensJTextField.get(0).getText());
							this.panelMere.setSommeCM(somme);
							this.panelMere.setSommeTotal();
						}
					}
			}

			if (e.getSource() == this.ensJTextField.get(1)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if(this.panelMere.estChiffre(this.ensJTextField.get(1).getText())==false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(1));
					}
					else{
						if (this.ensJTextField.get(1).getText().equals("") || Integer.parseInt(this.ensJTextField.get(1).getText())<0)
							this.ensJTextField.get(1).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(1));
						if (this.ensJTextField.get(0).getText().equals("0")||this.ensJTextField.get(0).getText().equals("")){	
							somme += Integer.parseInt(this.ensJTextField.get(1).getText());
							this.panelMere.setSommeCM();
							this.panelMere.setSommeTotal();
						}
						else {
							somme = Integer.parseInt(this.ensJTextField.get(1).getText())
									* Integer.parseInt(this.ensJTextField.get(0).getText());
							this.panelMere.setSommeCM(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) { }
			}

		} catch (Exception ex) { }

		try {
			// Addition des heures saisies dans les champs
			int somme = 0; //avant: -1
			if (e.getSource() == this.ensJTextField.get(2)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if(this.panelMere.estChiffre(this.ensJTextField.get(2).getText())==false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(2));
					}
					else {
						if (this.ensJTextField.get(2).getText().equals("") || Integer.parseInt(this.ensJTextField.get(2).getText())<0)
							this.ensJTextField.get(2).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(2));
						if (this.ensJTextField.get(3).getText().equals("0")||this.ensJTextField.get(3).getText().equals("")){
							somme += Integer.parseInt(this.ensJTextField.get(2).getText());
							this.panelMere.setSommeTD();
							this.panelMere.setSommeTotal();
						}
						else {
							somme = Integer.parseInt(this.ensJTextField.get(2).getText())
									* Integer.parseInt(this.ensJTextField.get(3).getText());
							this.panelMere.setSommeTD(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) {
					System.out.print("");
				}
			}

			if (e.getSource() == this.ensJTextField.get(3)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if(this.panelMere.estChiffre(this.ensJTextField.get(3).getText())==false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(3));
					}
					else {
						if (this.ensJTextField.get(3).getText().equals("") || Integer.parseInt(this.ensJTextField.get(3).getText())<0)
							this.ensJTextField.get(3).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(3));
						if (this.ensJTextField.get(2).getText().equals("0")||this.ensJTextField.get(2).getText().equals("")){
							somme += Integer.parseInt(this.ensJTextField.get(3).getText());
							this.panelMere.setSommeTD();
							this.panelMere.setSommeTotal();
						}
						else {
							somme = Integer.parseInt(this.ensJTextField.get(2).getText())
									* Integer.parseInt(this.ensJTextField.get(3).getText());
							this.panelMere.setSommeTD(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) {
					System.out.print("");
				}
			}
		} catch (Exception ex) {
			System.err.print("");
		}

		try {
			// Addition des heures saisies dans les champs
			int somme = 0; //avant: -1

			if (e.getSource() == this.ensJTextField.get(4)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if(this.panelMere.estChiffre(this.ensJTextField.get(4).getText())==false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(4));
					}
					else {
						if (this.ensJTextField.get(4).getText().equals("") || Integer.parseInt(this.ensJTextField.get(4).getText())<0)
							this.ensJTextField.get(4).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(4));
						if (this.ensJTextField.get(5).getText().equals("0")||this.ensJTextField.get(5).getText().equals("")){
							somme += Integer.parseInt(this.ensJTextField.get(4).getText());
							this.panelMere.setSommeTP();
							this.panelMere.setSommeTotal();
						}
						else {
							somme = Integer.parseInt(this.ensJTextField.get(4).getText())
									* Integer.parseInt(this.ensJTextField.get(5).getText());
							this.panelMere.setSommeTP(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) { }
			}

			if (e.getSource() == this.ensJTextField.get(5)) {
				try {
					// Vérification que la saisie de cette valeur n'a pas déjas été enregistrée dans
					// somme
					if(this.panelMere.estChiffre(this.ensJTextField.get(5).getText())==false) {
						this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
						this.setCouleurErreur(true, this.ensJTextField.get(5));
					}
					else {
						if (this.ensJTextField.get(5).getText().equals("") || Integer.parseInt(this.ensJTextField.get(5).getText())<0)
							this.ensJTextField.get(5).setText("0");
						this.setLabelErreur("");
						this.setCouleurErreur(false, this.ensJTextField.get(5));
						if (this.ensJTextField.get(4).getText().equals("0")||this.ensJTextField.get(4).getText().equals("")){
							somme += Integer.parseInt(this.ensJTextField.get(5).getText());
							this.panelMere.setSommeTP();
							this.panelMere.setSommeTotal();
						}
						else {
							somme = Integer.parseInt(this.ensJTextField.get(4).getText())
									* Integer.parseInt(this.ensJTextField.get(5).getText());
							this.panelMere.setSommeTP(somme);
							this.panelMere.setSommeTotal();
						}
					}
				}

				catch (NumberFormatException ex) { }
			}

		} catch (Exception ex) {
			System.err.print(""); //mettez rien, je sais plus comment j'ai fait mais les try catch sont obligatoires pour le bon fonctionnement des calculs et ça marche donc touchez pas
		}
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

		if (e.getSource() == this.ensJTextField.get(2)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(2).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(2));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(2));
			}
		}

		if (e.getSource() == this.ensJTextField.get(3)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(3).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(3));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(3));
			}
		}

		if (e.getSource() == this.ensJTextField.get(4)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(4).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(4));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(4));
			}
		}

		if (e.getSource() == this.ensJTextField.get(5)) {
			if (this.panelMere.estChiffre(this.ensJTextField.get(5).getText()) == false) {
				this.setLabelErreur("Erreur de saisie, veuillez entrer un nombre entier");
				this.setCouleurErreur(true, this.ensJTextField.get(5));
			}
			else{
				this.setLabelErreur("");
				this.setCouleurErreur(false, this.ensJTextField.get(5));
			}
		}


	}

	public boolean txtRempli() {
		boolean bOk = true;
		for(int i=1;i<this.ensJTextField.size() && bOk;i+=2) {
			if(this.ensJTextField.get(i).getText().equals("")) {
				bOk = false;
			}
		}
		return bOk;
	}

}
