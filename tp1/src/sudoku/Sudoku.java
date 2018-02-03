package sudoku;

/*
 * TP 1 - IA Informatique : Sudoku
 * 
 * @author Tassadit BOUADI.
 */
import java.util.Stack;
import java.util.ArrayList;


public class Sudoku {
	public static int TAILLE = 9;


	public static void main(String[] args) {
		int[][] grille1 = {
				{0,8,0,4,0,2,0,6,0},
				{0,3,4,0,0,0,9,1,0},
				{9,6,0,0,0,0,0,8,4},
				{0,0,0,2,1,6,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,3,5,7,0,0,0},
				{8,4,0,0,0,0,0,7,5},
				{0,2,6,0,0,0,1,3,0},
				{0,9,0,7,0,1,0,4,0}
		};

		//initialisation
		Grille grilleInit1 = new Grille(TAILLE, grille1);
		grilleInit1.afficheGrille();

		Stack<Grille> pile = new Stack<Grille>();
		pile.push(grilleInit1);

		long tmp = System.currentTimeMillis();
		boolean resul = resoudreSudoku(pile);


		if(resul){
			System.out.println("La grille a été résolue");
			Grille grilleResul = pile.peek();
			grilleResul.afficheGrille();
			System.out.println("Temps: " + (System.currentTimeMillis() - tmp));
		}
		else{
			System.out.println("Aucune solution n'a été trouvée");
			Grille grilleResul = pile.peek();
			grilleResul.afficheGrille();
		}
	}


	/**
	 * Fonction récursive qui recherche la solution, 
	 * en utilisant éventuellement des retours-arrière.
	 */
	public static boolean resoudreSudoku(Stack<Grille> pileGrilles){
		int i;
		Case cpossible;
		ArrayList<Integer> vpossible = new ArrayList<Integer>();
		Grille copie;
		Grille g = pileGrilles.pop();
		
		if(g.getNbCasesVides() == 0) {
			pileGrilles.push(g);
			return true;
		} else {
			cpossible = g.meilleurCase();
			
			System.out.println(cpossible.getI() + ", " + cpossible.getJ());

			// Initialisation des valeurs possibles
			for(i = 1; i < 10; i++) {
				if(g.casePossible(cpossible, i)) {
					vpossible.add(i);
				}
			}

			for(Integer v : vpossible) {
				copie = new Grille(g);

				copie.setCase(copie.getCase(cpossible.getI(), cpossible.getJ()), v);
				
				pileGrilles.push(copie);
				
				if(resoudreSudoku(pileGrilles)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
