<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="generator"
	content="CoffeeCup HTML Editor (www.coffeecup.com)">
<meta name="dcterms.created" content="seg, 23 mar 2015 13:50:48 GMT">
<meta name="description" content="">
<meta name="keywords" content="">
<link href="recargahome.css" rel="stylesheet" type="text/css" />
<title></title>

<!--[if IE]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>

    <script>
        function selecionado(divlocal){
        
        	var vSrc = "recursos\\imagem\\ "+divlocal+".jpg";
        	document.getElementById("operadora").value = divlocal;
        	document.getElementById("imagemSelecionada").src = vSrc;
        	
        	
        
        }   
           </script>
	<div id="conteudo">
		<form action="buscarRecargasDisponiveis" method="post" id="formulario" >
			<div id="conteudo">
			<c:if test="${erro!=null }">
				<h5  style="color: red"> ${erro }</h5>
			</c:if>
			<input type="hidden" id="operadora" name="operadora" >

				<div id="esquerda">
					<div id="divoi" class="internoEsquerda">
					
						<img src="recursos\imagem\oi.jpg" width="100" height="100" onclick="selecionado('oi');"/> <br>
					

					</div>
					<div id="divtim" class="internoDireita">
						<img src="recursos\imagem\tim.jpg" width="134" height="100" onclick="selecionado('tim');"/> <br>
						
					</div>
				</div>
				<div id="direita" >
					<div id="divvivo" class="internoEsquerda">
						<img name="operadora" id="imgvivo" src="recursos\imagem\vivo.jpg" width="134"
							height="100" onclick="selecionado('vivo');"/> <br> 
					</div>
					<div id="divclaro" class="internoDireita">
						<img name="operadora" id="imgclaro" src="recursos\imagem\claro.jpg" width="100"
							height="100"  onclick="selecionado('claro');"/> <br> 
					</div>
				</div>
			</div>
			
			<div  class="divimagemSelecionada" id="iimage"
			 data-placement="left" data-toggle="popover" title="Está faltando alguma coisa.   Selecione a operadora!">
				<img id="imagemSelecionada" style="height: 200px;" src="recursos\imagem\selecione.jpg">
			</div>
			<div id="telefone" class="conteiner">
				
					<input type="text" label="DDD" maxlength="2" id="iddd" name="ddd" label="DDD"  style="font-size:62px; height: 60px;" class="input-small input-big" 
						 data-placement="left" data-toggle="popover" title="Está faltando alguma coisa.   Preencha o ddd!"
					/>
			
					<input type="text" label="Fone" maxlength="9"  name="fone" id="ifone" " style="font-size:62px; height: 60px;" class="input-xxlarge input-big"
					  data-toggle="popover" title="Está faltando alguma coisa.   Preencha o Telefone!"
						/>
			
				
				<div id="divsubmit">
					
					<button style="line-height:46px;" id="btnsubmit" class="btn btn-large" type="button">Escolha o Valor</button>
				</div>
			</div>

		</form>


	</div>

	</form>
	</div>
	</div>
		
<script>
$("#btnsubmit").click(function(){
	
	var confirmaForm=true;
	var erromsg="";
	if($("#operadora").val() == ""){	
		confirmaForm=false;
		$('#iimage').popover('show');
	}
	if($("#iddd").val() == ""){
		confirmaForm=false;
		$('#iddd').popover('show');
	}
	if($("#ifone").val() == ""){
		$('#ifone').popover('show');
		confirmaForm=false;
	}
	if(confirmaForm == true){
		$("form#formulario").submit();
		document.getElementById("formulario").submit();
		
	}else{
		$("#erro").html(erromsg);
		$("#erro").show();
		$("#erro").css("visibility", "visible");
	}
	
});

</script>

</body>
</html>