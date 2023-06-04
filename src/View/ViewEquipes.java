package View;

import Controler.Controleur;
import Model.Club;

import View.JTableModel.EquipeJTableModel;
import View.JTableModel.JoueursJTableModel;

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
    private JTextField tf_dateDebut;
    private JTextField tf_dateFin;
    private JTextField tf_equipeLocale;
    private JTextField tf_equipeVisiteuse;
    private JTextField tf_joueurLocal1;
    private JTextField tf_joueurLocal2;
    private JTextField tf_joueurLocal3;
    private JTextField tf_joueurLocal4;
    private JTextField tf_joueurVisiteur1;
    private JTextField tf_joueurVisiteur2;
    private JTextField tf_joueurVisiteur3;
    private JTextField tf_joueurVisiteur4;
    private JLabel lab_joueurLocal11;
    private JLabel lab_joueurLocal12;
    private JLabel lab_joueurLocal13;
    private JLabel lab_joueurLocal14;
    private JLabel lab_scoreLocal11;
    private JLabel lab_scoreLocal12;
    private JLabel lab_scoreLocal13;
    private JLabel lab_scoreLocal14;
    private JLabel lab_scoreVisiteur11;
    private JLabel lab_scoreVisiteur12;
    private JLabel lab_scoreVisiteur13;
    private JLabel lab_scoreVisiteur14;
    private JLabel lab_joueurVisiteur11;
    private JLabel lab_joueurVisiteur12;
    private JLabel lab_joueurVisiteur13;
    private JLabel lab_joueurVisiteur14;
    private JLabel lab_joueurLocal21;
    private JLabel lab_joueurLocal22;
    private JLabel lab_joueurLocal23;
    private JLabel lab_joueurLocal24;
    private JLabel lab_scoreLocal21;
    private JLabel lab_scoreLocal22;
    private JLabel lab_scoreLocal23;
    private JLabel lab_scoreLocal24;
    private JLabel lab_scoreVisiteur21;
    private JLabel lab_scoreVisiteur22;
    private JLabel lab_scoreVisiteur23;
    private JLabel lab_scoreVisiteur24;
    private JLabel lab_joueurVisiteur21;
    private JLabel lab_joueurVisiteur22;
    private JLabel lab_joueurVisiteur23;
    private JLabel lab_joueurVisiteur24;
    private JLabel lab_joueurLocal31;
    private JLabel lab_joueurLocal32;
    private JLabel lab_joueurLocal33;
    private JLabel lab_joueurLocal34;
    private JLabel lab_scoreLocal31;
    private JLabel lab_scoreLocal32;
    private JLabel lab_scoreLocal33;
    private JLabel lab_scoreLocal34;
    private JLabel lab_scoreVisiteur31;
    private JLabel lab_scoreVisiteur32;
    private JLabel lab_scoreVisiteur33;
    private JLabel lab_scoreVisiteur34;
    private JLabel lab_joueurVisiteur31;
    private JLabel lab_joueurVisiteur32;
    private JLabel lab_joueurVisiteur33;
    private JLabel lab_joueurVisiteur34;
    private JLabel lab_joueurLocal41;
    private JLabel lab_joueurLocal42;
    private JLabel lab_joueurLocal43;
    private JLabel lab_joueurLocal44;
    private JLabel lab_scoreLocal41;
    private JLabel lab_scoreLocal42;
    private JLabel lab_scoreLocal43;
    private JLabel lab_scoreLocal44;
    private JLabel lab_scoreVisiteur41;
    private JLabel lab_scoreVisiteur42;
    private JLabel lab_scoreVisiteur43;
    private JLabel lab_scoreVisiteur44;
    private JLabel lab_joueurVisiteur1;
    private JLabel lab_joueurVisiteur42;
    private JLabel lab_joueurVisiteur43;
    private JLabel lab_joueurVisiteur44;

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
