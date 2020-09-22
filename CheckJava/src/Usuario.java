public class Usuario {
    private int codigoMatricula;
    private String nomeUsuario;
    private String cpf;
    private String email;
    private String telefone;
    private Cargo cargo;



    public Usuario(Integer codigoMatricula, String nomeUsuario, String cpf, String email, String telefone){  
        this.codigoMatricula = codigoMatricula;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.cpf = cpf; 
        this.telefone = telefone;
    }

    public Usuario(Integer codigoMatricula, String nomeUsuario, String cpf, String email, String telefone, Cargo cargo){
        this.codigoMatricula = codigoMatricula;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.cpf = cpf; 
        this.telefone = telefone;
        this.cargo = cargo; 
    } 

    
    
    /** 
     * @return Cargo
     */
    public Cargo getCargo() {
        return cargo;
    }
    
    /** 
     * @return int
     */
    public int getCodigoMatricula() {
        return codigoMatricula;
    }
    
    /** 
     * @return String
     */
    public String getCpf() {
        return cpf;
    }
    
    /** 
     * @return String
     */
    public String getEmail() {
        return email;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public String getTelefone() {
        return telefone;
    }
    
    /** 
     * @param cargo
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    /** 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /** 
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
   
}
