package Model;
import LogBean.LogEvent;
import LogBean.LogListener;

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
    private ArrayList<LogListener> mailingListLogListeners;

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

        mailingListLogListeners = new ArrayList<>();

        /*listeEquipesClub.add(new Equipe(1, "Les fumiers", 2, "Liège", "Hommes"));
        listeEquipesClub.add(new Equipe(2, "Les handicapés", 3, "Namur", "Femmes"));
        listeEquipesClub.add(new Equipe(3, "Les zinzins", 1, "Liège", "Vétérans"));*/

        /*try
        {
            listeEquipesAdverses.add(new Equipe(4, "Les Sylvatiens", 5, "Luxembourg", "Femme"));
            listeEquipesAdverses.add(new Equipe(5, "Les Blegnytois", 1, "Liège", "Homme"));
            listeEquipesAdverses.add(new Equipe(6, "Les Noa", 2, "Cheratte", "Vétéran"));
        }
        catch(Exception e)
        {
            System.out.println("Erreur");
        }*/

    }

    public void ajouteJoueur(Joueur j)
    {
        listeJoueursClub.add(j);
        notifyLogDetected("Ajout d'un joueur: ", j);
    }

    public void modifierJoueur(Joueur ancien, Joueur nouveau)
    {
        notifyLogDetected("Modification d'un joueur - anciennes données: ", ancien);
        notifyLogDetected("Modification d'un joueur - nouvelles données: ", nouveau);

        try
        {
            ancien.setNom(nouveau.getNom());
            ancien.setPrenom(nouveau.getPrenom());
            ancien.setDateNaissance(nouveau.getDateNaissance());
            ancien.setNumRegistreNational(nouveau.getNumRegistreNational());
            ancien.setAdresse(nouveau.getAdresse());
            ancien.setSexe(nouveau.getSexe());
            ancien.setClassement(nouveau.getClassement());
            ancien.setListeForce(nouveau.getListeForce());
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la tentative de mise à jour des données d'un joueur");
        }
    }

    public void supprimeJoueur(int indice)
    {
        Joueur j = listeJoueursClub.get(indice);
        notifyLogDetected("Suppression d'un joueur: ", j);
        listeJoueursClub.remove(indice);
    }

    public void ajouteStaff(Staff s)
    {
        listeStaffClub.add(s);
        notifyLogDetected("Ajout d'un staff: ", s);
    }

    public void modifierStaff(Staff ancien, Staff nouveau)
    {
        notifyLogDetected("Modification membre staff - anciennes données: ", ancien);
        notifyLogDetected("Modification membre staff - nouvelles données: ", nouveau);

        try
        {
            ancien.setNom(nouveau.getNom());
            ancien.setPrenom(nouveau.getPrenom());
            ancien.setDateNaissance(nouveau.getDateNaissance());
            ancien.setNumRegistreNational(nouveau.getNumRegistreNational());
            ancien.setAdresse(nouveau.getAdresse());
            ancien.setSexe(nouveau.getSexe());
            ancien.setRole(nouveau.getRole());
        }
        catch(Exception ex)
        {
            System.out.println("Erreur lors de la tentative de mise à jour des données d'un membre du staff");
        }
    }

    public void supprimeStaff(int indice)
    {
        Staff s = listeStaffClub.get(indice);
        notifyLogDetected("Suppression membre staff: ", s);
        listeStaffClub.remove(indice);
    }

    public void ajouteRencontre(Rencontre r)
    {
        listeRencontres.add(r);
        notifyLogDetected("Ajout d'une rencontre: ", r);
    }

    public void ajouteEquipe(Equipe e)
    {
        listeEquipesClub.add(e);
        Equipe.numEquipeCourant++;

        notifyLogDetected("Ajout d'une éuqipe: ", e);
    }

    public void modifierEquipe(Equipe ancien, Equipe nouveau)
    {
        notifyLogDetected("Modification d'une equipe - anciennes données: ", ancien);
        notifyLogDetected("Modification d'une equipe - nouvelles données: ", nouveau);

        try
        {
            ancien.setNumEquipe(nouveau.getNumEquipe());
            ancien.setCategorie(nouveau.getCategorie());
            ancien.setNomEquipe(nouveau.getNomEquipe());
            ancien.setDivision(nouveau.getDivision());
            ancien.setRegion(nouveau.getRegion());
        }
        catch(Exception e)
        {
            System.out.println("Erreur lors de la tentative de mise à jour des données d'une équipe");
        }

    }

    public void supprimeEquipe(int indice)
    {
        Equipe e = listeEquipesClub.get(indice);
        notifyLogDetected("Suppression d'une équipe: ", e);
        listeEquipesClub.remove(indice);
    }

    public void Save() throws IOException
    {
        //Sauvegarder le numéro d'équipe courant
        Properties prop = new Properties();
        prop.setProperty("numEquipe", String.valueOf(Equipe.numEquipeCourant));
        FileOutputStream fileOutputStream = new FileOutputStream("numEquipeCourant.properties", false);
        prop.store(fileOutputStream, "Prochain numéro d'équipe");

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


        fr = new FileReader("dataEquipesAdverses.csv");
        br = new BufferedReader(fr);
        br.readLine();
        Equipe e;
        while( (tuple = br.readLine()) != null)
        {
            tokenizer = new StringTokenizer(tuple, ";");
            e = new Equipe();

            item = tokenizer.nextToken();
            int numEquipe = Integer.parseInt(item);
            e.setNumEquipe(numEquipe);

            item = tokenizer.nextToken();
            e.setNomEquipe(item);

            item = tokenizer.nextToken();
            int division = Integer.parseInt(item);
            e.setDivision(division);

            item = tokenizer.nextToken();
            e.setRegion(item);

            item = tokenizer.nextToken();
            e.setCategorie(item);

            listeEquipesAdverses.add(e);
        }
        br.close();

        try
        {
            Properties prop = new Properties();
            prop.load(new FileInputStream("numEquipeCourant.properties"));
            String numEquipe = (String) prop.get("numEquipe");
            Equipe.numEquipeCourant = Integer.parseInt(numEquipe);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Le fichier properties n'existe pas pour l'instant...");
        }


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

    public void addLogListener(LogListener listener)
    {
        if(!mailingListLogListeners.contains(listener))
        {
            mailingListLogListeners.add(listener);
        }
    }

    public void removeLogListener(LogListener listener)
    {
        if(mailingListLogListeners.contains(listener))
        {
            mailingListLogListeners.remove(listener);
        }
    }

    private void notifyLogDetected(String msg, Object obj)
    {
        String log = createLog(msg, obj);

        LogEvent e = new LogEvent(this, log);
        for(int i=0; i < mailingListLogListeners.size(); i++)
        {
            LogListener listener = mailingListLogListeners.get(i);
            listener.logDetected(e);
        }
    }

    private String createLog(String msg, Object obj)
    {
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.FRANCE);
        String log;

        if(obj instanceof Personne) {
            Personne p = (Personne)obj;
            log = "[" + df.format(Calendar.getInstance().getTime()) + "] " + msg + " [NumeroRegistreNational = " + p.getNumRegistreNational() + "] [Nom = " + p.getNom() + "] [Prénom = " + p.getPrenom() + "]";
        }
        else if(obj instanceof Equipe) {
            Equipe e = (Equipe)obj;
            log = "[" + df.format(Calendar.getInstance().getTime()) + "] " + msg + "[NumeroEquipe = " + e.getNumEquipe() + "] [NomEquipe = " + e.getNomEquipe() + "]";
        }
        else {
            Rencontre r = (Rencontre)obj;
            String dateDebut = df.format(r.getDateDebut().getTime());
            log = "[" + df.format(Calendar.getInstance().getTime()) + "] " + msg +  "[EquipeLocale = " + r.getLocaux().getNomEquipe() + "] [EquipeVisiteuse = " + r.getVisiteurs().getNomEquipe() + "] [DateDebut = " + dateDebut + "]";
        }

        return log;
    }
}
