package lexico.JLex;

import semantica.*;

public class ALexOperations {
	
  private AnalizadorLexico alex;
  public ALexOperations(AnalizadorLexico alex) {
   this.alex = alex;   
  }
  public UnidadLexica error(){	  
	  return new UnidadLexica(alex.fila(), ClaseLexica.error , "ERROR");
  }
  public UnidadLexica unidadId() {
     return new UnidadLexica(alex.fila(),ClaseLexica.ID,alex.lexema()); 
  } 
  public UnidadLexica unidadPrograma() {
     return new UnidadLexica(alex.fila(),ClaseLexica.PROGRAMA,"PROGRAMA"); 
  } 
  public UnidadLexica unidadBegin() {
     return new UnidadLexica(alex.fila(),ClaseLexica.BEGIN,"BEGIN"); 
  } 
  
  public UnidadLexica unidadReturn() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.RETURN,"RETURN"); 
	  } 
  
  public UnidadLexica unidadEnd() {
     return new UnidadLexica(alex.fila(),ClaseLexica.ENDPROGRAMA,"ENDPROGRAMA"); 
  } 
  public UnidadLexica unidadEntero() {
     return new UnidadLexica(alex.fila(),ClaseLexica.ENTERO,alex.lexema()); 
  } 

  public UnidadLexica unidadInteger() {
	  return new UnidadLexica(alex.fila(),ClaseLexica.INTEGER,"INTEGER"); 
  } 

  public UnidadLexica unidadSuma() {
     return new UnidadLexica(alex.fila(),ClaseLexica.OPERADORSUMA,"+"); 
  } 
  public UnidadLexica unidadResta() {
     return new UnidadLexica(alex.fila(),ClaseLexica.OPERADORRESTA,"-"); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexica(alex.fila(),ClaseLexica.OPERADORMULTIPLICACION,"*"); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(),ClaseLexica.OPERADORDIVISION,"/"); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),ClaseLexica.PARENTESISAPERTURA,"("); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),ClaseLexica.PARENTESISCIERRE,")"); 
  }
  public UnidadLexica unidadAngAp() {
     return new UnidadLexica(alex.fila(),ClaseLexica.ANGULOAPERTURA,"<"); 
  } 
  public UnidadLexica unidadAngCierre() {
     return new UnidadLexica(alex.fila(),ClaseLexica.ANGULOCIERRE,">"); 
  } 
  
  public UnidadLexica unidadBloqueAp() {
     return new UnidadLexica(alex.fila(),ClaseLexica.BLOQUEAPERTURA,"["); 
  } 
  public UnidadLexica unidadBloqueCierre() {
     return new UnidadLexica(alex.fila(),ClaseLexica.BLOQUECIERRE,"]"); 
  } 
  
  public UnidadLexica unidadCAp() {
     return new UnidadLexica(alex.fila(),ClaseLexica.CORCHETEAPERTURA,"{"); 
  } 
  public UnidadLexica unidadCCierre() {
     return new UnidadLexica(alex.fila(),ClaseLexica.CORCHETECIERRE,"}"); 
  }
 
  public UnidadLexica unidadIgual() {
     return new UnidadLexica(alex.fila(),ClaseLexica.IGUAL,"="); 
  }
  public UnidadLexica unidadIgualLogico() {
     return new UnidadLexica(alex.fila(),ClaseLexica.IGUALLOGICO,"=="); 
  } 
  public UnidadLexica unidadDistinto() {
     return new UnidadLexica(alex.fila(),ClaseLexica.DISTINTO,"!="); 
  }
  public UnidadLexica unidadMayor() {
     return new UnidadLexica(alex.fila(),ClaseLexica.MAYOR,"?>"); 
  }
  public UnidadLexica unidadMenor() {
     return new UnidadLexica(alex.fila(),ClaseLexica.MENOR,"?<"); 
  }

  public UnidadLexica unidadComa() {
     return new UnidadLexica(alex.fila(),ClaseLexica.COMA,","); 
  } 
  
  public UnidadLexica unidadPuntocoma() {
	     return new UnidadLexica(alex.fila(),ClaseLexica.PUNTOCOMA,";"); 
  }
  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),ClaseLexica.EOF,"<EOF>"); 
  }
public UnidadLexica unidadCase() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.CASE,"CASE"); 
}
public UnidadLexica unidadDefault() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.DEFAULT,"DEFAULT");  
}
public UnidadLexica unidadSwitch() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.SWITCH,"SWITCH"); 
}

public UnidadLexica unidadAnd() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.OPERADORAND,"AND"); 
}
public UnidadLexica unidadOr() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.OPERADOROR,"OR"); 
}

public UnidadLexica unidadSino() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.SINO,"SINO"); 
}
public UnidadLexica unidadSi() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.SI,"SI"); 
}

//Es la palabra reservada para el booleano
public UnidadLexica unidadBooleano() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.BOOLEANO,alex.lexema()); 
}
public UnidadLexica unidadFunc() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.FUNC,"FUNC"); 
}
public UnidadLexica unidadBoolean() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.BOOLEAN,"BOOLEAN"); 
}
public UnidadLexica unidadMientras() {
	 return new UnidadLexica(alex.fila(),ClaseLexica.MIENTRAS,"MIENTRAS"); 
}
/* public static final int OPERADORINCREMENTO = 20;
public static final int FLOAT = 21;
public static final int OPERADORRESTA = 22;
public static final int OPERADORMULTIPLICACION = 23;
public static final int OPERADORDIVISION = 24;
public static final int PARENTESISAPERTURA = 25;
public static final int PARENTESISCIERRE = 26;
public static final int PROCE = 27;
public static final int OPERADORDECREMENTO = 28;
public static final int OPERADORRESTAAS = 29;
public static final int OPERADORSUMAAS = 30;
public static final int OPERADORIGUAL = 31;
public static final int MAYOR = 55;
public static final int MENOR = 32;
public static final int OPERADORAND = 33;
public static final int OPERADOROR = 34;
public static final int CHAR = 500;
public static final int SINO = 35;
public static final int SI = 36;
public static final int REAL = 37;
public static final int BOOLEANO = 38;
public static final int BOOLEAN = 39;
public static final int MIENTRAS = 40;*/
}
