package Model;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Enumeration;
import java.util.ArrayList;

public class Club implements Serializable
{
    private ArrayList<Joueur> listeJoueursClub;
    private ArrayList<Staff> listeStaffClub;
    private ArrayList<Joueur> listeJoueursAdverses;
    private ArrayList<Equipe> listeEquipesClub;
    private ArrayList<Equipe> listeEquipesAdverses;
    private ArrayList<Rencontre> listeRencontres;

    private static Club instance = new Club();

    public static Club getClubInstance() { return instance; }


    public ArrayList<Joueur> getListeJoueursClub() { return listeJoueursClub; }
    public ArrayList<Joueur> getListeJoueursAdverses() { return listeJoueursAdverses; }
    public ArrayList<Equipe> getListeEquipesClub() { return listeEquipesClub; }
    public ArrayList<Equipe> getListeEquipesAdverses() { return listeEquipesAdverses; }
    public ArrayList<Rencontre> getListeRencontres() { return listeRencontres; }
    public ArrayList<Staff> getListeStaffClub() { return listeStaffClub; }

    private Club()
    {
        listeJoueursClub = new ArrayList<>();
        listeJoueursAdverses = new ArrayList<>();

        listeStaffClub = new ArrayList<>();

        listeEquipesClub = new ArrayList<>();
        listeEquipesAdverses = new ArrayList<>();

        listeRencontres = new ArrayList<>();
    }

    public void Save() throws IOException
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("BePing.club", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(listeJoueursClub);
            oos.writeObject(listeStaffClub);
            oos.writeObject(listeEquipesClub);

            oos.flush();
            oos.close();
        }
        catch(FileNotFoundException e)
        {
            throw e;
        }
        catch(IOException e)
        {
            throw e;
        }
    }

    public void Load() throws IOException, ClassNotFoundException
    {
        try
        {
            FileInputStream fis = new FileInputStream("BePing.club");
            ObjectInputStream oos = new ObjectInputStream(fis);

            listeJoueursClub = (ArrayList<Joueur>) oos.readObject();
            listeStaffClub = (ArrayList<Staff>) oos.readObject();
            listeEquipesClub = (ArrayList<Equipe>) oos.readObject();

            oos.close();
        }
        catch(FileNotFoundException e)
        {
            throw e;
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(ClassNotFoundException e)
        {
            throw e;
        }
    }

    public void supprimeElement(Object obj)
    {
        if(obj.getClass() == Joueur.class)
        {
            listeJoueursClub.remove((Joueur)obj);
        }
        else if(obj.getClass() == Equipe.class)
        {
            listeEquipesClub.remove((Equipe)obj);
        }
        else if (obj.getClass() == Rencontre.class)
        {
            listeRencontres.remove((Rencontre)obj);
        }
    }
}
