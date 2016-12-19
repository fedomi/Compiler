package identificadores;

public class IdVisitor extends Visitor {
	
	//Visitante para los identificadores.
	
	private Symbol_table t;
	
	public IdVisitor (){
		t = new Symbol_table();
	}
	
	@Override
	public void enterBlock() {
		t.enterBlock();	
	}
	
	@Override
	public void exitBlock() {
		t.exitBlock();
	}
	
	//A�ade el s�mbolo a la tabla
	@Override
	public void visitSymbol(Symbol simbolo) {
		t.insert(simbolo.getIdentificador(), simbolo);	
	}
	
	//Busca el id en la tabla y devuelve el s�mbolo que le corresponde.
	@Override
	public Symbol visitId(String id) throws ExcepcionIdentificador{
		return t.searchId(id);
	}
	
}
