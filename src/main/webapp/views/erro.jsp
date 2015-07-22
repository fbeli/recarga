<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="site">
<c:set var="entrar" value="true" />

<div id="titulo_erro">
<h1 id="erro"> erro!</h1>
</div>
<div id="erro">
	<h3 id="erro" style="visibility: visible;">
	
		<br><br>
		
		
		
		Mensagem:	${erro.mensagem }
	</h3>
	<br>
	<img src="<%=request.getContextPath()%>/recursos/img/backWindow.png" onClick="window.location.href = '<%=request.getContextPath()%>'; " style="cursor:pointer;"/>
</div>
</div>

