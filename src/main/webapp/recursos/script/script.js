// JavaScript Document
	function ajustaAltura()
	{
		alt = $( window ).height();
	
		if(alt < 500)		
			$("#frame0").attr("style","width:80%; height:600px; background-color:#FFFFFF; box-shadow: 0px 0px 20px 5px #FFFFFF;  border-radius: 25px;");
		else
			$("#frame0").attr("style","width:80%; height:80%; background-color:#FFFFFF; box-shadow: 0px 0px 20px 5px #FFFFFF;  border-radius: 25px;");
		
	}
	
	function fixaLogoOnLoad(operadora){
	
		$("#"+operadora).attr("style","opacity:1;");
		
	}
	
	function fixaLogo(div)
	{
		$("#claro").attr("style","opacity:0.1;");
		$("#claro").attr("style",":hover { opacity:1; }");
		$("#tim").attr("style","opacity:0.1;");
		$("#tim").attr("style",":hover { opacity:1; }");
		$("#oi").attr("style","opacity:0.1;");
		$("#oi").attr("style",":hover { opacity:1; }");
		$("#vivo").attr("style","opacity:0.1;");
		$("#vivo").attr("style",":hover { opacity:1; }");
		$("#nextel").attr("style","opacity:0.1;");
		$("#nextel").attr("style",":hover { opacity:1; }")
		
		$("#status").html("<div align='center' style='height:15%;'/></div>Escolha a operadora...");

		if(div != "")
		{
			$("#"+div).attr("style","opacity:1;");
			$("#frameNumero").show();
			$("#status").html("<div align='center' style='height:15%;'/></div>Digite o DDD e telefone...");	
		}
		
		var tel = $("#fone").val();
		
		$("#frameValores").hide();
		$("#frameConfirma").hide();
		$("#frameConfirmacao1").hide();
		$("#frameConfirmacao2").hide();
		$("#ddd").val("");
		$("#fone").val("");
		$("#operadora").val(div);
		$("#ddd").focus();
		
		if(tel != null && tel != "")
			window.location.href = dominio;
		
	}
	var dominio = ""; 
	function ajustaDominio(esseDominio){
		dominio = esseDominio;
		
	}
		
	function myKeyPress(e,len,div)
	{
		var keynum;
		
		var oi = $("#"+div).val();
		n = oi.length;
		
		if(window.event)
		{ // IE					
			keynum = e.keyCode;
		}
		else if(e.which)				
			keynum = e.which;
        
		if((keynum > 47 && keynum < 58 && n < len)) 
		{
			return true;
		}
   		else
		{
   			 if(keynum != 8) 
			 	return false;
			else 
				return true;   
				
			
        }
	}
	
	function pulaCampo(div,len)
	{
		var oi = $("#"+div).val();
		
		if(oi.length == len)
		{
			if(div == "ddd")
				$("#fone").focus();
		}
	}
	
	function apagaCampo()
	{
		var tel = $("#fone").val();
		var ddd = $("#ddd").val();
		
		if(tel != "")
		{
			$("#fone").val("");
			$("#fone").focus();	
		}
		else if(ddd != "")
		{
			$("#ddd").val("");
			$("#ddd").focus();	
		}
		else
			$("#ddd").focus();	
	}
	
	function fixaValor(valor)
	{
		var tel = $("#fone").val();
		var ddd = $("#ddd").val();
		
		$("#frameNumero").hide();
		$("#frameValores").hide();
		$("#frameConfirma").show();
		
		$("#status").html("<div align='center' style='height:15%;'/>("+ddd+") "+tel	);
		$("#confirma_numero").html("R$ "+valor.toString()+",00");
		$("#valor").val(valor);
		$("#codProduto").val(valor);
		$("#confirma_valor_strong").html("R$ "+valor+",00");
	}
	
	function pegaDominio() {
		var url = location.href; //pega endereço que esta no navegador
		url = url.split("/"); //quebra o endeço de acordo com a / (barra)
		alert(url[2]); // retorna a parte www.endereco.com.br
	}
	
	function CloseThis(){
		if(navigator.userAgent.toLowerCase().indexOf('chrome') > -1)
			self.close();
		window.close();
	}
	function imprimir(div)
	{
		$('#'+div).printElement();
	}
	
	