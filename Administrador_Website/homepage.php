<?php
session_start();

if ( !isset($_SESSION['usuario']) and !isset($_SESSION['senha']) ) {
    session_destroy();

    unset ($_SESSION['usuario']);
    unset ($_SESSION['senha']);

    header('location:index.php');
}
?>

<html>
  <head>
      <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./favicon.ico">
    <title>Área de Cadastro</title>
    <link href="./Outros/css/bootstrap.min.css" rel="stylesheet">
    <link href="./cadastro.css" rel="stylesheet">
  </head>

  <body>

    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container-fluid">
       <div class="navbar-header">
         <a class="navbar-brand" href="homepage.php">CineCampo</a>
       </div>
      <ul class="nav navbar-nav">
       <li class="active"><a href="homepage.php">Cadastrar Filme</a></li>
       <li class="active"><a href="filmes.php">Filmes Lançamentos</a></li>
       <li class="active"><a href="breves.php">Cadastrar Próximos Filmes (Breves)</a></li>
       <li class="active"><a href="filmes_breves.php">Filmes Breves</a></li>
       <li class="active"><a href="comentarios.php">Visualizar Comentários</a></li>
       </ul>
      </div>
  </nav>

    <div class="container">
    <h2> Cadastrar Filme </h2>
     <form class="form-signin" method="POST" action="cadastrar_filmes.php" enctype="multipart/form-data">
        <div class="input-group">
         <?php if ($_GET["insertSuccess"]) echo '<div class="alert alert-success" role="alert" style="margin: 5 auto;">Submissão realizada com sucesso!</div>' ?>
         <?php if ($_GET["insertFailed"]) echo '<div class="alert alert-danger" role="alert" style="margin: 5 auto;">Submissão falhou! Impossível processar, tente novamente.</div>' ?>
         <?php if ($_GET["insertEmpty"]) echo '<div class="alert alert-danger" role="alert" style="margin: 5 auto;">Submissão falhou! Preencha todos os campos.</div>' ?>
  </div>

  <!-- HOMEPAGE -->

  <div class="form-group">
      <span class="input-group-addon" id="basic-addon-three">Nome do Filme:</span>
         <input type="text" class="form-control" name="field_nome" id="field_nome" aria-describedby="basic-addon-three" required
                data-fv-notempty-message="(Campo Vazio)">
  </div>

  <div class="form-group">
      <span class="input-group-addon" id="basic-addon">Sinopse:</span>
          <textarea class = "form-control" rows = "4" name="field_sinopse" id="field_sinopse" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)"></textarea>
  </div>
  <div class="form-group">
      <span class="input-group-addon" id="basic-addon">Duração:</span>
          <input type="text" class="form-control" name="field_duracao" id="field_duracao" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)">
  </div>
  <div class="form-group">
      <span class="input-group-addon" id="basic-addon">Genero:</span>
          <input type="text" class="form-control" name="field_genero" id="field_genero" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)">
  </div>
  <div class="form-group">
      <span class="input-group-addon" id="basic-addon">Sessão</span>
  </div>
<div class="form-group">
  <select class="custom-select" name="field_sessao">
  <option selected >Selecione uma Opção</option>
  <option value="Sessão 1 - 19:30hrs">Sessão 1</option>
  <option value="Sessão 2 - 21:45hrs">Sessão 2</option>
</select>
</div>
  <div class="form-check">
</div>
<div class="form-group">
      <span class="input-group-addon" id="basic-addon">Filme 3D</span>
</div>
<div class="form-group">
<select class="custom-select"  name="field_sala3d">
  <option selected >Selecione uma Opção</option>
  <option value="Sim">Sim</option>
  <option value="Não">Não</option>
</select>
</div>
<div class="form-group">
      <span class="input-group-addon" id="basic-addon">Capa do Filme:</span>
          <input type="text" class="form-control" name="field_imagem" id="field_imagem" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)">
  </div>
  <div class="form-group">
      <span class="input-group-addon" id="basic-addon">Trailer:</span>
          <input type="text" class="form-control" name="field_trailer" id="field_trailer" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)">
  </div>

    <div class="button-group">
        <button class="btn btn-md btn-success btn-block " type="submit">Cadastrar</button>
  </div>

  <div class="sair-div" style="margin-top:15px;">
  <center><a class="sair glyphicon glyphicon-share-alt" name="sair" href="logout.php"> Sair</a></center>
  </div>
      </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="./Outros/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="./Outros/js/bootstrap.min.js"></script>
    <script src="./Outros/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
