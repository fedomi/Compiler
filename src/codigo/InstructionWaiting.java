package codigo;
import codigo.CodeWriter.Etiqueta;

//Instrucci�n almacenada para la resoluci�n de sus referencias

public class InstructionWaiting extends InstWriter {

	public InstructionWaiting(String instruccion, Etiqueta etiqueta) {
		super(instruccion);
		this.etiqueta = etiqueta;
	}
	
	//Nos indica si se ha resuelto su referencia.
	
	@Override
	public boolean ready() {
		return etiqueta.isSet();
	}
	
	//Construye la instrucci�n 
	
	@Override
	public String compilaInstruccion() {
		return this.instruccion + " " + etiqueta.value();
	}
	
	private Etiqueta etiqueta;
}
