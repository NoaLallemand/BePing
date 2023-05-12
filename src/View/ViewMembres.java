package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionListener;
import java.util.Vector;

import Controler.Controleur;
import Model.Club;
import Model.Staff;
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

    public ViewMembres()
    {
        setTableModelForTableStaff();
    }

    public JPanel getMembresPanel() { return MembresPanel; }

    public JTable getTableStaff() { return tableStaff; }

    public JButton getBtnAjouterJoueur() { return ajouterJoueurButton; }
    public JButton getBtnModifierJoueur() { return modifierJoueurButton; }
    public JButton getBtnSupprimerJoueur() { return supprimerJoueurButton; }

    public JButton getBtnAjouterStaff() { return ajouterStaffButton; }
    public JButton getBtnModifierStaff() { return modifierStaffButton; }
    public JButton getBtnSupprimerStaff() { return supprimerStaffButton; }

    public void setControleur(Controleur c)
    {
        ajouterJoueurButton.addActionListener(c);

        ajouterStaffButton.addActionListener(c);
        modifierStaffButton.addActionListener(c);
        supprimerStaffButton.addActionListener(c);
    }

    public void setTableModelForTableStaff()
    {
        StaffJTableModel staffTableModel = new StaffJTableModel(Club.getClubInstance().getListeStaffClub());
        tableStaff.setModel(staffTableModel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Membres");
        ViewMembres v = new ViewMembres();
        frame.setContentPane(v.MembresPanel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }
}
