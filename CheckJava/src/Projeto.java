import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Projeto {
    private Tarefa tarefa;
    private int cdProjeto;
    private String nmProjeto;
    private Equipe equipe;

    public Projeto() {

    }

    Tarefa tf = new Tarefa();
    Medalha md = new Medalha();

    Scanner scan = new Scanner(System.in);
    Projeto projeto = new Projeto();
    Conecta conecta = new Conecta("USER", "PASS", "jdbc:{SERVER}}:{PORT}:{SERVER}",
            "oracle.jdbc.driver.OracleDriver");

    public Projeto(Tarefa tarefa, int cdProjeto, String nmProjeto, Equipe equipe) {
        this.tarefa = tarefa;
        this.cdProjeto = cdProjeto;
        this.nmProjeto = nmProjeto;
        this.equipe = equipe;
    }

    /**
     * @return int
     */
    public int getCdProjeto() {
        return cdProjeto;
    }

    /**
     * @return Equipe
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * @return Medalha
     */
    public Medalha getMd() {
        return md;
    }

    /**
     * @return String
     */
    public String getNmProjeto() {
        return nmProjeto;
    }

    /**
     * @return Tarefa
     */
    public Tarefa getTarefa() {
        return tarefa;
    }

    /**
     * @param equipe
     */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    /**
     * @param tarefa
     */
    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public void deletarProjeto() throws SQLException {

        System.out.println("Digite o nome do projeto: ");
        String nomeProjeto = scan.nextLine();
        ResultSet result = conecta.select("T_SG_PROJETO", String.format("NM_PROJETO = '%s'", nomeProjeto));
        if (!result.next()) {
            System.out.println("Não existe nenhum projeto com esse nome!! ");
        } else if (result.next()) {
            conecta.delete("T_SG_PROJETO", String.format("NM_PROJETO = 'nomeProjeto'", nomeProjeto));
        }

    }
}
