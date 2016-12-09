<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./favicon.ico">
    <title>Área de Login</title>
    <link href="./Outros/css/bootstrap.min.css" rel="stylesheet">
    <link href="./index.css" rel="stylesheet">
  </head>

  <body background="./Outros/background.jpg">

    <div class="container">
      <form class="form-signin" method="POST" action="login.php">
      <center>
        <img alt="Logomarca" src="logomarca.png" height="170" width="230">
      </center>  
      	<div class="input-group">
        <label for="field_username" class="sr-only">email</label>
          <span class="input-group-addon glyphicon glyphicon-user" id="sizing-addon1"></span>

        <input type="text" name="field_username" id="field_username" class="form-control" placeholder="Email" required autofocus>
        </div>	

      	<div class="input-group">
        <label for="field_password" class="sr-only">senha</label>
          <span class="input-group-addon glyphicon glyphicon-asterisk" id="sizing-addon1"></span>
        <input type="password" name="field_password" id="field_password" class="form-control" placeholder="Senha" required>
        </div>	

      	<div class="button-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit" style="  padding-top: 7px;">Entrar</button>
         </div>	

         <?php if ($_GET["loginFail"]) echo '<div class="alert alert-danger" role="alert" style="margin: 5 auto;">Login incorreto. Tente novamente!</div>
' 
  	?>

      </form>
    </div>
    
    <script src="./Outros/js/ie10-viewport-bug-workaround.js"></script>
    
  </body>
</html> 