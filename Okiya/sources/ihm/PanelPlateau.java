package sources.ihm;

/*----L'-import-pour-le-contrôleur----*/
import sources.Controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.*;

import java.io.File;


/**
	* Classe Panel Plateau
	* @author 	: -
	* @version 	: 1.0
	* date 		! 18/06/2020
*/

public class PanelPlateau extends JPanel implements MouseListener
{
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/*----------------------*/
	/* Attribut pour le jeu */
	/*----------------------*/
	private Controleur ctrl; // L'attribut renseigne sur le contrôleur.

	/*-------------------------------------*/
	/* Attribut pour l'affichage graphique */
	/*-------------------------------------*/
	private JLabel[][] lblImg; // L'attribut pour les images du plateau.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur PanelPlateau
		* @param ctrl : Le contrôleur.
	*/
	public PanelPlateau(Controleur ctrl)
	{
		/*---------------------------*/
		/* Informations sur la Frame */
		/*---------------------------*/
		this.ctrl = ctrl;
		this.setLayout( new GridLayout(4,4) );
		this.setBackground(Color.WHITE);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		String carteSpecificite;
		String carteVegetation;
		String sLien;
		File fichier;
		ImageIcon img;

		this.lblImg = new JLabel[4][4];
		for(int indiceLig=0; indiceLig<this.lblImg.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.lblImg.length; indiceCol++)
			{
				sLien = this.ctrl.getCarteNom(indiceLig, indiceCol);
				fichier = new File("./ressources/images/" + sLien + ".png");
				if ( ! fichier.exists() )
				{
					fichier = new File("./ressources/images/jeton_V.png");
				}
				img = new ImageIcon(fichier.getPath());
				this.lblImg[indiceLig][indiceCol] = new JLabel(img);
			}
		}

		/*-----------------------------*/
		/* Positionnement du composant */
		/*-----------------------------*/
		for(int indiceLig=0; indiceLig<this.lblImg.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.lblImg.length; indiceCol++)
			{
				this.add(this.lblImg[indiceLig][indiceCol]);
			}
		}
		/*-------------------------*/
		/* Activation du composant */
		/*-------------------------*/
		for(int indiceLig=0; indiceLig<this.lblImg.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.lblImg.length; indiceCol++)
			{
				this.lblImg[indiceLig][indiceCol].addMouseListener(this);
			}
		}
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
		* Récréation du PanelPlateau lors du chargement d'une partie.
	*/
	public void majPlateau()
	{
		// nettoye tout
		this.removeAll();

		this.setLayout( new GridLayout(4,4) );
		this.setBackground(Color.WHITE);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		String carteSpecificite;
		String carteVegetation;
		String sLien;
		File fichier;
		ImageIcon img;

		this.lblImg = new JLabel[4][4];
		for(int indiceLig=0; indiceLig<this.lblImg.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.lblImg.length; indiceCol++)
			{
				sLien = this.ctrl.getCarteNom(indiceLig, indiceCol);
				fichier = new File("./ressources/images/" + sLien + ".png");
				if ( ! fichier.exists() )
				{
					fichier = new File("./ressources/images/jeton_V.png");
				}
				img = new ImageIcon(fichier.getPath());
				this.lblImg[indiceLig][indiceCol] = new JLabel(img);
			}
		}

		/*-----------------------------*/
		/* Positionnement du composant */
		/*-----------------------------*/
		for(int indiceLig=0; indiceLig<this.lblImg.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.lblImg.length; indiceCol++)
			{
				this.add(this.lblImg[indiceLig][indiceCol]);
			}
		}
		/*-------------------------*/
		/* Activation du composant */
		/*-------------------------*/
		for(int indiceLig=0; indiceLig<this.lblImg.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.lblImg.length; indiceCol++)
			{
				this.lblImg[indiceLig][indiceCol].addMouseListener(this);
			}
		}

	}

	/*----------------------------------------------*/
	/*			L'ÉVÈNEMENT MOUSE-LISTENER			*/
	/*----------------------------------------------*/

	public void mouseEntered	(MouseEvent e) {}
	public void mouseExited		(MouseEvent e) {}
	public void mousePressed	(MouseEvent e) {}
	public void mouseReleased	(MouseEvent e) {}

	/**
			* Si le joueur clique sur une carte du plateau.
	*/
	public void mouseClicked(MouseEvent e)
	{
		if ( ! this.ctrl.estGagnant() )
		{
			for(int indiceLig=0; indiceLig<this.lblImg.length; indiceLig++)
			{
				for(int indiceCol=0; indiceCol<this.lblImg.length; indiceCol++)
				{
					if(e.getSource() == this.lblImg[indiceLig][indiceCol])
					{
						// carte choisit par le joueur
						String specificite 		= this.ctrl.getCarteTab(indiceLig, indiceCol).getSpecificite(); 
						String vegetation 		= this.ctrl.getCarteTab(indiceLig, indiceCol).getVegetation();

						// vérification des règles pour jouer
						if ( this.ctrl.estJouable(indiceLig, indiceCol, specificite, vegetation) )
						{
							int numJoueurActif = this.ctrl.getNumJoueurActif();
							// position du jeton sur la case jouée
							String sJeton = this.ctrl.getJetonJoueur();
							File fichier = new File("./ressources/images/" + sJeton + ".png");
							if ( ! fichier.exists() )
							{
								fichier = new File("./ressources/images/jeton_V.png");
							}
							ImageIcon img = new ImageIcon(fichier.getPath());
							this.lblImg[indiceLig][indiceCol].setIcon( img );

							// diminuer des jetons du joueur
							this.ctrl.getJoueur(numJoueurActif).diminuerNbJeton();

							// augmenter son nombre de coup
							this.ctrl.getJoueur(numJoueurActif).setNbCoup();

							// pose le jeton du joueur sur le plateau
							this.ctrl.setPlateauJeton(indiceLig, indiceCol, sJeton);

							// met à jour le nb de jeton du joueur
							this.ctrl.setNbJeton();

							String nomCarte = specificite + "_" + vegetation;
							// ajoute une carte dans la collection du joueur de la partie graphique
							this.ctrl.ajouterCarte(nomCarte);

							// ajoute une carte dans la collection du joueur de la partie métier
							this.ctrl.getJoueur(numJoueurActif).ajouterCarteCollectionJoueur(nomCarte);

							// mis à jour des contraintes
							String contrSpecificite = this.ctrl.getCarteTab(indiceLig,indiceCol).getSpecificite();
							String contrVegetation = this.ctrl.getCarteTab(indiceLig,indiceCol).getVegetation();
							this.ctrl.majContraintes(contrSpecificite, contrVegetation);

							// vérification si gagnant ou égalité
							if ( this.ctrl.estGagnant() || this.ctrl.estEgatlite() )
							{
								String messageTitre="";
								String messagePartie="";
								if ( this.ctrl.estGagnant() )
								{
									messageTitre="Victoire";
									messagePartie = "Le joueur " + this.ctrl.getJoueur(numJoueurActif).getNomCouleur() + "(" + this.ctrl.getJoueur(numJoueurActif).getPseudoJoueur() + ") " + "gagne !" + "\n";
									messagePartie += this.ctrl.getMessageGagne() + "\n";
								}
								else if ( this.ctrl.estEgatlite() )
								{
									messageTitre="Egalité";
									messagePartie = "Aucun de vous ne gagne : Égalité !";
								}
								
								messagePartie += " Voulez vous rejouez ? ";

								int optConfirme = JOptionPane.showConfirmDialog(this, messagePartie, messageTitre, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

								if ( optConfirme == JOptionPane.YES_OPTION)
								{
									String pseudoJoueur1 = "";
									String pseudoJoueur2 = "";
									for(int cpt=0; cpt<this.ctrl.getNbJoueur(); cpt++)
									{
										if ( cpt%2 == 0 )
										{
											pseudoJoueur1 = this.ctrl.getJoueur(cpt).getPseudoJoueur();
										}
										else
										{
											pseudoJoueur2 = this.ctrl.getJoueur(cpt).getPseudoJoueur();
										}
									}
									this.ctrl.nettoyerJeuGraphique();
									this.ctrl.fermerJeu();
									this.ctrl.lancerJeu(pseudoJoueur1, pseudoJoueur2);
								}
								else
								{
									this.ctrl.fermerJeu();
								} 
							}
							// joueur suivant
							this.ctrl.setJoueurSuivant();
						}
						else
						{
							String sMessErreur = this.ctrl.getMessageNonJouable();
							JOptionPane.showMessageDialog(this, sMessErreur, "Action impossible", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
	}
}