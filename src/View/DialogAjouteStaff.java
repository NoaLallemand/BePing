package View;

import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import Model.*;

public class DialogAjouteStaff extends JDialog
{
    private JTextField textField_Nom;
    private JTextField textField_Prenom;
    private JTextField textField_DateNais;
    private JTextField textField_NumRegNat;
    private JTextField textField_Adresse;
    private JComboBox cbBox_Sexe;
    private JComboBox cbBox_RoleClub;

    private JButton ajouterButton;
    private JButton annulerButton;
    private JPanel mainPanel;



    private String nom;
    private String prenom;
    private String numRegNat;
    private GregorianCalendar dateNais;
    private String adresse;
    private String sexe;
    private String role;
    private Staff membreStaff;
    private Staff donneesAncienStaff;

    private boolean ok;

    public boolean isOk() { return ok; }

    public DialogAjouteStaff(JFrame parent, boolean modal)
    {
        super(parent, modal);
        this.chargeComposants();

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                recupereContenuFormulaire();

                try
                {
                    membreStaff = new Staff(numRegNat, nom, prenom, dateNais, adresse, sexe, role);
                    ok = true;
                    setVisible(false);
                }
                catch(Exception exception)
                {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur lors de l'encodage du nouveau membre du staff", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setVisible(true);
    }

    public DialogAjouteStaff(JFrame parent, boolean modal, Staff s, int selectedRow)
    {
        super(parent, modal);
        ajouterButton.setText("Modifier");
        chargeComposants();
        donneesAncienStaff = s;

        textField_Nom.setText(s.getNom());
        textField_Prenom.setText(s.getPrenom());
        textField_NumRegNat.setText(s.getNumRegistreNational());
        textField_Adresse.setText(s.getAdresse());

        Date d = s.getDateNaissance().getTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
        String dateFormatee = df.format(d);
        textField_DateNais.setText(dateFormatee);

        if(s.getSexe().equals("Homme"))
            cbBox_Sexe.setSelectedIndex(0);
        else
            cbBox_Sexe.setSelectedIndex(1);

        boolean found = false;
        for(int i=0; i < cbBox_RoleClub.getItemCount() && found == false; i++)
        {
            String item = (String)cbBox_RoleClub.getItemAt(i);
            if(item.equals(s.getRole()))
            {
                found = true;
                cbBox_RoleClub.setSelectedIndex(i);
            }
        }

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                recupereContenuFormulaire();

                try
                {
                    membreStaff = new Staff(numRegNat, nom, prenom, dateNais, adresse, sexe, role);
                    if(membreStaff.equals(donneesAncienStaff))
                    {
                        throw new Exception("Modification impossible! Les nouvelles données du membre du staff sont identiques aux anciennes données...\nVeuillez modifier au moins un champ avant de confirmer la modification");
                    }
                    else
                    {
                        ok = true;
                        setVisible(false);
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,  ex.getMessage(), "Erreur lors de la modification du membre du staff", JOptionPane.ERROR_MESSAGE);
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

        cbBox_RoleClub.addItem("Coach Principal");
        cbBox_RoleClub.addItem("Coach assistant");
        cbBox_RoleClub.addItem("Kiné");
        cbBox_RoleClub.addItem("Diététicien");

        cbBox_Sexe.addItem("Homme");
        cbBox_Sexe.addItem("Femme");

        dateNais = new GregorianCalendar();
    }

    private void recupereContenuFormulaire()
    {
        nom = textField_Nom.getText();
        prenom = textField_Prenom.getText();
        numRegNat = textField_NumRegNat.getText();

        try
        {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
            Date d = df.parse(textField_DateNais.getText());
            dateNais.setTime(d);
        }
        catch(ParseException exception)
        {
            JOptionPane.showMessageDialog(null, "Le format de la date saisie est invalide!", "Date invalide", JOptionPane.ERROR_MESSAGE);
            return;
        }

        adresse = textField_Adresse.getText();
        sexe = (String)cbBox_Sexe.getSelectedItem();
        role = (String)cbBox_RoleClub.getSelectedItem();
    }

    public Staff getMembreStaff() { return membreStaff; }
}
