package Controler;

import java.awt.*;
import java.awt.event.*;
import View.*;
import Model.*;

import javax.swing.*;
import java.util.Date;

public class Controleur implements ActionListener
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

        if(e.getSource() == mainView.getViewMembres().getBtnAjouterJoueur())
        {
            DialogAjouteJoueur d = new DialogAjouteJoueur(mainView, true);
            d.dispose();
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
}
