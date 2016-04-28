<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body class="tab-content">
          

    <div class="row">
        
        <c:forEach items="${livros}" var="book">
            <div class="col-sm-3 modal-header2">
                <h2> ${book.titulo}</h2>
                <a href="AbrirLivro?isbn=${book.isbn}">
                    <img src="${book.fotoCapa}" alt="${book.titulo}" class="img-circle2 img-responsive" ></a>
            </div>

        </c:forEach>       
    </div>
    
    <c:if test="${sessionScope.usuario != null}">
        <a href="ExibirLivrosRecomendados"><button id="bottom-float-top" class="btn btn-danger">Recomendados</button> </a>         
    </c:if>
    
    
