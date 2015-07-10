package br.com.becb.middlewarerecarga.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.suporte.Suporte;

@Repository("jdbcSuporteDao")
public class JDBCSuporteDao extends JDBCBaseDao {

	public JDBCSuporteDao() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public void carregaConfiguracoes(){
		
		
		/*SqlRowSet row = getJdbcTemplate().queryForRowSet("select * from suporte");
		
		Suporte.loja = row.getString("loja");
		Suporte.senha = row.getString("senha");
		Suporte.url = row.getString("url");		
		Suporte.nome = row.getString("nome");
	*/
		getJdbcTemplate().query("select * from suporte where ativo = 1", new ResultSetExtractor() {  
		    public Object extractData(ResultSet rs) throws SQLException {  
		        List resultado = new ArrayList();  
		        while (rs.next()) {  
		        	Suporte.loja = rs.getString("loja");
		    		Suporte.senha = rs.getString("senha");
		    		Suporte.url = rs.getString("url");		
		    		Suporte.nome = rs.getString("nome"); 
		    		Suporte.endereco = rs.getString("endereco"); 
		    		Suporte.valorMinimo = rs.getDouble("valorMinimo")/100;
		        }  
		        return resultado;  
		    }  
		}); 
		
		
		
	}

}
