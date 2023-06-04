package View;

import Model.Equipe;
import Model.Joueur;
import Model.Rencontre;
import Model.ResultatMatch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.Period;
import java.util.*;

public class DialogAjouteRencontre extends JDialog implements ActionListener
{
    private JTextField textField_debutRencontre;
    private JTextField textField_FinRencontre;
    private JButton buttonEnregistrerRencontre;
    private JButton annulerButton;
    private JButton buttonEncoderJoueurs;
    private JButton buttonEncoderResultats;
    private JComboBox cbBox_EquipeLocale;
    private JComboBox cbBox_EquipeVisiteuse;
    private JPanel mainPanel;
    private JComboBox cbBox_CategorieEquipe;

    private boolean ok;
    private boolean isButtonEncoderJoueursClicked;
    private boolean isButtonEncoderResultatsClicked;

    private JFrame parent;

    private ArrayList<Joueur> listeJoueursClub;
    private ArrayList<Joueur> listeJoueursAdverses;
    private ArrayList<Equipe> listeEquipesClub;
    private ArrayList<Equipe> listeEquipesAdverses;

    private Joueur[] joueursLocauxSelectionnes;
    private Joueur[] joueursVisiteursSelectionnes;
    private Equipe equipeLocaleSelectionnee;
    private Equipe equipeVisiteuseSelectionne;
    private ResultatMatch[] resultatsMatchs;
    private GregorianCalendar dateDebutRencontre;
    private GregorianCalendar dateFinRencontre;

    private Rencontre nouvelleRencontre;
    public Rencontre getNouvelleRencontre() {return nouvelleRencontre;}

    public boolean isOk() { return ok; }

    public DialogAjouteRencontre(JFrame parent, boolean modal, ArrayList<Joueur> joueursClub, ArrayList<Joueur> joueursAdverses,
                                        ArrayList<Equipe> equipesClub, ArrayList<Equipe> equipesAdverses)
    {
        super(parent, modal);
        this.parent = parent;

        listeJoueursClub = joueursClub;
        listeJoueursAdverses = joueursAdverses;
        listeEquipesClub = equipesClub;
        listeEquipesAdverses = equipesAdverses;
        this.chargeComposants();

        buttonEnregistrerRencontre.addActionListener(this);
        buttonEncoderJoueurs.addActionListener(this);
        buttonEncoderResultats.addActionListener(this);
        annulerButton.addActionListener(this);

        setVisible(true);
    }

    private void chargeComposants()
    {
        setContentPane(mainPanel);
        setSize(400, 480);
        ok = false;

        isButtonEncoderJoueursClicked = false;
        isButtonEncoderResultatsClicked = false;

        dateDebutRencontre = new GregorianCalendar();
        dateFinRencontre = new GregorianCalendar();

        joueursLocauxSelectionnes = new Joueur[4];
        joueursVisiteursSelectionnes = new Joueur[4];
        resultatsMatchs = new ResultatMatch[16];

        cbBox_CategorieEquipe.addItem("Hommes");
        cbBox_CategorieEquipe.addItem("Femmes");
        cbBox_CategorieEquipe.addItem("Vétérans");

        cbBox_CategorieEquipe.addActionListener(this);
        remplirComboboxEquipesDisponibles("Hommes");
    }

    private void remplirComboboxEquipesDisponibles(String categrieEquipeSelectionne)
    {
        if(cbBox_EquipeLocale.getItemCount() > 0) {
            cbBox_EquipeLocale.removeAllItems();
        }

        if(cbBox_EquipeVisiteuse.getItemCount() > 0) {
            cbBox_EquipeVisiteuse.removeAllItems();
        }

        Equipe eq;
        for(int i=0; i < listeEquipesClub.size(); i++)
        {
            eq = listeEquipesClub.get(i);
            if(eq.getCategorie().equals(categrieEquipeSelectionne)) {
                cbBox_EquipeLocale.addItem(eq.getNomEquipe());
            }
        }

        if(cbBox_EquipeLocale.getItemCount() == 0) {
            String message = "Aucune équipe locale n'appartient à la catégorie \"" + categrieEquipeSelectionne + "\"";
            JOptionPane.showMessageDialog(null, message);
        }

        for(int i=0; i < listeEquipesAdverses.size(); i++)
        {
            eq = listeEquipesAdverses.get(i);
            if(eq.getCategorie().equals(categrieEquipeSelectionne)) {
                cbBox_EquipeVisiteuse.addItem(eq.getNomEquipe());
            }
        }

        if(cbBox_EquipeVisiteuse.getItemCount() == 0) {
            String message = "Aucune équipe locale n'appartient à la catégorie \"" + categrieEquipeSelectionne + "\"";
            JOptionPane.showMessageDialog(null, message);
        }
    }

    private ArrayList<String> creeListeJoueursDisponibles(ArrayList<Joueur> listeJoueursInitiale)
    {
        ArrayList<String> listeJoueursDispo = new ArrayList<>();

        String categorieEquipe = (String) cbBox_CategorieEquipe.getSelectedItem();
        if(categorieEquipe.equals("Hommes") || categorieEquipe.equals("Femmes"))
        {
            String sexeRecherche;
            if(categorieEquipe.equals("Hommes")){
                sexeRecherche = "Homme";
            }
            else {
                sexeRecherche = "Femme";
            }

            for(int i=0; i < listeJoueursInitiale.size(); i++)
            {
                Joueur j = listeJoueursInitiale.get(i);
                if(j.getSexe().equals(sexeRecherche))
                {
                    String res = j.getNumRegistreNational() + "   |   " + j.getNom() + "  " + j.getPrenom();
                    listeJoueursDispo.add(res);
                }
            }
        }
        else
        {
            for(int i=0; i < listeJoueursInitiale.size(); i++)
            {
                Joueur j = listeJoueursInitiale.get(i);
                String res = j.getNumRegistreNational() + "   |   " + j.getNom() + "  " + j.getPrenom();
                listeJoueursDispo.add(res);
            }
        }

        return listeJoueursDispo;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == cbBox_CategorieEquipe) {
            String categorieEquipe = (String)cbBox_CategorieEquipe.getSelectedItem();
            remplirComboboxEquipesDisponibles(categorieEquipe);
        }

        if(e.getSource() == annulerButton) {
            setVisible(false);
        }

        if(e.getSource() == buttonEnregistrerRencontre)
        {
            if(isButtonEncoderJoueursClicked && isButtonEncoderResultatsClicked)
            {
                if(cbBox_EquipeLocale.getItemCount() > 0 && cbBox_EquipeVisiteuse.getItemCount() > 0)
                {
                    int indiceItemSelectionne = cbBox_EquipeLocale.getSelectedIndex();
                    equipeLocaleSelectionnee = listeEquipesClub.get(indiceItemSelectionne);

                    indiceItemSelectionne = cbBox_EquipeVisiteuse.getSelectedIndex();
                    equipeVisiteuseSelectionne = listeEquipesAdverses.get(indiceItemSelectionne);

                    try
                    {
                        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,Locale.FRANCE);
                        Date d = df.parse(textField_debutRencontre.getText());
                        Date now = Calendar.getInstance().getTime();

                        System.out.println("date debut = " + d);
                        System.out.println("Ajd = " + now);


                        if(d.compareTo(now) < 0) {
                            JOptionPane.showMessageDialog(null, "La date du début de la rencontre ne peut pas être inférieure à la date du jour!", "Début de la rencontre invalide", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            dateDebutRencontre.setTime(d);

                            Date d1 = df.parse(textField_FinRencontre.getText());
                            if(d1.compareTo(d) <= 0){
                                JOptionPane.showMessageDialog(null, "La date de fin de la rencontre ne peut pas être inférieure à la date du jour!", "Fin de la rencontre invalide", JOptionPane.ERROR_MESSAGE);
                            }
                            else {

                                dateFinRencontre.setTime(d);
                                nouvelleRencontre = new Rencontre(equipeLocaleSelectionnee, equipeVisiteuseSelectionne, joueursLocauxSelectionnes, joueursVisiteursSelectionnes, resultatsMatchs, dateDebutRencontre, dateFinRencontre);

                                ok = true;
                                setVisible(false);
                            }
                        }

                    }
                    catch(ParseException exception)
                    {
                        JOptionPane.showMessageDialog(null,  "Le format de la date de début de rencontre ou de la date de fin de reoncontre est invalide!\nVeuillez respecter le format JJ/MM/YYYY HH:MI", "Date de la rencontre invalide", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    String message = "Impossible d'enregistrer cette rencontre! Aucune équipe locale ou visiteuse n'appartient à la catégorie " + cbBox_CategorieEquipe.getSelectedItem();
                    JOptionPane.showMessageDialog(null, message, "Impossible d'enregistrer la rencontre", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                String message;
                if(!isButtonEncoderResultatsClicked && !isButtonEncoderJoueursClicked) {
                    message = "Veuillez d'abord encoder les joueurs participant à la rencontre\nainsi que les résultats de la rencontre avant de confirmer l'ajout de cette rencontre.";
                }
                else if(!isButtonEncoderResultatsClicked){
                    message = "Veuillez d'abord encoder les résultats des joueurs participant à la rencontre avant d'enregistrer la rencontre.";
                }
                else {
                    message = "Veuillez d'abord encoder les joueurs pariticpant à la rencontre avant d'enregistrer la rencontre.";
                }
                JOptionPane.showMessageDialog(null, message, "Impossible d'enregistrer la rencontre", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == buttonEncoderJoueurs)
        {
            if(cbBox_EquipeLocale.getItemCount() > 0 && cbBox_EquipeVisiteuse.getItemCount() > 0)
            {
                ArrayList<String> listeJoueursLocauxDispo, listeJoueursVisiteursDispo;
                listeJoueursLocauxDispo = creeListeJoueursDisponibles(listeJoueursClub);
                listeJoueursVisiteursDispo = creeListeJoueursDisponibles(listeJoueursAdverses);

                if(listeJoueursLocauxDispo.size() >= 4 && listeJoueursVisiteursDispo.size() >= 4)
                {
                    DialogAjoutJoueursRencontre d;
                    int i = 0;
                    boolean cont = true;
                    do
                    {
                        d = new DialogAjoutJoueursRencontre(parent, true, listeJoueursLocauxDispo, true);
                        if(d.isOk())
                        {
                            String joueurSelectionne = d.getItemSelectionne();
                            String numRegistreJoueurSelectionne = joueurSelectionne.substring(0, 15); //pour récupérer le numéro de registre du joueur qui vient d'être sélectionné.

                            boolean found = false;
                            Joueur j;

                            for(int k=0; k < listeJoueursClub.size() && !found; k++)
                            {
                                j = listeJoueursClub.get(k);
                                if(numRegistreJoueurSelectionne.equals(j.getNumRegistreNational()))
                                {
                                    found = true;
                                    joueursLocauxSelectionnes[i] = j;
                                    listeJoueursLocauxDispo.remove(joueurSelectionne);
                                }
                            }
                        }
                        else
                        {
                            //l'utilisateur appuie sur le bouton annuler
                            cont = false;
                            for(int j=0; j<4; j++) {
                                joueursLocauxSelectionnes[j] = null;
                            }
                        }
                        d.dispose();
                        i++;
                    }while(i < 4 && cont == true);


                    if(cont == true) //Si l'utilisateur n'a pas appuyé sur le bouton annuler une seule fois...alors on continue en sélectionnant les joueurs adverses.
                    {
                        i = 0;
                        do
                        {
                            d = new DialogAjoutJoueursRencontre(parent, true, listeJoueursVisiteursDispo, false);
                            if(d.isOk())
                            {
                                String joueurSelectionne = d.getItemSelectionne();
                                String numRegistreJoueurSelectionne = joueurSelectionne.substring(0, 15); //pour récupérer le numéro de registre du joueur qui vient d'être sélectionné.

                                boolean found = false;
                                Joueur j;

                                for(int k=0; k < listeJoueursAdverses.size() && !found; k++)
                                {
                                    j = listeJoueursAdverses.get(k);
                                    if(numRegistreJoueurSelectionne.equals(j.getNumRegistreNational()))
                                    {
                                        found = true;
                                        joueursVisiteursSelectionnes[i] = j;
                                        listeJoueursVisiteursDispo.remove(joueurSelectionne);
                                    }
                                }
                            }
                            else
                            {
                                cont = false;
                                for(int j=0; j<4; j++) {
                                    joueursVisiteursSelectionnes[j] = null;
                                }
                            }
                            d.dispose();
                            i++;
                        }while(i < 4 && cont == true);

                        if(cont == true)
                        {
                            isButtonEncoderJoueursClicked = true;
                        }
                    }
                }
                else
                {
                    String message;
                    String debutMsg;

                    String categorie = (String) cbBox_CategorieEquipe.getSelectedItem();
                    if(categorie.equals("Hommes")) {
                        debutMsg = "Le nombre d'hommes";
                    }
                    else {
                        debutMsg = "Le nombre de femmes";
                    }

                    if(listeJoueursLocauxDispo.size() < 4 && listeJoueursVisiteursDispo.size() < 4) {
                        message = debutMsg + " dans les joueuses du club et dans les joueuses adverses est insuffisant!\nVous devez disposer de 4 joueurs du même sexe au minimum.";
                    }
                    else if(listeJoueursLocauxDispo.size() < 4) {
                        message = debutMsg + " dans les joueurs du club est insuffisant! Vous devez disposer de 4 joueurs du même sexe au minimum.";
                    }
                    else {
                        message = debutMsg + " dans les joueurs adverses est insuffisant! Vous devez dispoer de 4 joueurs du même sexe au minimum.";
                    }

                    JOptionPane.showMessageDialog(null, message, "Encodage des joueurs impossible",JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                String message = "Impossible d'encoder les joueurs participant a la rencontre! Aucune équipe locale ou visiteuse n'appartient à la catégorie " + cbBox_CategorieEquipe.getSelectedItem();
                JOptionPane.showMessageDialog(null, message);
            }
        }

        if(e.getSource() == buttonEncoderResultats)
        {
            if(isButtonEncoderJoueursClicked)
            {
                int i = 0;
                boolean cont = true;

                do
                {
                    Joueur j = joueursLocauxSelectionnes[i];
                    DialogAjoutResultatsRencontre d = new DialogAjoutResultatsRencontre(parent, true, j, joueursVisiteursSelectionnes);
                    if(d.isOk())
                    {
                        int l = (i * 4);
                        ResultatMatch[] quatreResultatsMatchs = d.getResultatsMatchs(); //retourne les résultats des 4 matchs du joueur local numero "i".
                        for(int k = l, m = 0; k < (l+4); k++, m++)
                        {
                            resultatsMatchs[k] = quatreResultatsMatchs[m];
                        }
                    }
                    else
                    {
                        cont = false;
                        if(i > 0)
                        {
                            int nbResultatsDejaEncodes = ((i - 1) * 4);
                            for(int a=0; a < nbResultatsDejaEncodes; a++)
                            {
                                resultatsMatchs[a] = null;
                            }
                        }
                    }
                    d.dispose();

                    i++;
                }while(i < 4 && cont == true);

                if(cont == true)
                {
                    isButtonEncoderResultatsClicked = true;
                }

            }
            else JOptionPane.showMessageDialog(null, "Vous devez d'abord enregistrer les joueurs participant à la rencontre avant\nd'enregistrer les résultats des différents matchs!", "Impossible d'encoder les résultats de matchs", JOptionPane.ERROR_MESSAGE);
        }
    }
}
