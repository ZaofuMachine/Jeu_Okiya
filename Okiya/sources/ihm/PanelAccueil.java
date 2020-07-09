package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.*;


/**
	* Classe Panel Accueil
	* @author 	: -
	* @version 	: 1.0
	* date 		! 20/06/2020
*/

public class PanelAccueil extends JPanel implements ItemListener
{
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	/*----------------------*/
	/* Attribut pour le jeu */
	/*----------------------*/
	private Controleur ctrl; // L'attribut renseigne sur le contrôleur.

	/*--------------------------------------*/
	/* Attributs pour l'affichage graphique */
	/*--------------------------------------*/
	private PanelInfosJoueur panelInfosJoueur;  	// L'attribut renseigne sur le PanelInfosJoueur.
	private PanelChargePartie panelChargePartie; 	// L'attribut renseigne sur le PanelChargePartie.

	private ButtonGroup 	bgSelection; 			// L'attribut renseigne sur le regroupement des radios-boutons. 	
	private JRadioButton 	rbNouvellePartie; 		// L'attribut renseigne sur la préférence pour une nouvelle partie.
	private JRadioButton 	rbChargerPartie;  		// L'attribut renseigne sur la préférence pour une charger partie.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur FrameJoueur
		* @param ctrl : Le contrôleur.
	*/
	public PanelAccueil(Controleur ctrl)
	{
		/*-----------------------------------*/
		/* Informations sur le panel accueil */
		/*-----------------------------------*/
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout() );

		/*-----------------------*/
		/* Les polices utilisées */
		/*-----------------------*/
		Font policeTitre = new Font("Calibri", Font.BOLD, 30);
		Font policeDroit = new Font("Calibri", Font.BOLD, 20);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		JLabel lblTitre = new JLabel("Okiya", JLabel.CENTER);
		lblTitre.setFont(policeTitre);
		lblTitre.setForeground(Color.WHITE);

		this.bgSelection = new ButtonGroup();
		this.rbNouvellePartie = new JRadioButton("Nouvelle partie");
		this.rbNouvellePartie.setFont(policeDroit);
		this.rbNouvellePartie.setForeground(Color.WHITE);
		this.rbChargerPartie = new JRadioButton("Charger une partie");
		this.rbChargerPartie.setFont(policeDroit);
		this.rbChargerPartie.setForeground(Color.WHITE);
		this.bgSelection.add( this.rbNouvellePartie );
		this.bgSelection.add( this.rbChargerPartie );

		this.panelInfosJoueur = new PanelInfosJoueur(ctrl);
		this.panelChargePartie = new PanelChargePartie(ctrl);

		JLabel lblDroit = new JLabel("Réalisation d'une application de jeu - 2020", JLabel.LEFT);
		lblDroit.setFont(policeDroit);
		lblDroit.setForeground(Color.BLACK);

		// Création des panels internes
		JPanel panelNord = new JPanel();
		JPanel panelCentre = new JPanel();
		JPanel panelCentreNord = new JPanel();
		JPanel panelCentreSud = new JPanel();
		JPanel panelSud = new JPanel();

		panelNord.setLayout( new FlowLayout() );
		panelCentre.setLayout( new GridLayout(2,1) );
		panelCentreNord.setLayout( new GridLayout(1,2) );
		panelCentreSud.setLayout( new GridLayout(1,2) );
		panelSud.setLayout( new FlowLayout() );

		this.rbNouvellePartie.setOpaque(false);
		this.rbNouvellePartie.setSelected(true);
		this.rbChargerPartie.setOpaque(false);
		panelNord.setOpaque(false);
		panelCentre.setOpaque(false);
		panelCentreNord.setOpaque(false);
		panelCentreSud.setOpaque(false);
		panelSud.setOpaque(false);
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		// Ajout des composants aux panels internes
		panelNord.add(lblTitre);
		panelCentreNord.add(this.rbNouvellePartie);
		panelCentreNord.add(this.panelInfosJoueur);
		panelCentreSud.add(this.rbChargerPartie);
		panelCentreSud.add(this.panelChargePartie);
		panelCentre.add(panelCentreNord);
		panelCentre.add(panelCentreSud);
		panelSud.add(lblDroit, BorderLayout.SOUTH);

		this.add(panelNord, BorderLayout.NORTH);
		this.add( panelCentre, BorderLayout.CENTER);
		this.add(panelCentre, BorderLayout.EAST);
		this.add(panelSud, BorderLayout.SOUTH);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.rbNouvellePartie.addItemListener(this);
		this.rbChargerPartie.addItemListener(this);

		this.panelChargePartie.setVisible(false);
		this.setVisible(true);

	}

	/*------------------*/
	/* Le fond du panel */
	/*------------------*/
	public void paintComponent(Graphics g)
	{
		String     sImage;
		Image      img;
		Graphics2D g2 = (Graphics2D) g;

		super.paintComponent(g);
		// Dessine le fond
		sImage = "./ressources/images/okiya_background.png";
		if ( sImage != null )
		{
			img = getToolkit().getImage ( sImage );
			g2.drawImage ( img, 0, 0, this.getWidth(), this.getHeight(), this );
		}
	}

	/*----------------------------------------------*/
	/*			L'ÉVÈNEMENT ITEM-LISTENER			*/
	/*----------------------------------------------*/

	/**
			* Si le sélectionne le bouton radio nouvelle partie ou charger partie.
	*/
	public void itemStateChanged(ItemEvent e)
	{
		if ( e.getSource() == this.rbNouvellePartie )
		{
			this.panelChargePartie.setVisible(false);
			this.panelInfosJoueur.setVisible(true);
		}

		if ( e.getSource() == this.rbChargerPartie )
		{
			this.panelInfosJoueur.setVisible(false);
			this.panelChargePartie.setVisible(true);
		} 
	}


	/**
		* Classe Panel Infos Joueur
		* @author 	: -
		* @version 	: 1.0
		* date 		! 20/06/2020
	*/

	private class PanelInfosJoueur extends JPanel implements ActionListener
	{
		/**
			* Définition du serialVersionUID
		*/
		private static final long serialVersionUID = 1L;

		/*----------------------*/
		/* Attribut pour le jeu */
		/*----------------------*/
		private Controleur ctrl; 			// L'attribut renseigne sur le contrôleur.

		/*--------------------------------------*/
		/* Attributs pour l'affichage graphique */
		/*--------------------------------------*/
		private JTextField txtfldJoueur1; 	// L'attribut renseigne sur le champ de saisie numéro 1.
		private JTextField txtfldJoueur2; 	// L'attribut renseigne sur le champ de saisie numéro 2.
		private JButton btnValider; 		// L'attribut renseigne sur le bouton de validation.

		/*-----------------*/
		/* Le Constructeur */
		/*-----------------*/

		/**
			* Constructeur PanelInfosJoueur
			* @param ctrl : Le contrôleur.
		*/
		public PanelInfosJoueur(Controleur ctrl)
		{
			/*----------------------------------------*/
			/* Informations sur le panel infos joueur */
			/*----------------------------------------*/
			this.ctrl = ctrl;
			this.setLayout( new GridLayout(3,2) );
			this.setOpaque(false);

			/*--------------------*/
			/* La police utilisée */
			/*--------------------*/
			Font policeChamp 	= new Font("Calibri", Font.BOLD, 20);

			/*-------------------------*/
			/* Création des composants */
			/*-------------------------*/
			JLabel lblPseudoJoueur1 = new JLabel("Pseudo du joueur 1 : ");
			lblPseudoJoueur1.setFont(policeChamp);
			lblPseudoJoueur1.setForeground(Color.BLACK);

			this.txtfldJoueur1 = new JTextField(10);
			this.txtfldJoueur1.setOpaque(false);
			this.txtfldJoueur1.setFont(policeChamp);
			this.txtfldJoueur1.setForeground(Color.BLACK);

			JLabel lblPseudoJoueur2 = new JLabel("Pseudo du joueur 2 : ");
			lblPseudoJoueur2.setForeground(Color.BLACK);
			lblPseudoJoueur2.setFont(policeChamp);

			this.txtfldJoueur2 = new JTextField(10);
			this.txtfldJoueur2.setOpaque(false);
			this.txtfldJoueur2.setFont(policeChamp);
			this.txtfldJoueur2.setForeground(Color.BLACK);

			this.txtfldJoueur1.setPreferredSize( new Dimension(20,20) );
			this.txtfldJoueur2.setPreferredSize( new Dimension(20,20) );

			this.btnValider = new JButton("Valider");
			this.btnValider.setFont(policeChamp);
			this.btnValider.setForeground(Color.BLACK);
			this.btnValider.setBorder(BorderFactory.createLineBorder(Color.black));

			// Création du panel interne
			JPanel panelChamp = new JPanel();
			panelChamp.setLayout( new GridLayout(2,2) );
			panelChamp.setOpaque(false);

			/*-------------------------------*/
			/* Positionnement des composants */
			/*-------------------------------*/
			panelChamp.add( lblPseudoJoueur1 );
			panelChamp.add( this.txtfldJoueur1 );
			panelChamp.add( lblPseudoJoueur2 );
			panelChamp.add( this.txtfldJoueur2 );

			this.add(panelChamp, BorderLayout.CENTER);
			this.add(this.btnValider, BorderLayout.SOUTH);

			/*---------------------------*/
			/* Activation des composants */
			/*---------------------------*/
			this.btnValider.addActionListener(this);

			this.setVisible(true);
		}

		/*--------------------------------------------------------------------------------------------------*/
		/* 											LA MÉTHODE 												*/
		/*--------------------------------------------------------------------------------------------------*/

		/*----------------------------------------------*/
		/*			L'ÉVÈNEMENT	ACTION-LISTENER			*/
		/*----------------------------------------------*/
		
		/**
			* Si le joueur clique sur le bouton de validation.
		*/
		public void actionPerformed(ActionEvent e)
		{
			if ( e.getSource() == this.btnValider )
			{

				if ( ! this.txtfldJoueur1.getText().equals("") && ! this.txtfldJoueur2.getText().equals("") ) // peut se traduire aussi par ! isEmpty()
				{
					if ( ! this.txtfldJoueur1.getText().equals(this.txtfldJoueur2.getText()) )
					{
						String nomJoueur1 = this.txtfldJoueur1.getText();
						String nomJoueur2 = this.txtfldJoueur2.getText();
						this.ctrl.lancerJeu(nomJoueur1, nomJoueur2);
						this.ctrl.fermerAccueil();
					}
					else if ( this.txtfldJoueur1.getText().equals(this.txtfldJoueur2.getText()) )
					{
						JOptionPane.showMessageDialog(this, "Interdiction d'avoir le même pseudo.", "Erreur de pseudo", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					int cptJoueur=0;
					String sRet = "Champ manquant pour le joueur ";
					if ( this.txtfldJoueur1.getText().equals("") && this.txtfldJoueur2.getText().equals("") )
					{
						sRet = "Champs manquants pour les joueurs ";
						sRet += Integer.toString(++cptJoueur) + " et " + Integer.toString(++cptJoueur) ;
					}
					else if ( this.txtfldJoueur1.getText().equals("") || this.txtfldJoueur2.getText().equals("") )
					{
						cptJoueur++;
						if ( this.txtfldJoueur1.getText().equals("") )
						{
							sRet += cptJoueur;
						}
						else if ( this.txtfldJoueur2.getText().equals("") ) 
						{
							sRet += ++cptJoueur;
						}
						cptJoueur--;
					}
					String sRetTitre = "Saisie manquante";

					if ( cptJoueur > 1 )
					{
						sRetTitre = "Plusieurs saisies manquantes";
					}
					JOptionPane.showMessageDialog(this, sRet, sRetTitre, JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
		* Classe Panel Charge Partie
		* @author 	: -
		* @version 	: 1.0
		* date 		! 20/06/2020
	*/

	private class PanelChargePartie extends JPanel implements ActionListener
	{
		/**
			* Définition du serialVersionUID
		*/
		private static final long serialVersionUID = 1L;

		/*----------------------*/
		/* Attribut pour le jeu */
		/*----------------------*/
		private Controleur ctrl; 			// L'attribut renseigne sur le contrôleur.

		/*-------------------------------------*/
		/* Attribut pour l'affichage graphique */
		/*-------------------------------------*/
		private JButton btnCharger; 		// L'attribut renseigne sur le bouton pour charger une partie.

		/*-----------------*/
		/* Le Constructeur */
		/*-----------------*/

		/**
			* Constructeur PanelChargePartie
			* @param ctrl : Le contrôleur.
		*/
		public PanelChargePartie(Controleur ctrl)
		{
			/*-----------------------------------------*/
			/* Informations sur le panel charge partie */
			/*-----------------------------------------*/
			this.ctrl = ctrl;
			this.setLayout( new BorderLayout() );
			this.setOpaque(false);

			/*--------------------*/
			/* La police utilisée */
			/*--------------------*/
			Font policeBouton = new Font("Calibri", Font.BOLD, 20);

			/*------------------------*/
			/* Création du  composant */
			/*------------------------*/
			this.btnCharger = new JButton("Charger une partie");
			this.btnCharger.setFont(policeBouton);
			this.btnCharger.setBorder(BorderFactory.createLineBorder(Color.black));
		
			/*-----------------------------*/
			/* Positionnement du comoosant */
			/*-----------------------------*/
			this.add(this.btnCharger, BorderLayout.CENTER);

			/*-------------------------*/
			/* Activation du composant */
			/*-------------------------*/
			this.btnCharger.addActionListener(this);

			this.setVisible(true);
		}

		/*--------------------------------------------------------------------------------------------------*/
		/* 											LA MÉTHODE 												*/
		/*--------------------------------------------------------------------------------------------------*/

		/*----------------------------------------------*/
		/*			L'ÉVÈNEMENT	ACTION-LISTENER			*/
		/*----------------------------------------------*/
		
		/**
			* Si le joueur clique sur le bouton pour charger une partie.
		*/
		public void actionPerformed(ActionEvent e)
		{
			if ( e.getSource() == this.btnCharger )
			{
				String[] ensJeux = this.ctrl.getJeuxEnregistres();

				if (  ! (ensJeux == null) )
				{
					JComboBox<String> cbox = new JComboBox<>(ensJeux);
					cbox.setEditable(true);

					int ouvertureOk = JOptionPane.showConfirmDialog(this, cbox, "Ouverture d'une partie", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if ( ouvertureOk == JOptionPane.OK_OPTION)
					{
						Object partieEnregistre = cbox.getSelectedItem();
						this.ctrl.ouvrir(partieEnregistre.toString());
					}

					this.ctrl.fermerAccueil();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Aucune partie enregistrée", "Ouverture d'une partie", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}