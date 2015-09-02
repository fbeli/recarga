<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Integração MOIP</title>



<script
type='text/javascript'
src='https://desenvolvedor.moip.com.br/sandbox/transparente/MoipWidget-v2.js'
charset='ISO-8859-1'>
</script>
</head>

<script type="text/javascript">
  boleto = function(){ //*AQUI VOCÊ DEVE COLOCAR O NOME DA FUNCAO A SER CAHAMADO
 
      var settings = {
    		  
    			    "Forma": "CartaoCredito",
    			    "Instituicao": "Visa",
    			    "Parcelas": "1",
    			    "CartaoCredito": {
    			        "Numero": "4073020000000002",
    			        "Expiracao": "12/15",
    			        "CodigoSeguranca": "123",
    			        "Portador": {
    			            "Nome": "Nome Sobrenome",
    			            "DataNascimento": "30/12/1987",
    			            "Telefone": "(11)3165-4020",
    			            "Identidade": "222.222.222-22"
    			        }}
    			 } //*AQUI VOCÊ DEVE COLOCAR O JSON COM OS DADOS PARA PAGAMENTO (BOLETO COMO EXEMPLO)
 
      MoipWidget(settings);//*AQUI VOCÊ DEVE SETAR O JSON PARA QUE O MOIP PROCESSE
 
  }
  function funcionou(){
	  alert("funcionou");
	  
  }
  function naoFuncionou(){
	  alert("não funcionou");
  }
  
  function chamarAction(){
	  alert("não funcionou");
  }
</script>
<body>

<div id="MoipWidget"
            data-token="${mr.token }"
            callback-method-success="FUNCAO_DE_SUCESSO"
            callback-method-error="FUNCAO_DE_FALHA">
</div>

<button id="chamarAction" onclick="chamarAction()">
 Imprimir Boleto
</button>
<button id="pagarBoleto" onclick="boleto();">
 Imprimir Boleto
</button>

</body>
</html>