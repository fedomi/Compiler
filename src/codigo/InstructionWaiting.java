package codigo;
import codigo.CodeWriter.Etiqueta;

//Instrucción almacenada para la resolución de sus referencias

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
	
	//Construye la instrucción 
	
	@Override
	public String compilaInstruccion() {
		return this.instruccion + " " + etiqueta.value();
	}
	
	private Etiqueta etiqueta;
}
