package sources.metier;

/*----Les-imports-pour-le-metier----*/
import sources.metier.Carte;
import sources.metier.Joueur;

/*----Les-imports-pour-la-serialisation----*/
import sources.serialisation.DisquetteJeu;
import sources.serialisation.Jeu;
import java.io.Serializable;

/*----Les-imports-pour-lecture-de-fichier----*/
import java.io.FileReader;
import java.util.Scanner;

import java.util.Collections;
import java.util.Stack;

/**
	* Classe Okiya
	* @author 	: -
	* @version 	: 1.0
	* date 		! 14/06/2020
*/

public class Okiya implements Serializable
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/
	
	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
		* Les éléments du jeu
	*/
	private Joueur[] 		tabJoueur; 			// L'attribut renseigne sur les joueurs. Dans ce jeu, il y a deux joueurs.
	private Carte[][] 		tabCarte; 			// L'attribut renseigne sur le tableau de carte du jeu qui est indépendant.
	private String[][] 		tabPlateau;			// L'attribut renseigne sur le plateau de jeu qui est formé du nom des cartes.
	private String 			contrSpecificite; 	// L'attribut renseigne sur la contrainte de spécificité.
	private String 			contrVegetation;	// L'attribut renseigne sur la contrainte de végétation.
	private String 			messageNonJouable; 	// L'attribut renseigne sur le message lorsque le joueur ne peut pas jouer.
	private String 			messageGagne; 		// L'attribut renseigne sur le message lorsque le joueur gagne.
	private boolean 		couleurAffichage; 	// L'attribut renseigne sur la couleur d'affichage.


	/*-------------------*/
	/* Les Constructeurs */
	/*-------------------*/

	/**
		* Constructeur Okiya
		* À l'instanciation d'un objet Okiya(), une partie de jeu est initialisée de manière non graphique.
	*/
	public Okiya()
	{
		/*------------------------*/
		/* Les conditions de jeux */
		/*------------------------*/
		this.tabJoueur = new Joueur[2]; 		// Le jeu se joue à deux joueurs.
		this.tabCarte = new Carte[4][4]; 		// Le tableau Carte qui crée les cartes et les mélange.
		this.tabPlateau = new String[4][4]; 	// Le tableau de carte sous forme de chaîne de caractères.
		this.contrSpecificite = ""; 			// Au début du jeu, aucune contrainte de spécificité.
		this.contrVegetation = ""; 				// Au début du jeu, aucune contrainte de végétation.
		this.messageNonJouable = "";			// Au début du jeu, aucun message non jouable.
		this.messageGagne = "";					// Au début du jeu, aucun message gagne.
		this.couleurAffichage = false;			// Au début du jeu, l'affichage est clair.

		/*---------------------*/
		/* Les initialisations */
		/*---------------------*/
		this.initCartes(); // L'initialisation des Cartes.
		this.initPlateau(); // L'initialisation du plateau.
		this.initJoueurs(); // L'initialisation des joueurs.
	}

	/**
		* Constructeur Okiya(String nomJeu)
		* À l'instanciation d'un objet Okiya(String nomJeu), une partie est alors chargé avec le nom de la partie renseignée.
		* @param nomJeu : le nom de la partie renseignée pour être chargé.
	*/
	public Okiya(String nomJeu)
	{
		Jeu jeu = new DisquetteJeu().chargerJeu(nomJeu); // Chargement d'une partie.

		/*--------------------------------------------------*/
		/* Adaptation des attributs d'Okiya à partir de jeu */
		/*--------------------------------------------------*/
		this.tabJoueur = new Joueur[jeu.getJeu().getNbJoueur()]; 	// Récupère le nombre de joueurs de la partie chargée (deux joueurs).
		this.tabCarte = new Carte[4][4];							// Le tableau Carte qui crée les cartes et les mélange.
		this.tabPlateau = new String[4][4]; 						// Le tableau de carte sous forme de chaîne de caractères.
		this.contrSpecificite = jeu.getJeu().getContrSpecificite(); // Récupère la contrainte de spécificité de la partie chargée.
		this.contrVegetation = jeu.getJeu().getContrVegetation(); 	// Récupère la contrainte de végétation de la partie chargée.
		this.couleurAffichage = jeu.getJeu().getCouleurAffichage();	// Récupère la couleur d'affichage.

		for(int cpt=0; cpt<this.tabJoueur.length; cpt++)
		{
			this.tabJoueur[cpt] = jeu.getJeu().getJoueur(cpt); // Charge les joueurs avec leurs caractéristiques.
		}
		
		for(int indiceLig=0; indiceLig<this.tabPlateau.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.tabPlateau.length; indiceCol++)
			{
				// Charge les Cartes.
				this.tabCarte[indiceLig][indiceCol] = jeu.getJeu().getCarteTab(indiceLig, indiceCol);
				// Charges les cartes sous forme de chaîne de caractères.
				this.tabPlateau[indiceLig][indiceCol] = jeu.getJeu().getCarteNom(indiceLig, indiceCol);
			}
		}
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LES ACCESSEURS			*/
	/*----------------------------------*/

	/**
		* @return Retourne un joueur.
		* @param  indice : indice du joueur qui est retourné.
	*/
	public Joueur getJoueur (int indice)
	{
		return this.tabJoueur[indice];
	}

	/**
		* @return Retourne une Carte.
		* @param indiceLig : indice de la ligne du tableau de Carte.
		* @param indiceCol : indice de la colonne du tableau de Carte.
	*/
	public Carte getCarteTab (int indiceLig, int indiceCol) 
	{ 
		return this.tabCarte[indiceLig][indiceCol];
	}

	/**
		* @return Retourne le tableau de Carte.
	*/
	public Carte[][] getTabCarte()
	{
		return this.tabCarte; 
	}

	/**
		* @return Retourne le nombre de joueur.
	*/
	public int getNbJoueur() 
	{ 
		return this.tabJoueur.length; 
	}

	/**
		* @return Retourne le numéro du joueur actif(le joueur qui peut jouer).
	*/
	public int getNumJoueurActif()
	{
		int numJoueurActif = -1;

		for (int cpt=0; cpt<this.tabJoueur.length; cpt++) 
		{
			if ( this.getJoueur(cpt).getActif() )
			{
				numJoueurActif = cpt;
			}
		}
		return numJoueurActif;
	}

	/**
		* @return Retourne le nom d'une carte du plateau de jeu.
		* @param indiceLig : indice de la ligne du plateau de jeu.
		* @param indiceCol : indice de la colonne du plateau de jeu.
	*/
	public String getCarteNom (int indiceLig, int indiceCol) 	
	{ 
		return this.tabPlateau[indiceLig][indiceCol]; 	
	}

	/**
		* @return Retourne la contrainte de spécificité.
	*/
	public String getContrSpecificite()
	{
		return this.contrSpecificite;
	}

	/**
		* @return Retourne la contrainte de végétation.
	*/
	public String getContrVegetation()
	{
		return this.contrVegetation;
	}

	/**
		* @return Retourne un message indiquant l'action que le joueur ne peut pas réaliser.
	*/
	public String getMessageNonJouable()
	{
		return this.messageNonJouable;
	}

	/**
		* @return Retourne un message indiquant la façon dont le joueur gagne.
	*/
	public String getMessageGagne()
	{
		return this.messageGagne;
	}

	/**
		* @return Retourne la couleur d'affichage.
	*/
	public boolean getCouleurAffichage()
	{
		return this.couleurAffichage;
	}

	/*--------------------------------------*/
	/*			LES MODIFICATEURS			*/
	/*--------------------------------------*/

	/** 
		* Indique que c'est au joueur suivant de jouer. 
	*/
	public void setJoueurSuivant()
	{
		for (int cpt=0; cpt<this.tabJoueur.length; cpt++) 
		{
			this.tabJoueur[cpt].setActif();
		}
	}

	/**
		* @return Retourne true : si le jeton du joueur peut être posé / false : si le jeton ne peut pas être posé
		* @param indiceLig 		: indice de la ligne du plateau de jeu.
		* @param indiceCol 		: indice de la colonne du plateau de jeu.
		* @param jeton 			: le nom du jeton (noir ou rouge).
	*/
	public boolean setPlateauJeton(int indiceLig, int indiceCol, String jeton)
	{
		if ( indiceLig < 0 || indiceLig > this.tabPlateau.length )
		{ 
			return false; 
		}

		if ( indiceCol < 0 || indiceCol > this.tabPlateau.length ) 
		{
			return false;
		}

		String tmp = this.tabPlateau[indiceLig][indiceCol];
		this.tabPlateau[indiceLig][indiceCol] = jeton;

		return true;
	}

	/**
		* Fixe la couleur d'affichage.
	*/
	public void setCouleurAffichage()
	{
		if ( this.couleurAffichage )
		{
			this.couleurAffichage = false;
		}
		else
		{
			this.couleurAffichage = true;
		}
	}

	/**
		* Met à jour les contraintes de cartes (spécificité et végétation) que le joueur doit respecter pour jouer.
		* @param contrSpecificite 	: la contrainte de spécificité.
		* @param contrVegetation 	: la contrainte de végétation. 
	*/
	public void majContraintes(String contrSpecificite, String contrVegetation)
	{
		this.contrSpecificite = contrSpecificite;
		this.contrVegetation  = contrVegetation;
	}

	/*------------------------------------------*/
	/*			VÉRIFICATION JOUABILITÉ			*/
	/*------------------------------------------*/

	/**
		* @return Retourne true : si le joueur peut jouer / false : si le joueur peut pas jouer.
		* @param indiceLig 		: indice de la ligne du plateau de jeu.
		* @param indiceCol 		: indice de la colonne du plateau de jeu.
		* @param specificite 	: une spécifité sur lequel le jouer veut jouer.
		* @param vegetation 	: une végétation sur lequel le jouer veut jouer.
	*/
	public boolean estJouable(int indiceLig, int indiceCol, String specificite, String vegetation)
	{

		// Vérification lors du premier coup des joueurs qu'ils ne peuvent pas jouer au centre du plateau. 
		if ( (indiceLig == 1 || indiceLig == 2) && (indiceCol == 1 || indiceCol == 2) && this.getJoueur(getNumJoueurActif()).getNbCoup()<1 )
		{
			this.messageNonJouable = "Vous ne pouvez pas pas jouer dans une des cases du centre du plateau";
			return false;
		} 


		// Vérification des contraintes.
		if ( (! this.getContrSpecificite().equals("") || !this.getContrVegetation().equals(""))

														&& 

			(!this.getContrSpecificite().equals(specificite) && !this.getContrVegetation().equals(vegetation)) )
		{
			this.messageNonJouable = "Vous ne pouvez pas jouer sur une carte ayant comme spécificité : " + specificite + " et comme végétation : " + vegetation;
			this.messageNonJouable += "\n" + "Les contraintes à respecter sont : ";
			this.messageNonJouable += "\n" + "spécificité 	: " + this.getContrSpecificite();
			this.messageNonJouable += "\n" + "végétation 	: " + this.getContrVegetation();
			return false;
		}

		// Vérification que si le pion est placé sur un autre pion, le joueur ne peut pas jouer.
		if ( this.estPris(indiceLig, indiceCol) )
		{
			this.messageNonJouable = "Vous ne pouvez pas jouer sur un autre pion";
			return false;
		}

		return true;	
	}

	/**
		* @return Retourne true : si un pion est déja placé à l'endroit souhaité / false : si si un pion n'est pas déja placé souhaité.
		* @param indiceLig 		: indice de la ligne du plateau de jeu.
		* @param indiceCol 		: indice de la colonne du plateau de jeu.
	*/
	public boolean estPris(int indiceLig, int indiceCol)
	{
		// Vérification que si le pion est placé sur un autre pion, le joueur ne peut pas jouer.
		if ( ! tabPlateau[indiceLig][indiceCol].equals("jeton_R") && ! tabPlateau[indiceLig][indiceCol].equals("jeton_N") )
		{
			return false;
		}
		return true;
	}

	/*--------------------------------------*/
	/*			LES INITIALISATIONS			*/
	/*--------------------------------------*/

	/**
		* Initialise les joueurs.
	*/
	private void initJoueurs() 
	{
		int joueurCouleur = (int) (Math.random() * this.getNbJoueur());

		for (int cpt=0; cpt<this.tabJoueur.length; cpt++) 
		{
			if ( cpt%2 == 0 )
			{
				this.tabJoueur[joueurCouleur] = new Joueur('R');
			}
			else
			{
				if ( joueurCouleur < cpt )
				{
					joueurCouleur++;
				}
				else
				{
					joueurCouleur--;
				}
				this.tabJoueur[joueurCouleur] = new Joueur('N');
			}
		}

		for (int cpt=0; cpt<this.tabJoueur.length; cpt++) 
		{
			if ( this.tabJoueur[cpt].getNumJoueur() > this.getNbJoueur() )
			{
				this.tabJoueur[cpt].setNumJoueur(cpt+1);
			}
		}

		// Initialisation du joueur actif
		this.tabJoueur[joueurCouleur].setActif();
	}

	/**
		* Initialise les Cartes.
	*/
	private void initCartes()
	{
		try
		{
			FileReader fr = new FileReader("./ressources/data/initialisation_cartes.dat");
			Scanner sc = new Scanner(fr);

			// Pour chaque ligne
			int cpt1=0;
			int cpt2=0;
			while( sc.hasNext() )
			{
				cpt2=0;
				while(cpt2 < this.tabCarte.length)
				{
					String ligne = sc.nextLine();
					String[] tabInfos = ligne.split("\t");
					this.tabCarte[cpt1][cpt2] = new Carte(tabInfos[0], tabInfos[1]);
					cpt2++;
				}
				cpt1++;
			}
			fr.close();
		}
		catch(Exception e) { e.printStackTrace(); }

		// Mélange des cartes
		this.melangerCartes();

	}

	/**
		* Initialise le plateau.
	*/
	private void initPlateau()
	{
		// Ajout des cartes du tableau de Cartes mélangés dans le tableau de plateau String
		for(int indiceLig=0; indiceLig<this.tabCarte.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.tabCarte.length; indiceCol++)
			{
				this.tabPlateau[indiceLig][indiceCol] = this.tabCarte[indiceLig][indiceCol].getSpecificite() + "_" + this.tabCarte[indiceLig][indiceCol].getVegetation();
			}
		}
	}

	/**
		* Mélange les Cartes.
	*/
	private void melangerCartes()
	{
		// Le 1er mélange  
		int cpt1=0;
		int cpt2=0;
		Carte tmp;


		int nbCartes = this.tabCarte.length;

		// Le 1er mélange
		while(cpt1<nbCartes)
		{
			cpt2 = (int) (Math.random() * this.tabCarte.length);

			tmp = this.tabCarte[cpt1][cpt2];
			this.tabCarte[cpt1][cpt2] = this.tabCarte[cpt2][cpt1];
			this.tabCarte[cpt2][cpt1] = tmp;
			tmp = null;

			while(cpt2<this.tabCarte.length-cpt1-1)
			{
				tmp = this.tabCarte[cpt2][cpt1];
				this.tabCarte[cpt2][cpt1]=this.tabCarte[cpt1][cpt2];
				this.tabCarte[cpt1][cpt2] = tmp;
				tmp = null;
				
				cpt2++;
			}
			cpt1++;

		}

		// Le 2ème mélange
		cpt1=0;
		cpt2=0;
		int cpt3=0;
		while(cpt1<this.tabCarte.length*this.tabCarte.length)
		{
			cpt2 = (int) (Math.random() * this.tabCarte.length);
			cpt3 = (int) (Math.random() * this.tabCarte.length);
			tmp  = this.tabCarte[cpt2][cpt3];

			this.tabCarte[cpt2][cpt3]=this.tabCarte[cpt3][cpt2];
			this.tabCarte[cpt3][cpt2]=tmp;
			cpt1++;
		}

		// Le 3ème mélange
		Stack<Carte> pileCarte = new Stack<Carte>();
		for(int indiceLig=0; indiceLig<this.tabCarte.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.tabCarte.length; indiceCol++)
			{
				pileCarte.push(this.tabCarte[indiceLig][indiceCol]);
			}
		}

		Collections.shuffle(pileCarte);

		for(int indiceLig=0; indiceLig<this.tabCarte.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.tabCarte.length; indiceCol++)
			{
				if ( ! pileCarte.empty() )
				{
					this.tabCarte[indiceLig][indiceCol] = pileCarte.pop();
				}
			}
		}
	}


	/*------------------------------------------*/
	/*			VÉRIFICATION DU GAGNANT			*/
	/*------------------------------------------*/

	/**
		* @return Retourne true : si le joueur a gagné / false : si le joueur n'a pas gagné. 
	*/

	public boolean estGagnant()
	{
		final int ligMax = this.tabPlateau.length; 
		final int colMax = this.tabPlateau.length;

		// Vérification de la condition numéro 1 :  
			// Aligner 4 Geishas à votre couleur dans le jardin impérial (l’alignement peut être vertical, horizontal ou diagonal)

		for(Joueur joueur : tabJoueur)
		{
			for(int indiceLig=0; indiceLig<this.tabPlateau.length; indiceLig++)
			{
				for(int indiceCol=0; indiceCol<this.tabPlateau.length; indiceCol++)
				{

					if ( indiceCol<=colMax 	&& compterJeton(this.tabPlateau, indiceLig, indiceCol, 1, 1) == 4
					 			// diagonale : vers le bas et à droite 
						|| indiceCol<=colMax 	&& compterJeton(this.tabPlateau, indiceLig, indiceCol, -1, 1) == 4
								// vers le haut et à droite
						|| indiceLig<=ligMax  	&& compterJeton(this.tabPlateau, indiceLig, indiceCol, 0, 1) == 4
								// horizontal vers la droite
						|| indiceLig<= ligMax	&& compterJeton(this.tabPlateau, indiceLig, indiceCol, 1, 0) == 4
								// vertical du haut vers le bas

						)
					{
						this.messageGagne = this.getJoueur(getNumJoueurActif()).getPseudoJoueur() + " a effectué un alignement de quatre jetons";
						return true;
					}
				}
			}
		}

		// Vérification de la condition numéro 2 : 
			// Faire un carré de 4 Geishas à votre couleur dans le jardin impérial (carré de 2x2)

		for(Joueur joueur : tabJoueur)
		{
			for(int indiceLig=0; indiceLig<this.tabPlateau.length; indiceLig++)
			{
				for(int indiceCol=0; indiceCol<this.tabPlateau.length; indiceCol++)
				{					
					if ( indiceCol<=colMax 	&& indiceLig<=ligMax && compterJetonCarre(this.tabPlateau, indiceLig, indiceCol, 0, 1) == 4 )
					{
						this.messageGagne = this.getJoueur(getNumJoueurActif()).getPseudoJoueur() + " a effectué un carré de quatre jetons";
						return true;
					}
				}
			}
		}

		// Vérification de la condition numéro 3 :
			// Empêcher votre adversaire de jouer

		boolean bEmpecherJouer=false;
		String[] tabSpecificiteVegetation;
		String nomSpecificite="";
		String nomVegetation="";
		int cptPossibilite=0; // compte les possiblités de jeux
		for(Joueur joueur : tabJoueur)
		{
			if ( joueur.getNbCoup()>0)
			{
				for(int indiceLig=0; indiceLig<this.tabPlateau.length; indiceLig++)
				{
					for(int indiceCol=0; indiceCol<this.tabPlateau.length; indiceCol++)
					{
						// si la case n'est pas un pion de joueur
						if ( ! this.tabPlateau[indiceLig][indiceCol].equals("jeton_R") && ! this.tabPlateau[indiceLig][indiceCol].equals("jeton_N") )
						{
							// on récupère la spécificité et la végétation
							tabSpecificiteVegetation = this.tabPlateau[indiceLig][indiceCol].split("_");

							for(int cpt=0; cpt<tabSpecificiteVegetation.length; cpt++)
							{
								if ( cpt%2 == 0 )
								{
									nomSpecificite = tabSpecificiteVegetation[cpt];
								}
								else
								{
									nomVegetation = tabSpecificiteVegetation[cpt];
								}

							}

							if ( ! this.getContrSpecificite().equals(nomSpecificite) && ! this.getContrVegetation().equals(nomVegetation) )
							{
								bEmpecherJouer = true;
							}
							else if ( this.getContrSpecificite().equals(nomSpecificite) || this.getContrVegetation().equals(nomVegetation) )
							{
								bEmpecherJouer = false;
								cptPossibilite++; // compte les possibilités pour jouer
							}
						}
					}
				}
			}
		}
		if(bEmpecherJouer && cptPossibilite < 1) // Si le nombre de possiblité de jeu est inférieur à 1, le joueur ne peut pas jouer
		{
			this.messageGagne = this.getJoueur(getNumJoueurActif()).getPseudoJoueur() + " a empêché de jouer son adversaire";
			return bEmpecherJouer;
		}

		return false;
	}

	/*----------------------------------------------*/
	/*			VÉRIFICATION DE L'ÉGALITÉ			*/
	/*----------------------------------------------*/

	/**
		* @return Retourne true : si la partie donne un égalité / false : si la partie ne donne pas un égalité. 
	*/

	public boolean estEgatlite()
	{
		int cpt=0;

		for(int indiceLig=0; indiceLig<this.tabPlateau.length; indiceLig++)
		{
			for(int indiceCol=0; indiceCol<this.tabPlateau.length; indiceCol++)
			{
				if ( this.tabPlateau[indiceLig][indiceCol].equals("jeton_N") || this.tabPlateau[indiceLig][indiceCol].equals("jeton_R"))
				{
					cpt++;
				}
			}
		}

		if ( cpt == (this.tabPlateau.length * this.tabPlateau.length) )
		{
			return true;
		}

		return false;
	}

	/**
		* @return Retourne le nombre de jeton aligné dans une direction précise.
		* @param tabPlateau : le plateau.
		* @param lig 		: indice de la ligne du plateau.
		* @param col 		: indice de la colonne du plateau.
		* @param ligDir 	: direction de la ligne (nord=-1 ou sud=1) pour compter les jetons. 
		* @param colDir 	: direction de la colonne (ouest=-1 ou est=1) pour compter les jetons.
	*/
	private int compterJeton(String[][] tabPlateau, int lig, int col, int ligDir, int colDir)
	{
		int cpt 	=	0; 		// compte le nombre de jeton aligné
		int ligCpt =	lig;	// s'occupe de la direction de la ligne (nord ou sud) du comptage des jetons
		int colCpt =	col; 	// s'occupe de la direction la colonne (ouest ou est) du comptage des jetons


		while(  ligCpt >= 0 && ligCpt < tabPlateau.length && colCpt >= 0 && colCpt < tabPlateau.length 
				&& tabPlateau[ligCpt][colCpt].equals(tabPlateau[lig][col]) )
		{
			ligCpt += ligDir; 
			colCpt += colDir;

			cpt++;

		}

		return cpt;
	}

	/**
		* @return Retourne le nombre de jeton formant un carrée dans une direction précise.
		* @param tabPlateau : le plateau.
		* @param lig 		: indice de la ligne du plateau.
		* @param col 		: indice de la colonne du plateau.
		* @param ligDir 	: direction de la ligne (nord=-1 ou sud=1) pour compter les jetons. 
		* @param colDir 	: direction de la colonne (ouest=-1 ou est=1) pour compter les jetons.
	*/
	private int compterJetonCarre(String[][] tabPlateau, int lig, int col, int ligDir, int colDir)
	{
		int cpt 	=	0; 		// compte le nombre de jeton aligné
		int ligCpt 	=	lig; 	// s'occupe de la direction de la ligne (nord ou sud) du comptage des jetons
		int colCpt 	=	col; 	// s'occupe de la direction la colonne (ouest ou est) du comptage des jetons


		while(  ligCpt >= 0 && ligCpt < tabPlateau.length && colCpt >= 0 && colCpt < tabPlateau.length 
				&& tabPlateau[ligCpt][colCpt].equals(tabPlateau[lig][col]) )
		{
			ligCpt += ligDir; 
			colCpt += colDir;

			cpt++;

			if ( cpt == 2 )
			{
				ligCpt++; 				// passe à la ligne suivante
				colCpt = colCpt - 2; 	// retourne à la case colonne de départ
			}
		}

		return cpt;
	}

	/*--------------------------------------------------------------------------------------*/
	/*							MAIN : PHASE DE TEST D'OKIYA								*/
	/*--------------------------------------------------------------------------------------*/
	public static void main(String[] args) 
	{
		new Okiya();
	}
}