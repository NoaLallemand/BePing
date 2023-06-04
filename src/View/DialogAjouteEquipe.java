package View;

import Model.Equipe;
import Model.Staff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DialogAjouteEquipe extends JDialog{
    private JTextField textField_Nom;
    private JTextField textField_Region;
    private JButton ajouterButton;
    private JButton annulerButton;
    private JComboBox cb_Division;
    private JComboBox cb_Categorie;
    private JPanel mainPanel;

    private String nom;

    private int divison;
    private String region;
    private String categorie;

    private Equipe equipe;

    private Equipe donneesAncienEquipe;

    private boolean ok;

    public boolean isOk() { return ok; }
    public DialogAjouteEquipe(JFrame parent, boolean modal)
    {
        super(parent,modal);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(380, 400);
        ok = false;

        cb_Division.addItem("Division 1");
        cb_Division.addItem("Division 2");
        cb_Division.addItem("Division 3");
        cb_Division.addItem("Division 4");
        cb_Division.addItem("Division 5");
        cb_Division.addItem("Division 6");
        cb_Division.addItem("Division 7");

        cb_Categorie.addItem("Hommes");
        cb_Categorie.addItem("Femmes");
        cb_Categorie.addItem("Vétérans");

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nom = textField_Nom.getText();
                divison = cb_Division.getSelectedIndex()+1;
                region = textField_Region.getText();
                categorie = (String) cb_Categorie.getSelectedItem();

                try {
                    equipe = new Equipe(1, nom, divison, region, categorie);
                    ok = true;
                    setVisible(false);
                }
                catch (Exception exception)
                {

                }
            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {setVisible(false);}
        });
        setVisible(true);
    }

    public DialogAjouteEquipe(JFrame parent, boolean modal, Equipe e, int selectedRow)
    {
        super(parent, modal);
        ajouterButton.setText("Modifier");
        chargeComposants();
        donneesAncienEquipe = e;

        textField_Nom.setText(e.getNomEquipe());
        textField_Region.setText(e.getRegion());


        if(e.getCategorie().equals("Homme"))
            cb_Categorie.setSelectedIndex(0);
        else if (e.getCategorie().equals("Femme"))
            cb_Categorie.setSelectedIndex(0);
        else
            cb_Categorie.setSelectedIndex(0);

        cb_Division.setSelectedIndex(e.getDivision()-1);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    recupereContenuFormulaire();
                    equipe = new Equipe(1,nom,divison,region,categorie);
                    if(equipe.equals(donneesAncienEquipe))
                    {
                        throw new Exception("Modification impossible! Les nouvelles données de l'équipe sont identiques aux anciennes données...\nVeuillez modifier au moins un champ avant de confirmer la modification");
                    }
                    else
                    {
                        ok = true;
                        setVisible(false);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setVisible(false); }
        });

        setVisible(true);
    }

    private void chargeComposants()
    {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(380, 400);
        ok = false;

        cb_Division.addItem("Division 1");
        cb_Division.addItem("Division 2");
        cb_Division.addItem("Division 3");
        cb_Division.addItem("Division 4");
        cb_Division.addItem("Division 5");
        cb_Division.addItem("Division 6");
        cb_Division.addItem("Division 7");

        cb_Categorie.addItem("Homme");
        cb_Categorie.addItem("Femme");
        cb_Categorie.addItem("Vétéran");

    }

    private void recupereContenuFormulaire() throws Exception
    {

        nom = textField_Nom.getText();
        divison = cb_Division.getSelectedIndex()+1;
        region = textField_Region.getText();
        categorie = (String) cb_Categorie.getSelectedItem();
    }
    public Equipe getEquipe(){return equipe;}
}
