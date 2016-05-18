package br.com.racarga.suporte;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.becb.middlewarerecarga.suporte.OrdenaConjunto;

public class TesteOrdena {

	@Test
	public void testOrdem(){
		
		
		OrdenaConjunto oc = new OrdenaConjunto();
		List<Integer> lista = new ArrayList();
		lista.add(new Integer(10));
		lista.add(new Integer(1));
		lista.add(new Integer(40));
		lista.add(new Integer(22));
		lista.add(new Integer(43));
		lista.add(new Integer(20));
		lista.add(new Integer(3));
		lista.add(new Integer(76));
		lista.add(new Integer(4));
		lista.add(new Integer(45));
		lista.add(new Integer(9));
		printLista(lista);
		System.out.println("########");
		
		printLista(oc.ordenaInt(lista));
		
	}
	private void printLista(List<Integer> lista){
		
		for(int i=0;i < lista.size();i++)
			System.out.println(lista.get(i));
		}
}
