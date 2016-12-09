<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title>CineCampo</title>

		<!-- Loading third party fonts -->
		<link href="http://fonts.googleapis.com/css?family=Roboto:100,300,400,700|" rel="stylesheet" type="text/css">
		<link href="fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="fonts/lineo-icon/style.css" rel="stylesheet" type="text/css">

		<!-- Loading main css file -->
		<link rel="stylesheet" href="style.css">
		
		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->

	<body class="slider-collapse">
		
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

					<div class="right-section pull-right">
						<a href="#" class="login-button">Criar uma conta</a>
					</div> <!-- .right-section -->

					<div class="main-navigation">
						<button class="toggle-menu"><i class="fa fa-bars"></i></button>
						<ul class="menu">
							<li class="menu-item home current-menu-item"><a href="index.php"><i class="icon-home"></i></a></li>
							<!-- ARRUMAR -->
							<li class="menu-item"><a href="#lancamentos">Lançamentos</a></li>
							<li class="menu-item"><a href="#breves">Breve</a></li>
							</li>
						</ul> <!-- .menu -->
						<div class="mobile-navigation"></div> <!-- .mobile-navigation -->
					</div> <!-- .main-navigation -->
				</div> <!-- .container -->
			</div> <!-- .site-header -->

			<a name="lancamentos"></a>
						<?php 
						$json = file_get_contents('http://138.68.15.190:8080/distribuidos/filmes/atuais');
						$json_data = json_decode($json,true);
						#print_r($json_data);
						$x = count($json_data["Filmes"]);
						for ($i=0; $i < $x; $i++) { 
							foreach ($json_data as $key => $value): ?> 
						<div class="home-slider">
							<ul class="slides">
								<div class="container">
									<li data-bg-image="dummy/slide-1.jpg">
										<div class="slide-content">
											<h2 class="slide-title"><?php print_r($json_data["Filmes"][$i]["nome"]);?></h2>
											<small class="slide-subtitle"><?php print_r($json_data["Filmes"][$i]["genero"]);?></small>
											<p><?php print_r($json_data["Filmes"][$i]["sinopse"]);?></p>
											<p><b><?php print_r($json_data["Filmes"][$i]["sessao"]);?></b></p>
											<p><b>3D: </b><?php print_r($json_data["Filmes"][$i]["tresdimensoes"]);?> </p>
											<small class="slide-subtitle">Nota: <?php print_r($json_data["Filmes"][$i]["avaliacao"]);?> </small>
											<p> 
											<a href=<?php print_r($string)?> target="_blank" class="button">Trailer</a>
											<a href="single.php?id=<?php print_r($json_data["Filmes"][$i]["id"]); ?>&" class="button">Comentar</a>
											<a href="single_comentarios.php?id=<?php print_r($json_data["Filmes"][$i]["id"]); ?>&" class="button">Comentários</a>
											</p>
										</div>
										<img style="width: 250px; height: 400px;" src=<?php print_r($json_data["Filmes"][$i]["imagemurl"]);?> class="slide-image">
									</li>
								</div>
							</ul> <!-- .slides -->
						</div> <!-- .home-slider -->
						<?php endforeach; ?>
						<?php } ?>
			<a name="breves"></a>	
			<main class="main-content">
				<div class="container">
					<div class="page">
						<section>
							<header>
							<a name="breves"></a>
								<h2> <b>Breve</b></h2>
							</header>

							<?php 
							$json = file_get_contents('http://138.68.15.190:8080/distribuidos/filmes/breves');
							$json_data = json_decode($json,true);
							#print_r($json_data);
							$x = count($json_data["Filmes"]);
							for ($i=0; $i < $x; $i++) { 
								foreach ($json_data as $key => $value): ?> 
									<?php $string = $json_data["Filmes"][$i]["videourl"];?>
									<?php $nova = str_replace('\u003d', '=', $string); ?>
							<div class="product-list">
								<div class="product">
									<div class="inner-product">
										<div class="figure-image">
											<img src=<?php print_r($json_data["Filmes"][$i]["imagemurl"]);?> alt="Game 1"></a>
										</div>
										<h3 class="product-title"><?php print_r($json_data["Filmes"][$i]["nome"]);?></a></h3>
										<small class="price"><?php print_r($json_data["Filmes"][$i]["genero"]);?></small>
										<p><?php print_r($json_data["Filmes"][$i]["sinopse"]);?></p>
										<p><b>Duração: </b><?php print_r($json_data["Filmes"][$i]["duracao"]);?></p>
										<a href=<?php print_r($string)?> target="_blank" class="button">Trailer</a>
									</div>
								</div> <!-- .product -->
								<?php endforeach; ?>
						<?php } ?>	
						</section>
					</div>
				</div> <!-- .container -->
			</main> <!-- .main-content -->

			<div class="site-footer">
				<div class="container">
					<div class="row">
						<div class="col-md-2">
							<div class="widget">

					<div class="colophon">
						<div class="copy">Copyright CineCampo 2016 - Todos os Direitos Reservados
						</div>
					</div> <!-- .colophon -->
				</div> <!-- .container -->
			</div> <!-- .site-footer -->
		</div>

		<div class="overlay"></div>
		<div class="auth-popup popup">
			<a href="#" class="close"><i class="fa fa-times"></i></a>
			<div class="row">
				<div class="col-md-6">
					<h2 class="section-title">Criar uma Conta</h2>
					<form action="#">
						<input type="text" name="usuario_cadastrar" placeholder="Nome Usuário">
						<input type="text" name="senha_cadastrar"  placeholder="Senha">
						<input type="submit" id="submit_cadastrar" value="Registrar">
					</form>
				</div> <!-- .column -->
			</div> <!-- .row -->
		</div> <!-- .auth-popup -->

		<?php
			$usuario = $_GET['usuario_cadastrar'];
			$senha = md5($_GET['senha_cadastrar']);
			$url = "http://138.68.15.190:8080/distribuidos/usuarios/cadastrar/{$usuario}/{$senha}";
			#print_r($url);
			$json = file_get_contents($url);
			$json_data = json_decode($json,true);
			if ($json_data["STATUS"]["SUCESSO_AO_CADASTRAR"]) {
				 echo "<script type='text/javascript'>alert('Cadastro Realizado com Sucesso !')</script>";
			}

		?>

		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>
		
	</body>

</html>