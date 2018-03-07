
public class FonctionEvaluationEleve implements FonctionEvaluation {

	@Override
	public double evaluation(Grille grille, int joueur) {
		double eval = 0;
		int[] coups = grille.generateurCoups();

		for(int i = 0; i < coups.length; i++) {
			eval += evalCoupGagnant(grille, joueur, coups[i]);
			eval += evalAlignementVertical(grille, joueur, coups[i]) - evalAlignementVertical(grille, Grille.joueurSuivant(joueur), coups[i]);
			eval += evalAlignementHorizontal(grille, joueur, coups[i]) - evalAlignementHorizontal(grille, Grille.joueurSuivant(joueur), coups[i]);
			eval += evalAlignementDiagonal1(grille, joueur, coups[i]) - evalAlignementDiagonal1(grille, Grille.joueurSuivant(joueur), coups[i]);
			eval += evalAlignemnentDiagonal2(grille, joueur, coups[i]) - evalAlignemnentDiagonal2(grille, Grille.joueurSuivant(joueur), coups[i]);
		}

		return eval;
	}

	/**
	 * 
	 * @param grille
	 * @param joueur
	 * @param coup
	 * @return
	 */
	private double evalCoupGagnant(Grille grille, int joueur, int coup) {
		double eval = 0;

		if(grille.coupGagnant(joueur, coup)) {
			eval = 1000;
		}
		
		if(grille.coupGagnant(Grille.joueurSuivant(joueur), coup)) {
			eval = -eval;
		}

		return eval;
	}


	/**
	 * 
	 * @param grille
	 * @param joueur
	 * @param coup
	 * @return
	 */
	private double evalAlignementVertical(Grille grille, int joueur, int coup) {
		double eval = 0;

		int nb_alignement = grille.getNbCasesVerticale(joueur, grille.getLigneLibre(coup), coup);


		eval += nb_alignement * 0.1;

		return eval;
	}

	/**
	 * 
	 * @param grille
	 * @param joueur
	 * @param coup
	 * @return
	 */
	private double evalAlignementHorizontal(Grille grille, int joueur, int coup) {
		double eval = 0;
		boolean victoire_possible_gauche = true, victoire_possible_droite = true;

		int nb_alignement = grille.getNbCasesHorizontale(joueur, grille.getLigneLibre(coup), coup);
		int nb_alignement_droite = 0, nb_alignement_gauche = 0;

		for(int i = coup + 1; i < Grille.NB_COLONNES && grille.getVal(grille.getLigneLibre(coup), i) == joueur; i++) {
			nb_alignement_droite++;
		}

		nb_alignement_gauche = nb_alignement - nb_alignement_droite - 1;

		for(int i = 0; (i < nb_alignement_gauche || i < nb_alignement_droite) && (victoire_possible_gauche || victoire_possible_droite); i++) {
			if(coup - i < 0 || grille.getVal(grille.getLigneLibre(coup), coup - i) == Grille.joueurSuivant(joueur)) { // On regarde à gauche
				victoire_possible_gauche = false;
			}

			if(coup + i >= Grille.NB_COLONNES || grille.getVal(grille.getLigneLibre(coup), coup + i) == Grille.joueurSuivant(joueur)) { // On regarde à droite
				victoire_possible_droite = false;
			}
		}

		if(victoire_possible_gauche || victoire_possible_droite) {
			eval += nb_alignement * 0.1;

			if(victoire_possible_gauche && victoire_possible_droite) {
				eval *= 2;
			}
		}

		return eval;
	}

	/**
	 * 
	 * @param grille
	 * @param joueur
	 * @param coup
	 * @return
	 */
	private double evalAlignementDiagonal1(Grille grille, int joueur, int coup) {
		double eval = 0;
		boolean victoire_possible_gauche = true, victoire_possible_droite = true;

		int nb_alignement = grille.getNbCasesDiagonale1(joueur, grille.getLigneLibre(coup), coup);
		int nb_alignement_droite = 0, nb_alignement_gauche = 0;

		for(int i = coup + 1, j = grille.getLigneLibre(coup) + 1; i < Grille.NB_COLONNES && j < Grille.NB_LIGNES &&  grille.getVal(j, i) == joueur; i++, j++) {
			nb_alignement_droite++;
		}

		nb_alignement_gauche = nb_alignement - nb_alignement_droite - 1;
		//System.out.println("joueur: " + joueur + " [" + coup + "," + grille.getLigneLibre(coup) + "], alignement = " + nb_alignement + ", alignement droite = " + nb_alignement_droite + ", alignement gauche = " + nb_alignement_gauche);

		for(int i = 0, j = 0; (i < nb_alignement_gauche || i < nb_alignement_droite) && (victoire_possible_gauche || victoire_possible_droite); i++, j++) {
			if(coup - i < 0 || grille.getLigneLibre(coup) - j < 0 || grille.getVal(grille.getLigneLibre(coup) - j, coup - i) == Grille.joueurSuivant(joueur)) { // On regarde à gauche
				victoire_possible_gauche = false;
			}

			if(coup + i >= Grille.NB_COLONNES || grille.getLigneLibre(coup) + j >= Grille.NB_LIGNES || grille.getVal(grille.getLigneLibre(coup), coup + i) == Grille.joueurSuivant(joueur)) { // On regarde à droite
				victoire_possible_droite = false;
			}
		}

		if(victoire_possible_gauche || victoire_possible_droite) {
			eval += nb_alignement * 0.1;

			if(victoire_possible_gauche && victoire_possible_droite) {
				eval *= 2;
			}
		}

		return eval;
	}

	private double evalAlignemnentDiagonal2(Grille grille, int joueur, int coup) {
		double eval = 0;
		boolean victoire_possible_gauche = true, victoire_possible_droite = true;

		int nb_alignement = grille.getNbCasesDiagonale2(joueur, grille.getLigneLibre(coup), coup);
		int nb_alignement_droite = 0, nb_alignement_gauche = 0;

		for(int i = coup + 1, j = grille.getLigneLibre(coup) - 1; i < Grille.NB_COLONNES && j >= 0 &&  grille.getVal(j, i) == joueur; i++, j--) {
			nb_alignement_droite++;
		}

		nb_alignement_gauche = nb_alignement - nb_alignement_droite - 1;

		for(int i = 0, j = 0; (i < nb_alignement_gauche || i < nb_alignement_droite) && (victoire_possible_gauche || victoire_possible_droite); i++, j--) {
			if(coup - i < 0 || grille.getLigneLibre(coup) - j >= Grille.NB_LIGNES || grille.getVal(grille.getLigneLibre(coup) - j, coup - i) == Grille.joueurSuivant(joueur)) { // On regarde à gauche
				victoire_possible_gauche = false;
			}

			if(coup + i >= Grille.NB_COLONNES || grille.getLigneLibre(coup) + j < 0 || grille.getVal(grille.getLigneLibre(coup), coup + i) == Grille.joueurSuivant(joueur)) { // On regarde à droite
				victoire_possible_droite = false;
			}
		}

		if(victoire_possible_gauche || victoire_possible_droite) {
			eval += nb_alignement * 0.1;

			if(victoire_possible_gauche && victoire_possible_droite) {
				eval *= 2;
			}
		}	

		return eval;	
	}

	public static void main(String[] args) {
		Grille localGrille = new Grille();
		localGrille.joueEn(Grille.JOUEUR1, 0);
		localGrille.joueEn(Grille.JOUEUR2, 2);
		localGrille.joueEn(Grille.JOUEUR1, 0);
		localGrille.joueEn(Grille.JOUEUR2, 1);
		localGrille.joueEn(Grille.JOUEUR1, 0);
		localGrille.joueEn(Grille.JOUEUR2, 0);

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
