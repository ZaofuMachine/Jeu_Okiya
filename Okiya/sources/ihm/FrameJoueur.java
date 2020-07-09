package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

/*----L'-import-pour-l'-ihm----*/
import sources.ihm.PanelPlateau;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;


/**
	* Classe Frame Joueur
	* @author 	: -
	* @version 	: 1.0
	* date 		! 09/06/2020
*/

public class FrameJoueur extends JFrame
{
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/*--------------------------------------*/
	/* Attributs pour l'affichage graphique */
	/*--------------------------------------*/
	private static 	int nbFrameJoueur; 			// L'attribut renseigne le nombre de FrameJoueur.
	private 		int numFrameJoueur; 		// L'attribut renseigne le numéro de la FrameJoueur.
	private 		PanelJoueur panelJoueur;	// L'attribut renseigne sur le PanelJoueur.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur FrameJoueur
		* @param ctrl 			: Le contrôleur.
		* @param largeurEcran 	: La largeur de l'écran de l'utilisateur.
		* @param hauteurEcran 	: La hauteur de l'écran de l'utilisateur.
		* @param nomJoueur 		: Le nom du joueur.
	*/

	public FrameJoueur(Controleur ctrl, int largeurEcran, int hauteurEcran, String nomJoueur)
	{
		/*----------------------------------------*/
		/* Informations sur la Frame du plateau   */
		/*----------------------------------------*/
		this.numFrameJoueur = ++FrameJoueur.nbFrameJoueur;

		double coeffLargeur = (largeurEcran / (double) 350);
		double coeffHauteur = (hauteurEcran / (double) 620);
		int largeurFrameJoueur = (int) (largeurEcran / coeffLargeur);
		int hauteurFrameJoueur = (int) (hauteurEcran / coeffHauteur);

		this.setSize(largeurFrameJoueur, hauteurFrameJoueur);
		this.setLayout( new BorderLayout() );

		for(int cpt=0; cpt<ctrl.getNbJoueur(); cpt++)
		{
			if ( cpt == this.numFrameJoueur )
			{
				this.setLocation( (largeurEcran/2)-(this.getWidth()/2)-500, (hauteurEcran/2)-(this.getHeight()/2) );
			}
			else
			{
				this.setLocation((largeurEcran/2)-(this.getWidth()/2)+500, (hauteurEcran/2)-(this.getHeight()/2) );
			}
		}

		/*------------------------*/
		/* Création du composant  */
		/*------------------------*/
		this.panelJoueur = new PanelJoueur(ctrl, largeurEcran, hauteurEcran, nomJoueur);

		/*-----------------------------*/
		/* Positionnement du composant */
		/*-----------------------------*/
		this.add(panelJoueur);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* Fixe le mode sombre.
	*/
	public void setModeSombre()
	{
		this.panelJoueur.setModeSombre();
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.panelJoueur.setModeClair();
	}

	/*----------------------------------*/
	/*			LE MODIFICATEUR			*/
	/*----------------------------------*/
	
	/**
		* Fixe le nombre de jetons du joueur.
	*/
	public void setNbJeton()
	{
		this.panelJoueur.setNbJeton();
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
		this.panelJoueur.ajouterCarte(nomCarte);
	}


	/*----------------------------------*/
	/*			LA MISE À JOUR			*/
	/*----------------------------------*/

	/**
		* Met à jour le panel du joueur.
		* @param largeurEcran 	: La largeur de l'écran de l'utilisateur.
		* @param hauteurEcran 	: La hauteur de l'écran de l'utilisateur.
		* @param nomJoueur 		: Le nom du joueur.
	*/
	public void majJoueur(int largeurEcran, int hauteurEcran, String nomJoueur)
	{
		this.panelJoueur.majJoueur(largeurEcran, hauteurEcran, nomJoueur);
	}

	/*--------------------------------------*/
	/*			LA RÉINITIALISATION			*/
	/*--------------------------------------*/
	
	/**
		* Réinitialise le nombre de FrameJoueur et le numéro de la FrameJoueur.
	*/
	public void resetNumFrameJoueur()
	{
		this.panelJoueur.resetNumPanelJoueur();
		FrameJoueur.nbFrameJoueur = 0;
		this.numFrameJoueur = 0;
	}

	/*----------------------------------*/
	/*			LA FERMETURE			*/
	/*----------------------------------*/
	
	/**
		* Ferme la FrameJoueur.
	*/
	public void fermerJoueur()
	{
		this.dispose();
	}
}