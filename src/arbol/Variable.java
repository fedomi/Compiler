package arbol;

import tipos.Clases;
import codigo.CodeWriter;
import codigo.Entorno;
import codigo.ExcepcionWriter;

public abstract class Variable extends Expresion{
	//Es una variable del arbol abstracto.
	//Lleva asociado un id.
	public abstract void codeL(Entorno env, CodeWriter writer) throws ExcepcionWriter;
	
	@Override
	public void codeR(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		this.codeL(env,writer);
		writer.write("ind");
	}
	
	@Override
	public void codeA(Entorno env, CodeWriter writer) throws ExcepcionWriter {
		if (this.getTipo().clase().equals(Clases.Basico))
			codeR(env, writer);
		else codeL(env, writer);
	}
	
	public String getId(){
		return id;
	}
	
	private String id;
}
