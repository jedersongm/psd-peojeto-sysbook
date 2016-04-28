<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body class="templatemo-bg-image-2">

    <div class="rowLivro media-object">
        <div class="col-sm-3 modal-header2">
                <h4> <a href="visualizaGrupos.jsp">Meus Gupos</a></h4>
                
        </div>
        <c:forEach items="${grupos}" var="grupo">
            <div class="col-sm-3 modal-header2">
                <h2> <a href="ControleVerGrupo?nome=${grupo.nome}">${grupo.nome}</a></h2>
                
            </div>

        </c:forEach>       
    </div>
