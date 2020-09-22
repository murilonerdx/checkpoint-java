import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Conexao.Propriedade;

public class Equipe {
    private String nomeEquipe;
    private String codigoEquipe;

    public Equipe() {

    }

    Conecta conecta = new Conecta("USER", "PASS", "jdbc:{SERVER}}:{PORT}:{SERVER}",
            "oracle.jdbc.driver.OracleDriver");

            
    Scanner scan = new Scanner(System.in);
    ArrayList<String> participantesEquipe = new ArrayList<>();
    ArrayList<String> codigoMatricula = new ArrayList<>();

    public ArrayList<String> getParticipantesEquipe() {
        return participantesEquipe;
    }

    /**
     * @return String
     */
    public String getCodigoEquipe() {
        return codigoEquipe;
    }

    /**
     * @return String
     */
    public String getNomeEquipe() {
        return nomeEquipe;
    }

    /**
     * @return Integer
     */

    public void criarEquipe(String nomeEquipe, String codeMatricula) {

        System.out.println("Adicionar participante: 1 - SIM | 2 - NÃO");
        int opcao = scan.nextInt();
        int a = 0;
        if (opcao == 1) {
            while (a < 1) {
                System.out.println("Digite o codigo da matricula do participante: ");
                String cdMatricula = scan.next();
                System.out.println("Digite o email do participante");
                String emailParticipante = scan.nextLine();
                participantesEquipe.add(emailParticipante.toUpperCase());
                codigoMatricula.add(cdMatricula);
                int posicao = participantesEquipe.size();
                posicao += 1;
                Propriedade[] propriedades1 = { 
                                new Propriedade("number", "seq_crescente.NEXTVAL"),
                                new Propriedade("number", "seq_crescente.NEXTVAL"),
                                new Propriedade("string", nomeEquipe.toUpperCase()),
                                new Propriedade("string", participantesEquipe.get(posicao)) };
                conecta.insert("T_SG_EQUIPE", propriedades1);

                System.out.println("Adicionar mais participantes ?: 1 - SIM | 2 - NÃO");
                int opcao2 = scan.nextInt();
                if (opcao2 == 1) {
                    for (int e = 0; e < participantesEquipe.size(); e++) {
                        Propriedade[] propriedades2 = { new Propriedade("number", "seq_crescente.NEXTVAL"),new Propriedade("number", "seq_crescente.NEXTVAL"),new Propriedade("string", nomeEquipe.toUpperCase()),new Propriedade("string", participantesEquipe.get(e)) };
                        conecta.insert("T_SG_EQUIPE", propriedades2);
                        System.out.println("Equipes adicicionada");
                    }
                    a += 1; 
                }
                else if (opcao2 == 2) {
                    a += 1;
                    break;
                }
            }
        }else if(opcao > 2 || opcao < 0){
            System.out.println("Opção não identificada");
        }
    }

    public void removerEquipe(String rmvEquipe) throws SQLException {
        String nameEquipe = rmvEquipe.toUpperCase();
        ResultSet result = conecta.select("FROM T_SG_EQUIPE", String.format("NM_EQUIPE = '%s'", nameEquipe));

        if (result.next()) {
            conecta.delete("T_SG_EQUIPE", String.format("NM_EQUIPE = 'nameEquipe'", nameEquipe));
        } else {
            System.out.println("Não existe nenhuma equipe com esse nome");
        }
    }

    public void listarEquipe() throws SQLException {
        ResultSet rs = conecta.select("T_SG_EQUIPE", null);
        System.out.println("EQUIPES CADASTRADAS");
        while (rs.next()) {
            String nomeEquipe = rs.getString("NM_EQUIPE");
            System.out.printf("%s", nomeEquipe);
        }

    }
}
