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
       <li class="active"><a href="filmes.php">Filmes Lançamentos</a></li>
       <li class="active"><a href="breves.php">Cadastrar Próximos Filmes (Breves)</a></li>
       <li class="active"><a href="filmes_breves.php">Filmes Breves</a></li>
       <li class="active"><a href="comentarios.php">Visualizar Comentários</a></li>
       </ul>
      </div>
  </nav>

    <div class="container">
    <h2> Filmes </h2>
     <form class="form-signin" method="POST" action="remover_breve.php" enctype="multipart/form-data">
        <div class="input-group">
         <?php if ($_GET["insertSuccess"]) echo '<div class="alert alert-success" role="alert" style="margin: 5 auto;">Remoção realizada com sucesso!</div>' ?>
         <?php if ($_GET["insertFailed"]) echo '<div class="alert alert-danger" role="alert" style="margin: 5 auto;">Submissão falhou! Impossível processar, tente novamente.</div>' ?>
         <?php if ($_GET["insertEmpty"]) echo '<div class="alert alert-danger" role="alert" style="margin: 5 auto;">Submissão falhou! Preencha todos os campos.</div>' ?>
  </div>

  <div class="input-group">
    <div class="form-group">
      <label for="viagem_list">Selecione o Filme Cadastrado (Breve):</label>
      <select class="form-control" id="viagem_list" name="viagem_list">
      <?php
      /* Informações do Banco de Dados*/
      $host = "138.68.15.190";
      $username = "root";
      $password = "asafaster";
      $database = "cinecampo";
      $table = "Breves";

      /* Autenticação */
      $conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
      mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

      $sql = "SELECT identificador,nome FROM Breves";
      $result = mysql_query($sql);

      while ($row = mysql_fetch_array($result)) {
          echo "<option>ID: " . $row['identificador']. " / Nome do Filme: " .$row['nome']. "</option>";
      }
      ?>
      </select>
    </div>
  </div>
  <!-- HOMEPAGE -->
    <div class="form-group">
        <span class="input-group-addon" id="basic-addon-three">Id do Filme</span>
           <input type="text" class="form-control" name="field_id" id="field_id" aria-describedby="basic-addon-three" required
                  data-fv-notempty-message="(Campo Vazio)">
    </div>
    <div class="button-group">
          <button class="btn btn-md btn-success btn-block" type="submit">Remover</button>
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
