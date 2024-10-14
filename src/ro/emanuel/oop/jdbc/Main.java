package ro.emanuel.oop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {

        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oop2024", connectionProps);

        Statement stmt = conn.createStatement();


        createAnimal(stmt, "Tom", "Pisică", 2);

        readAnimals(stmt);

        updateAnimal(stmt, 1, 3);  

        deleteAnimal(stmt, 2);  

        createReteta(stmt, "Salată Grecească", 15, "Roșii, brânză feta, măsline");
        readRetete(stmt);
        updateReteta(stmt, 1, 20);  
        deleteReteta(stmt, 1);  

        conn.close();
    }

   
    public static void createAnimal(Statement stmt, String nume, String specie, int varsta) throws SQLException {
        String sql = "INSERT INTO Animale (nume_animal, specie, varsta) " +
                     "VALUES ('" + nume + "', '" + specie + "', " + varsta + ")";
        stmt.executeUpdate(sql);
        System.out.println("Animal inserat cu succes!");
    }


    public static void readAnimals(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM Animale");
        while (rs.next()) {
            int id = rs.getInt("id_animal");
            String nume = rs.getString("nume_animal");
            String specie = rs.getString("specie");
            int varsta = rs.getInt("varsta");
            System.out.println(id + " | " + nume + " | " + specie + " | " + varsta);
        }
    }

    public static void updateAnimal(Statement stmt, int id_animal, int varstaNoua) throws SQLException {
        String sql = "UPDATE Animale SET varsta = " + varstaNoua + " WHERE id_animal = " + id_animal;
        stmt.executeUpdate(sql);
        System.out.println("Animalul cu id " + id_animal + " a fost actualizat!");
    }

    public static void deleteAnimal(Statement stmt, int id_animal) throws SQLException {
        String sql = "DELETE FROM Animale WHERE id_animal = " + id_animal;
        stmt.executeUpdate(sql);
        System.out.println("Animalul cu id " + id_animal + " a fost șters!");
    }

  

    public static void createReteta(Statement stmt, String numeReteta, int durata, String ingrediente) throws SQLException {
        String sql = "INSERT INTO Retete_Culinare (nume_reteta, durata_preparare, ingrediente_principale) " +
                     "VALUES ('" + numeReteta + "', " + durata + ", '" + ingrediente + "')";
        stmt.executeUpdate(sql);
        System.out.println("Rețetă inserată cu succes!");
    }

    public static void readRetete(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM Retete_Culinare");
        while (rs.next()) {
            int id = rs.getInt("id_reteta");
            String nume = rs.getString("nume_reteta");
            int durata = rs.getInt("durata_preparare");
            String ingrediente = rs.getString("ingrediente_principale");
            System.out.println(id + " | " + nume + " | " + durata + " min | " + ingrediente);
        }
    }

    public static void updateReteta(Statement stmt, int id_reteta, int durataNoua) throws SQLException {
        String sql = "UPDATE Retete_Culinare SET durata_preparare = " + durataNoua + " WHERE id_reteta = " + id_reteta;
        stmt.executeUpdate(sql);
        System.out.println("Rețeta cu id " + id_reteta + " a fost actualizată!");
    }

    public static void deleteReteta(Statement stmt, int id_reteta) throws SQLException {
        String sql = "DELETE FROM Retete_Culinare WHERE id_reteta = " + id_reteta;
        stmt.executeUpdate(sql);
        System.out.println("Rețeta cu id " + id_reteta + " a fost ștearsă!");
    }
}
