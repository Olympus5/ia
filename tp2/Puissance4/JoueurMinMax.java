public class JoueurMinMax implements Joueur {
	private static final int PROFONDEUR = 6;
	private int it = 0;

	@Override
	public Resultat coup(Grille grille, int joueur) {
		Resultat res = max(grille.copie(), PROFONDEUR, joueur, grille.generateurCoups()[0], false); 
		return res;
	}
	
	public Resultat max(Grille grille, int profondeur, int joueur, int col, boolean victoire) {
		Resultat res = null, tmp = null;
		double max = -(Double.MAX_VALUE);
		System.out.println(grille);
		
		if(profondeur == 0 || grille.estPleine() || victoire) {
			res = new Resultat(col, (new FonctionEvaluationProf()).evaluation(grille, joueur));
			return res;
		} else {
			int[] coups = grille.generateurCoups();
			Grille backtrack = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				
				victoire = grille.coupGagnant(joueur, coups[i]);
				grille.joueEn(joueur, coups[i]);
				
				tmp = min(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i], victoire);
				
				if(max < tmp.getValeur()) {
					max = tmp.getValeur();
					res = tmp;
				}
				
				grille = backtrack.copie();
			}
	
		}
		
		return res;
	}
	
	public Resultat min(Grille grille, int profondeur, int joueur, int col, boolean victoire) {
		Resultat res = null, tmp;
		double min = Double.MAX_VALUE;
		System.out.println(grille);
		
		if(profondeur == 0 || grille.estPleine() || victoire) {
			res = new Resultat(col, (new FonctionEvaluationProf()).evaluation(grille, joueur));
			return res;
		} else {
			int[] coups = grille.generateurCoups();
			Grille backtrack = grille.copie();
			
			for(int i = 0, c = coups.length; i < c; i++) {
				
				victoire = grille.coupGagnant(joueur, coups[i]);
				grille.joueEn(joueur, coups[i]);
				
				tmp = max(grille.copie(), profondeur-1, Grille.joueurSuivant(joueur), coups[i], victoire);

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
