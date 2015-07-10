<!DOCTYPE html PUBLIC>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=request.getContextPath()%>/recursos/script/jquery-1.9.1.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/estilos/sty.css" media="screen" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link href='http://fonts.googleapis.com/css?family=Montserrat|Open+Sans:700|Oxygen' rel='stylesheet' type='text/css'>
	<!-- script language="javascript" src="<%=request.getContextPath()%>/recursos/script/jquery.js"></script-->
	<script language="javascript" src="<%=request.getContextPath()%>/recursos/script/script.js"></script>
		
		
<title></title>
</head>
<body onload="fixaLogoOnLoad('${operadora}'), ajustaDominio('<%=request.getContextPath()%>')">

	
	
	<tiles:insertAttribute name="cabecalho"/>
	
	<tiles:insertAttribute name="corposuperior"/>
	
	<tiles:insertAttribute name="conteudo"/>

	<tiles:insertAttribute name="rodape"/>
	
	
	
</body>
</html>