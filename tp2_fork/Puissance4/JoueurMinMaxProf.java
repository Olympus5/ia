
public class JoueurMinMaxProf implements Joueur {
	private static final int PROFONDEUR = 4;
	private int it = 0;

	@Override
	public Resultat coup(Grille grille, int joueur) {
		return max(grille.copie(), PROFONDEUR, joueur, -1);
	}
	
	public Resultat max(Grille grille, int profondeur, int joueur, int colonne) {
		Resultat tmp, res = null;
		double eval = -(Double.MAX_VALUE);
		
		if(colonne > -1 && (profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, colonne))) {
			return new Resultat(colonne, (new FonctionEvaluationEleve()).evaluation(grille, joueur));			
		} else {
			int[] coups = grille.generateurCoups();
			Grille rollback = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				System.out.println("Min-Max itération: :" + it++);
				
				if(profondeur != PROFONDEUR) {
					grille.joueEn(joueur, coups[i]);
				}
				
				if(profondeur == PROFONDEUR && coups[i] == 5) {
					System.out.println(grille.coupGagnant(joueur, coups[i]));
					System.out.println(grille);
				}
				
				tmp = min(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i]);
				
				if(tmp.getColonne() == 4) {
				}
				
				if(eval < tmp.getValeur()) {
					eval = tmp.getValeur();
					res = tmp;
				}
				
				grille = rollback.copie();
			}
		}
		
		return res;
	}
	
	public Resultat min(Grille grille, int profondeur, int joueur, int colonne) {
		Resultat tmp, res = null;
		double eval = Double.MAX_VALUE;
		
		if(colonne > -1 && (profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, colonne))) {
			return new Resultat(colonne, (new FonctionEvaluationEleve()).evaluation(grille, joueur));
		} else {
			int[] coups = grille.generateurCoups();
			Grille rollback = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				System.out.println("Min-Max itération: :" + it++);

				if(profondeur != PROFONDEUR) grille.joueEn(joueur, coups[i]);
				tmp = max(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i]);
				
				if(eval > tmp.getValeur()) {
					eval = tmp.getValeur();
					res = tmp;
				}
				
				grille = rollback.copie();
			}
		}
		
		return res;
	}
	
}
