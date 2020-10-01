import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.Propriedades.Propriedade;
import dao.factory.Conecta;

public class Calendario {
    private int codigoEvento;
    private int codigoTarefa;
    private LocalDate dataEvento;
    private String nomeEvento;
    private String descricaoEvento;

    public Calendario() {

    }

    Conecta conecta = new Conecta("USER", "PASS", "jdbc:{SERVER}}:{PORT}:{SERVER}",
            "oracle.jdbc.driver.OracleDriver");

    public Calendario(int codigoEvento, int codigoTarefa, LocalDate dataEvento, String nomeEvento, String descrString) {
        this.codigoEvento = codigoEvento;
        this.codigoEvento = codigoTarefa;
        this.dataEvento = dataEvento;
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descrString;
    }

    /**
     * @return int
     */
    public int getCodigoEvento() {
        return codigoEvento;
    }

    /**
     * @return int
     */
    public int getCodigoTarefa() {
        return codigoTarefa;
    }

    /**
     * @return LocalDate
     */
    public LocalDate getDataEvento() {
        return dataEvento;
    }

    /**
     * @return String
     */
    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    /**
     * @return String
     */
    public String getNomeEvento() {
        return nomeEvento;
    }

    /**
     * @param dataEvento
     */
    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    /**
     * @param descricaoEvento
     */
    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    /**
     * @param nomeEvento
     */
    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    /**
     * @param codigoEvento
     */
    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    ArrayList<Integer> codigos = new ArrayList<>();

    /**
     * @param nomeEvento
     * @param codigoTarefa
     * @param dataEvento
     * @param descricaoEvento
     */

    public void criarNovoEvento(String nomeEvento, int codigoTarefa, LocalDate dataEvento, String descricaoEvento) { // ARRUMAR

        Boolean existe = conecta.exists("T_SG_CALENDARIO",
                String.format("NM_EVENTO = '%s'  AND TO_DATE('%s', 'yyyy-mm-dd') AND CD_TAREFA = %s",
                        nomeEvento.toUpperCase(), dataEvento.toString(), codigoTarefa));
        if (existe) {
            System.out.println("Já existe um Evento com o mesmo nome e na mesma data!");
        } else {
            Propriedade[] propriedades = { new Propriedade("number", "" + codigoTarefa),
                    new Propriedade("date", dataEvento.toString()), new Propriedade("string", nomeEvento),
                    new Propriedade("string", descricaoEvento) };

            conecta.insert("T_SG_CALENDARIO", propriedades);
            System.out.println("Tarefa adicionada");
        }
    }

    /**
     * @param tituloEvento
     * @param dataEvento
     * @param descricaoEvento
     */
    public void criarEventoParticular(String tituloEvento, LocalDate dataEvento, String descricaoEvento) {
        Propriedade[] propriedades = { new Propriedade("number", "seq_crescente.NEXTVAL"),
                new Propriedade("date", dataEvento.toString()),
                new Propriedade("string", descricaoEvento.toUpperCase()),
                new Propriedade("string", tituloEvento.toUpperCase()) };

        conecta.insert("T_SG_CALENDARIO", propriedades);
        System.out.println("Adicionado com sucesso");

    }

    /**
     * @param nomeEvento
     */
    public void deletarEventoParticular(String nomeEvento) {

        conecta.delete("T_SG_CALENDARIO", String.format("NM_EVENTO = '%s'", nomeEvento));

    }

    public void consultarEventoParticular() {
        try {
            ResultSet rs = conecta.select("T_SG_CALENDARIO", null);
            while (rs.next()) {
                int nmEvento = rs.getInt("NM_EVENTO");
                String dsEvento = rs.getString("DS_EVENTO");
                String dataEvento = rs.getString("DT_EVENTO");
                LocalDate dataEntrada = LocalDate.parse(dataEvento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.printf("NOME EVENTO %s, DESCRICAO EVENTO %s, DATA EVENTO %s\n", nmEvento, dsEvento,
                        dataEntrada);
            }

        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
    }

}
