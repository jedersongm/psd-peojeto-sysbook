<%@tag body-content="empty" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test = "${mensagem != null}">
        <div class="alert alert-danger">
            <strong>Atenção:</strong> ${mensagem}
        </div> 
    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
            <strong>Atenção:</strong> Entre com seu Usuario e Senha
        </div>
    </c:otherwise>

</c:choose>