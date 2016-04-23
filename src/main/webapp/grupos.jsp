<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="javascript/javascriptOutras.js"></script>
<div class="modal-backdrop text-center" id="cadastroGrupo">
    <div class="flutuarSomenteAdireita text-center">
        <h2><a class="textoBranco" href="#" id="closeGroup">X</a></h2>
    </div>
    <div class="modal-dialog modalMetade centro modalGrupo">
        <div class="form-group">
            <form action="ControleCadastroGrupo" method="post">
                <input class="botaoMedioEspecial margin-top" type="text" placeholder="Nome do grupo" name="nomeDoGrupo" required=""><br>
                <textarea class="botaoMedioEspecial margin-top textArea" name="descricao" placeholder="Descriçao" required=""></textarea><br><br>
                <input class="botaoPequeno" type="submit" value="Criar">
            </form>
        </div>
    </div>
</div>