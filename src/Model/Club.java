package Model;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.*;

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

        listeEquipesClub.add(new Equipe(1, "Les fumiers", 2, "Liège", "Homme"));
        listeEquipesClub.add(new Equipe(2, "Les handicapés", 3, "Namur", "Femme"));
        listeEquipesClub.add(new Equipe(3, "Les zinzins", 1, "Liège", "Vétéran"));

        listeEquipesAdverses.add(new Equipe(4, "Les Sylvatiens", 5, "Luxembourg", "Femme"));
        listeEquipesAdverses.add(new Equipe(5, "Les Blegnytois", 1, "Liège", "Homme"));
        listeEquipesAdverses.add(new Equipe(6, "Les Noa", 2, "Cheratte", "Vétéran"));
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
            oos.writeObject(listeRencontres);

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

    public void Load() throws Exception//, IOException, ClassNotFoundException, SecurityException
    {
        FileReader fr = new FileReader("dataJoueursAdverses.csv");
        BufferedReader br = new BufferedReader(fr);

        String enTete = br.readLine();

        StringTokenizer tokenizer;
        String tuple, item;
        Joueur j;
        while( (tuple = br.readLine()) != null)
        {
            tokenizer = new StringTokenizer(tuple, ";");
            j = new Joueur();

            item = tokenizer.nextToken();
            j.setNom(item);

            item = tokenizer.nextToken();
            j.setPrenom(item);

            item = tokenizer.nextToken();
            item = item.replace('-', '/');
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
            Date d = df.parse(item);
            GregorianCalendar g = j.getDateNaissance();
            g.setTime(d);

            item = tokenizer.nextToken();
            j.setNumRegistreNational(item);

            item = tokenizer.nextToken();
            j.setAdresse(item);

            item = tokenizer.nextToken();
            j.setSexe(item);

            item = tokenizer.nextToken();
            j.setClassement(item);

            item = tokenizer.nextToken();
            int listeForce = Integer.parseInt(item);
            j.setListeForce(listeForce);

            listeJoueursAdverses.add(j);
        }
        br.close();

        File f = new File("BePing.club");
        if(!f.exists())
        {
            System.out.println("Fichier non existant.");
            System.out.println("Création du fichier....");

            FileOutputStream fos = new FileOutputStream("BePing.club", false);
            fos.close();
            stateRecordedData = false; //En mettant stateRecordData à false, si aucun ajout, modification ou suppression de donnée n'est effectué jusqu'à la fermeture de l'app
                                        //alors lors de la fermeture de l'application, les données seront enregistrées une première fois.

                                        // vu qu'on vient de créer le fichier, on veut que l'enregistrement se fasse au moins une fois
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
            listeRencontres = (ArrayList<Rencontre>) oos.readObject();

            oos.close();
        }
    }
}
