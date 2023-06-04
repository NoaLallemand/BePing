package Model;

import java.io.Serializable;
import java.util.Objects;

public class Equipe implements Serializable
{
    public static int numEquipeCourant = 1;

    private int numEquipe;
    private String nomEquipe;
    private int division;
    private  String region;
    private  String categorie;

    public Equipe(int numEquipe ,String nomEquipe, int division, String region, String categorie) throws Exception
    {
        try
        {
            setNumEquipe(numEquipe);
            setNomEquipe(nomEquipe);
            setDivision(division);
            setRegion(region);
            setCategorie(categorie);
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    public int getNumEquipe() {return numEquipe;}
    public void setNumEquipe(int numEquipe) {this.numEquipe = numEquipe;}

    public String getNomEquipe() {return nomEquipe;}
    public void setNomEquipe(String nomEquipe) throws Exception
    {
        if(nomEquipe.length() > 0) {
            this.nomEquipe = nomEquipe;
        }
        else {
            throw new Exception("Le nom de l'équipe est requis!");
        }
    }

    public int getDivision() {return division;}
    public void setDivision(int division) {this.division = division;}

    public String getRegion() {return region;}
    public void setRegion(String region) throws Exception
    {
        if(region.length() > 0) {
            this.region = region;
        }
        else {
            throw new Exception("La région de l'équipe doit être connue!");
        }
    }

    public String getCategorie() {return categorie;}
    public void setCategorie(String categorie) {this.categorie = categorie;}


    @Override
    public String toString() {
        return "Equipe {\n" +
                "numEquipe='" + numEquipe + '\'' +
                "\nnomEquipe='" + nomEquipe + '\'' +
                "\ndivision=" + division +
                "\nregion='" + region + '\'' +
                "\ncategorie='" + categorie + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return numEquipe == equipe.numEquipe && division == equipe.division && Objects.equals(nomEquipe, equipe.nomEquipe) && Objects.equals(region, equipe.region) && Objects.equals(categorie, equipe.categorie);
    }

    public static void main(String Args[])
    {
        /*Equipe monEquipe = new Equipe(1,"DreamTeam",5,"Liège","Homme");
        System.out.println(monEquipe.toString());
        Equipe monEquipe1 = monEquipe;

        System.out.println(monEquipe.equals(monEquipe1));*/
    }
}
