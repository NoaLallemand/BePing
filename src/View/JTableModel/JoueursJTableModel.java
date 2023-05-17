package View.JTableModel;

import javax.swing.table.AbstractTableModel;
import Model.Joueur;
import Model.Staff;

import java.util.ArrayList;

public class JoueursJTableModel extends AbstractTableModel
{
    private String[] columnNames = {"Num. Registre National", "Nom", "Prénom", "Date de naissance", "Adresse", "Sexe", "Classement", "Liste force"};
    private ArrayList<Joueur> data;

    public JoueursJTableModel(ArrayList<Joueur> data)
    {
        this.data = data;
    }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public int getRowCount() { return data.size(); }

    @Override
    public Object getValueAt(int l, int c)
    {
        Joueur j = data.get(l);

        switch(c)
        {
            case 0:
                return j.getNumRegistreNational();

            case 1:
                return j.getNom();

            case 2:
                return j.getPrenom();

            case 3:
                return j.getDateNaissance();

            case 4:
                return j.getAdresse();

            case 5:
                return j.getSexe();

            case 6:
                return j.getClassement();

            case 7:
                return j.getListeForce();

            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col)
    {
        super.setValueAt(value, row, col);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int c)
    {
        return columnNames[c];
    }
}
