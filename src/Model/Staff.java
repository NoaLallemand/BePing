package Model;

import java.util.Date;
import java.util.Objects;

public class Staff extends Personne
{
    private String role;

    public Staff(String numRegNat, String nom, String prenom, Date dateNais, String adresse, String sexe, String role)
    {
        super(numRegNat, nom, prenom, dateNais, adresse, sexe);
        setRole(role);
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "Staff {\n" +
                super.toString() +
                "\nRole='" + getRole() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Staff staff = (Staff) o;
        return role.equals(staff.role);
    }
}

