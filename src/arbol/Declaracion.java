package arbol;

import java.util.ArrayList;

import tipos.Clases;
import tipos.Tipo;
import identificadores.*;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;
import errores.ErrorDispatcher;
import errores.GestionErroresAnalisis;


//Incluye declaración de funciones, de arrays, de variables simples...

public class Declaracion extends Sentencia implements Symbol{
		
		public Declaracion(){
			isDeclaracion = true;
		}
		
		public Declaracion (String id, Tipo tipo){
			this.id = id;
			this.tipo = tipo;
			isDeclaracion = true;
		}
		
	
		public Tipo getTipo(){
			return tipo;
		}
		public String getId(){
			return id;
		}

		
	
		public void code(Entorno env, CodeWriter writer) throws ExcepcionWriter {
			boolean ref = false;
			if (arg && !tipo.clase().equals(Clases.Basico))
				ref = true;
			env.asignar(id, tipo.tam(), ref);
		}

		
		public void aceptaVisitante(Visitor v) {
			v.visitSymbol(this);
			try {
				tipo.aceptaVisitante(v);
			} catch (ExcepcionIdentificador e) {
				GestionErroresAnalisis.getInstance().errorIdentificacion("El tipo complejo no está declarado.", getFila());
			}
			
		}
		
		
		//Permite tratar de manera especial la declaración de un argumento de función.
		public void setIsArgument(boolean b){
			this.arg = b;
		}
		
		
		public String getIdentificador() {
			return id;
		}
		
		@Override
		public Tipo compilaTipo() {
			return tipo;
		}
		
		
		public ArrayList<Declaracion> elementos() {
			//Necesario por herencia, a este nivel no hace nada.
			return new ArrayList<Declaracion>();
		}
		
		@Override
		public void resuelveTipo() {
			
		}
		
		@Override
		public int evStackLength() {
			return tipo.tam();
		}
		
		
		 String id;
		 Tipo tipo;
		private boolean arg = false;

		


}
