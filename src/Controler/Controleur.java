package Controler;

import java.awt.*;
import java.awt.event.*;
import View.*;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.View;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Controleur extends WindowAdapter implements ActionListener, ListSelectionListener
{
    private MainView mainView;
    private Club singleton;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == mainView.getMenuItemMembres())
        {
            mainView.changeSelectedView(mainView.getViewMembres().getMembresPanel());
        }
        else if(e.getSource() == mainView.getMenuItemEquipes())
        {
            mainView.changeSelectedView(mainView.getViewEquipes().getEquipesPanel());
        }
        else if(e.getSource() == mainView.getMenuItemSaveData())
        {
            if(!singleton.areAllDataRecorded())
            {
                try
                {
                    singleton.Save();
                    singleton.setStateRecordedData(true);
                    JOptionPane.showMessageDialog(null, "Toutes les modifications ont bien été enregistrées!", "Sauvegarde réussie", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }


        if(e.getSource() == mainView.getViewMembres().getBtnAjouterStaff()) {
            onNouveauStaff();
        }
        else if(e.getSource() == mainView.getViewMembres().getBtnModifierStaff()) {
            onModifierStaff();
        }
        else if(e.getSource() == mainView.getViewMembres().getBtnSupprimerStaff()) {
            onSupprimerStaff();
        }

        if(e.getSource() == mainView.getViewMembres().getBtnAjouterJoueur()) {
            onNouveauJoueur();
        }
        else if(e.getSource() == mainView.getViewMembres().getBtnModifierJoueur()) {
            onModifierJoueur();
        }
        else if(e.getSource() == mainView.getViewMembres().getBtnSupprimerJoueur()) {
            onSupprimerJoueur();
        }

        if(e.getSource() == mainView.getViewEquipes().getBtnAjouterEquipe()) {
            onNouvelleEquipe();
        }
        if (e.getSource() == mainView.getViewEquipes().getBtnModifierEquipeButton())
        {
            onModifierEquipe();
        }
        if (e.getSource() == mainView.getViewEquipes().getBtnSupprimerEquipeButton()){
            onSupprimerEquipe();
        }

        if(e.getSource() == mainView.getViewEquipes().getBtnAddFeuilleMatch()) {
            onNouvelleRencontre();
        }

    }

    //Méthode exécutée lors d'un changement d'élément sélectionné dans la JTable des joueurs pour actualiser l'affichage
    //des informations détaillées du joueur.
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if(e.getSource() == mainView.getViewMembres().getTableJoueurs().getSelectionModel() || e.getSource() == this)
        {
            ViewMembres vueMembres = mainView.getViewMembres();
            int indice = vueMembres.getTableJoueurs().getSelectedRow();
            Joueur j = singleton.getListeJoueursClub().get(indice);

            vueMembres.setTextOn_textField_Nom(j.getNom());
            vueMembres.setTextOn_textField_Prenom(j.getPrenom());
            vueMembres.setTextOn_textField_NumRegNat(j.getNumRegistreNational());
            vueMembres.setTextOn_textField_Adresse(j.getAdresse());
            vueMembres.setTextOn_textField_Classement(j.getClassement());
            vueMembres.setTextOn_textField_Sexe(j.getSexe());

            String listeForce = Integer.toString(j.getListeForce());
            vueMembres.setTextOn_textField_LForce(listeForce);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            String dateFormatee = sdf.format(j.getDateNaissance().getTime());
            vueMembres.setTextOn_textField_DateNaissance(dateFormatee);
        }
        else
        {
            ViewEquipes vueEquipes = mainView.getViewEquipes();
            int indice = vueEquipes.getTableRencontres().getSelectedRow();
            Rencontre r = singleton.getListeRencontres().get(indice);

            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE);
            vueEquipes.tf_dateDebut.setText(df.format(r.getDateDebut().getTime()));
            vueEquipes.tf_dateFin.setText(df.format(r.getDateFin().getTime()));

            vueEquipes.tf_equipeLocale.setText(r.getLocaux().getNomEquipe());
            vueEquipes.tf_equipeVisiteuse.setText(r.getVisiteurs().getNomEquipe());

            Joueur[] tabJoueursLocaux = r.getJoueursLocaux();
            Joueur[] tabJoueursVisiteurs = r.getJoueursVisiteurs();
            Joueur j;
            for(int i=0; i < tabJoueursLocaux.length; i++) {

                JTextField tf = recupTextFieldJoueursLocaux(i+1);
                j = tabJoueursLocaux[i];
                String contenu = j.getNom() + " " + j.getPrenom();
                tf.setText(contenu);
            }

            for(int i=0; i< tabJoueursVisiteurs.length; i++)
            {
                JTextField tf = recupTexteFieldJoueurVisiteur(i+1);
                j = tabJoueursVisiteurs[i];
                String contenu = j.getNom() + " " +j.getPrenom();
                tf.setText(contenu);
            }

            JLabel label;
            ResultatMatch[] resultatsMatchs = r.getResultat();
            int score;
            for(int numPanel = 1; numPanel < 5; numPanel++)
            {
                for(int i=0; i < 4; i++)
                {
                    for(int k=0; k < 4; k++)
                    {
                        label = recupLabelScore(numPanel, i, k);
                        switch(k)
                        {
                            case 0:
                                j = tabJoueursLocaux[numPanel-1];
                                label.setText(j.getNom() + " " + j.getPrenom());
                                break;

                            case 1:
                                score = resultatsMatchs[i + (4 * (numPanel-1))].getScoreLocaux();
                                label.setText(String.valueOf(score));
                                break;

                            case 2:
                                score = resultatsMatchs[i + (4 * (numPanel-1))].getScoreVisiteurs();
                                label.setText(String.valueOf(score));
                                break;

                            case 3:
                                j = tabJoueursVisiteurs[i];
                                label.setText(j.getNom() + " " + j.getPrenom());
                                break;
                        }
                    }
                }
            }
        }
    }

    private JLabel recupLabelScore(int numeroPanel, int ligne, int col)
    {
        ViewEquipes vueEquipes = mainView.getViewEquipes();
        JLabel label = null;

        switch(numeroPanel)
        {
            case 1: {
                switch (ligne) {
                    //Ligne 1 panel 1
                    case 0:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal11;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal11;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur11;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur11;
                                break;
                        }
                        break;

                    //Ligne 2 panel 1
                    case 1:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal12;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal12;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur12;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur12;
                                break;
                        }
                        break;

                    //Ligne 3 panel 1
                    case 2:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal13;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal13;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur13;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur13;
                                break;
                        }
                        break;

                    case 3:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal14;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal14;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur14;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur14;
                                break;
                        }
                        break;
                }
            }
            break;

            //Panel 2
            case 2: {
                switch (ligne) {
                    //Ligne 1 panel 2
                    case 0:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal21;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal21;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur21;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur21;
                                break;
                        }
                        break;

                    //Ligne 2 panel 1
                    case 1:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal22;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal22;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur22;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur22;
                                break;
                        }
                        break;

                    //Ligne 3 panel 1
                    case 2:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal23;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal23;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur23;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur23;
                                break;
                        }
                        break;

                    case 3:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal24;
                                break;

                            case 1:
                                label = vueEquipes.lab_scoreLocal24;
                                break;

                            case 2:
                                label = vueEquipes.lab_scoreVisiteur24;
                                break;

                            case 3:
                                label = vueEquipes.lab_joueurVisiteur24;
                                break;
                        }
                        break;
                }
            }
            break;


            case 3: {
                switch (ligne) {
                    case 0:

                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal31;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal31;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur31;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur31;
                                break;
                        }
                        break;
                    case 1:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal32;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal32;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur32;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur32;
                                break;
                        }
                        break;
                    case 2:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal33;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal33;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur33;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur33;
                                break;
                        }
                        break;
                    case 3:
                        switch (col) {
                            case 0:
                                label = vueEquipes.lab_joueurLocal34;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal34;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur34;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur34;
                                break;
                        }
                        break;
                }
            }
            break;


            case 4:
            {
                switch (ligne)
                {
                    case 0:
                        switch (col)
                        {
                            case 0:
                                label = vueEquipes.lab_joueurLocal41;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal41;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur41;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur41;
                                break;
                        }
                        break;

                    case 1:
                        switch (col)
                        {
                            case 0:
                                label = vueEquipes.lab_joueurLocal42;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal42;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur42;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur42;
                                break;
                        }
                        break;

                    case 2:
                        switch (col)
                        {
                            case 0:
                                label = vueEquipes.lab_joueurLocal43;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal43;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur43;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur43;
                                break;
                        }
                        break;

                    case 3:
                        switch (col)
                        {
                            case 0:
                                label = vueEquipes.lab_joueurLocal44;
                                break;
                            case 1:
                                label = vueEquipes.lab_scoreLocal44;
                                break;
                            case 2:
                                label = vueEquipes.lab_scoreVisiteur44;
                                break;
                            case 3:
                                label = vueEquipes.lab_joueurVisiteur44;
                                break;
                        }
                        break;
                }
            }
            break;
        }

        return label;
    }

    private JTextField recupTexteFieldJoueurVisiteur(int numTexteField)
    {
        JTextField tf = null;
        ViewEquipes vueEquipes = mainView.getViewEquipes();
        switch (numTexteField)
        {
            case 1:
                tf = vueEquipes.tf_joueurVisiteur1;
                break;
            case 2:
                tf = vueEquipes.tf_joueurVisiteur2;
                break;
            case 3:
                tf = vueEquipes.tf_joueurVisiteur3;
                break;
            case 4:
                tf = vueEquipes.tf_joueurVisiteur4;
                break;
        }
        return tf;
    }

    private JTextField recupTextFieldJoueursLocaux(int numeroTextField)
    {
        JTextField tf = null;
        ViewEquipes vueEquipes = mainView.getViewEquipes();
        switch(numeroTextField)
        {
            case 1:
                tf = vueEquipes.tf_joueurLocal1;
                break;

            case 2:
                tf = vueEquipes.tf_joueurLocal2;
                break;

            case 3:
                tf = vueEquipes.tf_joueurLocal3;
                break;

            case 4:
                tf = vueEquipes.tf_joueurLocal4;
                break;
        }

        return tf;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        if(!singleton.areAllDataRecorded())
        {
            try
            {
                singleton.Save();
            }
            catch(FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public Controleur(MainView v, Club c)
    {
        mainView = v;
        singleton = c;
    }

    private void onNouveauStaff()
    {
        DialogAjouteStaff d = new DialogAjouteStaff(mainView, true);
        if(d.isOk())
        {
            Staff s = d.getMembreStaff();
            singleton.ajouteStaff(s);

            JTable t = mainView.getViewMembres().getTableStaff();
            ((AbstractTableModel)(t.getModel())).fireTableRowsInserted(t.getRowCount()-1, t.getRowCount()-1);

            singleton.setStateRecordedData(false);
        }
        d.dispose();
    }

    private void onModifierStaff()
    {
        int indexSelectedRow;
        JTable refTableStaff = mainView.getViewMembres().getTableStaff();
        if( ((indexSelectedRow = refTableStaff.getSelectedRow()) != -1) && (refTableStaff.getRowCount() > 0) )
        {
            Staff s = singleton.getListeStaffClub().get(indexSelectedRow);
            DialogAjouteStaff d = new DialogAjouteStaff(mainView, true, s, indexSelectedRow);
            if(d.isOk())
            {
                Staff staffModif = d.getMembreStaff();
                singleton.modifierStaff(s, staffModif);

                ((AbstractTableModel)(refTableStaff.getModel())).fireTableRowsUpdated(indexSelectedRow, indexSelectedRow);
                singleton.setStateRecordedData(false);
            }
            d.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Aucun membre du staff n'est sélectionné...Veuillez sélectionner le membre du staff que vous voulez modifier.");
        }
    }

    private void onSupprimerStaff()
    {
        int indexSelectedRow;
        JTable refTableS = mainView.getViewMembres().getTableStaff();

        if( ((indexSelectedRow = refTableS.getSelectedRow()) != -1) && (refTableS.getRowCount() > 0) )
        {
            singleton.supprimeStaff(indexSelectedRow);
            refTableS.repaint();

            singleton.setStateRecordedData(false);
            //((AbstractTableModel)refTableS.getModel()).fireTableRowsDeleted(indexSelectedRow, indexSelectedRow);
        }
        else {
            JOptionPane.showMessageDialog(null, "Aucun membre du staff n'est sélectionné...veuillez sélectionner le membre du staff que vous souhaitez supprimer.");
        }
    }

    private void onNouveauJoueur()
    {
        DialogAjouteJoueur d = new DialogAjouteJoueur(mainView, true);
        if(d.isOk())
        {
            Joueur j = d.getNouveauJoueur();
            singleton.ajouteJoueur(j);

            JTable t = mainView.getViewMembres().getTableJoueurs();
            ((AbstractTableModel)(t.getModel())).fireTableRowsInserted(t.getRowCount()-1, t.getRowCount()-1);

            singleton.setStateRecordedData(false);
        }
        d.dispose();
    }

    private void onModifierJoueur()
    {
        int selectedRow;
        JTable refTableJ = mainView.getViewMembres().getTableJoueurs();

        if( ((selectedRow = refTableJ.getSelectedRow()) != -1) && (refTableJ.getRowCount() > 0) )
        {
            Joueur j = singleton.getListeJoueursClub().get(selectedRow);
            DialogAjouteJoueur d = new DialogAjouteJoueur(mainView, true, j, selectedRow);
            if(d.isOk())
            {
                Joueur joueurModif = d.getNouveauJoueur();
                singleton.modifierJoueur(j, joueurModif);

                ((AbstractTableModel)(refTableJ.getModel())).fireTableRowsUpdated(selectedRow, selectedRow);
                valueChanged(new ListSelectionEvent(this, selectedRow, selectedRow, false));
                singleton.setStateRecordedData(false);
            }
            d.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Aucun joueur n'est sélectionné...veuillez sélectionner le joueur que vous souhaitez modifier.");
        }
    }

    private void onSupprimerJoueur()
    {
        int indexSelectedRow;
        JTable refTableJ = mainView.getViewMembres().getTableJoueurs();

        if( ((indexSelectedRow = refTableJ.getSelectedRow()) != -1) && (refTableJ.getRowCount() > 0) )
        {
            singleton.supprimeJoueur(indexSelectedRow);
            refTableJ.repaint();

            singleton.setStateRecordedData(false);
            //((AbstractTableModel)refTableJ.getModel()).fireTableRowsDeleted(indexSelectedRow, indexSelectedRow);

            if(refTableJ.getRowCount() == 0 || indexSelectedRow == refTableJ.getRowCount())
            {
                ViewMembres vMembres = mainView.getViewMembres();

                vMembres.setTextOn_textField_Nom("");
                vMembres.setTextOn_textField_Prenom("");
                vMembres.setTextOn_textField_DateNaissance("");
                vMembres.setTextOn_textField_Adresse("");
                vMembres.setTextOn_textField_Classement("");
                vMembres.setTextOn_textField_LForce("");
                vMembres.setTextOn_textField_Sexe("");
                vMembres.setTextOn_textField_NumRegNat("");
            }
            else
            {
                valueChanged(new ListSelectionEvent(this, indexSelectedRow, indexSelectedRow, false));
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Aucun joueur n'est sélectionné...veuillez sélectionner le joueur que vous souhaitez supprimer.");
        }
    }
    private void onNouvelleEquipe()
    {
        DialogAjouteEquipe d = new DialogAjouteEquipe(mainView,true);
        if(d.isOk())
        {
            Equipe e = d.getEquipe();
            singleton.ajouteEquipe(e);

            JTable t = mainView.getViewEquipes().getTableEquipes();
            ((AbstractTableModel)(t.getModel())).fireTableRowsInserted(t.getRowCount()-1, t.getRowCount()-1);

            singleton.setStateRecordedData(false);
        }
        d.dispose();
    }

    public void onModifierEquipe()
    {
        int indexSelectedRow;
        JTable refTableEquipe = mainView.getViewEquipes().getTableEquipes();
        if( ((indexSelectedRow = refTableEquipe.getSelectedRow()) != -1) && (refTableEquipe.getRowCount() > 0) )
        {
            Equipe e = singleton.getListeEquipesClub().get(indexSelectedRow);
            DialogAjouteEquipe d = new DialogAjouteEquipe(mainView, true, e, indexSelectedRow);
            if(d.isOk())
            {
                Equipe equipeModif = d.getEquipe();
                singleton.modifierEquipe(e, equipeModif);

                ((AbstractTableModel)(refTableEquipe.getModel())).fireTableRowsUpdated(indexSelectedRow, indexSelectedRow);
                singleton.setStateRecordedData(false);
            }
            d.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Aucune équipe n'est sélectionnée...Veuillez sélectionner l'équipe que vous voulez modifier.");
        }
    }

    public void onSupprimerEquipe()
    {
        int indexSelectedRow;
        JTable refTableS = mainView.getViewEquipes().getTableEquipes();

        if( ((indexSelectedRow = refTableS.getSelectedRow()) != -1) && (refTableS.getRowCount() > 0) )
        {
            singleton.supprimeEquipe(indexSelectedRow);
            refTableS.repaint();
            singleton.setStateRecordedData(false);
            //((AbstractTableModel)refTableS.getModel()).fireTableRowsDeleted(indexSelectedRow, indexSelectedRow);
        }
        else {
            JOptionPane.showMessageDialog(null, "Aucune équipe n'est sélectionnée...veuillez sélectionner l'équipe que vous souhaitez supprimer.");
        }
    }

    private void onNouvelleRencontre()
    {
        if(singleton.getListeJoueursClub().size() >= 4 && singleton.getListeJoueursAdverses().size() >= 4)
        {
            DialogAjouteRencontre d = new DialogAjouteRencontre(mainView,true, singleton.getListeJoueursClub(), singleton.getListeJoueursAdverses(),
                    singleton.getListeEquipesClub(), singleton.getListeEquipesAdverses());
            if(d.isOk())
            {
                Rencontre r = d.getNouvelleRencontre();
                singleton.ajouteRencontre(r);
                System.out.println("Affichage de la nouvelle rencontre:\n" + r.toString());

                JTable t = mainView.getViewEquipes().getTableRencontres();
                ((AbstractTableModel)(t.getModel())).fireTableRowsInserted(t.getRowCount()-1, t.getRowCount()-1);

                singleton.setStateRecordedData(false);
            }
            d.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Impossible d'enregistrer une nouvelle feuille de match!\nLe nombre de joueurs du club ou bien le nombre de joueurs adverses est insuffisant!");
        }
    }
}
