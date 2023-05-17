package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import Model.Joueur;

public class DialogAjouteJoueur extends JDialog
{
    private JTextField textField_Nom;
    private JTextField textField_Prenom;
    private JTextField textField_DateNais;
    private JTextField textField_NumRegNat;
    private JTextField textField_Adresse;
    private JTextField textField_ListeForce;
    private JButton ajouterButton;
    private JButton annulerButton;
    private JPanel mainPanel;
    private JComboBox cbBox_Classement;
    private JComboBox cbBox_Sexe;


    private String nom;
    private String prenom;
    private String numRegNat;
    private GregorianCalendar dateNais;
    private String adresse;
    private String sexe;
    private String classement;
    private int listeForce;
    private Joueur nouveauJoueur;

    private boolean ok;

    public DialogAjouteJoueur(JFrame parent, boolean modal)
    {
        super(parent, modal);
        setContentPane(mainPanel);
        setSize(400, 480);
        ok = false;

        for(int ascii=65; ascii<70; ascii++)
        {
            String lettre = Character.toString(ascii);
            for(int j=0; j<8; j+=2)
            {
                String val = lettre + j;
                cbBox_Classement.addItem(val);
            }
        }

        cbBox_Sexe.addItem("Homme");
        cbBox_Sexe.addItem("Femme");

        dateNais = new GregorianCalendar();

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nom = textField_Nom.getText();
                prenom = textField_Prenom.getText();
                numRegNat = textField_NumRegNat.getText();

                try
                {
                    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
                    Date d = df.parse(textField_DateNais.getText());
                    dateNais.setTime(d);

                    listeForce = Integer.parseInt(textField_ListeForce.getText());
                }
                catch(ParseException exception)
                {
                    JOptionPane.showMessageDialog(null, "Le format de la date saisie est invalide!", "Date invalide", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                catch(NumberFormatException exception)
                {
                    JOptionPane.showMessageDialog(null, "Le nombre entré pour la liste de force est invalide!", "Liste de force invalide", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                adresse = textField_Adresse.getText();
                sexe = (String)cbBox_Sexe.getSelectedItem();
                classement = (String)cbBox_Classement.getSelectedItem();

                try
                {
                    nouveauJoueur = new Joueur(numRegNat, nom, prenom, dateNais, adresse, sexe, classement, listeForce);
                    ok = true;
                    setVisible(false);
                }
                catch(Exception exception)
                {
                    JOptionPane.showMessageDialog(null,  exception.getMessage(), "Erreur lors de l'encodage du nouveau joueur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setVisible(false); }
        });

        setVisible(true);
    }

    public boolean isOk() { return ok; }

    public Joueur getNouveauJoueur() { return nouveauJoueur; }
}
