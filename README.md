# Projeto Java





Projeto Java Checkpoint.

  - DDL Incluisa
  - Algumas classes e metodos foram retirados pois contem informações do banco, onde não incluimos no modo generico da conexão.
  - Fase de teste, pode haver alguns erros, pois não foi 100% testada sem os metodos e atribuições no qual foram retiradas.
  - A classe principal está vazia, para testes.
  - Lembresse é para estudos apenas.

# Versão anterior

  - Versão anterior do arquivo não contém a conexão generica, onde podemos setar valores, instanciando a propria classe
  - Banco de dados, conexões com chaves primarias possuem dependencias FK unica e PK unica.


Qual uso ?:
  - Apenas para estudos, e desenvolvimento, para integração com Servlet ou até mesmo Spring Boot
  - Report de erro
  - Integração, e uso de conexão em classe generica

### Como usar
Precisa instanciar as classes no arquivo principal, e chamar os metodos, porém precisa configurar a classe generica do banco.

Configurar a conexão com Oracle SQL 
- 
- Instanciando o Conecta - Conecta conecta = new Conecta("USER", "PASS", "jdbc:{HOST}:{PORT}:{SID}","oracle.jdbc.driver.OracleDriver");
```sh
public Conecta(String nameConecta, String passConecta, String conection, String driverOracle) {

        this.nameConecta = nameConecta;
        this.passConecta = passConecta;
        this.conection = conection;
        this.driverOracle = driverOracle;
    }.
```

Ao instanciar você pode usar as configurações já estabelecidas na classe Conecta. 
-

Conecta - EXISTS
-


Exists tem como parametro table e condition, que é percorrer a table com uma condição
exists(String table, String condition) -> exists(T_SG_USUARIOS, "WHERE NM_USUARIO = USER")
Caso ele encontre ele retorna o ResultSet, 1 ou False, no caso 1 True armazenando ele em uma variavel e depois chamando

```sh
Conecta conecta = new Conecta("USER", "PASS", "jdbc:{HOST}:{PORT}:{SID}","oracle.jdbc.driver.OracleDriver");

public void criarNovoEvento(String nomeEvento, int codigoTarefa, LocalDate dataEvento, String descricaoEvento) { 
    Boolean existe = conecta.exists("T_SG_CALENDARIO",String.format("NM_EVENTO = '%s'  AND TO_DATE('%s', 'yyyy-mm-dd') AND CD_TAREFA = %s",nomeEvento.toUpperCase(), dataEvento.toString(), codigoTarefa));
    if (existe) {
        System.out.println("Já existe um Evento com o mesmo nome e na mesma data!");
    }
    else {
        ...
        }
    }
```

Conecta - SELECT
-

Select tem como parametro table e condition, que é percorrer a table com uma condição
select(T_SG_USUARIOS, "WHERE NM_USUARIO = USER")
Porém diferente do EXISTS, ele volta ResultSet no qual você pode tratar igual ao exemplo abaixo, podendo percorrer variaveis com RS.NEXT(), ele salva RS e você consegue percorrer e pegar nomes especificos no caso do exemplo abaixo NM_EQUIPE armazenando em uma variavel


```sh
Conecta conecta = new Conecta("USER", "PASS", "jdbc:{HOST}:{PORT}:{SID}","oracle.jdbc.driver.OracleDriver");

public void listarEquipe() throws SQLException {
        ResultSet rs = conecta.select("T_SG_EQUIPE", null);
        System.out.println("EQUIPES CADASTRADAS");
        while (rs.next()) {
            String nomeEquipe = rs.getString("NM_EQUIPE");
            System.out.printf("%s", nomeEquipe);
        }
    }
```

Conecta - INSERT
-

Insert tem como parametro table e propriedades, existe uma classe chamada Propriedade, aonde você seta valores quantos quiser ao fazer INSERT VALUES na tabela. 
conecta.insert("T_SG_CARGO", propriedades);
No insert você precisa instanciar Propriedade, e nomear de acordo com a tabela, e de acordo com a ordem da tabela, para numero ultiliza "number", para string ultiliza "string", "date" para datas, bem intuitivo, as propriedades são importantes, pois dependendo da quantidade de valores na tabela, você vai adicionando na ordem, e de acordo com a atribuição. E no fim ultiliza CONECTA.INSERT("T_SG_CARGO", propriedades), tabela, e suas propriedades, só funcionada para metodos explicitos.


```sh
Conecta conecta = new Conecta("USER", "PASS", "jdbc:{HOST}:{PORT}:{SID}","oracle.jdbc.driver.OracleDriver");

public void criarCargo(String cargo) {
        Propriedade[] propriedades = { 
            new Propriedade("number", "seq_crescente.NEXTVAL"),
            new Propriedade("string", cargo.toUpperCase())};
            conecta.insert("T_SG_CARGO", propriedades);
    }
```

Conecta - DELETE
-

Delete tem como parametro table e condition, a tabela, e qual a condição para deletar lembresse a condição pode ser de um ou mais informações e ordem, basta colocar o comando de table, dentro de condition, como padrão ele utiliza WHERE -> condition, então é necessario colocar WHERE, apenas a condição EX: conecta.delete("T_SIP_PROJETOS","CD_PROJETOS = 924"));


```sh
Conecta conecta = new Conecta("USER", "PASS", "jdbc:{HOST}:{PORT}:{SID}","oracle.jdbc.driver.OracleDriver");

public void remover(String nomeTarefa) {
        conecta.delete("T_SIP_PROJETOS", String.format("CD_PROJETOS = '%s'", nomeTarefa));
    }
```




