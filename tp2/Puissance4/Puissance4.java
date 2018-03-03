/*
 * TP 3 : Puissance 4
 * 
 * @author Tassadit BOUADI.
 */

/**
 * Programme principal du jeu du puissance 4.
 * 
 */
public class Puissance4 {

	public static void main(String[] args) {
		//cr�ation des joueurs et appel de la fonction jouer
		JoueurHumain joueur1 = new JoueurHumain();
		JoueurMinMax joueur2 = new JoueurMinMax();
		
		jouer(joueur1, joueur2);
	}
	
	
	/**
	 * Fonction qui effectue la boucle de jeu.
	 * 
	 * @param joueur1 : le premier joueur.
	 * @param joueur2 : le second joueur.
	 */
	public static void jouer(Joueur joueur1, Joueur joueur2){
		Resultat res = null;
		int coup;
		Grille grille = new Grille();
		
		Joueur joueurCour = joueur1;	
		int numJoueur = Grille.JOUEUR1; //le joueur 1 commence � jouer
		
		int vainqueur = 0;//match nul
		boolean jeuFini = false;
		
		
		//boucle de jeu
		while(!jeuFini || grille.estPleine()){
			//affichage de la grille 
			System.out.println(grille);
			
			// Les joueurs jouent
			if(numJoueur == Grille.JOUEUR1) {
				res = joueur1.coup(grille, numJoueur);
			} else if(numJoueur == Grille.JOUEUR2) {
				res = joueur2.coup(grille, numJoueur);
			} else {
				System.err.println("Error");
				System.exit(-1);
			}
			
			//On test si on a un coups gagnant
			if(grille.coupGagnant(numJoueur, res.getColonne())) {
				jeuFini = true;
				vainqueur = numJoueur;
			}
			
			grille.joueEn(numJoueur, res.getColonne());
			
			// Fin boucle
			numJoueur = Grille.joueurSuivant(numJoueur);
		}
		
		//affichage de la grille 
		System.out.println(grille);
		
		
		//affichage du vainqueur
		switch(vainqueur){
			case Grille.JOUEUR1:
				System.out.println("Victoire du joueur 1");
				break;
			case Grille.JOUEUR2:
				System.out.println("Victoire du joueur 2");
				break;
			default:
				System.out.println("Match nul");
		}
	}

}
