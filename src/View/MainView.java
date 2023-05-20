package View;

import javax.swing.*;
import Controler.Controleur;
public class MainView extends JFrame
{
    private ViewMembres fenetreMembres;
    private ViewEquipes fenetreEquipes;
    private JMenuBar menuPrincipal;
    private JMenu menuClub;
    private JMenu menuParametres;
    private JMenuItem menuItemMembres;
    private JMenuItem menuItemEquipes;
    private JMenuItem menuItemSaveData;


    public MainView()
    {
        super();
        fenetreMembres = new ViewMembres();
        fenetreEquipes = new ViewEquipes();
        setContentPane(fenetreMembres.getMembresPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);

        menuPrincipal = new JMenuBar();
        setJMenuBar(menuPrincipal);

        menuClub = new JMenu("Gestion Club");
        menuParametres = new JMenu("Paramètres");
        menuPrincipal.add(menuParametres);
        menuPrincipal.add(menuClub);


        menuItemMembres = new JMenuItem("Membres");
        menuItemEquipes = new JMenuItem("Equipes");
        menuItemSaveData = new JMenuItem("Sauvegarder");
        menuClub.add(menuItemMembres);
        menuClub.add(menuItemEquipes);
        menuParametres.add(menuItemSaveData);
    }

    public ViewMembres getViewMembres() { return fenetreMembres; }
    public ViewEquipes getViewEquipes() { return fenetreEquipes; }

    public JMenuItem getMenuItemMembres() { return menuItemMembres; }

    public JMenuItem getMenuItemEquipes() { return menuItemEquipes; }
    public JMenuItem getMenuItemSaveData() { return menuItemSaveData; }

    public void setControleur(Controleur c)
    {
        menuItemMembres.addActionListener(c);
        menuItemEquipes.addActionListener(c);
        menuItemSaveData.addActionListener(c);

        fenetreMembres.setControleur(c);
        fenetreEquipes.setControleur(c);
        this.addWindowListener(c);
    }

    public void changeSelectedView(JPanel panel)
    {
        if(panel != getContentPane())
        {
            setContentPane(panel);
        }
    }
}
