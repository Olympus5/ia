package sudoku;

/**
 * TP 1 - IA Informatique : Sudoku
 * 
 * @author Tassadit BOUADI.
 */

import java.util.ArrayList;
import java.util.List;

public class Grille {
	private Case[][] _cases;
	private int _taille;
	private int _nbCasesVides;


	public Grille(int n){
		_taille = n;
		_nbCasesVides = n*n;
		_cases = new Case[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				_cases[i][j] = new Case(i, j, n);
			}
		}
	}

	public Grille(int n, int[][] grille){
		_taille = n;
		_nbCasesVides = n*n;
		_cases = new Case[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				_cases[i][j] = new Case(i, j, grille[i][j], n);
				if(_cases[i][j].getVal() != 0){
					_nbCasesVides --;
				}
			}
		}
	}

	public Grille(Grille grille){
		_taille = grille._taille;
		_nbCasesVides = grille._nbCasesVides;
		_cases = new Case[_taille][_taille];
		for(int i=0; i<_taille; i++){
			for(int j=0; j<_taille; j++){
				_cases[i][j] = new Case(grille.getCase(i, j));
			}
		}
	}


	public Case getCase(int i, int j){
		return _cases[i][j];
	}

	public int getNbCasesVides(){
		return _nbCasesVides;
	}


	/**
	 * Fonction qui donne la liste des cases sans valeur (peut être vide)
	 */
	public List<Case> getCasePossible(){
		List<Case> casePoss = new ArrayList<Case>();

		for(int i = 0; i < this._cases.length; i++) {
			for(int j = 0; j < this._cases[i].length; j++) {
				if(this.getCase(i, j).getVal() == 0) {
					casePoss.add(this.getCase(i, j));
				}
			}
		}

		return casePoss;
	}


	/**
	 * Fonction qui donne la valeur v à la case c.
	 */
	public void setCase(Case c, int v){
		this._cases[c.getI()][c.getJ()].setVal(v);
		this._nbCasesVides--;
	}


	/**
	 * Fonction qui rend vrai si la valeur v peut être donnée à la case C
	 * c'est-à-dire si la grille respecte toujours les contraintes du Sudoku.
	 */
	public boolean casePossible(Case c, int v){
		boolean res = true;
		int caseI = c.getI();
		int caseJ = c.getJ();
		int regionI = (caseI / 3) * 3; // Grâce a la division entière on peut deviner la bonne région dans l'axe i
		int regionJ = (caseJ / 3) * 3; // De même pour la region de l'axe j
		int i;
		int j;

		// Calcule de la région de la case

		// On check la ligne caseJ
		for(i = 0; i < 9 && res; i++) {
			if(i != caseI) {
				res = this._cases[i][caseJ].getVal() != v; 
			}
		}

		// On check la colonne caseI
		for(j = 0; j < 9 && res; j++) {
			if(j != caseJ) {
				res = this._cases[caseI][j].getVal() != v;
			}
		}

		// On check la region
		for(i = 0; i < 3 && res; i++) {
			for(j = 0; j < 3 && res; j++) {
				if(j != caseJ && i != caseI) {
					res = this._cases[regionI + i][regionJ + j].getVal() != v;
				}
			}
		}

		return res;
	}


	public void afficheGrille(){
		int v;
		int dim = (int)Math.sqrt(_taille);
		for(int i=0; i<_taille; i++){
			if(i%dim == 0){
				System.out.print(" ");
				for(int k=0; k<=_taille; k++)
					System.out.print("--");
				System.out.println();
			}
			for(int j=0; j<_taille; j++){
				if(j%dim == 0){
					System.out.print("|");
				}
				v = _cases[i][j].getVal();
				if(v == 0){
					System.out.print("  ");					
				}
				else{
					System.out.print(v + " ");
				}
			}
			System.out.println("|");
		}
		System.out.print(" ");
		for(int k=0; k<=_taille; k++)
			System.out.print("--");
		System.out.println();		
	}

	public Case meilleurCase() {
		Case res = null;
		List<Case> g = this.getCasePossible();
		int min = 0, tmp = 0;

		if(g.size() > 1) {
			for(Case c : g) {
				for(int i = 1; i < 10; i++) {
					if(this.casePossible(c, i)) {
						tmp++;
					}

					if(res == null || tmp < min) {
						min = tmp;
						res = c;
					}
				}
				
				if(min == 1) {
					break;
				}
			}
		} else {
			res = g.get(0);
		}

		return res;
	}
}
