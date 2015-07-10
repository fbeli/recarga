<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table">
	<tr>
	<td>Data Início</td>
	<td>transacoes.dataInicio</td>
	</tr>
	<tr>
	<td>Data Fim</td>
	<td>transacoes.dataFim</td>
	</tr>
	<tr>
	<td>Valor Total</td>
	<td>transacoes.total</td>
	</tr>
</table>
 <table id="transacao" class="table table-striped table-hover">
 <thead>
  <tr>
 	<th  >Telefone</th> 
 <th  >Código Operação </th>
 <th>Valor</th>
  <th >Data De Solicitacao</th>
   <th >Data De Confirmacao</th>
   <th >statusRecarga</th>
   <th>statusRecargaServer</th>
   <th>Vendedor</th>
   <th>Reemprimir Recibo</th>
   <th>Cancelar Transação<br>
   		<a href="cancelarPendentes">Cancelar Todas</a>
   	
   </th>
   
 </tr>
 </thead>
<c:set var="i" value="1" />
<c:forEach items="${recargas}" var="recarga">
<c:choose>
<c:when test="${i/2==0}" >
<tr>

</c:when>
<c:otherwise>
<tr style="tpar">
</c:otherwise>
</c:choose>


 	<td>${recarga.ddd} ${recarga.fone}</td> 
 <td>${recarga.codOnline}</td>
 <td>
 <fmt:formatNumber value="${recarga.valor }" type="currency"/>
 </td>
  <td style="font-size: 12px;"> <fmt:formatDate value="${recarga.dataDeSolicitacao}" type="both"   
pattern="dd/MM/yyyy HH:mm:ss" /></td>
   <td style="font-size: 12px;"> <fmt:formatDate value="${recarga.dataDeconfirmacao}" type="both"   
pattern="dd/MM/yyyy HH:mm:ss" /></td>
   <td>${recarga.statusRecarga}</td>
   <td>${recarga.statusRecargaServer}</td>
     <td>${recarga.usuarioLocal}</td>
   <td><a href="listarTransacaoPorId?id=${recarga.id}">Imprimir</a></td>
   <td><a href="cancelarRecarga?id=${recarga.id}">Cancelar</a></td>
   
 </tr>
<c:set var="i" value="${i+1}" />

 </c:forEach>
 </table>