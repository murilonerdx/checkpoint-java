import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Propriedades.Propriedade;
import dao.factory.Conecta;


public class Cargo {
    private int codigoCargo;
    private String nomeCargo;

    public Cargo(int codigoCargo, String nomeCargo) {
        this.codigoCargo = codigoCargo;
        this.nomeCargo = nomeCargo;
    }
    Conecta conecta = new Conecta("USER", "PASS", "jdbc:{SERVER}}:{PORT}:{SERVER}",
            "oracle.jdbc.driver.OracleDriver");
    
    /** 
     * @return int
     */
    public int getCodigoCargo() {
        return codigoCargo;
    }

    
    /** 
     * @return String
     */
    public String getNomeCargo() {
        return nomeCargo;
    }

    
    /** 
     * @param cargo
     */
    public void criarCargo(String cargo) {
        Propriedade[] propriedades = { 
            new Propriedade("number", "seq_crescente.NEXTVAL"),
            new Propriedade("string", cargo.toUpperCase())};
            conecta.insert("T_SG_CARGO", propriedades);
    }

    
    /**
     * @param cargo
     * @throws SQLException
     */
    public void deletarCargo(String cargo) throws SQLException {
            ResultSet resultado = conecta.select("T_SG_CARGO", String.format("NM_CARGO = '%s'", cargo.toUpperCase()));
            if (resultado.next()){
                conecta.delete("T_SG_CARGO", String.format("NM_CARGO = '%s'", cargo.toUpperCase()));
            }
            else{
                System.out.println("Não existe nenhum cargo com esse nome");
            }
    } 

    public void listarCargos() throws SQLException {
       
            ResultSet rs = conecta.select("FROM T_SG_CARGO", null);
            System.out.println("LISTA DE CARGOS"); 
            while (rs.next()) {
                String nomeCargo= rs.getString("NM_CARGO");
                System.out.printf("\n %s ", nomeCargo);
            }
        
    }
}
