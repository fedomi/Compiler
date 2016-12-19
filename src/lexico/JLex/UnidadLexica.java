package lexico.JLex;
import java_cup.runtime.Symbol;



public class UnidadLexica extends Symbol{
	  
  public UnidadLexica(int fila, int clase, String lexema) {
     super(clase, lexema);  
     this.fila = fila;
   }
  public int fila(){
	return fila;
}

   public int clase () {
		return super.sym;
	}
   
   public String lexema(){
		return (String) super.value;
   }

   public String toString() {
    return "[clase:"+clase()+",fila:"+fila()+",lexema:"+lexema()+"]";  
  }
   private int fila;
}
   
