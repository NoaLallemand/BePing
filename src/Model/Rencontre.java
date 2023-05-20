package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Rencontre implements Serializable
{

    private Equipe locaux;
    private Equipe visiteurs;
    private Joueur[] joueursLocaux; //4 joueurs Locaux
    private Joueur[] joueursVisiteurs; //4 joueurs Visiteurs
    private ResultatMatch[] resultat; //16 matchs différents au total
    private Date dateDebut;
    private Date dateFin;

    public Equipe getLocaux() {
        return locaux;
    }
    public Equipe getVisiteurs() {
        return visiteurs;
    }
    public Joueur[] getJoueursLocaux() {return joueursLocaux;}
    public Joueur[] getJoueursVisiteurs() {return joueursVisiteurs;}
    public ResultatMatch[] getResultat() {
        return resultat;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }

    public void setLocaux(Equipe locaux) {
        this.locaux = locaux;
    }
    public void setVisiteurs(Equipe visiteurs) {
        this.visiteurs = visiteurs;
    }
    public void setJoueursLocaux(Joueur[] tabJoueurs) { this.joueursLocaux = tabJoueurs; }
    public void setJoueursVisiteurs(Joueur[] tabJoueurs) { this.joueursVisiteurs = tabJoueurs; }
    public void setResultat(ResultatMatch[] resultat) { this.resultat = resultat; }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Rencontre(Equipe locaux, Equipe visiteurs, Joueur[] joueursLocaux, Joueur[] joueursVisiteurs, ResultatMatch[] resultat, Date dateDebut, Date dateFin) {
        setLocaux(locaux);
        setVisiteurs(visiteurs);
        setResultat(resultat);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setJoueursLocaux(joueursLocaux);
        setJoueursVisiteurs(joueursVisiteurs);
    }

    @Override
    public String toString() {
        return "Rencontre" +
                "\n{locaux='" + locaux + '\'' +
                "\nvisiteurs='" + visiteurs + '\'' +
                "\nresultat='" + resultat + '\'' +
                "\ndateDebut='" + dateDebut + '\'' +
                "\ndateFin='" + dateFin + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rencontre rencontre = (Rencontre) o;
        return Objects.equals(locaux, rencontre.locaux) && Objects.equals(visiteurs, rencontre.visiteurs) &&
                Arrays.equals(joueursLocaux, rencontre.joueursLocaux) &&
                Arrays.equals(joueursVisiteurs, rencontre.joueursVisiteurs) &&
                Arrays.equals(resultat, rencontre.resultat) && Objects.equals(dateDebut, rencontre.dateDebut) &&
                Objects.equals(dateFin, rencontre.dateFin);
    }

    public static void main(String Args[])
    {
        /*Equipe equipeLocaux = new Equipe(1, "Dalhem Pong", 1, "Liège", "D3");
        Equipe equipeVisiteurs = new Equipe(2, "Liège Pong", 2, "Liège", "D1");

        Joueur[] joueursLocaux = new Joueur[4];
        joueursLocaux[0] = new Joueur("03.06.23-335.79", "Lallemand", "Noa",
                new Date(2003, 6, 23), "Rue Félix Delhaes 45C, 4607 Dalhem", "H", "C2", 4);
        joueursLocaux[1] = new Joueur("01.02.12-175.25", "Garcia", "Adrian",
                new Date(2001, 2, 12), "Rue du marché, 4020 Liège", "H", "C1", 2);*/

        //Rencontre premiereRencontre = new Rencontre(equipeLocaux, equipeVisiteurs, joueursLocaux, , new ResultatMatch(2, 0),
                //new Date(2023, 4, 22, 18, 0), new Date(2023, 4, 22, 20, 0));

        //System.out.println(premiereRencontre.toString());
    }
}
