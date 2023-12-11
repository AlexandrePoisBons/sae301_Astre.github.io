package ihm.previsionnel				;

//Imports classes externes
import ihm.acceuil.FrameAccueil			;

//Imports classes Java
import java.awt.BorderLayout			;
import java.awt.GridLayout				;
import java.awt.event.ActionEvent		;
import java.awt.event.ActionListener	;
import javax.swing.*					;

public class PanelPrevi extends JPanel implements ActionListener {
	//Attributs
	private FrameAccueil 		frame				;
	private PanelCenterPrevi 	panelCenterPrevi	;
	private JButton 			btnCreerRessource	;
	private JButton 			btnCreerSAE			;
	private JButton 			btnCreerStage		;
	private JButton 			btnModifier			;
	private JButton 			btnSupprimer		;	

	//Constructeur
	public PanelPrevi(FrameAccueil frAccueil){
		//Synchronisation des pages
		this.frame = frAccueil;

		// création des composants
		JPanel panelSud 		= new JPanel			(					);
		this.panelCenterPrevi 	= new PanelCenterPrevi	(this.frame			);
		this.btnCreerRessource 	= new JButton			("Créer ressource"	);
		this.btnCreerSAE 		= new JButton			("Créer SAE"		);
		this.btnCreerStage 		= new JButton			("Créer stage/suivi");
		this.btnModifier 		= new JButton			("Modifier"			);
		this.btnSupprimer 		= new JButton			("Supprimer"		);

		//Layout
		this.setLayout		(new BorderLayout	()			);
		panelSud.setLayout	(new GridLayout		(1, 4, 5, 0));

		//Ajout des composants
		panelSud.add(this.btnCreerRessource						);
		panelSud.add(this.btnCreerSAE							);
		panelSud.add(this.btnCreerStage							);
		panelSud.add(this.btnModifier							);
		panelSud.add(this.btnSupprimer							);
		this	.add(panelCenterPrevi, 		BorderLayout.CENTER	);
		this	.add(panelSud, 				BorderLayout.SOUTH	);

		//ActionListeners
		this.btnCreerRessource	.addActionListener(this);
		this.btnCreerSAE		.addActionListener(this);
		this.btnCreerStage		.addActionListener(this);
		this.btnModifier		.addActionListener(this);
		this.btnSupprimer		.addActionListener(this);
	}

	//Permets de faire une action en fonction du bouton cliqué
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnCreerRessource){

		}
		if(e.getSource() == this.btnCreerSAE){

		}
		if(e.getSource() == this.btnCreerStage){

		}
		if(e.getSource() == this.btnModifier){

		}
		if(e.getSource() == this.btnSupprimer){

		}
	}
}
