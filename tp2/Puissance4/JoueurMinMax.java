public class JoueurMinMax implements Joueur {
	private static final int PROFONDEUR = 4;

	@Override
	public Resultat coup(Grille grille, int joueur) {
		Resultat res = max(grille.copie(), PROFONDEUR, joueur, grille.generateurCoups()[0]); 
		return res;
	}
	
	public Resultat max(Grille grille, int profondeur, int joueur, int col) {
		Resultat res = null, tmp = null;
		double max = -(Double.MAX_VALUE);
		
		if(profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, col)) {
			res = new Resultat(col, (new FonctionEvaluationProf()).evaluation(grille, joueur));
			return res;
		} else {
			int[] coups = grille.generateurCoups();
			Grille backtrack = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				tmp = min(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i]);
				
				grille.joueEn(joueur, coups[i]);
				
				if(max < tmp.getValeur()) {
					max = tmp.getValeur();
					res = tmp;
				}
				
				grille = backtrack.copie();
			}
	
		}
		
		return res;
	}
	
	public Resultat min(Grille grille, int profondeur, int joueur, int col) {
		Resultat res = null, tmp;
		double min = Double.MAX_VALUE;
		
		if(profondeur == 0 || grille.estPleine() || grille.coupGagnant(joueur, col)) {
			res = new Resultat(col, (new FonctionEvaluationProf()).evaluation(grille, joueur));
			return res;
		} else {
			int[] coups = grille.generateurCoups();
			Grille backtrack = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				tmp = max(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i]);

				grille.joueEn(joueur, coups[i]);
				
				if(min > tmp.getValeur()) {
					min = tmp.getValeur();
					res = tmp;
				}
				
				grille = backtrack.copie();
			}
		}
		return res;
	}

}
