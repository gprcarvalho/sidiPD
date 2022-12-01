<h1 align="center"> Residência Onboard Porto Digital - Squad SiDi </h1>

<h2 align"center"> # Descrição do Projeto </h2> 
<p align="justify"> Este projeto trata-se de uma API construída utilizando a linguagem de programação Java, o framework Spring Boot e o banco de dados PostgreSQL. Este é um 
projeto para fins acadêmicos visando atender uma demanda real da empresa SiDi.
O projeto foi desenvolvido por sete alunos do curso de Análise e Desenvolvimento de Sistemas da faculdade Senac de Pernambuco.</p>

<img src="https://arquivo.portodigital.org/arqSite/residencia.png"/>

<h2 align="center"> # Problemática </h2>
Com adoção do modelo remoto e híbrido e a visita de times externos à unidade de recife, surgiu a necessidade de gerenciar os espaços de trabalho para garantir um local para cada colaborador, que precisa de estações de trabalho temporárias.

<h2 align="center"> # Solução </h2>
Uma aplicação simples de reserva de baias de trabalho, onde o administrativo possa adicionar, editar, excluir ou bloquear os espaços, para que colaborador visualize a disponibilidade, realize o agendamento, e caso necessário, possa editar e cancelar.

<h2 align="center"> # Lógica de Funcionamento da Solução </h2>
Sistema desenvolvido para efetuar as reservas das estações de trabalho. Serão cadastrados três tipos de usuários. Administrador, gestor e colaborador, cada uma deles com suas funcionalidades dentro do sistema. O usuário colaborador poderá reservar, atualizar, visualizar e cancelar suas solicitações de reserva no sistema. Já o gestor terá as mesmas funcionalidades do colaborador, incluindo também reservar mais de uma estação no mesmo dia e visualizar, alterar e excluir todas as reservas feitas por outros usuários.  O usuário administrador terá todas as permissões possíveis dentro do sistema, além de todas as permissões já citadas, ele poderá também excluir, atualizar, cadastrar e visualizar usuários e estações. 

<h2 align="center"> # Linguagens, frameworks e tecnologias utilizadas </h2>
<ul>
  <li> Java </li>
  <li> Spring Boot </li>
  <li> PostgreSQL </li>
  <li> Insomnia / Postman </li>
  <li> Git / Github </li>
</ul>
  
  <h2 align="center"> # Preparação do Ambiente </h2>
<ul>
<li> Baixar e fazer instalação do  Java  17 - https://www.azul.com/downloads/?package=jdk </li>
<li> Baixar o Spring Tools 4 - https://spring.io/tools </li>
<li> Fazer a instalação do lombok - https://www.youtube.com/watch?v=W0ywxkvc4_M </li>
<li> Baixar e fazer instalação do PostgreSQL - https://www.postgresql.org/download/ </li>
<li> Baixar e instalar o Insomnia para fazer as requisições - https://insomnia.rest/download
</ul>

<h2 align="center"> # Funcionalidades e rotas para consumo da API </h2>

<ul>
  <li><h2>Funcionalidades User</h2>
  <li>Visualizar todos os usuários cadastrados
  <li>Buscar usuário por ID
  <li>Cadastrar usuário 
  <li>Atualizar usuário 
  <li>Deletar usuário 
</ul>
<ul>
<li><h2>Rotas API(User)</h2>
<li>/api/users   
<li>/api/user/{id}
<li>/api/user
<li>/api/user/{id}
<li>/api
</ul>
<ul>
<li><h2>Métodos de Requisição HTTP(User)</h2>
<li>GET /api/users
<li>GET /api/users/{id}
<li>POST /api/user
<li>PUT /api/user/{id}
<li>DELETE /api
</ul>
<ul>
  <li><h2>Funcionalidades Login</h2>
  <li>Realizar Login
</ul>
<ul>
  <li><h2>Rotas API(Login)</h2>
    <li>/api/login
</ul>
<ul>
  <li><h2>Método de requisição(Login) HTTP</h2>
    <li>POST /api/login
</ul>
<ul>
  <li><h2>Funcionalidades Station</h2>
    <li>Visualizar todos as estações cadastradas
    <li>Buscar estação por ID
    <li>Cadastrar estação
    <li>Atualizar estação
    <li>Deletar estação
    <li>Visualizar estações disponíveis por data
</ul>
<ul>
  <li><h2>Rotas API(Station)</h2>
  <li>/api/stations
  <li>/api/station/{id}
  <li>/api/station
  <li>/api/station/{id}
  <li>/api/station/{id}
  <li>/api/station/date
</ul>
<ul><h2>Métodos de requisição HTTP</h2>
  <li>GET /api/stations
  <li>GET /api/station/{id}
  <li>POST /api/station
  <li>PUT /api/station/{id}
  <li>DELETE /api/station/{id}
  <li>POST /api/station/date
</ul>

<h2 align="center"> # Equipe </h2>

:man_technologist: Mateus Rodrigues (DEV) - > https://github.com/mateusrdn

:man_technologist: Guilherme Régis (DEV) - > https://github.com/gprcarvalho

:woman_technologist: Maria Gabriella (PO) - > https://github.com/MGabriellaS

:man_technologist: Gabriel Ribeiro - > https://github.com/Gabriel5291

:man_technologist: Artur Lima - > https://github.com/Arturlima77

:man_technologist: Gustavo César 

:man_technologist: Marcelo Cláudio








