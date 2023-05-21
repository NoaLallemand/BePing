package View;

import Controler.Controleur;

import javax.swing.*;

public class ViewEquipes {
    private JPanel EquipesPanel;
    private JTable tableEquipes;
    private JTable tableRencontres;
    private JButton ajouterEquipeButton;
    private JButton modifierEquipeButton;
    private JButton supprimerEquipeButton;
    private JButton addFeuilleMatchButton;
    private JScrollBar scrollBar_PanelDetailsRencontre;

    public JPanel getEquipesPanel() { return EquipesPanel; }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Equipes");
        frame.setContentPane(new ViewEquipes().EquipesPanel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }

    public JButton getBtnAjouterEquipe() {return ajouterEquipeButton;}
    public JButton getBtnAddFeuilleMatch() { return addFeuilleMatchButton; }
    public void setControleur(Controleur c)
    {
        ajouterEquipeButton.addActionListener(c);

        addFeuilleMatchButton.addActionListener(c);
    }
}
