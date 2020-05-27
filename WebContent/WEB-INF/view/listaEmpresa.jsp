<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url value="/crud?p=NovaEmpresaForm" var="linkServletNovaEmpresa"/>
<c:url value="/crud?p=MostraEmpresa&id=" var="linkServletMostraEmpresa"/>    
<c:url value="/crud?p=RemoveEmpresa&id=" var="linkServletRemoveEmpresa"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <br>
  <a href="${linkServletNovaEmpresa}">Cadastrar</a>
  <br>
  <br>
  Lista de Empresas:
  <ul>
    <c:forEach items="${empresas}" var="empresa">
      <li>${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/>      
      <a href="${linkServletMostraEmpresa}${empresa.id}">Alterar</a>
      <a href="${linkServletRemoveEmpresa}${empresa.id}">Remover</a>    
      </li>
    </c:forEach>  
  </ul>  

</body>
</html>