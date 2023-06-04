package View.JTableModel;

import javax.swing.table.AbstractTableModel;
import Model.Equipe;

import java.util.ArrayList;

public class EquipeJTableModel extends AbstractTableModel
{

    private String [] columnNames = {"Num. Equipe","Nom Equipe","Division","Région","Catégorie"};

    private ArrayList<Equipe> data;

    public EquipeJTableModel(ArrayList<Equipe> data){this.data = data;}


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int l, int c)
    {
        Equipe e = data.get(l);

        switch (c)
        {
            case 0:
                return e.getNumEquipe();
            case 1:
                return e.getNomEquipe();
            case 2:
                return e.getDivision();
            case 3:
                return e.getRegion();
            case 4:
                return e.getCategorie();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col)
    {
        super.setValueAt(value, row, col);
        //fireTableDataChanged();
    }
    @Override
    public String getColumnName(int c)
    {
        return columnNames[c];
    }
}
