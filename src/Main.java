import java.util.ArrayList;
import java.util.Date;


import Model.*;
import View.*;
import Controler.*;

public class Main {
    public static void main(String[] args)
    {
        MainView fenetrePrincipale = new MainView();
        Controleur controleur = new Controleur(fenetrePrincipale, Club.getClubInstance());
        fenetrePrincipale.setControleur(controleur);
        fenetrePrincipale.setVisible(true);
    }
}