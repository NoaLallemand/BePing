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
    private boolean stateRecordedData;

    private static Club instance = new Club();

    public static Club getClubInstance() { return instance; }


    public ArrayList<Joueur> getListeJoueursClub() { return listeJoueursClub; }
    public ArrayList<Joueur> getListeJoueursAdverses() { return listeJoueursAdverses; }
    public ArrayList<Equipe> getListeEquipesClub() { return listeEquipesClub; }
    public ArrayList<Equipe> getListeEquipesAdverses() { return listeEquipesAdverses; }
    public ArrayList<Rencontre> getListeRencontres() { return listeRencontres; }
    public ArrayList<Staff> getListeStaffClub() { return listeStaffClub; }

    public void setStateRecordedData(boolean state) { stateRecordedData = state; }
    public boolean areAllDataRecorded() { return stateRecordedData; }

    private Club()
    {
        listeJoueursClub = new ArrayList<>();
        listeJoueursAdverses = new ArrayList<>();

        listeStaffClub = new ArrayList<>();

        listeEquipesClub = new ArrayList<>();
        listeEquipesAdverses = new ArrayList<>();

        listeRencontres = new ArrayList<>();
        stateRecordedData = true;
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

    public void Load() throws IOException, ClassNotFoundException, SecurityException
    {
        File f = new File("BePing.club");
        if(!f.exists())
        {
            System.out.println("Fichier non existant.");
            System.out.println("Création du fichier....");

            FileOutputStream fos = new FileOutputStream("BePing.club", false);
            fos.close();
            stateRecordedData = false; //vu qu'on vient de créer le fichier, on veut que l'enregistrement se fasse au moins une fois
                                        //car lors de la prochaine relecture du fichier, on verra qu'il existera bel et bien et il faut
                                        //donc qu'il y ait déjà quelque chose d'écrit sinon l'exception EOFException sera lancée et on
                                        //aura un petit message d'erreur au lancement de l'application.

            if(f.exists())
            {
                System.out.print("Chemin d'accès absolu au fichier: ");
                System.out.println(f.getAbsolutePath());
            }
        }
        else
        {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream oos = new ObjectInputStream(fis);

            listeJoueursClub = (ArrayList<Joueur>) oos.readObject();
            listeStaffClub = (ArrayList<Staff>) oos.readObject();
            listeEquipesClub = (ArrayList<Equipe>) oos.readObject();

            oos.close();
        }
    }
}
