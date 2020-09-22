import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexao.Propriedade;

public class Conecta { 
    String nameConecta;
    String passConecta;
    String conection;
    String driverOracle;

    public Conecta(String nameConecta, String passConecta, String conection, String driverOracle) {

        this.nameConecta = nameConecta;
        this.passConecta = passConecta;
        this.conection = conection;
        this.driverOracle = driverOracle;
    }

    public Boolean exists(String table, String condition) {
        try {
            ResultSet rs = select(table, condition);
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public ResultSet select(String table, String condition) {
        try {
            Class.forName(this.driverOracle);
            Connection con = DriverManager.getConnection(this.conection, this.nameConecta, this.passConecta);

            Statement stmt = con.createStatement();
            if (condition != null && condition != "") {
                ResultSet rsConditional = stmt
                        .executeQuery(String.format("SELECT * FROM %s WHERE %s", table, condition));
                con.close();
                return rsConditional;
            }

            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s", table));
            con.close();
            return rs;

        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void insert(String table, Propriedade[] propriedades) {
        try {
            Class.forName(this.driverOracle);
            Connection con = DriverManager.getConnection(this.conection, this.nameConecta, this.passConecta);

            Statement stmt = con.createStatement();
            String query = String.format("INSERT INTO %s VALUES(", table);
            for (int i = 0; i < propriedades.length; i++) {
                switch (propriedades[i].getTipo()) {
                    case "string":
                        query += String.format("'%s'", propriedades[i].getValor());
                        break;
                    case "number":
                        query += String.format("%s", propriedades[i].getValor());
                        break;
                    case "date":
                        query += String.format("TO_DATE('%s','yyyy-mm-dd')", propriedades[i].getValor());
                        break;
                }

                if (i + 1 < propriedades.length)
                    query += ",";

            }
            query += ")";
            stmt.executeUpdate(query);
            System.out.println("Adicionado com sucesso");

            con.close();
        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }

    }

    public void delete(String table, String condition){
        try {
            Class.forName(this.driverOracle);
            Connection con = DriverManager.getConnection(this.conection, this.nameConecta, this.passConecta);

            Statement stmt = con.createStatement();
            String query = String.format("DELETE FROM  %s WHERE (%s)", table, condition);
            stmt.executeUpdate(query);
            System.out.println("Deletado com sucesso");

            con.close();
        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
    }


    public void consulta(String table, String condition){
        try {
            Class.forName(this.driverOracle);
            Connection con = DriverManager.getConnection(this.conection, this.nameConecta, this.passConecta);

            Statement stmt = con.createStatement();
            String query = String.format("DELETE FROM  %s WHERE (%s)", table, condition);
            stmt.executeUpdate(query);
            System.out.println("Deletado com sucesso");

            con.close();
        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
    }
}
