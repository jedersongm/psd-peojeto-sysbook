
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="grupos" class="tab-pane text-center">
    <br>
    <div class="modal-dialog text-center">
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#criarGrupo"><span class="glyphicon glyphicon-plus-sign"></span>Novo grupo</button>
    </div>
    <c:if test="${grupos == null}">
        <h3>Você ainda não faz parte de nenhum grupo!</h3>
    </c:if>
    <c:if test="${grupos != null}">
        <div class="temaGrupo">Seus grupos!</div>
    </c:if>
    <br>
    <div class="row-grupo">
        <c:forEach items="${grupos}" var="g">
            <div class="col-sm-3 modal-header">
                <br>
                <h2><a href="verGrupo.jsp?idGrupo=${g.id}">${g.nome}</a></h2>
            </div>
        </c:forEach>
    </div>
    
</div>

<!-- Modal -->
<div id="criarGrupo" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Cadastro de Grupo</h4>
      </div>
      <div class="modal-body">
          <form role="form" action="ControleCadastroGrupo" method="post">
              <div class="form-group">
                  <label for="groupName">Nome</label>
                  <input type="text" name="groupName" class="form-control" id="groupName">
              </div>
              
              <div class="form-group">
                  <label for="groupDescription">Descrição</label>
                  <input type="text" name="groupDescription" class="form-control" id="groupDescription">
              </div>
               
          <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-save"></span>Salvar</button>
          </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal"><span class="glyphicon glyphicon-remove">Close</button>
        <button type="reset" class="btn btn-default pull-left" >Limpar</button>
      </div>
    </div>
  </div>
</div>