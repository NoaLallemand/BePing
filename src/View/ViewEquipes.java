package View;

import Controler.Controleur;
import Model.Club;
import View.JTableModel.EquipeJTableModel;
import View.JTableModel.JoueursJTableModel;

import javax.swing.*;

public class ViewEquipes {
    private JPanel EquipesPanel;
    private JTable tableEquipes;
    private JButton ajouterEquipeButton;
    private JButton modifierEquipeButton;
    private JButton supprimerEquipeButton;
    private JTable tableRencontres;
    private JButton addFeuilleMatchButton;
    private JScrollBar scrollBar_PanelDetailsRencontre;

    public JPanel getEquipesPanel() { return EquipesPanel; }

    public JTable getTableEquipes() {return tableEquipes;}


    public static void main(String[] args) {
        JFrame frame = new JFrame("Equipes");
        frame.setContentPane(new ViewEquipes().EquipesPanel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }

    public JButton getBtnAjouterEquipe() {return ajouterEquipeButton;}

    public JButton getBtnModifierEquipeButton(){return  modifierEquipeButton;}

    public  JButton getBtnSupprimerEquipeButton(){return supprimerEquipeButton;}
    public void setControleur(Controleur c)
    {
        ajouterEquipeButton.addActionListener(c);
        modifierEquipeButton.addActionListener(c);
        supprimerEquipeButton.addActionListener(c);
    }

    public void setTableModelForTableEquipes()
    {
        EquipeJTableModel equipesTableModel = new EquipeJTableModel(Club.getClubInstance().getListeEquipesClub());
        tableEquipes = new JTable(equipesTableModel);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        setTableModelForTableEquipes();

    }
}
