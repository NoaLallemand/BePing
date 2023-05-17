package View;

import Model.Equipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        cb_Categorie.addItem("Homme");
        cb_Categorie.addItem("Femme");
        cb_Categorie.addItem("Vétéran");

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nom = textField_Nom.getText();
                divison = cb_Division.getSelectedIndex();
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
    public Equipe getEquipe(){return equipe;}
}
