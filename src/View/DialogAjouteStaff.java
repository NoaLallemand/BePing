package View;

import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import Model.*;

public class DialogAjouteStaff extends JDialog
{
    private JTextField textField_Nom;
    private JTextField textField_Prenom;
    private JTextField textField_DateNais;
    private JTextField textField_NumRegNat;
    private JTextField textField_Adresse;
    private JTextField textField_Sexe;

    private JButton ajouterButton;
    private JButton annulerButton;
    private JComboBox cbBox_RoleClub;
    private JPanel mainPanel;


    private String nom;
    private String prenom;
    private String numRegNat;
    private Date dateNais;
    private String adresse;
    private String sexe;
    private String role;
    private Staff membreStaff;

    private boolean ok;

    public boolean isOk() { return ok; }

    public DialogAjouteStaff(JFrame parent, boolean modal)
    {
        super(parent, modal);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(380, 400);
        ok = false;

        cbBox_RoleClub.addItem("Coach Principal");
        cbBox_RoleClub.addItem("Coach assistant");
        cbBox_RoleClub.addItem("Kiné");
        cbBox_RoleClub.addItem("Diététicien");

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nom = textField_Nom.getText();
                prenom = textField_Prenom.getText();
                numRegNat = textField_NumRegNat.getText();

                try
                {
                    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE);
                    dateNais = df.parse(textField_DateNais.getText());
                }
                catch(ParseException exception)
                {
                    JOptionPane.showMessageDialog(null, "Le format de la date saisie est invalide!", "Date invalide", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                adresse = textField_Adresse.getText();
                sexe = textField_Sexe.getText();
                role = (String)cbBox_RoleClub.getSelectedItem();

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

    public Staff getMembreStaff() { return membreStaff; }
}
