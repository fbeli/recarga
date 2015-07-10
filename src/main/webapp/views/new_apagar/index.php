<?php
	
	function anti_injc($php)
	{	
		$php = trim($php);
		$php = strip_tags($php);
		$php = addslashes($php);
		
		return $php;
	}
	
	$display2 = "none";
	$display3 = "none";

	/*
		
		SÃO DOIS MOMENTOS:
		FLUXO DE PÁGINA: INICIO, ONDE MOSTRA A ESCOLHA DA OPERADORA E O FORM PARA DIGITAR DDD E NUMERO 
		FLUXO DE PAGINA: MEIO, ONDE EXIBE COM OS VALORES PARA A ESCOLHA DO MESMO, $display2 = block;
		FLUXO DE PAGINA: FINAL, ONDE CONFIRMA A RECARGA, display3 = block;
		
	*/
	
	@$exe = anti_injc($_POST['exe']);
	
	if($exe == "display2")
	{
		$display2 = "block";
		$display3 = "none";
		
		@$ddd = anti_injc($_POST['ddd']);
		@$tel = anti_injc($_POST['tel']);
		@$op = anti_injc($_POST['operadora']);
	}
	else if($exe == "display3")
	{
		$display2 = "none";
		$display3 = "block";
	}
	
	
	
?> 


<!DOCTYPE html PUBLIC>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="/sty.css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href='http://fonts.googleapis.com/css?family=Montserrat|Open+Sans:700|Oxygen' rel='stylesheet' type='text/css'>
		
		<script language="javascript" src="/jquery.js"></script>
		<script language="javascript" src="/script.js"></script>
		
<title></title>
</head>

<body>
	
	<div align="center" style="width:100%; height:100%;">
		
		<!-- topo -->
		<div align="center" style="width:100%; height:45px; background-image: url(/img/back_topo.png);">
		</div> 
		<!-- topo -->
		
		<div align="center" style="height:5%;"/></div>
		
		<!-- Miolo -->
		
		<div align="center" id="frame0"> 
			
			<?php
				$frase = "Escolha a operadora...";
				
				if($exe == "display2")
					$frase = "(".$ddd.")"." ".$tel." escolha o valor...";
				
			
			?>
			
			<div align="center" style="height:7%;"/></div>
			<div align="center" class="titulo_miolo" id="status" /><div align="center" style="height:15%;"/></div><?php echo $frase; ?></div>
			<div align="center" style="height:3%;"/></div>
			<div align="center" style="height:25%;">
			
				<div align="center" style="width:2.5%; float:left;"/>&nbsp;</div>
				
				<div align="center" class="div_logo" style="opacity: <?php echo ($op == "Claro" ? "1.0" : "0.3"); ?>" id="Claro" onClick="fixaLogo('Claro');">
				
					<div align="center" style="height:10%;"/>&nbsp;</div>
					
					<img src="/img/logoClaro.jpg" height="80%" />				</div>
				
				<div align="center" style="width:1%; float:left;"/>&nbsp;</div>
				
				<div align="center" class="div_logo" style="opacity: <?php echo ($op == "Tim" ? "1.0" : "0.3"); ?>" id="Tim" onClick="fixaLogo('Tim');">
			
					<div align="center" style="height:25%;"/>&nbsp;</div>
					<img src="/img/logoTim.png" height="45%" width="80%" />				</div>
				
				<div align="center" style="width:1%; float:left;"/>&nbsp;</div>
				
				<div align="center" class="div_logo" style="opacity: <?php echo ($op == "Oi" ? "1.0" : "0.3"); ?>" id="Oi" onClick="fixaLogo('Oi');">
			
					<div align="center" style="height:10%;"/>&nbsp;</div>
					<img src="/img/logoOi.png" height="80%" />				
				
				</div>
				
				<div align="center" style="width:1%; float:left;"/>&nbsp;</div>
				
				<div align="center" class="div_logo" style="opacity: <?php echo ($op == "Vivo" ? "1.0" : "0.3"); ?>" id="Vivo" onClick="fixaLogo('Vivo');">
			
					<div align="center" style="height:20%;"/>&nbsp;</div>
					<img src="/img/logoVivo.jpg" height="55%" width="80%" />				</div>
				
				<div align="center" style="width:1%; float:left;"/>&nbsp;</div>
				
				<div align="center" class="div_logo" style="opacity: <?php echo ($op == "Nextel" ? "1.0" : "0.3"); ?>" id="Nextel" onClick="fixaLogo('Nextel');">
			
					<div align="center" style="height:27%;"/>&nbsp;</div>
					<img src="/img/logoNextel.png" height="40%" width="80%" />				</div>
				
			</div>
			
			<div align="center" style="height:25%; display:none;" id="frameNumero">
			<form action="/index.php" method="post" id="teco_teco" onSubmit="return showValores();">	
			
				<div align="center" style="height:50%;"></div>
				
				
				<span align="center"><input name="ddd" type="text" class="textField" onKeyPress="return myKeyPress(event,2,'ddd');" onKeyUp="pulaCampo('ddd',2);" id="ddd" maxlength="2" value="<?php echo $ddd; ?>"></span>
				
				<span align="center"/>&nbsp;</span>
				
				<span align="center"><input name="tel" type="text" class="textField" onKeyPress="return myKeyPress(event,9,'tel');"  id="tel" maxlength="9" style="width:350px;"  value="<?php echo $tel; ?>"></span>
				
				
				
				<span align="center"><input name="" type="text" class="button" onClick="apagaCampo();"></span>
				
				<div align="center" style="height:25%;"></div>
				
				<input name="Avançar" type="submit" class="div_bnt" value="Avançar">
				
				<input name="operadora" id="operadora" type="hidden" value="<?php echo $op; ?>">
				<input id="exe" value="display2" type="hidden" name="exe" >
			</form>
				
			</div>
			
			<div align="center" style="height:25%; display:<?php echo $display2; ?>;" id="frameValores">
				
				<div align="center" style="height:20%;"></div>
				
				<div align="center">
					
					<table align="center" cellspacing="10" id="frameValoresTable">
						
						<tr>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
						</tr>
						<tr>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
						</tr>
						<tr>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
							<td><div align='center' class='div_valor' onClick="fixaValor(0);"><br>0,00</div></td>
						</tr>
					
					</table>
			
				</div>

			</div>
			
			<div align="center" style="height:25%; display:none;" id="frameConfirma">
			<form action="/index.php" method="post" id="teco_teco2">	
				<div align="center" style="height:30%;"></div>
				<span id="confirma_numero" class="confirma_tel"><strong></strong></span><br><br><br>
	
				<input name="Confirmar" type="submit" class="div_bnt" value="Confirmar">
			<input id="exe" value="display3" type="hidden" name="exe" >
			<input id="valor" value="" type="hidden" name="valor" >
			<input id="" value="<?php echo $ddd; ?>" type="hidden" name="ddd" >
			<input id="" value="<?php echo $tel; ?>" type="hidden" name="tel" >
			<input name="operadora" id="" type="hidden" value="<?php echo $op; ?>">
			</form>
			</div>
			
			<div align="left" class="divBackPopup" style="display:<?php echo $display3; ?>" id="frameConfirmacao1"></div>
			<div align="left" class="divPopupBody" style="display:<?php echo $display3; ?>; top:25%; height:50%; overflow-y:scroll;" id="frameConfirmacao2">
				
				<div align="right"><img src="img/iconClose.png" style="cursor:pointer;"  onClick="fixaLogo('');"/></div>
				
				confirmação da recarga
			</div>
			
			<div align="left" class="divBackPopup" style="display:;" id="frameLogin0"></div>
			<div align="center" class="divPopupBody" style="display:; top:25%; height:150px; line-height:24px;" id="frameLogin1">
			<form action="/index.php" method="post" id="teco_teco3">		
				<div align="center" style="font-family:'Oxygen', sans-serif; font-size:16px;">Login e Senha:</div>
				<input name="login" type="text" class="textField2" maxlength="5" style="width:80px;"><br>
				<input name="senha" type="password" class="textField2" maxlength="5" style="width:80px;"><br>
				
				<input name="Logar" type="submit" class="div_bnt" value="Logar" style="padding:10px;">
			</form>	
			</div>
			
		</div>
		
		<!-- Miolo -->
		<div align="center" style="height:5%;"/></div>
		
	</div>
	
	<script>
		ajustaAltura();
	</script>
	
</body>
</html>
