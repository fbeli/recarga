<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	
<div id="topo">
	<a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>\recursos\imagem\Logo.png"
		width="80" height="80" /></a>

	<div class="logado">
		<sec:authorize access="isAnonymous()">
			
			<form style="margin-top: 1.0em;" action="<c:url value="/j_spring_security_check"/>" method="post">
				<div class="row">
					Usuário: <input type="text" name="j_username" placeholder="Usu&aacute;rio" class="inputLogin"/>
					Senha: <input type="password" name="j_password" placeholder="Senha" class="inputLogin"/>
					<input type="submit" value="Entrar"  class="inputLogin"/>&nbsp;
					
				</div>
				
			</form>
			
			</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			Bem vindo(a) <%=request.getSession().getAttribute("username") %> -
			<a href="<c:url value="/logout"/>">Sair</a>
		</sec:authorize>
		

	</div>

</div>
