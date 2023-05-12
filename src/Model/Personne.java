package Model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Personne {
    protected String numRegistreNational;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;
    protected String adresse;
    protected String sexe;

    public Personne(String numRegistreNational, String nom, String prenom, Date dateNaissance, String adresse, String sexe) {
        setNumRegistreNational(numRegistreNational);
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(dateNaissance);
        setAdresse(adresse);
        setSexe(sexe);
    }


    public String getNumRegistreNational() {
        return numRegistreNational;
    }

    public void setNumRegistreNational(String numRegistreNational) {
        this.numRegistreNational = numRegistreNational;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE);
        return "Personne {\n" +
                "numRegistreNational='" + numRegistreNational + '\'' +
                "\nnom='" + nom + '\'' +
                "\nprenom='" + prenom + '\'' +
                "\ndateNaissance=" + d.format(dateNaissance) +
                "\nadresse='" + adresse + '\'' +
                "\nsexe=" + sexe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(numRegistreNational, personne.numRegistreNational) && Objects.equals(nom, personne.nom) && Objects.equals(prenom, personne.prenom) && Objects.equals(dateNaissance, personne.dateNaissance) && Objects.equals(adresse, personne.adresse) && Objects.equals(sexe, personne.sexe);
    }

    public static void main(String Args[])
    {
        Personne maPersonne = new Personne("Be03030210","Noa","Lalleman",new Date(2003,03,20),"Marveld,155","Homme");
        System.out.println(maPersonne.toString());
    }
}