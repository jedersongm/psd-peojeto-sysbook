<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body class="tab-content">
    
        
        
   

    <div class="rowLivro media-object">
        
        <c:forEach items="${livros}" var="book">
            <div class="col-sm-3 modal-header2">
                <h2> ${book.titulo}</h2>
                <a href="AbrirLivro?titulo=${book.titulo}">
                    <img src="${book.fotoCapa}" alt="${book.titulo}" class="img-circle2 img-responsive" ></a>
            </div>

        </c:forEach>       
    </div>
    <c:if test="${sessionScope.usuario != null}">
            <div>
                <a href="ExibirLivrosRecomendados"><button class="btn btn-info" type="button">Livros Recomendados</button></a>
            </div>             
    </c:if>
    
