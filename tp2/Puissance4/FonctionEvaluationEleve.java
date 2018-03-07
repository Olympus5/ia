
public class FonctionEvaluationEleve implements FonctionEvaluation {

	@Override
	public double evaluation(Grille grille, int joueur) {
		/*if(grille.getNbCoups() < 7) { // Aucune victoire possible jusqu'à 6 coups, J1 à joué 3 pions ainsi que J2
			return 0;
		}*/
		return myEvaluation(grille, joueur);
	}

	/**
	 *
	 * @param grille
	 * @param joueur
	 * @return
	 */
	private double myEvaluation(Grille grille , int joueur) {
		int[] coups = grille.generateurCoups();
		double eval = 0, tmp = 0;


		//Coups gagnat
		tmp = evalCoupsGagnant(grille, joueur, coups) - evalCoupsGagnant(grille, Grille.joueurSuivant(joueur), coups);
		System.out.println("gagnant: " + tmp);
		eval += tmp;
		//Coups verticaux
		tmp = evalAligmenentVertical(grille, joueur, coups) - evalAligmenentVertical(grille, Grille.joueurSuivant(joueur), coups);
		System.out.println("vertical: " + tmp);
		eval += tmp;
		//Coups horizontaux
		tmp = evalAligmenentHorizontal(grille, joueur, coups) - evalAligmenentHorizontal(grille, Grille.joueurSuivant(joueur), coups);
		System.out.println("horizontal: " + tmp);
		eval += tmp;
		//Coups diagonal/pente negative

		//Coups diagonal/pente positive
		return eval;
	}

	private double evalCoupsGagnant(Grille grille, int joueur, int[] coups) {
		double eval = 0;

		for(int i = 0; i < coups.length; i++) {// On évalue les coups gagnants qui rapport 1
			if(grille.coupGagnant(joueur, coups[i])) {
				eval += 1000.;
			}
		}

		return eval;
	}

	private double evalAligmenentVertical(Grille grille, int joueur, int[] coups) {
		double eval = 0;
		for(int i = 0; i < coups.length;i++) {
			for(int j = 2, nb_pions = 0; j < Grille.NB_LIGNES-1; j++) {
				nb_pions = grille.getNbCasesVerticale(joueur, j, coups[i]);

				if(nb_pions > 1 && grille.getVal(j+1, coups[i]) != Grille.joueurSuivant(joueur)) {
					eval += nb_pions * 0.1; //Plus le nb est grand plus on se rapproche d'une victoire donc eval augment logiquement
				}
			}
		}

		return eval;
	}

	private double evalAligmenentHorizontal(Grille grille, int joueur, int[] coups) {
		double eval = 0;
		int nb_pions = 0;
		int nb_blanc = 0;

		for(int i = 0; i < Grille.NB_LIGNES; i++) {
			for(int j = 0; j < Grille.NB_COLONNES; j++) {
				if(grille.getVal(i, j) == joueur) {
					nb_pions++;
					System.out.println(nb_pions);
				} else if(grille.getVal(i, j) == Grille.joueurSuivant(joueur)) {
					nb_blanc = 0;
					nb_pions = 0;
				} else {
					nb_blanc += (nb_pions < 1) ? 0 : 1;
				}
				
				if(nb_pions + nb_blanc == 4 && nb_blanc != 4) {
					eval += nb_pions * 0.1;
					nb_pions = nb_blanc = 0;
				}
			}
			
			nb_pions = nb_blanc = 0;
		}
		return eval;
	}

	private int nbAligmenentDiagonal1(Grille grille, int joueur, int nb_coups) {
		double eval = 0;
		return 0;
	}

	private double evalAligmenentDiagonal2(Grille grille, int joueur, int nb_coups) {
		double eval = 0.;
		int nb_pions = 0, nb_blancs = 0;
		
		for(int c = 0, l = 2; c < 4 &&  l < 4; ) {
			if(grille.getVal(l, c) == joueur) {
				nb_pions++;
			} else if(grille.getVal(l, c) == Grille.joueurSuivant(joueur)) {
				nb_pions = nb_blancs = 0;
			} else {
				nb_blancs += (nb_pions < 1)? 0 : 1;
			}
			
			if(nb_pions + nb_blancs == 4 && nb_blancs != 4) {
				eval += nb_pions * 0.1;
				nb_pions = nb_blancs = 0;
			}
		}
		
		return eval;
	}

	public static void main(String[] args) {
		Grille localGrille = new Grille();
		localGrille.joueEn(Grille.JOUEUR1, 3);
		localGrille.joueEn(Grille.JOUEUR1, 3);
		localGrille.joueEn(Grille.JOUEUR1, 2);
		localGrille.joueEn(Grille.JOUEUR1, 4);
		System.out.println(localGrille);

		int[] arrayOfInt = localGrille.generateurCoups();
		for (int i = 0; i < arrayOfInt.length; i++) {
			System.out.print(arrayOfInt[i] + " ");
		}
		System.out.println();

		FonctionEvaluationEleve localFonctionEvaluationProf = new FonctionEvaluationEleve();
		double d = localFonctionEvaluationProf.evaluation(localGrille, Grille.JOUEUR2);
		System.out.println(d);

	}
}
