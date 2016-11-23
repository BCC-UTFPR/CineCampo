<?php
session_start();
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
    <h2> Filmes </h2>
     <form class="form-signin" method="POST" action="cadastrar_filmes.php" enctype="multipart/form-data">

        <div class="input-group">
         <?php if ($_GET["insertSuccess"]) echo '<div class="alert alert-success" role="alert" style="margin: 5 auto;">Submissão realizada com sucesso!</div>' ?>
         <?php if ($_GET["insertFailed"]) echo '<div class="alert alert-danger" role="alert" style="margin: 5 auto;">Submissão falhou! Impossível processar, tente novamente.</div>' ?>
         <?php if ($_GET["insertEmpty"]) echo '<div class="alert alert-danger" role="alert" style="margin: 5 auto;">Submissão falhou! Preencha todos os campos.</div>' ?>
  </div>

  <div class="input-group">
    <div class="form-group">
      <label for="viagem_list">Selecione a viagem e preencha o formulário a seguir:</label>
      <select class="form-control" id="viagem_list" name="viagem_list">
      <?php
      /* Informações do Banco de Dados*/
      $host = "localhost";
      $username = "root";
      $password = "asafaster";
      $database = "cinecampo";
      $table = "CadastrarFilme";

      /* Autenticação */
      $conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
      mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

      $sql = "SELECT id,nomeFilme FROM CadastrarFilme";
      $result = mysql_query($sql);

      while ($row = mysql_fetch_array($result)) {
          echo "<option>ID: " . $row['id']. " / Nome do Filme: " .$row['nomeFilme']. "</option>";
      }
      ?>
      </select>
    </div>
  </div>
  <!-- HOMEPAGE -->

  

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
