# Gerenciador de Contatos

Este projeto é uma API de CRUD de contatos.

## Requisitos

Antes de executar a aplicação, certifique-se de ter as seguintes ferramentas instaladas:

- JDK (Java Development Kit) 8 ou superior
- Maven

## Configuração

Siga as etapas abaixo para configurar e executar localmente a aplicação (para pacote jar):

1. Faça o clone do repositório:
```bash
git clone https://github.com/GabrielRomanoo/gerenciador-contatos
```

2. Navegue até o diretório do projeto:
```bash
cd nome-do-projeto
```

3. Compile o projeto com o Maven:
```bash
mvn clean package
mvn install
```

4. Navegue até o diretório target do projeto:
```bash
cd target
```

5. Execute a aplicação:
```bash
java -jar .\gerenciadorcontatos-0.0.1-SNAPSHOT.jar
```

Agora a aplicação está em execução e pronta para ser utilizada.

## Endpoints

A API possui os seguintes endpoints para realizar operações CRUD de contatos:

### Listar todos os contatos

- Método: GET
- Endpoint: /contatos/{id} (opcional)
- 
Este endpoint retorna todos os contatos cadastrados quando acessado sem especificar o parâmetro {id}. Caso o parâmetro {id} seja preenchido com um valor válido, será retornado apenas o contato específico com o ID correspondente.

### Cadastrar um novo contato

- Método: POST
- Endpoint: /contatos

Envie um objeto JSON no corpo da requisição com os dados do novo contato a ser cadastrado.

Exemplo de corpo da requisição:
```json
{
  "nome": "fulanoDeTal",
  "email": "username@domain.com",
  "telefone": "(11) 91234-5678",
  "dataNascimento": "01/01/2000",
  "enderecos": [
    {
      "rua": "Rua Exemplo",
      "numero": 1,
      "cep": "00000-000"
    }
  ]
}
```

### Alterar os dados de um contato

- Método: PUT
- Endpoint: /contatos/{id}

Substitua {id} pelo identificador do contato que deseja atualizar. Envie um objeto JSON no corpo da requisição com os novos dados do contato.

Exemplo de corpo da requisição:
```json
{
  "nome": "fulanoDeTal2",
  "email": "username@domain.com",
  "telefone": "(11) 91234-5678",
  "dataNascimento": "01/01/2000",
  "enderecos": [
    {
      "rua": "Rua Exemplo 2",
      "numero": 1,
      "cep": "00000-000"
    }
  ]
}
```

### Atualizar parte dos dados de um contato

- Método: PATCH
- Endpoint: /contatos/{id}

Substitua {id} pelo identificador do contato que deseja atualizar. Envie um objeto JSON no corpo da requisição com os campos que deseja atualizar.

Exemplo de corpo da requisição:
```json
{
  "email": "novoemail@example.com"
}
```

### Excluir um contato

- Método: DELETE
- Endpoint: /contatos/{id}

Substitua {id} pelo identificador do contato que deseja excluir.

