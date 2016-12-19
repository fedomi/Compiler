package codigo;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
/* Gestiona la escritura en el txt del código de la máquina P. Cuenta con un buffer 
 * en el que se almacenan intrucciones a la espera de que se resuelvan referencias.
 */
public class CodeWriter {
	public CodeWriter(Writer writer) {
		this.writer = writer;
	}
	
	/*
	 Escribe el String si no hay ninguna intrucción en cola.
	 */
	public void write (String inst) throws ExcepcionWriter{
		pos++;
		if (buffer.size() > 0)
			buffer.add(new InstWriter(inst));
		else
			try {
				writer.write(inst + ";" + System.lineSeparator());
			} catch (IOException e) {
				throw new ExcepcionWriter("Error al escribir la instrucción: " + inst, e);
			}
	}
	
	/*Escribe la intrucción si está resuelta su referencia y
	 *  no hay cola de instrucciones esperando*/
	
	public void write (InstWriter inst) throws ExcepcionWriter{
		pos++;
		if (buffer.size() > 0 || !inst.ready())
			buffer.add(inst);
		else
			try {
				writer.write(inst.compilaInstruccion() + ";n" + System.lineSeparator());
				pos++;
			} catch (IOException e) {
				throw new ExcepcionWriter("Error al escribir la instrucción: " + inst.compilaInstruccion(), e);
			}
	}
	
	//Crea una etiqueta para marcarla
	public Etiqueta reservaEtiqueta (){
		return new Etiqueta();
	}
	
	// Apunta la posición actual del writer para poder hacer referencias, 
	// como al usarse suele resolver referencias se llama a flush para que 
	// vacíe el buffer
	public void marca (Etiqueta etiqueta) throws ExcepcionWriter{
		etiqueta.value(pos);
		flush();
	}
	
	//Escribe todas las instrucciones que estén en el buffer 
	// hasta que encuentra una que tiene una referencia no resuelta 
	// o lo vacía.
	private void flush () throws ExcepcionWriter{
		
		while (!buffer.isEmpty() && buffer.peek().ready())
			try {
				writer.write(buffer.pop().compilaInstruccion() + ";" + System.lineSeparator());
				pos++;
			} catch (IOException e) {
				throw new ExcepcionWriter("Error al escribir instrucción del buffer", e);
			}
	}
	
	/* Nos permite conocer la posición actual del writer
	 */
	public int pos(){
		return pos;
	}
	
	
	
	private int pos = 0;
	private LinkedList<InstWriter> buffer = new LinkedList<>();
	private Writer writer;
	
	public class Etiqueta {
		protected Etiqueta () {}
		
		protected void value (int value){
			this.value = value;
		}
		public int value (){
			return this.value;
		}
		public boolean isSet (){
			return value == -1 ? false : true;
		}
		private int value = -1;
	}

}
