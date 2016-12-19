package main;

import java.io.*;

import lexico.JLex.UnidadLexica;

import arbol.Programa;

import semantica.AnalizadorLexico;
import semantica.AnalizadorSintactico;
import semantica.ClaseLexica;


//Falta importar.
public class Main { 
	public static void main(String[] args) throws Exception { 

		//Pruebas del léxico
		Reader input = new InputStreamReader(new FileInputStream("input.txt")); 
		AnalizadorLexico alex = new AnalizadorLexico(input);
		UnidadLexica unidad;
		do{
			unidad = (UnidadLexica) alex.next_token();
			System.out.println(unidad.toString());
		}while(unidad.clase() != ClaseLexica.EOF);
		input.close();
		//Pruebas semántica.
		Reader input2 = new InputStreamReader(new FileInputStream("input.txt")); 
		AnalizadorLexico alex2 = new AnalizadorLexico(input2);
		AnalizadorSintactico asint = new AnalizadorSintactico(alex2); 
		asint.setScanner(alex2);
		Programa p = (Programa) asint.parse().value;

		Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("codigo.txt")));;

		p.visitaSimbolos();
		p.resuelveTipo();
		p.compila(w);
		w.close();
	} 
} 
