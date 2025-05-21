<<<<<<< HEAD
# Squad04-FSPH 2.0
Projeto de residência para a organização do Lacen para área de  entomologia
=======
# Como rodar o programa 
# pre requisitos
primeiro é necessario ter as seguintes ferramentas instaladas na seguinte ordem
-wsl 2
-uma distro linux do wsl (ubuntu , mint etc)
-docker
-postman

# passo 1
ir no terminal do intelij e selecionar o bash (terminal do git/ubuntu)
apos isso digitar o comando docker-compose up --build para criar a build do docker q vai ser usado como um mini banco de dados 
# importante
apos o container ter sido criado , para rodar so precisa do comando  docker-compose up
digite apenas o --build APENAS se vc tiver deletado o container anterior ou se ele nao existir

# passo 2 
clicar no botao de play e rodar a aplicaçao 

espere ate a notificaçao " tomcat port 8080 " chegar para abrir o postman

# passo 3
com tudo isso feito é so ir no postman com a URL base http://localhost:8080 para a execuçao dos testes






