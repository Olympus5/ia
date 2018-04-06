
public class JoueurAlphaBeta implements Joueur {
	private static final int PROFONDEUR = 4;
	private int it = 0;
	private Resultat cinq;
	private int joueur;

	@Override
	public Resultat coup(Grille grille, int joueur) {
		this.joueur = joueur;
		return max(grille.copie(), PROFONDEUR, joueur, -1, -(Double.MAX_VALUE), Double.MAX_VALUE);
	}
	
	public Resultat max(Grille grille, int profondeur, int joueur, int colonne, double alpha, double beta) {
		Resultat tmp, res = null;
		double eval = -(Double.MAX_VALUE);
		
		if(colonne > -1 && (profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, colonne))) {
			return new Resultat(colonne, (new FonctionEvaluationEleve()).evaluation(grille, joueur));			
		} else {
			int[] coups = grille.generateurCoups();
			Grille rollback = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				System.out.println("Alpha-Beta itération: :" + it++);
				
				if(profondeur != PROFONDEUR) {
					grille.joueEn(joueur, coups[i]);
				}
				
				if(profondeur == PROFONDEUR && coups[i] == 5) {
					System.out.println(grille.coupGagnant(joueur, coups[i]));
					System.out.println(grille);
				}
				
				tmp = min(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i], eval, beta);
				
				if(tmp.getColonne() == 4) {
					this.cinq = tmp;
				}
				
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
		
		//System.out.println(this.cinq + " / " + res);
		
		return res;
	}
	
	public Resultat min(Grille grille, int profondeur, int joueur, int colonne, double alpha, double beta) {
		Resultat tmp, res = null;
		double eval = Double.MAX_VALUE;
		
		if(colonne > -1 && (profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, colonne))) {
			return new Resultat(colonne, (new FonctionEvaluationEleve()).evaluation(grille, joueur));
		} else {
			int[] coups = grille.generateurCoups();
			Grille rollback = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				System.out.println("Alpha-Beta itération: :" + it++);

				if(profondeur != PROFONDEUR) grille.joueEn(joueur, coups[i]);
				tmp = max(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i], alpha, eval);
				
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
