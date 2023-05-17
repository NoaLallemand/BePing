package Model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.lang.Exception;

public class Personne
{
    protected String numRegistreNational;
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;
    protected String adresse;
    protected String sexe;

    public Personne(String numRegistreNational, String nom, String prenom, Date dateNaissance, String adresse, String sexe) throws Exception
    {
        try
        {
            setNumRegistreNational(numRegistreNational);
            setNom(nom);
            setPrenom(prenom);
            setDateNaissance(dateNaissance);
            setAdresse(adresse);
            setSexe(sexe);
        }
        catch(Exception e)
        {
            throw e;
        }

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

    public void setNom(String nom) throws Exception
    {
        if(nom.length() > 0)
            this.nom = nom;
        else
            throw new Exception("Veuillez renseigner le nom de la personne!");
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws Exception
    {
        if(prenom.length() > 0)
            this.prenom = prenom;
        else
            throw new Exception("Veuillez renseigner le prénom de la personne!");
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

    public void setAdresse(String adresse) throws Exception
    {
        if(adresse.length() > 0)
            this.adresse = adresse;
        else
            throw new Exception("Veuillez renseigner l'adresse de la personne!");
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {this.sexe = sexe; }

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
        //Personne maPersonne = new Personne("Be03030210","Noa","Lalleman",new Date(2003,03,20),"Marveld,155","Homme");
        //System.out.println(maPersonne.toString());
    }
}