package Model;

import java.io.Serializable;
import java.util.Objects;

public class ResultatMatch implements Serializable
{
    private int scoreLocaux;
    private Joueur joueurLocaux;
    private int scoreVisiteurs;
    private Joueur joueurVisiteur;

    public void setScoreLocaux(int scoreLocaux) {
        this.scoreLocaux = scoreLocaux;
    }
    public void setScoreVisiteurs(int scoreVisiteurs) {
        this.scoreVisiteurs = scoreVisiteurs;
    }
    public void setJoueurLocaux(Joueur joueurLocaux) {this.joueurLocaux = joueurLocaux;}
    public void setJoueurVisiteur(Joueur joueurVisiteur) {this.joueurVisiteur = joueurVisiteur;}

    public int getScoreLocaux() {
        return scoreLocaux;
    }
    public int getScoreVisiteurs() {
        return scoreVisiteurs;
    }
    public Joueur getJoueurLocaux() {return joueurLocaux;}
    public Joueur getJoueurVisiteur() {return joueurVisiteur;}

    public ResultatMatch(int scoreLocaux, int scoreVisiteurs, Joueur joueurLocaux, Joueur joueurVisiteur) {
        setScoreLocaux(scoreLocaux);
        setScoreVisiteurs(scoreVisiteurs);
        setJoueurLocaux(joueurLocaux);
        setJoueurVisiteur(joueurVisiteur);
    }

    @Override
    public String toString() {
        return "ResultatMatch{" +
                "joueurLocaux='" + joueurLocaux + '\'' +
                ", scoreLocaux='" + scoreLocaux + '\'' +
                ", scoreVisiteurs='" + scoreVisiteurs + '\'' +
                ", joueurVisiteur='" + joueurVisiteur + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultatMatch that = (ResultatMatch) o;
        return scoreLocaux == that.scoreLocaux && scoreVisiteurs == that.scoreVisiteurs && joueurLocaux.equals(that.joueurLocaux) && joueurVisiteur.equals(that.joueurVisiteur);
    }
}
