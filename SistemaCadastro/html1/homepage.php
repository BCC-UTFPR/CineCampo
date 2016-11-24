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
       <li class="active"><a href="filmes.php">Filmes</a></li>
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
         <input type="text" class="form-control" name="field_nome_filme" id="field_nome_filme" aria-describedby="basic-addon-three" required
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
      <span class="input-group-addon" id="basic-addon">Inicio das Sessões:</span>
          <input type="text" class="form-control" name="field_inicio_sessoes" id="field_inicio_sessoes" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)">
  </div>
  <div class="form-group">
      <span class="input-group-addon" id="basic-addon">Término das Sessões:</span>
          <input type="text" class="form-control" name="field_termino_sessoes" id="field_termino_sessoes" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)">
  </div>
  <div class="form-group">
      <span class="input-group-addon" id="basic-addon">Salas</span>
  </div>
  <div class="form-check">
  <label class="form-check-label">
    <input class="form-check-input" type="checkbox" name="field_sala_1" id="field_sala_1" value="SIM">
    Sala 1 - 19:30hrs
  </label>
</div>
<div class="form-check">
  <label class="form-check-label">
    <input class="form-check-input" type="checkbox" name="field_sala_2" id="field_sala_2" value="SIM">
    Sala 2 - 21:30hrs
  </label>
</div>
<div class="form-group">
      <span class="input-group-addon" id="basic-addon">Filme 3D</span>
</div>
<select class="custom-select">
  <option selected>Selecione uma Opção</option>
  <option name="field_sala3d_sim" id="field_sala3d" value="SIM">Sim</option>
  <option name="field_sala3d_nao" id="field_sala3d" value="NAO">Não</option>
</select>
<div class="form-group">
      <span class="input-group-addon" id="basic-addon">Banner do Filme</span>
</div>
  <div class="input-group">
          <input type="file" name="field_imagem" required
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
