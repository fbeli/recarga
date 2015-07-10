<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table class="table">
	<tr>
	<td style="width: 150px">Data In�cio</td>
	<td style="width: 150px">${transacoes.dataInicio}</td>
	</tr>
	<tr>
	<td>Data Fim</td>
	<td>${transacoes.dataFim}</td>
	</tr>
	<tr>
	<td>Valor Total</td>
	<td><fmt:formatNumber value="${transacoes.total }" type="currency"/></td>
	</tr>
</table>

<br>
 <table id="transacao" class="table table-striped table-hover">
 <thead>
  <tr>
 	<th  >Telefone</th> 
 <th >C�digo Opera��o </th>
 <th>Valor</th>
  <th>Data De Solicitacao</th>
   <th >Data De Confirmacao</th>
   <th >statusRecarga</th>
   <th>statusRecargaServer</th>
   <th>Vendedor</th>
   <th>Reemprimir Recibo</th>
   <th>Cancelar Transa��o</th>
   
 </tr>
 </thead>
 <tr class="success">
 
 	<td></th> 
 <td >Recarga Online </td>
 <td> <fmt:formatNumber value="${transacoes.totalOnline }" type="currency"/></td>
  <td></td>
   <td ></td>
   <td ></th>
   <td></td>
   <td>Vendedor</td>
   <td></td>
   <td></td>
 
 </tr>
<c:set var="i" value="1" />
<c:forEach items="${transacoes.recargasOnline}" var="recarga">
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
 
 
 <br>
 <table id="transacaoPin" class="table table-striped table-hover">
 <thead>
  <tr>
 	<th  >Telefone</th> 
 <th >C�digo Opera��o </th>
 <th>Valor</th>
  <th>Data De Solicitacao</th>
   <th >Data De Confirmacao</th>
   <th >statusRecarga</th>
   <th>statusRecargaServer</th>
   <th>Vendedor</th>
   <th>Reemprimir Recibo</th>
   <th>Cancelar Transa��o</th>
   
 </tr>
 </thead>
 <tr class="warning">
 
 	<td></th> 
 <td >Recarga Pin </td>
 <td><fmt:formatNumber value="${transacoes.totalPin }" type="currency"/></td>
  <td></td>
   <td ></td>
   <td ></th>
   <td></td>
   <td>Vendedor</td>
   <td></td>
   <td></td>
 
 </tr>
<c:set var="i" value="1" />
<c:forEach items="${transacoes.recargasPin}" var="recarga">
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
 
 <br>
 
 <table id="transacao" class="table table-striped table-hover">
 <thead>
  <tr>
 	<th  >Telefone</th> 
 <th >C�digo Opera��o </th>
 <th>Valor</th>
  <th>Data De Solicitacao</th>
   <th >Data De Confirmacao</th>
   <th >statusRecarga</th>
   <th>statusRecargaServer</th>
   <th>Vendedor</th>
   <th>Reemprimir Recibo</th>
   <th>Cancelar Transa��o</th>
   
 </tr>
 </thead>
 <tr class="error">
 
 <td></th> 
 <td >Recarga N�o Completadas </td>
 <td></td>
  <td></td>
   <td ></td>
   <td ></th>
   <td></td>
   <td>Vendedor</td>
   <td></td>
   <td><a href="cancelarPendentes">Cancelar Todas</a></td>
 
 </tr>
<c:set var="i" value="1" />
<c:forEach items="${transacoes.recargasNaoConfirmadas}" var="recarga">
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
 
 