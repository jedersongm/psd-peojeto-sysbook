<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body class = templatemo-bg-image-2>
    <div class="row media-object">
        <h3>Solicitações de Amizade</h3>
        <c:if test="${solicitantes == null}">
            <div class="col-md-5">
            <div class="alert alert-danger">
                <strong>Atenção:</strong> Você não possui solicitações de Amizade
            </div>
          </div>
        </c:if>
        <c:if test="${solicitantes != null}">
          <c:forEach items="${solicitantes}" var="user">
              <div class="col-sm-3 modal-header">
                  <a href="AbrirUsuario?id=${user.id}">
                  <img src="${user.foto}" alt="${user.apelido}" class="img-circle img-responsive" width="80" height="60">
                  </a><legend> ${user.apelido}</legend>
              </div>
          </c:forEach>
        </c:if>
    </div>
