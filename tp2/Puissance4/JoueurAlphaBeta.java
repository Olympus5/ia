
public class JoueurAlphaBeta implements Joueur {
	private static final int PROFONDEUR = 4;

	@Override
	public Resultat coup(Grille grille, int joueur) {
		return max(grille.copie(), PROFONDEUR, joueur, grille.generateurCoups()[0], -(Double.MAX_VALUE), Double.MAX_VALUE);
	}
	
	public Resultat max(Grille grille, int profondeur, int joueur, int colonne, double alpha, double beta) {
		Resultat tmp, res = null;
		double eval = -(Double.MAX_VALUE);
		
		if(profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, colonne)) {
			return new Resultat(colonne, (new FonctionEvaluationProf()).evaluation(grille, joueur));			
		} else {
			int[] coups = grille.generateurCoups();
			Grille rollback = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				tmp = min(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i], eval, beta);
				
				grille.joueEn(joueur, coups[i]);
				
				if(eval < tmp.getValeur()) {
					eval = tmp.getValeur();
					res = tmp;
				}
				
				if(eval > beta) {
					return res;
				}
				
				grille = rollback.copie();
			}
		}
		
		return res;
	}
	
	public Resultat min(Grille grille, int profondeur, int joueur, int colonne, double alpha, double beta) {
		Resultat tmp, res = null;
		double eval = Double.MAX_VALUE;
		
		if(profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, colonne)) {
			return new Resultat(colonne, (new FonctionEvaluationProf()).evaluation(grille, joueur));
		} else {
			int[] coups = grille.generateurCoups();
			Grille rollback = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				tmp = max(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i], alpha, eval);
				
				grille.joueEn(joueur, coups[i]);
				
				if(eval > tmp.getValeur()) {
					eval = tmp.getValeur();
					res = tmp;
				}
				
				if(eval <= alpha) {
					return res;
				}
				
				grille = rollback.copie();
			}
		}
		
		return res;
	}
	
}
