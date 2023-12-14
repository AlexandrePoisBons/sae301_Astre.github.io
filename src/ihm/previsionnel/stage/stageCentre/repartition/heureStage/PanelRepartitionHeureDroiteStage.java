package ihm.previsionnel.stage.stageCentre.repartition.heureStage;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;

public class PanelRepartitionHeureDroiteStage extends JPanel {
	private JPanel panelC;
	private ArrayList<JTextField> ensTxtFld;
	public PanelRepartitionHeureDroiteStage(){
		this.panelC = new JPanel();
		this.ensTxtFld = new ArrayList<JTextField>();
		for(int i = 0; i < 2; i++){
            JTextField textField = new JTextField(3);
            this.ensTxtFld.add(textField); 
        }
		
		this.panelC.setLayout(new GridBagLayout());
		GridBagConstraints gbcC = new GridBagConstraints();


		// Ajout des composants avec GridBagLayout
        gbcC.gridx = 0;
        gbcC.gridy = 0;
        gbcC.insets = new Insets(9, 0, 2, 0);
		gbcC.gridx = 4;
		this.panelC.add(new JLabel("Σ"), gbcC);
		gbcC.insets = new Insets(0, 0, 2, 0);
		gbcC.gridy = 2;
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(0), gbcC);
		gbcC.gridy = 3;
		gbcC.gridx = 4;
		this.panelC.add(this.ensTxtFld.get(1), gbcC);
		this.add(this.panelC);
	}
}