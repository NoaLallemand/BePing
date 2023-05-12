package Model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Contrat {
    private float salaire;
    private Date dateDebut;
    private Date dateFin;


    public void setSalaire(float montantSalaire) {
        this.salaire = montantSalaire;
    }

    public void setDateDebut(Date debut) {
        this.dateDebut = debut;
    }

    public void setDateFin(Date fin) {
        this.dateFin = fin;
    }

    public float getSalaire() {
        return salaire;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Contrat(float montantSalaire, Date debut, Date fin)
    {
        setSalaire(montantSalaire);
        setDateDebut(debut);
        setDateFin(fin);
    }

    @Override
    public String toString() {
        DateFormat d = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE);
        return "Contrat{" +
                "\nSalaire='" + getSalaire() + '\'' +
                ", DateDebut='" + d.format(getDateDebut()) + '\'' +
                ", DateFin='" + d.format(getDateFin()) + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrat contrat = (Contrat) o;
        return Float.compare(contrat.salaire, salaire) == 0 && Objects.equals(dateDebut, contrat.dateDebut) && Objects.equals(dateFin, contrat.dateFin);
    }

    public static void main(String Args[])
    {
        Contrat monContrat = new Contrat(2600, new Date(2023, 4, 23), new Date(2023, 6, 30));
        System.out.println(monContrat.toString());

        Contrat monContrat2 = monContrat;
        System.out.println(monContrat2.equals(monContrat));

        Contrat monContrat3 = new Contrat(2600, new Date(2023, 4, 23), new Date(2023, 6, 30));
        System.out.println(monContrat3.equals(monContrat));

        Contrat monContrat4 = new Contrat(2100, new Date(2023, 1, 1), new Date(2023, 2, 10));
        System.out.println(monContrat4.equals(monContrat));
    }
}
