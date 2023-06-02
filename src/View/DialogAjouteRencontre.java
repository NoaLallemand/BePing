package View;

import Model.Equipe;
import Model.Joueur;
import Model.Rencontre;
import Model.ResultatMatch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
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

        for(int i=0; i < listeEquipesClub.size(); i++)
        {
            Equipe e = listeEquipesClub.get(i);
            cbBox_EquipeLocale.addItem(e.getNomEquipe());
        }

        for(int i=0; i < listeEquipesAdverses.size(); i++)
        {
            Equipe e = listeEquipesAdverses.get(i);
            cbBox_EquipeVisiteuse.addItem(e.getNomEquipe());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == annulerButton) {
            setVisible(false);
        }

        if(e.getSource() == buttonEnregistrerRencontre)
        {
            if(isButtonEncoderJoueursClicked && isButtonEncoderResultatsClicked)
            {
                int indiceItemSelectionne = cbBox_EquipeLocale.getSelectedIndex();
                equipeLocaleSelectionnee = listeEquipesClub.get(indiceItemSelectionne);

                indiceItemSelectionne = cbBox_EquipeVisiteuse.getSelectedIndex();
                equipeVisiteuseSelectionne = listeEquipesAdverses.get(indiceItemSelectionne);

                try
                {
                    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE);
                    Date d = df.parse(textField_debutRencontre.getText());
                    dateDebutRencontre.setTime(d);

                    d = df.parse(textField_FinRencontre.getText());
                    dateFinRencontre.setTime(d);

                    nouvelleRencontre = new Rencontre(equipeLocaleSelectionnee, equipeVisiteuseSelectionne, joueursLocauxSelectionnes, joueursVisiteursSelectionnes, resultatsMatchs, dateDebutRencontre, dateFinRencontre);

                    ok = true;
                    setVisible(false);
                }
                catch(ParseException exception)
                {
                    JOptionPane.showMessageDialog(null,  "Le format de la date de début de rencontre ou de la date de fin de reoncontre est invalide!\nVeuillez respecter le format JJ/MM/YYYY HH:MI", "Date de la rencontre invalide", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, message);
            }
        }

        if(e.getSource() == buttonEncoderJoueurs)
        {
            ArrayList<String> listeJoueursDispoToString = new ArrayList<>();

            for(int i=0; i < listeJoueursClub.size(); i++)
            {
                Joueur j = listeJoueursClub.get(i);
                String res = j.getNumRegistreNational() + "   |   " + j.getNom() + "  " + j.getPrenom();
                listeJoueursDispoToString.add(res);
            }

            DialogAjoutJoueursRencontre d;
            int i = 0;
            boolean cont = true;
            do
            {
                d = new DialogAjoutJoueursRencontre(parent, true, listeJoueursDispoToString, true);
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
                            listeJoueursDispoToString.remove(joueurSelectionne);
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


            if(cont == true) //Si l'utilisateur n'a pas appuyé sur le bouton annuler une seule fois...alors on continue simplement.
            {
                //Maintenant on gère la sélection/encodage des joueurs adverses pour la rencontre
                listeJoueursDispoToString = new ArrayList<String>();

                for(i=0; i < listeJoueursAdverses.size(); i++)
                {
                    Joueur j = listeJoueursAdverses.get(i);
                    String res = j.getNumRegistreNational() + "   |   " + j.getNom() + "  " + j.getPrenom();
                    listeJoueursDispoToString.add(res);
                }

                i = 0;
                do
                {
                    d = new DialogAjoutJoueursRencontre(parent, true, listeJoueursDispoToString, false);
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
                                listeJoueursDispoToString.remove(joueurSelectionne);
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

                            System.out.println(resultatsMatchs[k].toString());
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
            else JOptionPane.showMessageDialog(null, "Vous devez d'abord enregistrer les joueurs participant à la rencontre avant\nd'enregistrer les résultats des différents matchs!");
        }
    }
}
