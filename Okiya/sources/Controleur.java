package sources;

/*----Les-imports-pour-le-metier----*/
import sources.metier.Okiya;
import sources.metier.Carte;
import sources.metier.Joueur;

/*----Les-imports-pour-l'-ihm----*/
import sources.ihm.FrameAccueil;
import sources.ihm.FramePlateau;
import sources.ihm.FrameJoueur;
import java.awt.Dimension;
import java.awt.Point;

/*----Les-imports-pour-la-serialisation----*/
import sources.serialisation.Jeu;
import sources.serialisation.DisquetteJeu;
import java.util.Stack;

/**
	* Classe Controleur
	* @author 	: -
	* @version 	: 1.0
	* date 		! 14/06/2020
*/

public class Controleur
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/

	/**
		* Partie Métier
	*/ 
	private Okiya metier; // L'attribut pour traiter le jeu de façon transparente pour l'utilisateur.


	/**
		* La partie IHM (Interface Graphique c'est l'IHM = Interface Homme Machine).
	*/
	private FrameAccueil ihmAccueil; // L'attribut de la partie ihm concernant l'accueil du jeu.

	private FramePlateau ihmPlateau; // L'attribut de la partie ihm concernant la fenêtre du plateau de jeu.

	private FrameJoueur[] ihmJoueur; // L'attribut de la partie ihm concernant la fenêtre du joueur.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur Controleur
		* À l'instanciation d'un objet Controleur, une fenêtre d'accueil du jeu apparaît 
		* avec le nom de chaque joueur à saisir.
	*/
	public Controleur()
	{

		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		this.ihmAccueil = new FrameAccueil(this, largeurEcran, hauteurEcran);
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*------------------------------------------------------*/
	/*				MÉTHODES DE LA PARTIE METIER 			*/
	/*------------------------------------------------------*/

	/**
		* Lance une partie de jeu à partir d'une partie initialisée
		* @param nomJoueur1 : le nom du joueur numéro 1.
		* @param nomJoueur2 : le nom du joueur numéro 2.
	*/
	public void lancerJeu(String nomJoueur1, String nomJoueur2)
	{
		this.initialiserJeu(nomJoueur1, nomJoueur2);
	}

	/**
		* Initialise une partie de jeu
		* @param nomJoueur1 : le nom du joueur numéro 1.
		* @param nomJoueur2 : le nom du joueur numéro 2.
	*/
	private void initialiserJeu(String nomJoueur1, String nomJoueur2)
	{
		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		if ( this.metier == null )
		{
			this.metier = new Okiya();
		}

		int joueurCouleur = (int) (Math.random() * this.getNbJoueur());
		for(int cpt=0; cpt<this.metier.getNbJoueur(); cpt++)
		{
			if ( joueurCouleur%2 == 0 )
			{
				this.getJoueur(joueurCouleur).setPseudo(nomJoueur1);
				joueurCouleur++;
			}
			else
			{
				this.getJoueur(joueurCouleur).setPseudo(nomJoueur2);
				joueurCouleur--;	
			}
		}

		this.ihmPlateau = new FramePlateau(this, largeurEcran, hauteurEcran);

		this.ihmJoueur = new FrameJoueur[this.metier.getNbJoueur()];
		for(int cpt=0; cpt<this.metier.getNbJoueur(); cpt++)
		{
			if ( cpt%2 == 0 )
			{
				this.ihmJoueur[cpt] = new FrameJoueur(this,  largeurEcran, hauteurEcran, nomJoueur1);
			}
			else
			{
				this.ihmJoueur[cpt] = new FrameJoueur(this, largeurEcran, hauteurEcran, nomJoueur2);
			}
		}
	}

	private void reinitialiserJeu(String nomJoueur1, String nomJoueur2)
	{
		this.initialiserJeu(nomJoueur1, nomJoueur2);
	}

	/**
		* Ferme l'interface d'accueil du jeu.
	*/
	public void fermerAccueil()
	{
		this.ihmAccueil.fermerAccueil();
	}

	/**
		* Met à jour les contraintes de cartes (spécificité et végétation) que le joueur doit respecter pour jouer.
		* @param contrSpecificite 	: la contrainte spécificité.
		* @param contrVegetation 	: la contrainte végétation.
	*/
	public void majContraintes(String contrSpecificite, String contrVegetation)
	{
		this.metier.majContraintes(contrSpecificite, contrVegetation);
	}

	/**
		* @return Retourne la contrainte de spécificité sur lequel le joueur ne peut pas jouer une carte.
	*/
	public String getContrSpecificite()
	{
		return this.metier.getContrSpecificite();
	}

	/**
		* @return Retourne la contrainte de végétation sur lequel le joueur ne peut pas jouer une carte.
	*/
	public String getContrVegetation()
	{
		return this.metier.getContrVegetation();
	}

	/**
		* @return Retourne le nombre de joueur.
	*/
	public int getNbJoueur() 
	{
		return this.metier.getNbJoueur(); 
	}

	/**
		* @return Retourne un joueur.
		* @param  indice : indice du joueur qui est retourné.
	*/
	public Joueur getJoueur(int indice)					
	{
		return this.metier.getJoueur(indice); 
	}

	/**
		* @return Retourne le nom d'une carte du plateau de jeu.
		* @param indiceLig : indice de la ligne du plateau de jeu.
		* @param indiceCol : indice de la colonne du plateau de jeu.
	*/
	public String getCarteNom(int indiceLig, int indiceCol) 
	{
		return this.metier.getCarteNom(indiceLig, indiceCol); 	
	}

	/**
		* @return Retourne une Carte.
		* @param indiceLig : indice de la ligne du plateau de jeu.
		* @param indiceCol : indice de la colonne du plateau de jeu. 
	*/
	public Carte getCarteTab(int indiceLig, int indiceCol) 
	{
		return this.metier.getCarteTab(indiceLig, indiceCol);
	}

	/**
		* @return Retourne true : si le joueur peut jouer / false : si le joueur peut pas jouer.
		* @param indiceLig 		: indice de la ligne du plateau de jeu.
		* @param indiceCol 		: indice de la colonne du plateau de jeu.
		* @param specificite 	: une spécifité sur lequel le jouer veut jouer.
		* @param vegetation 	: une végétation sur lequel le jouer veut jouer.
	*/
	public boolean estJouable(int indiceLig, int indiceCol, String specificite, String vegetation) 
	{
		return this.metier.estJouable(indiceLig, indiceCol, specificite,vegetation);
	}

	/**
		* @return Retourne le numéro du joueur actif.
	*/
	public int getNumJoueurActif()
	{
		return this.metier.getNumJoueurActif();
	}

	/**
		* @return Retourne la couleur d'affichage.
	*/
	public boolean getCouleurAffichage()
	{
		return this.metier.getCouleurAffichage();
	}

	/**
		* Indique que c'est au joueur suivant de jouer.
	*/
	public void setJoueurSuivant()
	{
		this.metier.setJoueurSuivant();
	}

	/**
		* @return Retourne true : si le joueur a gagné / false : si le joueur n'a pas gagné.
	*/
	public boolean estGagnant()
	{
		return this.metier.estGagnant();
	}

	/**
		* @return Retourne true : si la partie donne un égalité / false : si la partie ne donne pas un égalité.
	*/
	public boolean estEgatlite()
	{
		return this.metier.estEgatlite();
	}

	/**
		* @return Retourne true : si le jeton du joueur peut être posé / false : si le jeton ne peut pas être posé
		* @param indiceLig 		: indice de la ligne du plateau de jeu.
		* @param indiceCol 		: indice de la colonne du plateau de jeu.
		* @param jeton 			: le nom du jeton (noir ou rouge).
	*/
	public boolean setPlateauJeton(int indiceLig, int indiceCol, String jeton)
	{
		return this.metier.setPlateauJeton(indiceLig, indiceCol, jeton);
	}

		/**
		* @return Retourne un message indiquant l'action que le joueur ne peut pas réaliser.
	*/
	public String getMessageNonJouable()
	{
		return this.metier.getMessageNonJouable();
	}

	/**
		* @return Retourne un message indiquant la façon dont le joueur gagne.
	*/
	public String getMessageGagne()
	{
		return this.metier.getMessageGagne();
	}

	/** 
		* @return Retourne le nom du jeton (noir ou rouge).
	*/
	public String getJetonJoueur()
	{
		String sJetonJoueur = "jeton_";
		sJetonJoueur += this.metier.getJoueur(this.getNumJoueurActif()).getCouleur();

		return sJetonJoueur;
	}
	
	/**
		* @return Retourne le nom du jeton (noir ou rouge).
		* @param numPanel : Le numéro du panel.
	*/
	public String getNomJeton(int numPanel)
	{
		String sNomJeton = "jeton_";

		if ( this.getJoueur(numPanel-1).getCouleur() == 'R')
		{
			sNomJeton += 'R';
		}
		else if ( this.getJoueur(numPanel-1).getCouleur() == 'N' )
		{
			sNomJeton += 'N';
		}

		return sNomJeton;
	} 

	/*------------------------------------------------------*/
	/*				MÉTHODES DE LA PARTIE IHM 				*/
	/*------------------------------------------------------*/

	/**
		* Modifie le nombre de jeton du joueur.
	*/
	public void setNbJeton()
	{
		this.ihmJoueur[this.getNumJoueurActif()].setNbJeton();
	}

	/**
		* Ajout une carte dans la collection du joueur.
		* @param nomCarte : Le nom de la carte.
	*/
	public void ajouterCarte(String nomCarte)
	{
		this.ihmJoueur[this.getNumJoueurActif()].ajouterCarte(nomCarte);
	}

	/**
		* Fixe le mode sombre.
	*/
	public void setModeSombre()
	{
		this.metier.setCouleurAffichage();
		this.ihmPlateau.setModeSombre();
		for(FrameJoueur frameJoueur : this.ihmJoueur) 
		{
			frameJoueur.setModeSombre(); 
		}
	}

	/**
		* Fixe le mode clair.
	*/
	public void setModeClair()
	{
		this.metier.setCouleurAffichage();
		this.ihmPlateau.setModeClair();
		for(FrameJoueur frameJoueur : this.ihmJoueur) 
		{
			frameJoueur.setModeClair();
		}
	}

	/**
		* Le déplacement des fenêtres graphiques.
		* @param origine : L'origine (Plateau).
	*/
	public void positionFrame(String origine)
	{
		Point p;
		if ( origine.equals("P") && this.ihmPlateau != null && this.ihmJoueur[0] != null && this.ihmJoueur[1] != null )
		{
			p = this.ihmPlateau.getLocation();
			this.ihmJoueur[0].setLocation(p.x-400, p.y);
			this.ihmJoueur[1].setLocation(p.x+610, p.y);
		}
	}

	/**
		* Quitte le jeu.
	*/
	public void fermerJeu()
	{
		this.metier = null;
		this.ihmAccueil = null;
		this.ihmPlateau.fermerPlateau();
		this.ihmPlateau = null;
		for(FrameJoueur frameJoueur : this.ihmJoueur) 
		{
			frameJoueur.resetNumFrameJoueur(); 
			frameJoueur.fermerJoueur(); 
			frameJoueur = null; 
		}
	}

	/**
		* Permet de sauvegarder une partie.
	*/
	public void nettoyerJeuGraphique()
	{
		if ( this.ihmPlateau != null )
		{
			this.ihmPlateau.fermerPlateau();
			for(FrameJoueur frameJoueur : this.ihmJoueur)
			{
				frameJoueur.resetNumFrameJoueur();
				frameJoueur.fermerJoueur();
			}
		}
	}

	/*--------------------------------------------------------------*/
	/*				MÉTHODES DE LA PARTIE SÉRIALISATION				*/
	/*--------------------------------------------------------------*/

	/**
		* @return Retourne les noms des parties enregistrées.
	*/
	public String[] getJeuxEnregistres()
	{
		Stack<Jeu> ensJeux = new DisquetteJeu().getPileJeux();
		if ( ensJeux!= null )
		{
			String[] tabNomJeu = new String[ensJeux.size()];
			for(int cpt=0; cpt<tabNomJeu.length; cpt++)
			{
				tabNomJeu[cpt] = ensJeux.get(cpt).getNom();
			}

			return tabNomJeu;
		}
		return null;
	}

	/**
		* Permet de sauvegarder une partie.
		* @param nomJeu : le nom de la partie que le joueur sauvegarde.
	*/
	public void sauvegarder(String nomJeu)
	{
		DisquetteJeu disquetteJeu = new DisquetteJeu();
		disquetteJeu.sauvegardeJeu(nomJeu, this.metier);
	}

	/**
		* Permet d'ouvrir une partie.
		* @param nomJeu : le nom de la partie que le joueur ouvre.
	*/
	public void ouvrir(String nomJeu)
	{
		this.nettoyerJeuGraphique();

		int 		largeurEcran, hauteurEcran;
		Dimension 	dimEcran;

		dimEcran     = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		hauteurEcran = (int) dimEcran.getHeight();
		largeurEcran = (int) dimEcran.getWidth();

		this.metier = new Okiya(nomJeu);

		int indiceJoueur=0;
		String nomJoueur1 = this.metier.getJoueur(indiceJoueur++).getPseudoJoueur();
		String nomJoueur2 = this.metier.getJoueur(indiceJoueur).getPseudoJoueur();
		this.reinitialiserJeu(nomJoueur1, nomJoueur2);

		indiceJoueur = 0;
		this.ihmPlateau.majPlateau();
		this.ihmJoueur[indiceJoueur].majJoueur(largeurEcran, hauteurEcran, this.metier.getJoueur(indiceJoueur).getPseudoJoueur());
		indiceJoueur++;
		this.ihmJoueur[indiceJoueur].majJoueur(largeurEcran, hauteurEcran, this.metier.getJoueur(indiceJoueur).getPseudoJoueur());
	}

	/*--------------------------------------------------------------------------------------*/
	/*							MAIN : ÉXÉCUTION DE L'APPLICATION							*/
	/*--------------------------------------------------------------------------------------*/

	public static void main(String[] args) 
	{
		new Controleur();	
	}

}