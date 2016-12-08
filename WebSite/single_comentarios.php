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

		<!-- Loading main css file -->
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
								<?php $id = $json_data["Filmes"][$i]["id"];?>
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
											<h2 class="entry-title"><?php print_r("Comentários do Filme ".$json_data["Filmes"][$i]["nome"]);?></h2>
											<?php $json_comentarios = file_get_contents('http://138.68.15.190:8080/distribuidos/comentarios/listar/'.$id);?>
											<?php $json_data_comentarios = json_decode($json_comentarios,true);?>
											<?php $y = count($json_data_comentarios["Comentários"]); ;?>
											<?php for ($z=0; $z < $y; $z++) : ?>
												<?php foreach ($json_data_comentarios as $key => $value): ?>
											<p><b>Usuário: </b><?php print_r($json_data_comentarios["Comentários"][$z]["usuario"]) ?></p>
											<p><b>Comentário: </b><?php print_r($json_data_comentarios["Comentários"][$z]["comentario"]) ?></p>
												<?php endforeach; ?>
											<?php endfor; ?>
										</div>
									</div>
								</div>
								<?php } ?>
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
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>
		
	</body>

</html>