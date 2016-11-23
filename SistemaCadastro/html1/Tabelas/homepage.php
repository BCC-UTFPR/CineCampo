<?php
session_start();

if ( !isset($_SESSION['username']) and !isset($_SESSION['password']) ) {
    session_destroy();

    unset ($_SESSION['username']);
    unset ($_SESSION['password']);

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

    <nav class="navbar navbar-fixed-top navbar-default ">

      <a class="navbar-brand" >Steinmacher Transportes</a>
      <ul class="nav navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="homepage.php">Área de Cadastro</a>
        </li>
      </ul>
     </ul>
    </nav>

    <div class="container">
    <h2> Cadastrar Gasto </h2>
     <form class="form-signin" method="POST" action="cadastrar_gastos.php" enctype="multipart/form-data">

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
			$username = "fronche2_admin";
			$password = "adminadmin";
			$database = "fronche2_engsoftware";
			$table = "Gastos";

			/* Autenticação */
			$conn = mysql_connect("$host","$username","$password") or die ("Impossível conectar!");
			mysql_select_db("$database") or die ("Database inválida. Tente novamente.");

			$sql = "SELECT ID_Viagem,Funcionario_ID_Funcionario,Veiculo_ID_Veiculo FROM Viagem";
			$result = mysql_query($sql);

			while ($row = mysql_fetch_array($result)) {
			    echo "<option>ID Viagem:" . $row['ID_Viagem']. " / ID Funcionário: " .$row['Funcionario_ID_Funcionario']. " / ID Veículo: " .$row['Veiculo_ID_Veiculo']. "</option>";
			}
			?>
		  </select>
		</div>
	</div>

	<div class="input-group">
		  <span class="input-group-addon" id="basic-addon-three">Tipo:</span>
   		   <input type="text" class="form-control" name="field_tipo"id="field_tipo" aria-describedby="basic-addon-three" required
                data-fv-notempty-message="(Campo Vazio)">
	</div>
	<div class="input-group">
  		<span class="input-group-addon" id="basic-addon">Descrição:</span>
	        <input type="text" class="form-control" name="field_descricao" id="field_descricao" aria-describedby="basic-addon" required
                data-fv-notempty-message="(Campo Vazio)">
	</div>
	<div class="input-group">
	  	<span class="input-group-addon" id="basic-addon-two">Valor:</span>
     		 <input type="number" min="0" step="0.10" placeholder="0,0" class="form-control" name="field_valor" id="field_valor" aria-describedby="basic-addon-two" required
                data-fv-notempty-message="(Campo Vazio)">
                  <span class="input-group-addon">R$</span>
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
