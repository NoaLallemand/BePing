package View;

import Model.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogAjoutJoueursRencontre extends JDialog
{
    private JComboBox cbBox_ListeJoueurs;
    private JButton ajouterJoueurButton;
    private JButton annulerButton;
    private JPanel mainPanel;
    private JLabel titreDialogLabel;


    private ArrayList<String> joueursDisponibles;
    private boolean ok;
    private String itemSelectionne;

    public boolean isOk() { return ok; }
    public String getItemSelectionne() { return itemSelectionne; }

    public DialogAjoutJoueursRencontre(JFrame parent, boolean modal, ArrayList<String> listeJoueursToString, boolean isListeJoueursClub)
    {
        super(parent, modal);

        joueursDisponibles = listeJoueursToString;
        if(isListeJoueursClub)
            titreDialogLabel.setText("Choix d'un joueur du club");
        else
            titreDialogLabel.setText("Choix d'un joueur adverse");
        
        this.chargeComposants();

        ajouterJoueurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceItemSelectionne = cbBox_ListeJoueurs.getSelectedIndex();
                itemSelectionne = (String)cbBox_ListeJoueurs.getItemAt(indiceItemSelectionne);
                ok = true;
                setVisible(false);
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setVisible(false); }
        });

        setVisible(true);
    }

    private void chargeComposants()
    {
        setContentPane(mainPanel);
        setSize(400, 200);
        ok = false;

        String s;
        for(int i=0; i < joueursDisponibles.size(); i++)
        {
            s = joueursDisponibles.get(i);
            cbBox_ListeJoueurs.addItem(s);
        }
    }
}
