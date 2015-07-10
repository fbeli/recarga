package br.com.becb.middlewarerecarga.dao.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Expression;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.exceptions.ErroException;

@Repository("hDaoRecarga")
public class HibernateRecarga<T> extends HBDAO<T>  {

	public HibernateRecarga() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class getClazz() {
		return Recarga.class;
	}
	
	public List<Recarga> getRecargaPorFone(String ddd, String fone) throws ErroException{
		
		List<Recarga> recargas;

		String consulta = "from Recarga r where r.ddd =:ddd and r.fone =:fone order by r.dataDeSolicitacao desc";
		Session session = getSession();
		
		Query query = session.createQuery(consulta);
		query.setString("ddd", ddd);
		query.setString("fone", fone);

		recargas =  query.list();
		session.close();
		return recargas;

		
	}
	public List<Recarga> getRecargaPin() throws ErroException{
		
		List<Recarga> recargas;

		String consulta = "from Recarga r where r.pin is not null order by r.dataDeSolicitacao desc";
		Session session = getSession();
		
		Query query = session.createQuery(consulta);
		

		recargas =  query.list();
		session.close();
		return recargas;
	
	}
	
	
public List<Recarga> getRecargaPorUserEData(String usuario, Date dataInicio, Date dataFim) throws ErroException{
		
		List<Recarga> recargas;

		Session session = getSession();

		Criteria busca = session.createCriteria(Recarga.class);
		busca.add(Restrictions.between("dataDeSolicitacao", dataInicio, dataFim));
		if(usuario != null && usuario.trim().length()>1)
			busca.add(Restrictions.eq("usuarioLocal", usuario));
		
		recargas = busca.list();
		
		//Query query = session.createQuery(consulta);
		//query.setString("usuario", usuario);
		//query.setDate("dataInicio", dataInicio);
		//query.setDate("dataFim", dataFim);
		
		System.out.println(dataInicio + "   "+ dataFim);

		//recargas =  query.list();
		
		return recargas;
	
	}
	

	public Recarga getRecargaPorId(long id) throws ErroException{
		
		Recarga recarga;

		String consulta = "from Recarga r where r.id =:id";
		Session session = getSession();
		
		Query query = session.createQuery(consulta);	
		query.setLong("id", id);

		recarga =  (Recarga) query.uniqueResult();
		session.close();
		return recarga;

		
	}
	
	

}
