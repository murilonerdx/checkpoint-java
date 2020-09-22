import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Tarefa {
    private int codigoTarefa;
    private int codigoProjeto;
    private String nomeTarefa;
    private LocalDate dataInicial;
    private LocalDate dataEntrega;
    private String status;

    public Tarefa(int codigoProjeto, String nomeTarefa, LocalDate dataInicial, LocalDate dataEntrega, String status) {
        this.codigoProjeto = codigoProjeto;
        this.dataInicial = dataInicial;
        this.dataEntrega = dataEntrega;
        this.nomeTarefa = nomeTarefa;
    }

    public Tarefa() {

    }

    Conecta conecta = new Conecta("USER", "PASS", "jdbc:{SERVER}}:{PORT}:{SERVER}",
            "oracle.jdbc.driver.OracleDriver");

    /**
     * @return LocalDate
     */
    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    /**
     * @return LocalDate
     */
    public LocalDate getDataInicial() {
        return dataInicial;
    }

    /**
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return Integer
     */
    public Integer getCodigoProjeto() {
        return codigoProjeto;
    }

    /**
     * @return int
     */
    public int getCodigoTarefa() {
        return codigoTarefa;
    }

    /**
     * @return String
     */
    public String getNomeTarefa() {
        return nomeTarefa;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    Scanner scan = new Scanner(System.in);

    /**
     * @param nomeTarefa
     * @param statusTarefa
     * @param dataInicial
     * @param dataFinal
     */

    /**
     * @param nomeTarefa
     */
    public void remover(String nomeTarefa) {
        conecta.delete("T_SIP_PROJETOS", String.format("CD_PROJETOS = '%s'", nomeTarefa));
    }

    public void consulta() throws SQLException {

        ResultSet rs = conecta.select("T_SG_EQUIPE", null);
        System.out.println("TAREFAS CADASTRADAS");
        while (rs.next()) {
            String nomeTarefa = rs.getString("NM_TAREFA");
            Date dataEntrega = rs.getDate("DT_ENTREGA");
            Date dataInicio = rs.getDate("DT_INICIO");
            String statusTarefa = rs.getString("ST_STATUS");

            Date dataAtual = dataInicio;
            Date dataFim = dataEntrega;

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dataAtualFormatada = dateFormat.format(dataAtual);
            String dataEntregaFormatada = dateFormat.format(dataFim);

            System.out.printf(
                    "NOME DA TAREFA %d | STATUS DA TAREFA %s | DATA INICIO %s | DATA DE ENTREGA %S |  STATUS %s\n",
                    nomeTarefa, dataAtualFormatada, dataEntregaFormatada, statusTarefa);
            System.out.println("__________________________________________________");
        }
    }
}
