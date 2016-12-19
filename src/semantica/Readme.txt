Compilación típica de una especificación CUP:


	java 	–cp cup.jar java_cup.Main –parser <nombre del parser>		 
			-symbols <nombre de la clase con las constantes para las clases léxicas>
			-nopositions <archivo cup>

		(-nopositions inhibe el enlazado de los símbolos en la pila de análisis, ya que esta 
		característica no está bien soportada por la versión actual de CUP, al introducir 
		dependencias con una implementación concreta de Symbol). 
		
		Así mismo, la opción –dump_states permite volcar una versión legible de los estados del 
		autómata LALR(1) (útil para depurar conflictos en la gramática).
		
En este caso concreto:

	java -cp cup.jar java_cup.Main -parser AnalizadorSintactico -symbols ClaseLexica -nopositions sintaxis.cup

funcino d{
	int m; 
}