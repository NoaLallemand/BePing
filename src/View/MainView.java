package View;

import javax.swing.*;
import Controler.Controleur;
public class MainView extends JFrame
{
    private ViewMembres fenetreMembres;
    private ViewEquipes fenetreEquipes;

    private JMenuBar menuPrincipal;
    private JMenu menuEquipes;
    private JMenuItem menuItemEquipes;
    private JMenu menuMembres;
    private JMenuItem menuItemMembres;

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

        menuMembres = new JMenu("Gestion Membres");
        menuEquipes = new JMenu("Gestion Equipes");
        menuPrincipal.add(menuMembres);
        menuPrincipal.add(menuEquipes);

        menuItemMembres = new JMenuItem("Membres");
        menuItemEquipes = new JMenuItem("Equipes");
        menuMembres.add(menuItemMembres);
        menuEquipes.add(menuItemEquipes);
    }

    public ViewMembres getViewMembres() { return fenetreMembres; }
    public ViewEquipes getViewEquipes() { return fenetreEquipes; }

    public JMenu getMenuMembres() { return menuMembres; }
    public JMenu getMenuEquipes() { return menuEquipes; }

    public JMenuItem getMenuItemMembres() { return menuItemMembres; }

    public JMenuItem getMenuItemEquipes() { return menuItemEquipes; }

    public void setControleur(Controleur c)
    {
        menuItemMembres.addActionListener(c);
        menuItemEquipes.addActionListener(c);

        fenetreMembres.setControleur(c);
        fenetreEquipes.setControleur(c);
    }

    public void changeSelectedView(JPanel panel)
    {
        if(panel != getContentPane())
        {
            setContentPane(panel);
        }
    }
}
