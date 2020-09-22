public enum StatusEnum {
	EMPROGRESSO("EM PROGRESSO"), 
    AFAZER("A FAZER"), 
    PARADO("PARADO"), 
    FEITO("FEITO"); 

	private String novoStatus;

	StatusEnum(String novoStatus){
		this.novoStatus = novoStatus;
	}

	public String getNovoStatus()
	{
		return novoStatus;
	}

	public static String FEITO() {
		return FEITO();
	}
}


