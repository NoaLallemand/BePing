package Model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Joueur extends Personne
{
    private String classement;
    private int listeForce;

    public void setClassement(String classement) {
        this.classement = classement;
    }
    public void setListeForce(int listeForce) {
        this.listeForce = listeForce;
    }

    public String getClassement() {
        return classement;
    }

    public int getListeForce() {
        return listeForce;
    }

    public Joueur(String numRegistreNational, String nom, String prenom, GregorianCalendar dateNaissance, String adresse, String sexe, String classement, int listeForce) throws Exception
    {
        super(numRegistreNational, nom, prenom, dateNaissance, adresse, sexe);
        try
        {
            setClassement(classement);
            setListeForce(listeForce);
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Joueur {\n" +
                super.toString() +
                "\nClassement='" + getClassement() + '\'' +
                "\nListe de Force='" + getListeForce() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return super.equals(joueur) && listeForce == joueur.listeForce && classement.equals(joueur.classement);
    }

    public static void main(String Args[])
    {
        //Joueur monJoueur = new Joueur("03.06.23-335.79", "Lallemand", "Noa",
                                        //new Date(2003, 6, 23), "Rue Félix Delhaes 45C, 4607 Dalhem",
                                        //"H", "D1", 8);
        //System.out.println(monJoueur.toString());
    }
}
