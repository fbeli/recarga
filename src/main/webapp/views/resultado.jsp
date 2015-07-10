<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="site">
<c:set var="entrar" value="true" />

<div id="titulo_erro">
<h1 id="erro"> Atualização de Produtos</h1>
</div>
<div id="erro">
	<h3 id="erro" style="visibility: visible;">
	Mensagem:	${mensagem }
		<br><br>
		
		
		
		${status }
	</h3>
</div>
</div>

