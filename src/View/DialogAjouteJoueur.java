package View;

import javax.swing.*;

public class DialogAjouteJoueur extends JDialog
{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton ajouterButton;
    private JButton annulerButton;
    private JPanel mainPanel;

    public DialogAjouteJoueur(JFrame parent, boolean modal)
    {
        super(parent, modal);
        setContentPane(mainPanel);
        setSize(400, 480);
        setVisible(true);
    }
}
