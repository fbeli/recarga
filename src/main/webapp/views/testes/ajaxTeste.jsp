<html>
<head>
<TITLE>Crunchify - Spring MVC Example with AJAX call</TITLE>
 
<style type="text/css">
<style type="text/css">
        div {
            width: 600px;
            height: 600px;
        }
        .loader {
            display: none;
            float: left;
        }
    
body {
    background-image:
        url('http://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function crunchifyAjax() {
        $.ajax({
            url : 'ajaxtest',
            success : function(data) {
                $('#result').html(data);
            }
        });
    }
    
    function getNome(){
    	$.ajax({
    		url: 'ajaxtestNome',
    		success : function(data) {
    			alert(data);
    		}
    	})
    }
    
</script>
 
<script type="text/javascript">
//faz a configuração do tempo para saber o status a x sec
    var intervalId = 0;
    intervalId = setInterval(crunchifyAjax, 3000);
</script>
</head>
 
<body>
    <div align="center">
        <br> <br> ${message} <br> <br>
        <div id="result"></div>
        <br>
<hr>
<div onclick="getNome()">
Veja o Nome
</div>
<hr>



<script type="text/javascript">
function getNome2(){ //Quando clicado no elemento input
	
    $.ajax({
        url: 'ajaxtestNome',
        success: function(data) {
          $("#nomeTodo").html(data);
          //alert(data);
        },
        beforeSend: function(){
          $('.loader').css({display:"block"});
        },
        complete: function(){
          $('.loader').css({display:"none"});
        }
  });
}
</script>
<!-- http://vinteum.com/ajax-facil-com-jquery/ -->
	<img src="/recargaweb\recursos\imagem\Logo.png" class="loader" alt="loading" 
		width="60" height="60" />
     
     <div> <input type="button" id="btn1" name="btn1"value="AJAX!" onclick="getNome2()"></div>
     
     <div id="nomeTodo"> &nbsp;</div>
    </div>
    
    <hr>
    
    <div>
    <br>
    <hr>
     <table>
                <tr><td colspan="2"><div id="error" class="error"></div></td></tr>
                <tr><td>Enter your name : </td><td> <input type="text" id="name"><br/></td></tr>
                <tr><td>Education : </td><td> <input type="text" id="education"><br/></td></tr>
                <tr><td colspan="2"><input type="button" value="Add Users" onclick="getNome3()"><br/></td></tr>
                <tr><td colspan="2"><div id="info" class="success"></div></td></tr>
        </table>
    </div>
    <div id="resposta3"> Terra&nbsp;
    </div>
    <script type="text/javascript">
    function getNome3(){
	    
    	 var name = $('#name').val();
    	 var education = $('#education').val();
	
	    $.ajax({
	        type:"post",
	        data: "name=" + name + "&education=" + education,
	        url:"testeForm",	        
	        dataType: "json",
	        success: function(response){
	            // we have the response
	            if(response.status == "SUCCESS"){
	        		$("#info").html("sucesso:  "+response.result);
	            }else
	            	$("#info").html("erro:   "+response.result);
	        },
	         error: function(e){
	             alert('Error: ' + e);
	         }
	    });
    }
</script>
        
</body>
</html>