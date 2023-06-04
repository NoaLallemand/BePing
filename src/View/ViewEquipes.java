package View;

import Controler.Controleur;
import Model.Club;

import View.JTableModel.EquipeJTableModel;

import View.JTableModel.RencontreJTableModel;


import javax.swing.*;

public class ViewEquipes {
    private JPanel EquipesPanel;
    private JTable tableEquipes;
    private JTable tableRencontres;
    private JButton ajouterEquipeButton;
    private JButton modifierEquipeButton;
    private JButton supprimerEquipeButton;
    private JButton addFeuilleMatchButton;
    public JTextField tf_dateDebut;
    public JTextField tf_dateFin;
    public JTextField tf_equipeLocale;
    public JTextField tf_equipeVisiteuse;
    public JTextField tf_joueurLocal1;
    public JTextField tf_joueurLocal2;
    public JTextField tf_joueurLocal3;
    public JTextField tf_joueurLocal4;
    public JTextField tf_joueurVisiteur1;
    public JTextField tf_joueurVisiteur2;
    public JTextField tf_joueurVisiteur3;
    public JTextField tf_joueurVisiteur4;
    public JLabel lab_joueurLocal11;
    public JLabel lab_joueurLocal12;
    public JLabel lab_joueurLocal13;
    public JLabel lab_joueurLocal14;
    public JLabel lab_scoreLocal11;
    public JLabel lab_scoreLocal12;
    public JLabel lab_scoreLocal13;
    public JLabel lab_scoreLocal14;
    public JLabel lab_scoreVisiteur11;
    public JLabel lab_scoreVisiteur12;
    public JLabel lab_scoreVisiteur13;
    public JLabel lab_scoreVisiteur14;
    public JLabel lab_joueurVisiteur11;
    public JLabel lab_joueurVisiteur12;
    public JLabel lab_joueurVisiteur13;
    public JLabel lab_joueurVisiteur14;
    public JLabel lab_joueurLocal21;
    public JLabel lab_joueurLocal22;
    public JLabel lab_joueurLocal23;
    public JLabel lab_joueurLocal24;
    public JLabel lab_scoreLocal21;
    public JLabel lab_scoreLocal22;
    public JLabel lab_scoreLocal23;
    public JLabel lab_scoreLocal24;
    public JLabel lab_scoreVisiteur21;
    public JLabel lab_scoreVisiteur22;
    public JLabel lab_scoreVisiteur23;
    public JLabel lab_scoreVisiteur24;
    public JLabel lab_joueurVisiteur21;
    public JLabel lab_joueurVisiteur22;
    public JLabel lab_joueurVisiteur23;
    public JLabel lab_joueurVisiteur24;
    public JLabel lab_joueurLocal31;
    public JLabel lab_joueurLocal32;
    public JLabel lab_joueurLocal33;
    public JLabel lab_joueurLocal34;
    public JLabel lab_scoreLocal31;
    public JLabel lab_scoreLocal32;
    public JLabel lab_scoreLocal33;
    public JLabel lab_scoreLocal34;
    public JLabel lab_scoreVisiteur31;
    public JLabel lab_scoreVisiteur32;
    public JLabel lab_scoreVisiteur33;
    public JLabel lab_scoreVisiteur34;
    public JLabel lab_joueurVisiteur31;
    public JLabel lab_joueurVisiteur32;
    public JLabel lab_joueurVisiteur33;
    public JLabel lab_joueurVisiteur34;
    public JLabel lab_joueurLocal41;
    public JLabel lab_joueurLocal42;
    public JLabel lab_joueurLocal43;
    public JLabel lab_joueurLocal44;
    public JLabel lab_scoreLocal41;
    public JLabel lab_scoreLocal42;
    public JLabel lab_scoreLocal43;
    public JLabel lab_scoreLocal44;
    public JLabel lab_scoreVisiteur41;
    public JLabel lab_scoreVisiteur42;
    public JLabel lab_scoreVisiteur43;
    public JLabel lab_scoreVisiteur44;
    public JLabel lab_joueurVisiteur41;
    public JLabel lab_joueurVisiteur42;
    public JLabel lab_joueurVisiteur43;
    public JLabel lab_joueurVisiteur44;

    public JPanel getEquipesPanel() { return EquipesPanel; }

    public JTable getTableEquipes() {return tableEquipes;}
    public JTable getTableRencontres() { return tableRencontres; }

    public JButton getBtnAjouterEquipe() {return ajouterEquipeButton;}


    public JButton getBtnModifierEquipeButton(){return  modifierEquipeButton;}

    public  JButton getBtnSupprimerEquipeButton(){return supprimerEquipeButton;}

    public JButton getBtnAddFeuilleMatch() { return addFeuilleMatchButton; }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Equipes");
        frame.setContentPane(new ViewEquipes().EquipesPanel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }


    public void setControleur(Controleur c)
    {
        ajouterEquipeButton.addActionListener(c);
        modifierEquipeButton.addActionListener(c);
        supprimerEquipeButton.addActionListener(c);
        addFeuilleMatchButton.addActionListener(c);

        tableRencontres.getSelectionModel().addListSelectionListener(c);
    }

    public void setTableModelForTableEquipes()
    {
        EquipeJTableModel equipesTableModel = new EquipeJTableModel(Club.getClubInstance().getListeEquipesClub());
        tableEquipes = new JTable(equipesTableModel);
    }

    public void setTableModelForTableRencontres()
    {
        RencontreJTableModel rencontreTableModel = new RencontreJTableModel(Club.getClubInstance().getListeRencontres());
        tableRencontres = new JTable(rencontreTableModel);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        setTableModelForTableEquipes();
        setTableModelForTableRencontres();

    }
}
