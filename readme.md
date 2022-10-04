<h3>Features</h3>

- [x] Incluir itens no carrinho<br>
- [x] Visualizar carrinho<br>
- [x] Fechar carrinho<br>
- [x] Excluir item do carrinho<br>


<table>
<tr>
	<th>Ferramenta</th>
	<th>Versão</th>
</tr>
<tr>
	<td>Java JDK</td>
	<td>17</td>
</tr>
<tr>
	<td>Git</td>
	<td>2.**</td>
</tr>
<tr>
	<td>Gradle</td>
	<td>7.**</td>
</tr>
<tr>
	<td>Postman</td>
	<td>9.**</td>
</tr>
</table>
<h1 style="color:red">Aula 01</h1>

- Domínio da Aplicação (POO)
- Consumo da API (iFood)
  ![Requisição API](.//Requisi%C3%A7%C3%A3oAPI.png)
- Passo a passo Pensamento Computacional    
    - Coletar os dados
    - Analisar os dados
    - Decompor(Dividir em problemas menores)
    - Identificar Padrões
    - Trabalhar a Abstração
    - Criar os algoritmos(Sequência de pequenas soluções)
    - Construir Modelos(Simular, testar, experimentar)



### Paradigma de Programação Orientada a Objetos (POO)
> Modelo de análise, projeto e programação baseado na aproximação entre o mundo real e o mundo virtual, através da criação e interação entre Classes, Atributos, Métodos, objetos, entre outros...

##### 4 Pilares da POO (Princípio da Abstração):
    - Encapsulamento
    - Herança
    - Composição
    - Polimorfismo

##### Classe x Objeto
    > Classe: Aonde se encontram as características, funcionalidades(Ex: Ambientes da Casa)

    > Objetos: Representa o geral, o total, o que engloba as classes(Ex: Casa)

### Diagrama ER
![Diagrama ER](./Diagrama%20ER.jpeg)

<h1 style="color:red">Aula 02</h1>

## Construção de uma `API REST` com `JAVA` e `SPRING BOOT`
- Objetivo da aula:
    - A partir da abstração realizada na aula anterior, iremos modelar e desenvolver uma API REST usando a linguagem de programação Java e o framework Spring Boot. Para isso, entenderemos o estilo arquitetural REST e a estrutura de projetos Spring.

##### Arquitetura de uma Aplicação SpringBoot
![SpringBoot Architecture](./SpringBoot%20Architecture.jpeg)

```html
Utilizaremos as seguintes dependências:

    - Spring WEB (RESTful)
        > Apache Tomcat Container.

    - Spring Data JPA (SQL)
        > Persistência de Dados em SQL (Tabelas).
        > Hibernate (ORM).
    
    - H2 Database (In-memory database)
        > Driver do BD (Sistema de Gerenciamento).
        > Uso para testes. 
    
    - Lombok (Optional)
        > Usado neste caso para abstrair os Constructors, Getters and Setters, etc...
    
    - SpringFox Boot Starter
        > Swagger UI
```

Criamos também um arquivo `data.sql` dentro do folder
`resources` a fim de testarmos o carrinho de compras,
adicionando as classes `restaurante, cliente, produto, carrinho`
seus respectivos valores/nomes vistos no Banco de Dados.

Após, criaremos a Camada de Persistência, dentro de
`me.dio.carrinhoApi` , onde estarão as classes (Interfaces)
que se conectaram com o Banco de Dados, criando apenas aonde
possuem tabelas, no caso, as classes que possuem `@Entity` 

Criadas as interfaces, extenderemos a interface através do 
`JpaRepository<T, ID>`, `T: Tabela, ID: Primary Key`, facilitando
sua conexão ao banco de dados e abstraindo várias linhas de
código

<h1 style="color:red">Aula 03</h1>

Criamos o package `resources` a fim de remeter a parte de controller 
da API, para se comunicar com os métodos HTTP, utilizando um DTO.

Package repository armazena as interfaces que se conectam com os
banco de dados, com `@JpaRepository<tabela, primary key>`, indo para
o package resources, sendo a camada da API, aonde se agrupam os endpoints
(acesso a API)...