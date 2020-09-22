import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Conexao.Propriedade;

public class Medalha {
    private Integer cdMedalha;
    private String dsMedalha;
    private String tipoMedalha;
    private Equipe equipe;

    public Medalha() {

    }

    /**
     * @return Integer
     */
    public Integer getCdMedalha() {
        return cdMedalha;
    }

    /**
     * @return String
     */
    public String getDsMedalha() {
        return dsMedalha;
    }

    /**
     * @return String
     */
    public String getTipoMedalha() {
        return tipoMedalha;
    }

    /**
     * @return Equipe
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * @param tipoMedalha
     */
    public void setTipoMedalha(String tipoMedalha) {
        this.tipoMedalha = tipoMedalha;
    }

    /**
     * @param codigoMatricula
     * @param nomeColaborador
     * @param email
     * @param cargo
     * @param medalhaTipo
     */

    ArrayList<Usuario> medalhas = new ArrayList<Usuario>();

    /**
     * @param nomeColaborador
     * @param email
     * @param cargo
     * @param medalhaTipo
     */

    Conecta conecta = new Conecta("USER", "PASS", "jdbc:{SERVER}}:{PORT}:{SERVER}",
            "oracle.jdbc.driver.OracleDriver");
    Equipe eq = new Equipe();
    ArrayList<String> qtdAtual = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    
    public void adicionarMedalha(String codProjeto) throws SQLException {
        ResultSet result = conecta.select("T_SG_PROJETO", String.format("CD_PROJETO =  %s", codProjeto));
        System.out.println("Você quer dar medalha individual ou para equipe toda ? | 1 - INVIDIDUAL ## 2 - EM EQUIPE");
        int opcao = scan.nextInt();
        if (result.next()) {
            if (opcao == 1) {
                ResultSet result2 = conecta.select("T_SG_PROJETO", String.format("CD_PROJETO =  %s", codProjeto));
                while (result2.next()) {
                    String nomeEquipe = result2.getString("NM_EQUIPE");
                    qtdAtual.add(nomeEquipe);
                    for (int f = 0; f < eq.getParticipantesEquipe().size(); f++) {
                        Propriedade[] propriedades = { new Propriedade("number", "seq_crescente.NEXTVAL"),
                                new Propriedade("string", eq.codigoMatricula.get(f)),
                                new Propriedade("number", codProjeto), new Propriedade("number", "" + 1) };
                        conecta.insert("T_SG_MEDALHA", propriedades);

                    }
                }
            } else if (opcao == 2) {
                ResultSet result2 = conecta.select("T_SG_PROJETO", String.format("CD_PROJETO =  %s", codProjeto));
                while (result2.next()) {
                    String nomeEquipe = result2.getString("NM_EQUIPE");
                    qtdAtual.add(nomeEquipe);
                    for (int f = 0; f < eq.getParticipantesEquipe().size(); f++) {
                        Propriedade[] propriedades = { new Propriedade("number", "seq_crescente.NEXTVAL"),
                                new Propriedade("string", eq.codigoMatricula.get(f)),
                                new Propriedade("number", codProjeto), new Propriedade("number", "" + 2) };
                        conecta.insert("T_SG_MEDALHA", propriedades);

                    }
                }
            }
        } else {
            System.out.println("Não existe codigo do projeto");
        }
    }

    /**
     * @param nome
     * @throws SQLException
     */
    public void listarMedalha(String codigoMatricula) throws SQLException {
        ResultSet rs = conecta.select("T_SG_EQUIPE", null);
        System.out.println("EQUIPES CADASTRADAS");
        while (rs.next()) {
            String cdMedalha = rs.getString("CD_MEDALHA");
            int cdProjeto = rs.getInt("CD_PROJETO");
            int tipoMedalha = rs.getInt("TIPO_MEDALHA");
            System.out.println("_______________________________");
            if (tipoMedalha == 1) {
                String newValueMedalha = "Individual";
                System.out.printf("CODICO DA MEDALHA: %s | CODIGO DO PROJETO: %d | TIPO DE MEDALHA : %s \n", cdMedalha,
                        cdProjeto, newValueMedalha);
            } else if (tipoMedalha == 2) {
                String newValueMedalha = "EM EQUIPE";
                System.out.printf(
                        "CODICO DA MEDALHA: %s | CODIGO DO PROJETO: %d | TIPO DE MEDALHA : %d medalha de %s\n",
                        cdMedalha, cdProjeto, newValueMedalha);
            }
        }
    }

}
