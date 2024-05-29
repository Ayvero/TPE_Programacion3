package TPE;


	public class Processor {
	    private String id;
	    private String codigo;
	    private boolean refrigerado;
	    private int anioFuncionamiento;

	    public Processor(String id, String codigo, boolean refrigerado, int anioFuncionamiento) {
	        this.id = id;
	        this.codigo = codigo;
	        this.refrigerado = refrigerado;
	        this.anioFuncionamiento = anioFuncionamiento;
	    }

	    public String getId() {
	        return id;
	    }

	    public String getCodigo() {
	        return codigo;
	    }

	    public boolean isRefrigerado() {
	        return refrigerado;
	    }

	    public int getAnioFuncionamiento() {
	        return anioFuncionamiento;
	    }
	}


