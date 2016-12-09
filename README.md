<h1> CineCampo </h1>
Este é um sistema distribuído completo, relacionado à uma empresa de cinemas fictícia. :movie_camera:
É divido em três partes:

(a) Web Service -  Utiliza o protocolo rest, aplicando métodos GET, POST, PUT E UPDATE. É implementado usando o Eclipse JAVA EE, Apache Tomcat 7 e o Jersey framework. Utiliza o padrão de projeto JAVA EE 6 CDI (Injeção de dependências e contextos).

(b) Administração - É o website da administração, se comunica diretamente com o banco de dados MySQL. Cadastra filmes em cartaz, em breve, verifica usuários, comentários e afins. Escrito na linguagem PHP, usando Bootstrap para o front-end. 

(c) Clientes - Os clientes são divididos entre o aplicativo para smartphone e o website, ambos com as mesmas funcionalidades. São os responsáveis por utilizar o Web Service para popular os dados necessários, cadastrar comentários e afins. O website é feito em PHP e o aplicativo em Java Android.

__Para maiores informaçes, favor consultar o artigo presente em nosso repositório.__
