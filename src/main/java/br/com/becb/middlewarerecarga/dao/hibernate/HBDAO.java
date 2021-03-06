package br.com.becb.middlewarerecarga.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.Logar;



@Transactional(propagation = Propagation.REQUIRED)
public abstract class HBDAO<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}

	protected Session getSession() throws ErroException {
		Session session=  getSessionFactory().getCurrentSession();
		try{
			/*if(!session.isConnected())
				return getSession(session, 0);
			else{*/
				if(!session.getTransaction().isActive())		
					session.beginTransaction();
				if(!session.getTransaction().isActive())
					return getSession(session, 0);
			//}
				
		}
		catch(Exception e){
				e.printStackTrace();		
			return getSession(session, 0);
		}
		//Logar.info("\n*********\nSess�o de Banco j� dispon�vel no Pool \n*********\n");
			return session;
	}
	
	protected Session getSession(Session session, int vezes) throws ErroException {
		
		if (vezes <5){
			vezes++;
			try{

				Logar.info(Calendar.getInstance().getTimeInMillis()+"");
				Thread.sleep(1800);
				Logar.info(Calendar.getInstance().getTimeInMillis()+"");
				Logar.info("\n*********\nTentando Abrir Sess�o \n*********\n");
				session = getSessionFactory().openSession();
				if(!session.getTransaction().isActive()){		
					Transaction t = session.beginTransaction();
					
					//FIXME testar esta transa��o ebrta oufechada
					if(!t.isActive())
						t.begin();
					
				}
				
				if(!session.getTransaction().isActive())

					return this.getSession(session, vezes+1);
				
					
			}catch(Exception e){
				e.printStackTrace();
				Logar.info(e.toString());
				return this.getSession(session, vezes+1);
			}
		}else{
			throw new ConfirmacaoDeTransacaoException("N�o � poss�vel criar transa��o. Tente em alguns segundos.");
		}
		return session;
	}

	protected abstract Class getClazz();

	public void persistir(T objeto) throws ErroException {

		Session session = getSession();
		if(!session.getTransaction().isActive()){
			Transaction trans = session.beginTransaction();
			if (!trans.isActive())
					trans.begin();
			session.saveOrUpdate(objeto);
			trans.commit();
		}else{
			Transaction trans = session.getTransaction();
			
			session.saveOrUpdate(objeto);
			trans.commit();
		};
		//FIXME Verificar se voltou a funcionar
		if(session.isOpen())
			session.close();

	}

	public void excluir(T objeto) throws ErroException {
		getSession().delete(objeto);
	}

	public T get(Long id) throws ErroException {
		return (T) getSession().get(getClazz(), id);
	}

	public List<T> list(int offset, int max) throws HibernateException, ErroException {
		return (List<T>) getSession().createCriteria(getClazz())
				.setMaxResults(max).setFirstResult(offset).list();
	}

	public void merge(Object object) throws ErroException {
		Session session = getSession();
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.merge(object);
		session.getTransaction().commit();
	}
/*	public static void closeSession() throws HibernateException {
	    Session session = (Session) threadLocal.get();
	    threadLocal.set(null);

	    if (session != null) {
	      session.close();
	    }
	  }*/
}
