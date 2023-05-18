package Controler;

import java.awt.*;
import java.awt.event.*;
import View.*;
import Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Controleur implements ActionListener, ListSelectionListener
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


        if(e.getSource() == mainView.getViewMembres().getBtnAjouterStaff()) {
            onNouveauStaff();
        }

        if(e.getSource() == mainView.getViewMembres().getBtnAjouterJoueur()) {
            onNouveauJoueur();
        }
        else if(e.getSource() == mainView.getViewMembres().getBtnModifierJoueur()) {
            onModifierJoueur();
        }

        if(e.getSource() == mainView.getViewEquipes().getBtnAjouterEquipe()) {
            onNouvelleEquipe();
        }
    }

    //Méthode exécutée lors d'un changement d'élément sélectionné dans la JTable des joueurs.
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
            for(int i=0; i<7; i++)
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
            }
        }
        d.dispose();
    }

    public void onNouveauJoueur()
    {
        DialogAjouteJoueur d = new DialogAjouteJoueur(mainView, true);
        if(d.isOk())
        {
            Joueur j = d.getNouveauJoueur();
            singleton.getListeJoueursClub().add(j);

            JTable t = mainView.getViewMembres().getTableJoueurs();

            t.setValueAt(j.getNumRegistreNational(), t.getRowCount()-1, 0);
            t.setValueAt(j.getNom(), t.getRowCount()-1, 1);
            t.setValueAt(j.getPrenom(), t.getRowCount()-1, 2);

        }
        d.dispose();
    }

    public void onModifierJoueur()
    {
        int selectedRow;
        if( (selectedRow = mainView.getViewMembres().getTableJoueurs().getSelectedRow()) != -1)
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
                    JOptionPane.showMessageDialog(null, "");
                }

                if( (selectedRow = mainView.getViewMembres().getTableJoueurs().getSelectedRow()) != -1)
                {
                    System.out.println("Valeur de selectedRow = " + selectedRow);
                    JTable jt = mainView.getViewMembres().getTableJoueurs();
                    jt.setValueAt(j.getNumRegistreNational(), selectedRow, 0);
                    jt.setValueAt(j.getNom(), selectedRow, 1);
                    jt.setValueAt(j.getPrenom(), selectedRow, 2);
                }

                valueChanged(new ListSelectionEvent(mainView.getViewMembres().getTableJoueurs(), selectedRow, selectedRow, false));
            }
            d.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Aucun joueur n'est sélectionné...veuillez sélectionner le joueur que vous souhaitez modifier.");
        }
    }
    public void onNouvelleEquipe()
    {
        DialogAjouteEquipe d = new DialogAjouteEquipe(mainView,true);
    }
}
