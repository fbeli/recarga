package br.com.becb.middlewarerecarga.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;

@Repository("hDaodddProduto")
public class HibernateDDDProduto<T> extends HBDAO<T> {

	public HibernateDDDProduto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class getClazz() {
		return DDDProduto.class;
	}

	public DDDProduto salvar(DDDProduto dddProduto) throws ErroException {

		// DDDProduto dddProdutoQ = verificarDDDExistente(dddProduto);

		// if (dddProdutoQ == null || dddProduto.getDdd() !=
		// dddProdutoQ.getDdd()) {

		Session session = getSession();

		try {
			Transaction trans = session.beginTransaction();
			session.clear();
			session.saveOrUpdate(dddProduto);
			trans.commit();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		// }else
		// dddProduto = dddProdutoQ;

		return dddProduto;
	}

	public DDDProduto verificarDDDExistente(DDDProduto dddProduto) throws ErroException {

		String consulta = "from DDDProduto d where d.ddd =:ddd";
		Session session = getSession();
		//session.beginTransaction();
		Query query = session.createQuery(consulta);
		query.setInteger("ddd", dddProduto.getDdd());

		DDDProduto dddProdutoQ = (DDDProduto) query.uniqueResult();
		session.close();
		return dddProdutoQ;

	}
	
	public DDDProduto buscarDDD(String ddd) throws NumberFormatException, ErroException{
		return this.buscarDDD(Integer.parseInt(ddd));
	}
	
	public DDDProduto buscarDDD(int ddd) throws ErroException{
		
		Criteria busca = getSession().createCriteria(DDDProduto.class);
		busca.add(Restrictions.eq("ddd", ddd));
		
		//String consulta = "from DDDProduto d where d.ddd =:ddd";
		//Session session = getSession();
		//session.beginTransaction();
		//Query query = session.createQuery(consulta);

		//query.setInteger("ddd", ddd);
		DDDProduto dddProdutoQ = (DDDProduto) busca.uniqueResult();
		//session.close();
		
		if(null == dddProdutoQ)
			dddProdutoQ = buscaProdutoQuery(ddd);
		return dddProdutoQ;
	}
	private DDDProduto buscaProdutoQuery(int ddd) throws ErroException{
		String consulta = "from DDDProduto d where d.ddd =:ddd";
		Session session = getSession();
		//session.beginTransaction();
		Query query = session.createQuery(consulta);

		query.setInteger("ddd", ddd);
		
		DDDProduto dddProdutoQ = (DDDProduto)query.uniqueResult();
		return dddProdutoQ;
	}
}
