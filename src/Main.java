import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import Model.*;
import View.*;
import Controler.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            Club.getClubInstance().Load();
        }
        catch(IOException | ClassNotFoundException | SecurityException e )
        {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'importation des données issues de l'application...", "Importation données", JOptionPane.ERROR_MESSAGE);
        }

        MainView fenetrePrincipale = new MainView();
        Controleur controleur = new Controleur(fenetrePrincipale, Club.getClubInstance());
        fenetrePrincipale.setControleur(controleur);
        fenetrePrincipale.setVisible(true);
    }
}