package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.border.LineBorder;
import javax.swing.*;

import java.io.File;

/**
	* Classe Panel Joueur
	* @author 	: -
	* @version 	: 1.0
	* date 		! 18/06/2020
*/

public class PanelJoueur extends JPanel implements MouseListener
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
	private static int nbPanelJoueur; 	// L'attribut renseigne le nombre de PanelJoueur.
	private static int nbMajJoueur; 	// L'attribut renseigne sur le nombre de mis à jour de PanelJoueur.
	private int numPanelJoueur; 		// L'attribut renseigne sur le numéro de PanelJoueur.

	private JLabel lblTitre;			// L'attribut renseigne sur la maison du joueur.
	private JLabel lblPseudo;			// L'attribut renseigne sur le pseudo du joueur.
	private JLabel lblImg; 				// L'attribut renseigne sur la couleur du joueur grâce à une image.
	private JLabel lblNbJeton;			// L'attribut renseigne sur le nombre de jeton du joueur.
	private JPanel panelScrollCarte;	// L'attribut renseigne les cartes acquises.
	private JScrollPane scrollPane; 	// L'attribut permet de naviguer dans les cartes acquises.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur PanelJoueur
		* @param ctrl : Le contrôleur.
		* @param largeurEcran 	: La largeur de l'écran de l'utilisateur.
		* @param hauteurEcran 	: La hauteur de l'écran de l'utilisateur.
		* @param nomJoueur 		: Le nom du joueur.
	*/
	public PanelJoueur(Controleur ctrl, int largeurEcran, int hauteurEcran, String nomJoueur)
	{
		/*---------------------------*/
		/* Informations sur la Frame */
		/*---------------------------*/
		this.ctrl = ctrl;
		this.numPanelJoueur = ++PanelJoueur.nbPanelJoueur;
		this.setLayout( new BorderLayout(0,0) );
		this.setBackground(Color.WHITE);

		/*-----------------------*/
		/* Les polices utilisées */
		/*-----------------------*/
		Font policeTitre = new Font("Calibri", Font.BOLD, 30);
		Font policePseudo = new Font("Calibri", Font.BOLD, 25);
		Font policeNbJeton = new Font("Calibri", Font.BOLD, 20);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.lblTitre = new JLabel();
		this.lblPseudo = new JLabel(nomJoueur);

		String sLien = this.ctrl.getNomJeton(this.numPanelJoueur);

		File fichier = new File("./ressources/images/" + sLien + ".png");
		if ( ! fichier.exists() )
		{
			fichier = new File("./ressources/images/jeton_V.png");
		}
		ImageIcon img = new ImageIcon(fichier.getPath());

		this.lblImg = new JLabel(img);
		this.lblImg.setPreferredSize( new Dimension(img.getIconWidth(), img.getIconHeight()));
		this.lblNbJeton = new JLabel( "Vous avez : " + Integer.toString( this.ctrl.getJoueur(this.numPanelJoueur-1).getNbJeton() ) + " jetons" );

		this.panelScrollCarte = new JPanel();
		this.panelScrollCarte.setLayout( new GridLayout(3,3) );
		this.scrollPane = new JScrollPane(this.panelScrollCarte);
		double coeffLargeur = (largeurEcran / (double) 280);
		double coeffHauteur = (hauteurEcran / (double) 410);
		int largeurScrollPane = (int) (largeurEcran / coeffLargeur);
		int hauteurScrollPane = (int) (hauteurEcran / coeffHauteur);
		this.scrollPane.setPreferredSize( new DimensionUIResource( largeurScrollPane, hauteurScrollPane) );

		String titre = "Maison ";

		if ( this.ctrl.getJoueur(this.numPanelJoueur-1).getCouleur() == 'R' )
		{
			titre += "Rouge";
			this.lblTitre.setForeground(Color.RED);
			this.lblPseudo.setForeground(Color.RED);
			this.lblNbJeton.setForeground(Color.RED);
			this.lblImg.setBorder(new LineBorder(Color.RED, 1));
			this.scrollPane.setBorder(new LineBorder(Color.RED, 1));
		}
		else
		{
			titre += "Noir";
			this.lblTitre.setForeground(Color.BLACK);
			this.lblPseudo.setForeground(Color.BLACK);
			this.lblNbJeton.setForeground(Color.BLACK);
			this.lblImg.setBorder(new LineBorder(Color.BLACK, 1));
			this.scrollPane.setBorder(new LineBorder(Color.BLACK, 1)); 
		}
		this.lblTitre.setText(titre);
		this.lblTitre.setFont(policeTitre);
		this.lblPseudo.setFont(policePseudo);
		this.lblNbJeton.setFont(policeNbJeton);

		JPanel panelNord = new JPanel();
		JPanel panelNordCentre = new JPanel();		
		JPanel panelCentre = new JPanel();
		JPanel panelSud = new JPanel();

		panelNord.setLayout( new FlowLayout() );
		panelNordCentre.setLayout( new BorderLayout(50,0) );
		panelCentre.setLayout( new FlowLayout() );
		panelSud.setLayout( new FlowLayout(FlowLayout.CENTER, 0, -2) );

		panelNord.setOpaque(false);
		panelNordCentre.setOpaque(false);
		panelCentre.setOpaque(false);
		panelSud.setOpaque(false);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		panelNordCentre.add(this.lblTitre, BorderLayout.NORTH);
		panelNordCentre.add(this.lblPseudo, BorderLayout.WEST);
		panelNordCentre.add(this.lblImg, BorderLayout.CENTER);
		panelNord.add(panelNordCentre);
		panelCentre.add(this.scrollPane);
		panelSud.add(this.lblNbJeton, JLabel.CENTER);

		this.add(panelNord, BorderLayout.NORTH);
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelSud, BorderLayout.PAGE_END);

		/*-------------------------*/
		/* Activation du composant */
		/*-------------------------*/
		this.lblImg.addMouseListener(this);
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*------------------------------*/
	/*			L'ACCESSEUR			*/
	/*------------------------------*/

	/**
		* @return Retourne le numéro du PanelJoueur.
	*/
	public int getNumPanelJoueur()
	{
		return this.numPanelJoueur;
	}

	/*----------------------------------*/
	/*			LE MODIFICATEUR			*/
	/*----------------------------------*/

	/**
		* Fixe le nombre de jeton du joueur.
	*/
	public void setNbJeton()
	{
		String messageLabel=  this.ctrl.getJoueur(this.ctrl.getNumJoueurActif()).getPseudoJoueur() + ", il vous reste ";
		
		if ( this.ctrl.getJoueur(this.ctrl.getNumJoueurActif()).getNbJeton() < 2 )
		{
			if ( this.ctrl.getJoueur(this.ctrl.getNumJoueurActif()).getNbJeton() != 0 )
			{
				if ( this.ctrl.getJoueur(this.numPanelJoueur-1).getCouleur() == 'R' )
				{
					this.lblNbJeton.setForeground(Color.RED);
				}
				else if ( this.ctrl.getJoueur(this.numPanelJoueur-1).getCouleur() == 'N' )
				{
					this.lblNbJeton.setForeground(Color.BLACK);
				}
				messageLabel += Integer.toString( this.ctrl.getJoueur(this.ctrl.getNumJoueurActif()).getNbJeton() ) + " jeton";
			}
			else
			{
				messageLabel = "Vous n'avez plus de jeton pour jouer ! ";
			}
		}
		else
		{
			messageLabel += Integer.toString( this.ctrl.getJoueur(this.ctrl.getNumJoueurActif()).getNbJeton() ) + " jetons";
			this.lblNbJeton.setForeground(Color.BLACK);
			this.lblNbJeton.setBackground(Color.WHITE);
		}

		this.lblNbJeton.setText( messageLabel );
	}

	/*--------------------------*/
	/*			L'AJOUT			*/
	/*--------------------------*/

	/**
		* Ajout une carte dans la collection du joueur.
		* @param nomCarte : le nom de la carte.
	*/
	public void ajouterCarte(String nomCarte)
	{
		File fichier = new File("./ressources/images/" + nomCarte + ".png");

		if (  ! fichier.exists() )
		{
			fichier = new File("./ressources/images/jeton_V.png");
			ImageIcon img = new ImageIcon(fichier.getPath());
			JLabel lblImg = new JLabel( img );
			this.panelScrollCarte.add( lblImg );
			JOptionPane.showMessageDialog(this, "Le fichier pour charger une image dans votre collection n'existe pas !", "Erreur de chargement d'une image", JOptionPane.ERROR_MESSAGE);
		}
		ImageIcon img = new ImageIcon(fichier.getPath());
		JLabel lblImg = new JLabel( img );
		this.panelScrollCarte.add( lblImg );
	}

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* Fixe le mode sombre.
	*/
	public void setModeSombre()
	{
		this.setBackground(Color.DARK_GRAY);
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.setBackground(Color.WHITE);
	}

	/*----------------------------------*/
	/*			LA MISE À JOUR			*/
	/*----------------------------------*/

	/**
		* Récréation du PanelJoueur lors du chargement d'une partie.
		* @param largeurEcran 	: La largeur de l'écran de l'utilisateur.
		* @param hauteurEcran 	: La hauteur de l'écran de l'utilisateur.
		* @param nomJoueur 		: Le nom du joueur.
	*/
	public void majJoueur(int largeurEcran, int hauteurEcran, String nomJoueur)
	{
		this.removeAll();
		this.remove(this.lblImg);
		this.remove(this.lblNbJeton);

		/*-----------------------------*/
		/* Informations sur la Frame   */
		/*-----------------------------*/
		this.ctrl = ctrl;
		this.setLayout( new BorderLayout(0,0) );
		this.setBackground(Color.WHITE);

		/*-----------------------*/
		/* Les polices utilisées */
		/*-----------------------*/
		Font policeTitre = new Font("Calibri", Font.BOLD, 30);
		Font policePseudo = new Font("Calibri", Font.BOLD, 25);
		Font policeNbJeton = new Font("Calibri", Font.BOLD, 20);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.lblTitre = new JLabel();
		this.lblPseudo = new JLabel(nomJoueur);

		File fichier;
		ImageIcon img;

		String sLien = this.ctrl.getNomJeton(this.numPanelJoueur);
		fichier = new File("./ressources/images/" + sLien + ".png");
		if ( ! fichier.exists() )
		{
			fichier = new File("./ressources/images/jeton_V.png");
		}
		img = new ImageIcon(fichier.getPath());
		this.lblImg 	= new JLabel(img);
		this.lblImg.setPreferredSize( new Dimension(img.getIconWidth(), img.getIconHeight()));
		this.lblNbJeton = new JLabel( "Vous avez : " + Integer.toString( this.ctrl.getJoueur(this.numPanelJoueur-1).getNbJeton() ) + " jetons" );

		this.panelScrollCarte = new JPanel();
		this.panelScrollCarte.setLayout( new GridLayout(3,3) );
		this.scrollPane = new JScrollPane(this.panelScrollCarte);
		double coeffLargeur = (largeurEcran / (double) 280);
		double coeffHauteur = (hauteurEcran / (double) 410);
		int largeurScrollPane = (int) (largeurEcran / coeffLargeur);
		int hauteurScrollPane = (int) (hauteurEcran / coeffHauteur);
		this.scrollPane.setPreferredSize( new DimensionUIResource( largeurScrollPane, hauteurScrollPane) );

		// Mise à jour des cartes du ScrollPane
		for(int cpt=0; cpt<this.ctrl.getJoueur(this.ctrl.getNumJoueurActif()).getNbPlaceCartes(); cpt++)
		{
			String nomCarte = "";
			
			nomCarte = this.ctrl.getJoueur(this.numPanelJoueur-1).getCarteCollectionJoueur(cpt);

			if ( nomCarte != null && ! nomCarte.equals("") )
			{
				fichier = new File("./ressources/images/" + nomCarte + ".png");
				if ( ! fichier.exists() )
				{
					fichier = new File("./ressources/images/jeton_V.png");
				}
				img = new ImageIcon(fichier.getPath());
				JLabel lblImg = new JLabel( img );
				this.panelScrollCarte.add( lblImg );
			}
		}
		
		String titre = "Maison ";

		if ( this.ctrl.getJoueur(this.numPanelJoueur-1).getCouleur() == 'R' )
		{
			titre += "Rouge";
			this.lblTitre.setForeground(Color.RED);
			this.lblPseudo.setForeground(Color.RED);
			this.lblNbJeton.setForeground(Color.RED);
			this.lblImg.setBorder(new LineBorder(Color.RED, 1));
			this.scrollPane.setBorder(new LineBorder(Color.RED, 1));
		}
		else
		{
			titre += "Noir";
			this.lblTitre.setForeground(Color.BLACK);
			this.lblPseudo.setForeground(Color.BLACK);
			this.lblNbJeton.setForeground(Color.BLACK);
			this.lblImg.setBorder(new LineBorder(Color.BLACK, 1));
			this.scrollPane.setBorder(new LineBorder(Color.BLACK, 1));
		}
		this.lblTitre.setText(titre);
		this.lblTitre.setFont(policeTitre);
		this.lblPseudo.setFont(policePseudo);
		this.lblNbJeton.setFont(policeNbJeton);

		JPanel panelNord = new JPanel();
		JPanel panelNordCentre = new JPanel();		
		JPanel panelCentre = new JPanel();
		JPanel panelSud = new JPanel();

		panelNord.setLayout( new FlowLayout() );
		panelNordCentre.setLayout( new BorderLayout(50,0) );
		panelCentre.setLayout( new FlowLayout() );
		panelSud.setLayout( new FlowLayout(FlowLayout.CENTER, 0, -2) );

		panelNord.setOpaque(false);
		panelNordCentre.setOpaque(false);
		panelCentre.setOpaque(false);
		panelSud.setOpaque(false);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		panelNordCentre.add(this.lblTitre, BorderLayout.NORTH);
		panelNordCentre.add(this.lblPseudo, BorderLayout.WEST);
		panelNordCentre.add(this.lblImg, BorderLayout.CENTER);
		panelNord.add(panelNordCentre);
		panelCentre.add(this.scrollPane);
		panelSud.add(this.lblNbJeton, JLabel.CENTER);

		this.add(panelNord, BorderLayout.NORTH);
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelSud, BorderLayout.PAGE_END);

		/*--------------------------*/
		/* Activation du composants */
		/*--------------------------*/
		this.lblImg.addMouseListener(this);
	}

	/*--------------------------------------*/
	/*			LA RÉINITIALISATION			*/
	/*--------------------------------------*/

	/**
		* Réinitialise le PanelJoueur.
	*/

	public void resetNumPanelJoueur()
	{
		PanelJoueur.nbPanelJoueur = 0;
		PanelJoueur.nbMajJoueur = 0;
		this.numPanelJoueur = 0;
	}

	/*----------------------------------------------*/
	/*			L'ÉVÈNEMENT MOUSE-LISTENER			*/
	/*----------------------------------------------*/

	public void mouseEntered	(MouseEvent e) {}
	public void mouseExited		(MouseEvent e) {}
	public void mousePressed	(MouseEvent e) {}
	public void mouseReleased	(MouseEvent e) {}

	/**
			* Si le joueur clique sur l'image de sa couleur.
	*/
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() == this.lblImg)
		{
			String messageCaracteristique = "Pseudo : " + this.lblPseudo.getText() + "\n";
			messageCaracteristique += " " + this.lblTitre.getText() + "\n";
			messageCaracteristique += " " + this.lblNbJeton.getText();
			JOptionPane.showMessageDialog(this, messageCaracteristique, "Les caractéristiques", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}