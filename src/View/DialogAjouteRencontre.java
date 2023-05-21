package View;

import Model.Equipe;
import Model.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

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
    private Joueur[] joueursLocauxSelectionnes;
    private ArrayList<Joueur> listeJoueursAdverses;
    private Joueur[] joueursVisiteursSelectionnes;
    private ArrayList<Equipe> listeEquipesClub;
    private ArrayList<Equipe> listeEquipesAdverses;
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

        joueursLocauxSelectionnes = new Joueur[4];
        joueursVisiteursSelectionnes = new Joueur[4];

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

            }
            else JOptionPane.showMessageDialog(null, "Vous devez d'abord enregistrer les joueurs participant à la rencontre avant\nd'enregistrer les résultats des différents matchs!");
        }
    }
}
