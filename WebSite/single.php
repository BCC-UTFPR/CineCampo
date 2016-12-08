<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title>Cinecampo - Comentários</title>

		<!-- Loading third party fonts -->
		<link href="http://fonts.googleapis.com/css?family=Roboto:100,300,400,700|" rel="stylesheet" type="text/css">
		<link href="fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="fonts/lineo-icon/style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

		<!-- Loading main css file -->
		<link rel="stylesheet" href="ranking_bar.css">
		<link rel="stylesheet" href="style.css">

		
		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->

	</head>


	<body>
		
		<div id="site-content">
			<div class="site-header">
				<div class="container">
					<a href="index.php" id="branding">
						<img src="images/logo1.png" alt="" class="logo">
						<div class="logo-text">
							<h1 class="site-title">CineCampo</h1>
							<!--
							<small class="site-description">Tagline goes here</small>
							-->
						</div>
					</a> <!-- #branding -->
					<div class="main-navigation">
						<button class="toggle-menu"><i class="fa fa-bars"></i></button>
						<ul class="menu">
							<li class="menu-item home current-menu-item"><a href="index.php"><i class="icon-home"></i></a></li>
							<!-- ARRUMAR -->
							<li class="menu-item"><a href="index.php#lancamento">Lançamentos</a></li>
							<li class="menu-item"><a href="index.php#breves">Breve</a></li>
							</li>
						</ul> <!-- .menu -->
						<div class="mobile-navigation"></div> <!-- .mobile-navigation -->
					</div> <!-- .main-navigation -->
				</div> <!-- .container -->
			</div> <!-- .site-header -->
			

			<main class="main-content">
				<div class="container">
					<div class="page">

					<?php 
						$id_url = $_GET["id"];
						$json = file_get_contents('http://138.68.15.190:8080/distribuidos/filmes/atuais');
						$json_data = json_decode($json,true);
						#print_r($json_data);
						$x = count($json_data["Filmes"]);
						for ($i=0; $i < $x; $i++) { 
							foreach ($json_data as $key => $value): ?>
							<?php $id = $json_data["Filmes"][$i]["id"]; ?>
							<?php if ($id_url == $id) { ?>
								<div class="entry-content">
							<div class="row">
								<div class="col-sm-6 col-md-4">
									<div class="product-images">
										<figure class="large-image">
											<img src=<?php print_r($json_data["Filmes"][$i]["imagemurl"]);?> alt="">
										</figure>
									</div>
								</div>
								<div class="col-sm-6 col-md-8">
									<h2 class="entry-title"><?php print_r($json_data["Filmes"][$i]["nome"]);?></h2>
									<form action="#">
										<b>ID:</b>
										<p><input type="text" name="id" value="<?php print_r($id_url);?>"></p>
										<p><input type="text" name="usuario_comentar" placeholder="Nome Usuário"></p>
										<p><input type="password" name="senha_comentar"  placeholder="Senha"></p>
										<p><textarea cols="45" rows="10" name="comentario" placeholder="Digite seu Comentário"></textarea></p>
										<div class="stars">
										    <input class="star star-5" id="star-5" value="5" type="radio" name="star"/>
										    <label class="star star-5" for="star-5"></label>
										    <input class="star star-4" id="star-4" value="4" type="radio" name="star"/>
										    <label class="star star-4" for="star-4"></label>
										    <input class="star star-3" id="star-3" value="3" type="radio" name="star"/>
										    <label class="star star-3" for="star-3"></label>
										    <input class="star star-2" id="star-2" value="2" type="radio" name="star"/>
										    <label class="star star-2" for="star-2"></label>
										    <input class="star star-1" id="star-1" value="1" type="radio" name="star"/>
										    <label class="star star-1" for="star-1"></label>
										</div> 
										<p><input type="submit" id="submit_comentar" value="Comentar"></p>
									</form>	
								</div>
							</div>
						</div>
							<?php }?>
						
						<?php endforeach; ?>
						<?php } ?>
			<div class="site-footer">
				<div class="container">
					<div class="colophon">
						<div class="copy">Copyright CineCampo 2016 - Todos os Direitos Reservados</div>
						</div> <!-- .social-links -->
					</div> <!-- .colophon -->
				</div> <!-- .container -->
			</div> <!-- .site-footer -->
		</div>

		<?php
			$usuario = $_GET['usuario_comentar'];
			$senha = md5($_GET['senha_comentar']);
			$comentario = $_GET['comentario'];
			$nota = $_GET['star'];
			$url_logar = "http://138.68.15.190:8080/distribuidos/usuarios/logar/{$usuario}/{$senha}";
			$json_logar = file_get_contents($url_logar);
			$json_data_logar = json_decode($json_logar,true);
			if ($json_data_logar["STATUS"]["SUCESSO_AO_LOGAR"]) {
				$url_nota = "http://138.68.15.190:8080/distribuidos/filmes/avaliar/{$id_url}/{$nota}";
				//print_r($url_nota);
				$json_url = file_get_contents($url_nota);
				$json_data_nota = json_decode($json_url,true);
				$url1 ="http://138.68.15.190:8080/distribuidos/comentarios/comentar/{$id_url}/{$usuario}/{$comentario}";
				$json1 = file_get_contents($url1);
				$json_data1 = json_decode($json1,true);
				if ($json_data1["STATUS"]["SUCESSO_AO_COMENTAR"]) {
					echo "<script type='text/javascript'>alert('Comentário Realizado com Sucesso !')</script>";
				}
			}

		?>

		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>
		
	</body>

</html>