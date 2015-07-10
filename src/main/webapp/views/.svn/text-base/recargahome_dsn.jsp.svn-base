
			
			
			<div align="center" style="height:25%; display:none;" id="frameNumero">
			<form action="buscarRecargasDisponiveis" method="post" id="formulario" onSubmit="return showValores();">	
			
				
				<div align="center" style="height:50%;"></div>
				
				
				<span align="center">
					<input name="ddd" type="text" class="textField" onKeyPress="return myKeyPress(event,2,'ddd');" onKeyUp="pulaCampo('ddd',2);" id="ddd" maxlength="2">
			
				</span>
				
				<span align="center"/>&nbsp;</span>
				
				<span align="center">
					<input name="fone" type="text" class="textField" onKeyPress="return myKeyPress(event,9,'fone');"  id="fone" maxlength="9" style="width:350px;"  >
					
					
				</span>
				
				
				
				<span align="center"><input name="" type="text" class="button" onClick="apagaCampo();"></span>
				
				<div align="center" style="height:25%;"></div>
				
				<input name="Avançar" type="submit" class="div_bnt" value="Avançar" id="btnsubmit">
				
				<input name="operadora" id="operadora" type="hidden">

			</form>
				
			</div>	
	
	
	<script>
	
	function showValores()
	{
		var confirmaForm=true;
		
		var ddd 		= $("#ddd").val();
		var tel 		= $("#fone").val();
		var operadora	= $("#operadora").val();
		
		
		if(operadora == ""){	
			confirmaForm=false;
			$('#iimage').popover('show');
		}
		if(ddd == "" || ddd.length != 2 ){
			confirmaForm=false;
			$('#ddd').popover('show');
		}
		if(tel  == "" || tel.length != 9  ){
			$('#tel').popover('show');
			confirmaForm=false;
		}
		
		
		
		return confirmaForm;
		
		
	}
		

	</script>
