package View.JTableModel;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Model.Staff;

import java.util.ArrayList;

public class StaffJTableModel extends AbstractTableModel
{
    private String[] columnNames = {"Num. Registre National", "Nom", "Prénom", "Date de naissance", "Adresse", "Sexe", "Role"};
    private ArrayList<Staff> data;

    public StaffJTableModel(ArrayList<Staff> data)
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
        Staff s = data.get(l);

        switch(c)
        {
            case 0:
                return s.getNumRegistreNational();

            case 1:
                return s.getNom();

            case 2:
                return s.getPrenom();

            case 3:
                return s.getDateNaissance();

            case 4:
                return s.getAdresse();

            case 5:
                return s.getSexe();

            case 6:
                return s.getRole();

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
