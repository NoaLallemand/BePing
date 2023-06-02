package View.JTableModel;

import Model.Joueur;
import Model.Rencontre;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RencontreJTableModel extends AbstractTableModel
{
    private String[] columnNames = {"Equipe Locale", "Equipe Visisteuse", "Début de la rencontre"};
    private ArrayList<Rencontre> data;

    public RencontreJTableModel(ArrayList<Rencontre> data)
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
        Rencontre r = data.get(l);

        switch(c)
        {
            case 0:
                return r.getLocaux().getNomEquipe();

            case 1:
                return r.getVisiteurs().getNomEquipe();

            case 2:
                Date d = r.getDateDebut().getTime();
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE);
                String dateFormatee = df.format(d);
                return dateFormatee;

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
