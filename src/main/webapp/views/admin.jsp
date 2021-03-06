<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- link href="recursos\estilos\adm.css" rel="stylesheet" type="text/css" /-->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
	

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>P�gina de administrador</title>
</head>
<body>

	  <div class="row-fluid">
        <div class="span9">
          <div class="hero-unit">
            <h1>Administra��o de Recargas</h1>
            <p>�rea para adm, execu��o de relat�rios e visualiza��es de recargas</p>           
          </div>
          
          <div class="span9" data-original-title="">${mensagem}</div>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
          
          <div class="row-fluid">
            <div class="span4">
              <h2>Incluir usu�rio</h2>
           
                  <form id="formNovoUser"
                    action=" <%=request.getContextPath()%>/admin/cadastrarUser">
                  
                      <label>Nome:</label>
                      <input type="text" name="nome" id="nome" />
                   
                   
                      <label>Login:</label>
                      <input type="text" name="login" id="login" />
                   
                      <label>Senha:</label>
                      <td><input type="password" id="senha" name="senha" />
                   
                      <label>Confirmar senha:</label>
                      <input type="password" id="confSenha" />
                   
                      <label>Role:</label>
                      <select name="role" form="formNovoUser">
                          <option value="ROLE_MEMBRO">1 - ROLE_MEMBRO</option>
                           <option value="ROLE_SUB_VENDEDOR">2 - ROLE_SUB_VENDEDOR</option>
                          <option value="ROLE_VENDEDOR">3 - ROLE_VENDEDOR</option>
                          <option value="ROLE_SUPERVISOR">4 - ROLE_SUPERVISOR</option>
                          <option value="ROLE_ADMIN">5 - ROLE_ADMIN</option>
                      </select>
                   <br>
                   <input type="submit" value="Confirmar">
                      
                    
                  </form>
             </div><!--/span-->
			<div class="span4">
              <h3>Alterar senha</h3>
           	
           		
	            <form id="formAlterarSenha"
	            action=" <%=request.getContextPath()%>/admin/alterarSenha">
	           
	              <label>Nome:</label>
	              <select name="id">
	              <option value="">Todos</option>
	              <c:forEach items="${usuarios}" var="usuario">
	                  <option value="${usuario.id}">${usuario.nome} - ${usuario.login }</option>
	              </c:forEach>
	              </select><br>
	                  <label>Senha:</label>
                      <input type="text" name="senha" id="alt_senha" />
                  
	      <br>
	                  <label>Confirmar senha Senha:</label>
                      <input type="text" id="alt_confSenha" />
                  
	      
              <br><input type="submit" value="buscar"  id="bt_altLogin" >
                  
	           </form> 
	        </div>
            
            <div class="span4">
              <h3>Cancelar Usuario</h3>
              
               		
	            <form id="formCancelarUser"
	            action=" <%=request.getContextPath()%>/admin/cancelarUser">
	           
	              <label>Nome:</label>
	              <!-- td><input type="text" name="nome" id="vt_ddd" /></td-->
	              <select name="nome">
	              <option value="">Todos</option>
	              <c:forEach items="${usuariosAtivos}" var="usuario">
	                  <option value="${usuario.id}">${usuario.nome} - ${usuario.login }</option>
	              </c:forEach>
	              </select></td>
	      
	      
          	    <br><input type="submit" value="buscar">
                  
	           </form> 
	      
              
              
            </div>
          </div><!--/row-->
          </sec:authorize>
           <div class="row-fluid">
            <div class="span4">
              <h3>Verifica Transa��o Online</h3>
            
                <form id="formVerificaTransacao"
                  action=" <%=request.getContextPath()%>/admin/verificaTransacao">
                 
                    <label>ddd:</label>
                   <input type="text" name="ddd" id="vt_ddd" />
                    <label>Fone:</label>
                    <input type="text" name="fone" id="vt_fone" />
                    <br>
                      <input type="submit" value="buscar">
                  
                </form>
               

            </div><!--/span-->
            <div class="span4">
              <h2>Verifica Transa��o Online </h2>
              <form id="formVerificaTransacao"
            action=" <%=request.getContextPath()%>/admin/listarTransacaoPorId">
            
              <label>Id Transa��o:</label>
              <input type="text" name="id" id="vt_id" />
              <br><input type="submit" value="buscar">
           
          </form>
            </div><!--/span-->

            </div>
          <div class="row-fluid">
            <div class="span4">
              <h2>Listar Produtos</h2>
               <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERVISOR')">
		            <form id="formVerificaTransacao"
		            action=" <%=request.getContextPath()%>/admin/buscarProdutos">
		           
		              <label>DDD:</label>(deixe em branco para buscar todos)
		             <input type="text" name="ddd" id="ddd" />
		              <br><input type="submit" value="buscar"></td>
		          
		          </form>
              </sec:authorize>
            </div><!--/span-->
            <div class="span4">
              <h2>Listar Transa��o por Usu�rios</h2>
             
            <form id="formVerificaTransacao"
            action=" <%=request.getContextPath()%>/admin/verificaTransacaoPorUsuario">
           
              <label>Nome:</label>
              <!-- td><input type="text" name="nome" id="vt_ddd" /></td-->
              <select name="nome">
              <option value="">Todos</option>
              <c:forEach items="${usuarios}" var="usuario">
                  <option value="${usuario.nome}">${usuario.nome} - ${usuario.login }</option>
              </c:forEach>
              </select></td>
              
           
              <br><input type="checkbox" name="hoje"> Fechar O caixa
              <label>Data Inicio:</label>
             <input type="text" name="dataInicio" id="vt_dataInicio" />
          
              <label>Data Fim:</label>
              <input type="text" name="dataFim" id="vt_dataFim" />
            
              <br><input type="submit" value="buscar">
          </form>
          
            
            </div><!--/span-->
            <div class="span4">
              <h3>Verifica Transa��o Pendente</h3> <br> <a
        href="<%=request.getContextPath()%>/admin/listarTransacaoPendente">
          Lista transa��es pendentes </a></td>
          <br><br>
          <h3>Atualizar Produtos</h3>
        <br> <a
        href="<%=request.getContextPath()%>/admin/atualizarProdutos">
          Atualizar Produtos</a>
            </div><!--/span-->
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->
		
	
<script type="text/javascript">
$("#btLogin").click( function(){
	if($("#senha").val() == $("#confSenha").val()){
		$("#formNovoUser").submit();
		
	}else{
		$("#tdMsg").html("Senhas N�o conferem, favor, colocar senhas iguais")
	}
		
});

$("#bt_altLogin").click( function(){
	if($("#alt_Senha").val() == $("#alt_confSenha").val()){
		$("#formAlterarSenha").submit();
		
	}else{
		$("#tdMsg").html("Senhas N�o conferem, favor, colocar senhas iguais")
	}
		
});



</script>

<script>
$(function() {
    $("#vt_dataInicio").datepicker({
        showOn: "button",
        buttonImage: "<%=request.getContextPath()%>/recursos/imagem/calendar.png",
        buttonImageOnly: true,
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado','Domingo'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
        monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    
    });
});
	
$(function() {
    $("#vt_dataFim").datepicker({
        showOn: "button",
        buttonImage: "<%=request.getContextPath()%>/recursos/imagem/calendar.png",
        buttonImageOnly: true,
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado','Domingo'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
        monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    
    });
});
</script>

</body>
</html>