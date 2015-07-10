// JavaScript Document
	function ajustaAltura()
	{
		alt = $( window ).height();
	
		if(alt < 500)		
			$("#frame0").attr("style","width:80%; height:600px; background-color:#FFFFFF; box-shadow: 0px 0px 20px 5px #FFFFFF;  border-radius: 25px;");
		else
			$("#frame0").attr("style","width:80%; height:80%; background-color:#FFFFFF; box-shadow: 0px 0px 20px 5px #FFFFFF;  border-radius: 25px;");
		
	}
	
	function fixaLogo(div)
	{
		$("#Claro").attr("style","opacity:0.2;");
		$("#Claro").attr("style",":hover { opacity:1; }");
		$("#Tim").attr("style","opacity:0.2;");
		$("#Tim").attr("style",":hover { opacity:1; }");
		$("#Oi").attr("style","opacity:0.2;");
		$("#Oi").attr("style",":hover { opacity:1; }");
		$("#Vivo").attr("style","opacity:0.2;");
		$("#Vivo").attr("style",":hover { opacity:1; }");
		$("#Nextel").attr("style","opacity:0.2;");
		$("#Nextel").attr("style",":hover { opacity:1; }")
		
		$("#status").html("<div align='center' style='height:15%;'/></div>Escolha a operadora...");

		if(div != "")
		{
			$("#"+div).attr("style","opacity:1;");
			$("#frameNumero").show();
			$("#status").html("<div align='center' style='height:15%;'/></div>Digite o DDD e o número do telefone...");	
		}
		
		$("#frameValores").hide();
		$("#frameConfirma").hide();
		$("#frameConfirmacao1").hide();
		$("#frameConfirmacao2").hide();
		$("#ddd").val("");
		$("#tel").val("");
		$("#operadora").val(div);
		$("#ddd").focus();
		
		alert($("# "))
		
	}
	
	function showValores()
	{
		var ddd 		= $("#ddd").val();
		var tel 		= $("#tel").val();
		var operadora	= $("#operadora").val();
		
		if(ddd.length == 2 && tel.length == 9)
			return true;
		else
			return false;
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
				$("#tel").focus();
		}
	}
	
	function apagaCampo()
	{
		var tel = $("#tel").val();
		var ddd = $("#ddd").val();
		
		if(tel != "")
		{
			$("#tel").val("");
			$("#tel").focus();	
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
		var tel = $("#tel").val();
		var ddd = $("#ddd").val();
		
		$("#frameNumero").hide();
		$("#frameValores").hide();
		$("#frameConfirma").show();
		
		$("#status").html("<div align='center' style='height:15%;'/>("+ddd+") "+tel.substr(0,5)+"-"+tel.substr(5,8));
		$("#confirma_numero").html("R$ "+valor.toString()+",00");
		$("#valor").val(valor);
	}