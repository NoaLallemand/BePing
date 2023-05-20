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
            try
            {
                singleton.Save();
            }
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
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
    }

    //Méthode exécutée lors d'un changement d'élément sélectionné dans la JTable des joueurs pour actualiser l'affichage
    //des informations détaillées du joueur.
    @Override
    public void valueChanged(ListSelectionEvent e) {

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

    @Override
    public void windowClosing(WindowEvent e)
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

    public Controleur(MainView v, Club c)
    {
        mainView = v;
        singleton = c;
    }

    public void onNouveauStaff()
    {
        DialogAjouteStaff d = new DialogAjouteStaff(mainView, true);
        if(d.isOk())
        {
            Staff s = d.getMembreStaff();
            singleton.getListeStaffClub().add(s);

            JTable t = mainView.getViewMembres().getTableStaff();
            ((AbstractTableModel)(t.getModel())).fireTableRowsInserted(t.getRowCount()-1, t.getRowCount()-1);
            /*for(int i=0; i<7; i++)
            {
                switch(i)
                {
                    case 0:
                        t.setValueAt(s.getNumRegistreNational(), t.getRowCount()-1, i);
                        break;

                    case 1:
                        t.setValueAt(s.getNom(), t.getRowCount()-1, i);
                        break;

                    case 2:
                        t.setValueAt(s.getPrenom(), t.getRowCount()-1, i);
                        break;

                    case 3:
                        t.setValueAt(s.getDateNaissance(), t.getRowCount()-1, i);
                        break;

                    case 4:
                        t.setValueAt(s.getAdresse(), t.getRowCount()-1, i);
                        break;

                    case 5:
                        t.setValueAt(s.getSexe(), t.getRowCount()-1, i);
                        break;

                    case 6:
                        t.setValueAt(s.getRole(), t.getRowCount()-1, i);
                }
            }*/
        }
        d.dispose();
    }

    public void onModifierStaff()
    {
        int indexSelectedRow;
        JTable refTableStaff = mainView.getViewMembres().getTableStaff();
        if( (indexSelectedRow = refTableStaff.getSelectedRow()) != -1)
        {
            Staff s = singleton.getListeStaffClub().get(indexSelectedRow);
            DialogAjouteStaff d = new DialogAjouteStaff(mainView, true, s, indexSelectedRow);
            if(d.isOk())
            {
                Staff staffModif = d.getMembreStaff();
                try
                {
                    s.setNom(staffModif.getNom());
                    s.setPrenom(staffModif.getPrenom());
                    s.setNumRegistreNational(staffModif.getNumRegistreNational());
                    s.setAdresse(staffModif.getAdresse());
                    s.setDateNaissance(staffModif.getDateNaissance());
                    s.setSexe(staffModif.getSexe());
                    s.setRole(staffModif.getRole());
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                ((AbstractTableModel)(refTableStaff.getModel())).fireTableRowsUpdated(indexSelectedRow, indexSelectedRow);
            }
            d.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Aucun membre du staff n'est sélectionné...Veuillez sélectionner le membre du staff que vous voulez modifier.");
        }
    }

    public void onSupprimerStaff()
    {
        int indexSelectedRow;
        JTable refTableS = mainView.getViewMembres().getTableStaff();

        if( (indexSelectedRow = refTableS.getSelectedRow()) != -1)
        {
            singleton.getListeStaffClub().remove(indexSelectedRow);
            mainView.repaint();
            //((AbstractTableModel)refTableS.getModel()).fireTableRowsDeleted(indexSelectedRow, indexSelectedRow);
        }
        else {
            JOptionPane.showMessageDialog(null, "Aucun membre du staff n'est sélectionné...veuillez sélectionner le membre du staff que vous souhaitez supprimer.");
        }
    }

    public void onNouveauJoueur()
    {
        DialogAjouteJoueur d = new DialogAjouteJoueur(mainView, true);
        if(d.isOk())
        {
            Joueur j = d.getNouveauJoueur();
            singleton.getListeJoueursClub().add(j);


            JTable t = mainView.getViewMembres().getTableJoueurs();
            ((AbstractTableModel)(t.getModel())).fireTableRowsInserted(t.getRowCount()-1, t.getRowCount()-1);
            /*for(int i=0; i<3; i++)
            {
                switch(i)
                {
                    case 0:
                        t.setValueAt(j.getNumRegistreNational(), t.getRowCount()-1, i);
                        break;

                    case 1:
                        t.setValueAt(j.getNom(), t.getRowCount()-1, i);
                        break;

                    case 2:
                        t.setValueAt(j.getPrenom(), t.getRowCount()-1, i);
                }
            }*/
        }
        d.dispose();
    }

    public void onModifierJoueur()
    {
        int selectedRow;
        JTable refTableJ = mainView.getViewMembres().getTableJoueurs();

        if( (selectedRow = refTableJ.getSelectedRow()) != -1)
        {
            Joueur j = singleton.getListeJoueursClub().get(selectedRow);
            DialogAjouteJoueur d = new DialogAjouteJoueur(mainView, true, j, selectedRow);
            if(d.isOk())
            {
                Joueur joueurModif = d.getNouveauJoueur();
                try
                {
                    j.setNom(joueurModif.getNom());
                    j.setPrenom(joueurModif.getPrenom());
                    j.setDateNaissance(joueurModif.getDateNaissance());
                    j.setNumRegistreNational(joueurModif.getNumRegistreNational());
                    j.setAdresse(joueurModif.getAdresse());
                    j.setClassement(joueurModif.getClassement());
                    j.setListeForce(joueurModif.getListeForce());
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

                ((AbstractTableModel)(refTableJ.getModel())).fireTableRowsUpdated(selectedRow, selectedRow);
                valueChanged(new ListSelectionEvent(this, selectedRow, selectedRow, false));
            }
            d.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Aucun joueur n'est sélectionné...veuillez sélectionner le joueur que vous souhaitez modifier.");
        }
    }

    public void onSupprimerJoueur()
    {
        int indexSelectedRow;
        JTable refTableJ = mainView.getViewMembres().getTableJoueurs();

        if( (indexSelectedRow = refTableJ.getSelectedRow()) != -1) {

            singleton.getListeJoueursClub().remove(indexSelectedRow);
            mainView.repaint();
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
        else {
            JOptionPane.showMessageDialog(null, "Aucun joueur n'est sélectionné...veuillez sélectionner le joueur que vous souhaitez supprimer.");
        }
    }
    public void onNouvelleEquipe()
    {
        DialogAjouteEquipe d = new DialogAjouteEquipe(mainView,true);
    }
}
