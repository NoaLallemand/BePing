package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionListener;
import java.util.Vector;

import Controler.Controleur;
import Model.Club;
import View.JTableModel.JoueursJTableModel;
import View.JTableModel.StaffJTableModel;

public class ViewMembres
{
    private JPanel MembresPanel;
    private JScrollBar scrollBar_PaneAffJoueurs;
    private JTable tableJoueurs;
    private JTable tableStaff;
    private JButton ajouterJoueurButton;
    private JButton supprimerJoueurButton;
    private JButton modifierJoueurButton;
    private JButton ajouterStaffButton;
    private JButton supprimerStaffButton;
    private JButton modifierStaffButton;
    private JTextField textField_Nom;
    private JTextField textField_Prenom;
    private JTextField textField_DateNaissance;
    private JTextField textField_Adresse;
    private JTextField textField_NumRegNat;
    private JTextField textField_Classement;
    private JTextField textField_Sexe;
    private JTextField textField_LForce;
    private JTextField textField_Salaire;
    private JTextField textField_DateDebut;
    private JTextField textField_DateFin;
    private JPanel JPanel_infosContrat;

    public ViewMembres()
    {

    }

    public JPanel getMembresPanel() { return MembresPanel; }

    public JTable getTableStaff() { return tableStaff; }
    public JTable getTableJoueurs() { return tableJoueurs; }

    public JButton getBtnAjouterJoueur() { return ajouterJoueurButton; }
    public JButton getBtnModifierJoueur() { return modifierJoueurButton; }
    public JButton getBtnSupprimerJoueur() { return supprimerJoueurButton; }

    public JButton getBtnAjouterStaff() { return ajouterStaffButton; }
    public JButton getBtnModifierStaff() { return modifierStaffButton; }
    public JButton getBtnSupprimerStaff() { return supprimerStaffButton; }

    public void setTextOn_textField_Nom(String nom) { textField_Nom.setText(nom); }
    public void setTextOn_textField_Prenom(String prenom) { textField_Prenom.setText(prenom); }
    public void setTextOn_textField_DateNaissance(String dateNaissance) { textField_DateNaissance.setText(dateNaissance); }
    public void setTextOn_textField_NumRegNat(String numRegNat) { textField_NumRegNat.setText(numRegNat); }
    public void setTextOn_textField_Adresse(String adresse) { textField_Adresse.setText(adresse); }
    public void setTextOn_textField_Sexe(String sexe) { textField_Sexe.setText(sexe); }
    public void setTextOn_textField_Classement(String classement) { textField_Classement.setText(classement); }
    public void setTextOn_textField_LForce(String listeForce) { textField_LForce.setText(listeForce);}

    public void setControleur(Controleur c)
    {
        ajouterJoueurButton.addActionListener(c);
        modifierJoueurButton.addActionListener(c);
        supprimerJoueurButton.addActionListener(c);

        ajouterStaffButton.addActionListener(c);
        modifierStaffButton.addActionListener(c);
        supprimerStaffButton.addActionListener(c);

        tableJoueurs.getSelectionModel().addListSelectionListener(c);
    }

    public void setTableModelForTableStaff()
    {
        StaffJTableModel staffTableModel = new StaffJTableModel(Club.getClubInstance().getListeStaffClub());
        tableStaff = new JTable(staffTableModel);
    }

    public void setTableModelForTableJoueurs()
    {
        JoueursJTableModel joueursTableModel = new JoueursJTableModel(Club.getClubInstance().getListeJoueursClub());
        tableJoueurs = new JTable(joueursTableModel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Membres");
        ViewMembres v = new ViewMembres();
        frame.setContentPane(v.MembresPanel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        setTableModelForTableStaff();
        setTableModelForTableJoueurs();
    }
}
