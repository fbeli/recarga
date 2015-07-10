<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="<%=request.getContextPath()%>/recursos/script/jquery.printelement.min.js"></script>




<div id="borda">

	<div id="bilhete">
		<div id="icones" align="right" onclick="imprimir('printable');">
			<img src="<%=request.getContextPath()%>/recursos/imagem/print.png"
				onclick="imprimir('printable');" width="100" height="100" id="print" /> 
		</div>
		<div id="printable">

			<h2 id="valor" align="center">
				${recarga.produto.operadora.nomeOperadora} <br> ${recarga.ddd}
				${recarga.fone} <br>
				
			</h2>
			<h3 id="valor">

				Código de Compra: ${recarga.codOnline}
				<br>nsu: ${recarga.nsu}
				<br>Identificação local:  ${recarga.id}
				
				<br> Valor Recarga:<fmt:formatNumber value="${recarga.valor}" type="currency" />
				<br> Status Recarga: ${recarga.statusRecarga}
				<br> Data de Confirmação:<fmt:formatDate value="${recarga.dataDeconfirmacao}" type="both"
						pattern="dd/MM/yyyy HH:mm:ss" />
				<br> ***************<br>${recarga.mensagem}<br>***************<br>


				
				<br>idTerminal: ${recarga.idTerminal}
				<br>${site}
				<br>${promocao}


			</h3>
		</div>
	</div>
	<div>
		<img src="<%=request.getContextPath()%>/recursos/img/backWindow.png" 
			onClick="window.location.href = 'http://${endereco }'; " 
			style="cursor:pointer; height: 50; "/>
		
	</div>

</div>
</div>

