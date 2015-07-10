package br.com.becb.middlewarerecarga.suporte;

import java.util.List;

import br.com.becb.middlewarerecarga.entidades.Produto;

public class OrdenaConjunto {

	
	public List<Integer> ordenaInt(List<Integer> lista){
		
		Integer a;
		Integer b;
		for(int j=0; j<lista.size();j++){
			
			for(int i =0; i<lista.size(); i++){
				if(i+1 < lista.size()){
					a = lista.get(i);
					
					b = lista.get(i+1);
					
					if(b<a){
						lista.set(i, b);
						lista.set(i+1, a);
					}
				}
			}
			
		}
		
		
		return lista;		
	}
	
	public static List<Produto> ordenaProdutos(List<Produto> lista){
		
		Produto a;
		Produto b;
		for(int j=0; j<lista.size();j++){
			
			for(int i =0; i<lista.size(); i++){
				if(i+1 < lista.size()){
					a = lista.get(i);
					
					b = lista.get(i+1);
					
					if(b.getPrecoVendaProduto() < a.getPrecoVendaProduto()){
						lista.set(i, b);
						lista.set(i+1, a);
					}
				}
			}
			
		}
		
		
		return lista;		
	}
}
