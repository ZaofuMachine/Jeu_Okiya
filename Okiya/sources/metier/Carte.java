package sources.metier;

/*----L'-import-pour-la-serialisation----*/
import java.io.Serializable;

/**
	* Classe Carte
	* @author 	: -
	* @version 	: 1.0
	* date 		! 09/06/2020
*/

/**
	* Explication : 
	* Une carte possède une spécificité : Soleil levant / Tanzaku 	/ Oiseau 	/ 	Pluie
	* Une carte possède une végétation 	: Érable 		/ Cerisier 	/ Pin 		/ 	Iris
*/

public class Carte implements Serializable
{
	/*---------------*/
	/* Les Attributs */
	/*---------------*/

	/**
		* Définition du serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/**
		* Les éléments d'une carte
	*/
	private String specificite; 	// L'attribut renseigne la spécificité de la carte.
	private String vegetation; 		// L'attribut renseigne la végétation de la carte.
	
	/*-----------------*/
	/* Le Constructeur */
	/*-----------------*/

	/**
		* Constructeur Carte
		* À l'instanciation d'un objet Carte(String specificite, String vegetation), une carte est créée.
		* @param specificite 	: la spécificité de la carte.
		* @param vegetation 	: la végétation de la carte.
	*/
	public Carte(String specificite, String vegetation)
	{
		this.specificite = specificite; 	// La spécificité de la carte.
		this.vegetation = vegetation; 		// La végétation de la carte.
	}

	/*------------------------------------------------------------------------------------------------------*/
	/* 											LES MÉTHODES 												*/
	/*------------------------------------------------------------------------------------------------------*/


	/*----------------------------------*/
	/*			LES ACCESSEURS			*/
	/*----------------------------------*/

	/**
		* @return Retourne la spécificité de la carte.
	*/
	public String getSpecificite()
	{
		return this.specificite;
	}

	/**
		* @return Retourne la végétation de la carte.
	*/
	public String getVegetation()
	{
		return this.vegetation;
	}

	/*------------------------------*/
	/*			COMPARAISON			*/
	/*------------------------------*/

	/**
		* @return Retourne true : si cette carte est la même que la carte comparée / false : si cette carte n'est pas la même que la carte comparée.
		* @param carte : la carte qui est comparée.
	*/
	public boolean equals(Carte carte)
	{
		return this.specificite.equals(carte.specificite) && this.vegetation.equals(carte.vegetation);
	}

	/*------------------------------*/
	/*			L'AFFICHAGE			*/
	/*------------------------------*/

	/**
		* @return Affiche les caractéristiques(spécificité et végétation) d'une carte.
	*/
	public String toString()
	{
		return "Carte : " + this.specificite + " " + this.vegetation;
	}
}