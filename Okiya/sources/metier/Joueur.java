package sources.metier;

import java.io.Serializable;

/**
	* Classe Joueur
	* @author 	: -
	* @version 	: 1.0
	* date 		! 09/06/2020
*/

public class Joueur implements Serializable
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/

	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
		* Les éléments du joueur
	*/
	private static 	int nbJoueur; 			// L'attribut renseigne sur le nombre de joueurs (numéro séquentiel auto-incrementé).
	private 		int numJoueur; 			// L'attribut renseigne sur le numéro du joueur.
	private 		String pseudoJoueur; 	// L'attribut renseigne sur le pseudo du joueur.
	private 		PileString pileString; 	// L'attribut renseigne sur la pile de cartes acquises par le joueur.
	private 		String[] carteJoueur;	// L'attribut renseigne sur les cartes acquises par le joueur.
	private 		int nbJeton; 			// L'attribut renseigne sur le nombre de jeton du joueur.
	private 		int nbCoup;				// L'attribut renseigne sur le nombre de coup joué par le joueur.
	private 		char couleur; 			// L'attribut renseigne sur la couleur du joueur (noir ou rouge). 
	private 		boolean estActif; 		// L'attribut renseigne si le joueur doit jouer.

	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur Joueur
		* À l'instanciation d'un objet Joueur(char couleur), un joueur est créé.
		* @param couleur : couleur du joueur.
	*/
	public Joueur(char couleur)
	{
		/*----------------------*/
		/* Les caractéristiques */
		/*----------------------*/
		this.numJoueur 		= ++Joueur.nbJoueur; 	// Le numéro du joueur.
		this.pseudoJoueur 	= "";					// Le joueur possède un pseudo qu'il communique à l'accueil du jeu.
		this.pileString 	= new PileString(8);	// La pile de carte peut avoir jusqu'à 8 cartes.
		this.carteJoueur 	= new String[8];		// Le joueur peut avoir jusqu'à 8 cartes.
		this.nbJeton 		= 8; 					// Le nombre de jeton du joueur qui au début de la partie est initialisé à 8 jetons.
		this.nbCoup 		= 0; 					// Le nombre de coup joué par le joueur qui au début de la partie n'a pas encore joué.
		this.couleur 		= couleur; 				// La couleur du joueur (noir ou rouge).
		this.estActif 		= false; 				// Au début de la partie, le joueur n'est pas actif.
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/

	/*----------------------------------*/
	/*			LES ACCESSEURS			*/
	/*----------------------------------*/

	/**
		* @return Retourne le numéro du joueur.
	*/
	public int getNumJoueur()
	{
		return this.numJoueur;
	}

	/**
		* @return Retourne le pseudo du joueur.
	*/
	public String getPseudoJoueur()
	{
		return this.pseudoJoueur;
	}

	/**
		* @return Retourne le nombre de jeton du joueur.
	*/
	public int getNbJeton()
	{
		return this.nbJeton;
	}

	/**
		* @return Retourne le nombre de coups joués par le joueur.
	*/
	public int getNbCoup()
	{
		return this.nbCoup;
	}

	/**
		* @return Retourne la couleur du joueur (noir : 'N' - rouge : 'R').
	*/
	public char getCouleur()
	{
		return this.couleur;
	}

	/**
		* @return Retourne la taille de la collection de carte du joueur.
	*/
	public int getNbPlaceCartes()
	{
		return this.carteJoueur.length;
	}

	/**
		* @return Retourne le nom de la couleur du joueur (noir : "Noir" - rouge : "Rouge").
	*/
	public String getNomCouleur()
	{
		if ( this.couleur == 'R' )
		{
			return "Rouge";
		}
		else if ( this.couleur == 'N' )
		{
			return "Noir";
		}
		else
		{
			return "Pas de couleur";
		}
	}

	/**
		* @return Retourne true : si le joueur est actif / false : si le joueur n'est pas actif.
	*/
	public boolean getActif()
	{
		return this.estActif;
	}

	/*--------------------------------------*/
	/*			LES MODIFICATEURS			*/
	/*--------------------------------------*/

	/**
		* Fixe le pseudo du joueur.
		* @param pseudo : le pseudo du joueur.
	*/
	public void setPseudo(String pseudo)
	{
		if ( pseudo != null )
		{
			this.pseudoJoueur = pseudo;
		}
		else
		{
			this.pseudoJoueur = "J" + this.numJoueur;
		}
	}

	/**
		* Fixe le numéro du joueur lors d'une réinitialisation.
		* @param numJoueur : le numéro du joueur.
	*/
	public void setNumJoueur(int numJoueur)
	{
		this.numJoueur = Joueur.nbJoueur = numJoueur;
	}

	/**
		* Lors de chaque coup joué par le joueur, le nombre de coup augmente.
	*/
	public void setNbCoup()
	{
		if ( this.nbCoup != 8 )
		{
			this.nbCoup++;
		}
	}

	/**
		* Lors de chaque coup joué par le joueur, le nombre de jeton diminue.
	*/
	public void diminuerNbJeton() 
	{
		if ( this.nbJeton != 0)
		{
			this.nbJeton--;
		} 
	}

	/**
		* Si le joueur n'était pas actif, il devient actif. Sinon, il devient inactif. 
	*/
	public void setActif()
	{
		if ( ! this.estActif ) 
		{
			this.estActif = true;
		}
		else 
		{ 
			this.estActif = false;
		}
	}

	/*--------------------------*/
	/*			L'AJOUT			*/
	/*--------------------------*/

	/**
		* @return Retourne une carte de la collection du joueur.
		* @param cpt : le numéro de la carte.
	*/
	public String getCarteCollectionJoueur(int cpt)
	{
		if ( cpt < 0 || cpt > this.carteJoueur.length )
		{
			return null;
		}
		return this.carteJoueur[cpt];
	}

	/**
		* Ajout d'une carte acquise dans la collection du joueur.
		* @param nomCarte : le nom de la carte.
	*/
	public void ajouterCarteCollectionJoueur(String nomCarte)
	{
		this.pileString.empiler(nomCarte);
		this.carteJoueur[this.pileString.getSommet()] = nomCarte;
	}


	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* @return Affiche les caractéristiques(nombre de jeton et sa couleur) du joueur.
	*/
	public String toString()
	{
		return "Joueur : " + "numéro " + this.numJoueur + " " + this.nbJeton + " jetons" + " " + this.couleur + " couleur";
	}



	private class PileString implements Serializable
	{
		/*---------------------------------------REPRÉSENTATION-D'UNE-PILE----------------------------------------------*/

					/****************************************************************************

											
								Empiler ->	|							|	<- Dépiler 
											|							|
											|							|
											|							|
											|	[&&&&&&&&&&&&&&&&&&&]	|	<- Sommet
											|							|
											|***************************|
											|							|
											|	[&&&&&&&&&&&&&&&&&&&]	|
											|							|
											|***************************|
											|							|
											|	[&&&&&&&&&&&&&&&&&&&]	|
											|							|
											|***************************|
											|							|
											|	[&&&&&&&&&&&&&&&&&&&]	|
											|							|
											|***************************|
											|							|
											|	[&&&&&&&&&&&&&&&&&&&]	|
											|							|
											|***************************|
											|___________________________|


					****************************************************************************/

		/*--------------------------------------------------------------------------------------------------------------*/

		/*---------------*/
		/* Les Attributs */
		/*---------------*/

		/**
			* Définition du serialVersionUID
		*/
		private static final long serialVersionUID = 1L;
	
		private String[] ensString; // L'attribut renseigne sur le nom des cartes acquises par le joueur.
		private int sommet;			// L'attribut renseigne sur le sommet de la pile.

		/*-----------------*/
		/* Le Constructeur */
		/*-----------------*/

		/**
			* Constructeur PileString
			* À l'instanciation d'un objet PileString(int nbEltTotal), une pile est créée.
			* @param nbEltTotal : nombre d'éléments de la pile.
		*/
		public PileString(int nbEltTotal)
		{
			this.ensString = new String[nbEltTotal];
			this.sommet = -1;	
		}

		/**
			* @return Retourne le numéro du sommet de la pile.
		*/
		public int getSommet()
		{
			return this.sommet;
		}

		/**
			* @return Retourne true : si la pile est vide / false : si la pile n'est pas vide.
		*/
		public boolean estVide()
		{
			return this.sommet == - 1;
		}

		/**
			* @return Retourne true : si la pile est pleine / false : si la pile n'est pas pleine.
		*/
		public boolean estPleine()
		{
			return this.sommet == this.ensString.length - 1;
		}

		/**
			* @return Retourne true : si l'action empiler a été effectué / false : si l'action empiler n'a pas été effectué.
			* @param nomCarte : le nom de la carte.
		*/
		public boolean empiler(String nomCarte)
		{
			if( this.estPleine() )
			{
				return false;
			}

			this.ensString[++this.sommet] = nomCarte;
			return true;
		}

		/**
			* @return Retourne le nom d'une carte du sommet de la pile.
		*/
		public String depiler()
		{
			return this.ensString[this.sommet--];
		}

		/**
			* Retourne la pile.
		*/
		public void retourner()
		{
			PileString tmp = new PileString(this.ensString.length);

			while( ! this.estVide() )
			{
				tmp.empiler( this.depiler() );
			}

			this.ensString = tmp.ensString;
			this.sommet = tmp.sommet;
		}

		/**
			* @return Affiche la pile.
		*/
		public String toString()
		{
			String sRet = "";
			for(int cpt=0; cpt<this.sommet+1; cpt++)
			{
				sRet += this.ensString[cpt];
			}
			return sRet;
		}
	}
}