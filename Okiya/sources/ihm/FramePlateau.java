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
	* Classe Frame Plateau
	* @author 	: -
	* @version 	: 1.0
	* date 		! 09/06/2020
*/

public class FramePlateau extends JFrame
{
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;

	/*----------------------*/
	/* Attribut pour le jeu */
	/*----------------------*/
	private Controleur ctrl; 				// L'attribut renseigne sur le contrôleur.
	
	/*--------------------------------------*/
	/* Attributs pour l'affichage graphique */
	/*--------------------------------------*/
	private PanelMenu 		panelMenu; 		// L'attribut renseigne sur le PanelMenu.
	private PanelPlateau 	panelPlateau;	// L'attribut renseigne sur le PanelPlateau.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur FrameJoueur
		* @param ctrl 			: Le contrôleur.
		* @param largeurEcran 	: La largeur de l'écran de l'utilisateur.
		* @param hauteurEcran 	: La hauteur de l'écran de l'utilisateur.
	*/
	public FramePlateau(Controleur ctrl, int largeurEcran, int hauteurEcran)
	{
		/*----------------------------------------*/
		/* Informations sur la Frame du plateau   */
		/*----------------------------------------*/
		this.ctrl = ctrl;
		this.setTitle("Le jeu Okiya");
		double coeffLargeur = (largeurEcran / (double) 570);
		double coeffHauteur = (hauteurEcran / (double) 620);

		int largeurFramePlateau = (int) (largeurEcran / coeffLargeur);
		int hauteurFramePlateau = (int) (hauteurEcran / coeffHauteur);
		
		this.setSize(largeurFramePlateau, hauteurFramePlateau); 
		this.setLocation( (largeurEcran/2)-(this.getWidth()/2), (hauteurEcran/2)-(this.getHeight()/2) );
		this.setLayout( new BorderLayout() );

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelMenu = new PanelMenu(ctrl);
		this.panelPlateau = new PanelPlateau(ctrl);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		this.add(panelMenu, BorderLayout.NORTH);
		this.add(panelPlateau);

		/*-------------------------*/
		/* Activation du composant */
		/*-------------------------*/
		this.addComponentListener( new PositionFrame() );

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		this.panelMenu.setModeSombre();
		this.panelPlateau.setModeSombre();
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.panelMenu.setModeClair();
		this.panelPlateau.setModeClair();
	}


	/*----------------------------------*/
	/*			LA MISE À JOUR			*/
	/*----------------------------------*/

	/**
		* Met à jour le panel plateau.
	*/
	public void majPlateau()
	{
		this.panelPlateau.majPlateau();
	}

	/*----------------------------------*/
	/*			LA FERMETURE			*/
	/*----------------------------------*/

	/**
		* Ferme la FramePlateau.
	*/
	public void fermerPlateau()
	{
		this.dispose();
	}

	/**
		* Le déplacement des fenêtres graphiques.
	*/
	private class FermetureFrame extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			FramePlateau.this.ctrl.fermerJeu();
		}
	}

	/*----------------------------------------------*/
	/*			DÉPLACAMENT DE LA FENETRE			*/
	/*----------------------------------------------*/

	/**
		* Le déplacement des fenêtres graphiques.
	*/
	private class PositionFrame extends ComponentAdapter
	{
		public void componentMoved(ComponentEvent e)
		{
			FramePlateau.this.ctrl.positionFrame("P");
		}
	}
}