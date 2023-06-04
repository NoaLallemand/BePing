package Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.*;
import java.lang.Exception;

public class Personne implements Serializable
{
    protected String numRegistreNational;
    protected String nom;
    protected String prenom;
    protected GregorianCalendar dateNaissance;
    protected String adresse;
    protected String sexe;

    public Personne()
    {
        numRegistreNational = "";
        nom = "";
        prenom = "";
        dateNaissance = new GregorianCalendar();
        adresse = "";
        sexe = "";
    }

    public Personne(String numRegistreNational, String nom, String prenom, GregorianCalendar dateNaissance, String adresse, String sexe) throws Exception
    {
        try
        {
            setNom(nom);
            setPrenom(prenom);
            setDateNaissance(dateNaissance);
            setNumRegistreNational(numRegistreNational);
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

    public void setNumRegistreNational(String numRegistreNational) throws Exception
    {
        if(dateNaissance == null)
        {
            throw new Exception("Aucune date de naissance n'a été saisie...impossible de vérifier le numéro de registre!");
        }
        else
        {
            int ret;
            if( (ret = verifNumRegNat(numRegistreNational)) == 0)
            {
                this.numRegistreNational = numRegistreNational;
            }
            else
            {
                String message;
                switch(ret)
                {
                    case -1:
                        message = "Le numéro de registre doit avoir une longueur de 15 caractères!";
                        break;

                    case -2:
                        message = "L'année de naissance renseignée pour le numéro de registre ne correspond pas à votre année de naissance";
                        break;

                    case -3:
                        message = "Le mois de naissance renseigné pour le numéro de registre ne correspond pas à votre mois de naissance";
                        break;

                    case -4:
                        message = "Le jour de naissance renseigné pour le numéro de registre ne correspond pas à votre jour de naissance";
                        break;

                    case -5:
                        message = "Le format du numéro de registre est incorrect! Les chiffres de la date de naissance doivent être séparés par des '.' et le numéro de registre doit contenir un '-' avant les 5 derniers chiffres!";
                        break;

                    case -6:
                        message = "Le numéro de registre national doit se terminer par 5 chiffres!";
                        break;

                    default:
                        message = "";
                }
                throw new Exception(message);
            }
        }
    }

    private int verifNumRegNat(String numRegistreNational)
    {
        if (numRegistreNational.length() != 15) return -1;

        //Vérification de la présence des chiffres correspondant à l'année, le mois et le jour de naissance.
        for (int i = 0; i < 8; i+=3)
        {
            String temp = numRegistreNational.substring(i, i+2);
            switch (i)
            {
                case 0:
                {
                    int anneeNais;
                    if ( (anneeNais = dateNaissance.get(GregorianCalendar.YEAR)) >= 2000)
                        anneeNais -= 2000;
                    else
                        anneeNais -= 1900;

                    if (Integer.parseInt(temp) != anneeNais) return -2;
                }
                break;

                case 3:
                    if (Integer.parseInt(temp) != dateNaissance.get(GregorianCalendar.MONTH)+1) return -3;
                    break;

                case 6:
                    if (Integer.parseInt(temp) != dateNaissance.get(GregorianCalendar.DAY_OF_MONTH)) return -4;
                    break;
            }
        }

        //Vérification de la présence des "." et du "-" dans le numéro de registre.
        for (int i = 2; i < 13; i += 3)
        {
            String temp = numRegistreNational.substring(i, i+1);
            switch (i)
            {
                case 8:
                    i++; //car après l'emplacement du tiret il y a 3 chiffres et pas 2, avant le prochain point.
                    if (!temp.equals("-")) return -5;
                    break;

                default:
                    if (!temp.equals(".")) return -5;
                    break;
            }
        }

        //Vérification de la présence de chiffres après le tiret :
        String sChiffres = numRegistreNational.substring(9, 12);
        int chiffres = Integer.parseInt(sChiffres);
        if (chiffres < 0 || chiffres > 999) return -6;

        sChiffres = numRegistreNational.substring(13, 15);
        chiffres = Integer.parseInt(sChiffres);
        if (chiffres < 0 || chiffres > 99) return -6;

        return 0;
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

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance) {
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
        Date d = dateNaissance.getTime();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE);
        return "Personne {\n" +
                "numRegistreNational='" + numRegistreNational + '\'' +
                "\nnom='" + nom + '\'' +
                "\nprenom='" + prenom + '\'' +
                "\ndateNaissance=" + df.format(d) +
                "\nadresse='" + adresse + '\'' +
                "\nsexe=" + sexe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return numRegistreNational.equals(personne.numRegistreNational) && nom.equals(personne.nom) && prenom.equals(personne.prenom) && dateNaissance.equals(personne.dateNaissance) && adresse.equals(personne.adresse) && sexe.equals(personne.sexe);
    }

    public static void main(String Args[])
    {
        //Personne maPersonne = new Personne("Be03030210","Noa","Lalleman",new Date(2003,03,20),"Marveld,155","Homme");
        //System.out.println(maPersonne.toString());
    }
}