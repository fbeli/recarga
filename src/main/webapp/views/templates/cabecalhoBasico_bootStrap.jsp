<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	
	
	
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
	
 	<a class="brand" href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>\recursos\imagem\Logo.png"
		width="60" height="60" /></a>

	<div class="logado">
		<sec:authorize access="isAnonymous()">
			
			
			<form class="navbar-form pull-right" action="<c:url value="/j_spring_security_check"/>" method="post">
				<div class="row">
					<input type="text" name="j_username" placeholder="Usu&aacute;rio" class="span2"/>
					<input type="password" name="j_password" placeholder="Senha" class="span2"/>
					<button type="submit" class="btn">Entrar</button>		
					
				</div>
				
			</form>
			
			</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
Bem vindo(a) <%=request.getSession().getAttribute("username") %> -
<a href="<c:url value="/logout"/>">Sair</a>
		</sec:authorize>
		

	</div>

</div>
</div>
<div></div>
