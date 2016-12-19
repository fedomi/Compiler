Compilaci�n t�pica de una especificaci�n CUP:


	java 	�cp cup.jar java_cup.Main �parser <nombre del parser>		 
			-symbols <nombre de la clase con las constantes para las clases l�xicas>
			-nopositions <archivo cup>

		(-nopositions inhibe el enlazado de los s�mbolos en la pila de an�lisis, ya que esta 
		caracter�stica no est� bien soportada por la versi�n actual de CUP, al introducir 
		dependencias con una implementaci�n concreta de Symbol). 
		
		As� mismo, la opci�n �dump_states permite volcar una versi�n legible de los estados del 
		aut�mata LALR(1) (�til para depurar conflictos en la gram�tica).
		
En este caso concreto:

	java -cp cup.jar java_cup.Main -parser AnalizadorSintactico -symbols ClaseLexica -nopositions sintaxis.cup

funcino d{
	int m; 
}