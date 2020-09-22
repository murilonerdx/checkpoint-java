public class Chat {
    private Integer id;
    private String nomeSala;
    private Integer matricula;
    private String mensagem;

    public Chat(){

    }

    
    /** 
     * @return Integer
     */
    public Integer getId() {
        return id;
    }
    
    /** 
     * @return Integer
     */
    public Integer getMatricula() {
        return matricula;
    }
    
    /** 
     * @return String
     */
    public String getMensagem() {
        return mensagem;
    }
    
    /** 
     * @return String
     */
    public String getNomeSala() {
        return nomeSala;
    }
    
    /** 
     * @param mensagem
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    
}
