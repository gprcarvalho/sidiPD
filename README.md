<h1 align="center">SiDi WorkStations</h1>
<h3 align="justify">Projeto acadêmico da residência Onboard em software do Porto Digital em parceria com a Faculdade Senac de Pernambuco.</h3>
<img src="https://arquivo.portodigital.org/arqSite/residencia.png"/>
<h2 align="center">Problemática</h2>
<p align="justify">Com adoção do modelo remoto e híbrido e a visita de times externos à unidade de Recife, surgiu a necessidade de gerenciar os espaços de trabalho para garantir um local para cada colaborador que precisa de estações de trabalho temporárias e também para visitantes.</p>
<h2 align="center">Solução</h2>
<p align=justify">Uma aplicação simples de reserva de estações de trabalho, onde o administrativo possa adicionar, editar e excluir os espaços, para que colaborador visualize a disponibilidade, realize o agendamento, e caso necessário, possa editar e cancelar.</p>
                 <h2 align="center">Equipe</h2>
                                                      <ul>
                                                     <li> 👨‍💻 Guilherme Régis - Desenvolvedor -> https://github.com/gprcarvalho </li>
                                                     <li> 👨‍💻 Mateus Rodrigues - Desenvolvedor -> https://github.com/mateusrdn </li> 
                                                     <li> 👨‍💻 Artur Bernardino - Desenvolvedor -> https://github.com/Arturlima77 </li>
                                                     <li> 👨‍💻 Gabriel Ribeiro - Scrum Master -> https://github.com/Gabriel5291 </li>
                                                     <li> 👨‍💻 Marcelo Cláudio - Tester -> https://github.com/MarceloClaudio </li>
                                                     <li> 👨‍💻 Gustavo César - Tester </li>
                                                     <li> 👩‍💻 Maria Gabriella - Product Owner -> https://github.com/MGabriellaS </li>
                                                     </ul>
<h2 align="center">Tecnologias, Linguagens e Frameworks</h2>
<ul>
                  <li>Java</li>
                  <li>Spring Boot</li>
                  <li>PostgreSQL</li>
                  <li>Insonmia</li>
                  <li>Git/Github
                  </ul>
                  <h2 align="center">Preparação do Ambiente</h2>
                                    <ul>
                                    <li>Baixar e fazer instalação do  Java  17 - https://www.azul.com/downloads/?package=jdk</li>
                                    <li>Baixar o Spring Tools 4 - https://spring.io/tools</li>
                                    <li>Fazer a instalação do lombok - https://www.youtube.com/watch?v=W0ywxkvc4_M</li>
                                    <li>Baixar e fazer instalação do PostgreSQL - https://www.postgresql.org/download/</li>
                                    <li>Baixa e instalar o Insonmia - https://insomnia.rest/download</li>
                                    </ul>
                                    <h2 align="center">Rodando a API</h2>
                                    <ol>
                                    <li>Abrir o Spring Tools 4</li>
                                    <li>Importar o projeto Maven</li>
                                    <li>Criar o database no PostgreSQL</li>
                                    <li>Inserir no arquivo de config Application.properties o user e a senha do database criado</li>
                                    <li>Abrir o Insonmia e executar as requisições com as rotas descritas no tópico abaixo</li>
                                    <li>Utilizar a porta 8080 do localhost</li>
                                    </ol>
                                    <h2 align="center">Funcionalidades e Rotas da API</h2>
                                                                                  
                                    
                                          | Funcionalidades User |          | Rotas API |          | Métodos de requisição HTTP |
                                    
                                          | -------------------- |          | --------- |          | ------------------------- |
                                          | Visualizar todos os users |     | /api/users |         | GET |
                                          | Buscar usuário por ID |         | /api/user{id} |      | GET |
                                          | Cadastrar Usuário |             | /api/user |          | POST |
                                          | Atualizar Usuário |             | /api/user{id} |      | PUT |
                                          | Deletar Usuário |               | /api |               | DELETE |
                                                      
                                          | Funcionalidades Login |         | Rotas API |          | Métodos de requisição HTTP |
                                           
                                          | --------------------- |         | --------- |          | -------------------------- |
                                          | Realizar Login |                | /api/login |         | POST |
                                                      
                                          | Funcionalidades Station |       | Rotas API |          | Método de requisição HTTP |
                                           
                                          | ----------------------- |       | --------- |          | ------------------------- |
                                            Visualizar todas as             
                                            workstations disponíveis |      | /api/stations |      | GET |
                                          | Buscar workstation por ID |     | /api/station/{id} |  | GET |
                                          | Cadastrar workstation |         | /api/station |       | POST |
                                          | Atualizar workstation |         | /api/station{id} |   | PUT |
                                          | Deletar workstation |           | /api/station/{id} |  | DELETE |
                                          | Visualizar workstations         | /api/station/date |  | POST |
                                            disponíveis por data |

                                          | Funcionalidades Reservations |  | Rotas API |                       | Método de requisição HTTP |
                                                 
                                          | ---------------------------- |  | --------- |                       | ------------------------- |
                                          | Visualizar reservas efetuadas | | /api/reservations |               | GET |
                                          | Buscar reservas por ID |        | /api/reservation/{id} |           | GET |
                                          | Fazer uma reserva |             | /api/reservation/me |             | POST |
                                          | Fazer reserva p/ outro user |   | /api/reservation/for |            | POST |
                                          | Atualizar reserva |             | /api/reservation/{id} |           | PUT |
                                          | Cancelar uma reserva |          | /api/reservation/canceled/{id} |  | PUT |
                                          | Visualizar todas as reservas    | /api/reservations/{id} |          | GET |
                                            efetuadas por cada usuário |
                                       
                                                      
                                                 
                                                                                     
