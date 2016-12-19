package errores;

import lexico.JLex.UnidadLexica;

public class GestionErroresAnalisis {
	
	public static GestionErroresAnalisis getInstance(){
		if (instance == null)
			instance = new GestionErroresAnalisis();
		return instance;
	}
	
   public void errorLexico(int fila, String lexema) {
     System.out.println("ERROR fila "+ fila +": Carácter inesperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.print("ERROR fila "+ unidadLexica.fila()+": Elemento inesperado "+unidadLexica.value);
     System.exit(1);
   }
   
   public void errorTipos(String msg, int fila){
		System.err.println("Error en fase de comprobación de tipos (fila " + fila +"): " + msg);
	}
   
   public void errorIdentificacion(String msg, int fila){
		System.err.println("Error en fase de identificación " + "de identificadores (fila " + fila +"): " + msg);
	}
   
   private static GestionErroresAnalisis instance;
}
