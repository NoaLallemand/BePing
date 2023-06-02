package View;

import Model.Joueur;
import Model.ResultatMatch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class DialogAjoutResultatsRencontre extends JDialog implements ActionListener
{
    private JTextField tf_joueurLocal;
    private JTextField tf_scoreLocaux1;
    private JTextField tf_scoreVisiteurs1;
    private JTextField tf_joueurVisiteur1;
    private JTextField tf_joueurVisiteur2;
    private JTextField tf_scoreLocaux2;
    private JTextField tf_scoreVisiteurs2;
    private JTextField tf_joueurVisiteur3;
    private JTextField tf_scoreLocaux3;
    private JTextField tf_scoreVisiteurs3;
    private JTextField tf_joueurVisiteur4;
    private JTextField tf_scoreLocaux4;
    private JTextField tf_scoreVisiteurs4;
    private JButton buttonAjouter;
    private JButton buttonAnnuler;
    private JPanel mainPanel;

    private boolean ok;
    private Joueur joueurLocal;
    private Joueur[] joueursVisiteurs;
    private ResultatMatch[] resultatsMatchs;

    public boolean isOk() { return ok; }
    public ResultatMatch[] getResultatsMatchs() { return resultatsMatchs; }

    public DialogAjoutResultatsRencontre(JFrame parent, boolean modal, Joueur joueurLocal, Joueur[] joueursVisiteurs)
    {
        super(parent, modal);
        this.joueurLocal = joueurLocal;
        this.joueursVisiteurs = joueursVisiteurs;

        this.chargeComposants();
    }

    private void chargeComposants()
    {
        setContentPane(mainPanel);
        setSize(400, 480);
        ok = false;

        resultatsMatchs = new ResultatMatch[4];

        String jLocalToString = joueurLocal.getNom() + " " + joueurLocal.getPrenom();
        tf_joueurLocal.setText(jLocalToString);
        tf_joueurLocal.setEnabled(false);

        Joueur j;
        for(int i=0; i<4; i++)
        {
            j = joueursVisiteurs[i];
            String jVisiteurToString = j.getNom() + " " + j.getPrenom();

            JTextField tf = getTextFieldJoueurVisiteurNumberX(i+1);
            tf.setText(jVisiteurToString);
            tf.setEditable(false);
        }

        buttonAjouter.addActionListener(this);
        buttonAnnuler.addActionListener(this);

        setVisible(true);
    }

    private void recupereDonneesResultatsMatchs() throws Exception
    {
        int stockeScoreLocaux, stockeScoreVisiteurs;
        for(int i=0; i<4; i++)
        {
            JTextField tfScoreLocaux = getTextFiedScoreLocauxNumberX(i+1);
            String contenuScoreLocaux = tfScoreLocaux.getText();

            JTextField tfScoreVisiteurs = getTextFieldScoreVisiteursNumber(i+1);
            String contenuScoreVisiteurs = tfScoreVisiteurs.getText();

            try
            {
               stockeScoreLocaux  = Integer.parseInt(contenuScoreLocaux);
               stockeScoreVisiteurs = Integer.parseInt(contenuScoreVisiteurs);

               resultatsMatchs[i] = new ResultatMatch(stockeScoreLocaux, stockeScoreVisiteurs, joueurLocal, joueursVisiteurs[i]);
            }
            catch(NumberFormatException e)
            {
                String msg = "Le score saisi pour le match " + (i+1) + " est invalide!";
                throw new Exception(msg);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == buttonAjouter)
        {
            try
            {
                recupereDonneesResultatsMatchs();

                ok = true;
                setVisible(false);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur lors de l'encodage des résultats", JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(e.getSource() == buttonAnnuler)
        {
            setVisible(false);
        }
    }

    private JTextField getTextFieldJoueurVisiteurNumberX(int number)
    {
        JTextField tf;
        switch(number)
        {
            case 1:
                tf = tf_joueurVisiteur1;
                break;

            case 2:
                tf = tf_joueurVisiteur2;
                break;

            case 3:
                tf = tf_joueurVisiteur3;
                break;

            case 4:
                tf = tf_joueurVisiteur4;
                break;

            default:
                tf = tf_joueurVisiteur1;
        }

        return tf;
    }

    private JTextField getTextFiedScoreLocauxNumberX(int number)
    {
        JTextField tf;
        switch(number)
        {
            case 1:
                tf = tf_scoreLocaux1;
                break;

            case 2:
                tf = tf_scoreLocaux2;
                break;

            case 3:
                tf = tf_scoreLocaux3;
                break;

            case 4:
                tf = tf_scoreLocaux4;
                break;

            default:
                tf = tf_scoreLocaux1;
        }

        return tf;
    }

    private JTextField getTextFieldScoreVisiteursNumber(int number)
    {
        JTextField tf;
        switch(number)
        {
            case 1:
                tf = tf_scoreVisiteurs1;
                break;

            case 2:
                tf = tf_scoreVisiteurs2;
                break;

            case 3:
                tf = tf_scoreVisiteurs3;
                break;

            case 4:
                tf = tf_scoreVisiteurs4;
                break;

            default:
                tf = tf_scoreVisiteurs4;
        }

        return tf;
    }
}
