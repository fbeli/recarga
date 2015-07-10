
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<div align="center" style="width:100%; height:100%;">
		<!-- topo -->
		
		<div class="logado">
		<sec:authorize access="isAnonymous()">
			
			
			<form class="navbar-form pull-right" action="<%=request.getContextPath()%>/j_spring_security_check"  method="post" id="frmLogin">
				<div class="row">
					<input type="text" name="j_username" placeholder="Usu&aacute;rio" class="span2"/>
					<input type="password" name="j_password" placeholder="Senha" class="span2"/>
					<img src="<%=request.getContextPath()%>/recursos/img/btnEntrar.png" onClick="$('#frmLogin').submit()" style="cursor:pointer;"/>
						
					
				</div>
				
			</form>
			
			</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			Bem vindo(a) <%=request.getSession().getAttribute("username") %> -
			<a href="<%=request.getContextPath()%>/logout"/>
				<img src="<%=request.getContextPath()%>/recursos/img/btnLogout.png" style="cursor:pointer;"/>
			</a>
			
		</sec:authorize>
		

	</div>
		
		<div align="right" style="width:100%; height:40px; padding-top:8px; background-image: url(<%=request.getContextPath()%>//recursos//img//back_topo.png);">
			<img src="<%=request.getContextPath()%>/recursos/img/backWindow.png" onClick="window.location.href = '<%=request.getContextPath()%>'; " style="cursor:pointer;"/>&nbsp;&nbsp;&nbsp;
			<img src="<%=request.getContextPath()%>/recursos/img/closeWindow.png" onClick="CloseThis();" style="cursor:pointer;"/>&nbsp;&nbsp;&nbsp;
		</div> 
		<!-- topo -->
		
		<div align="center" style="height:5%;"/></div>
		
		<!-- Miolo -->
		
		<div align="center" id="frame0"> 
			
		<div align="center" style="height:7%;"/></div>
		
