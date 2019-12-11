# API Star Wars

#### Requisitos:
- Ubuntu 18.04 LTS ou Fedora 31
- Node.js 12.13.1 LTS
- Yarn 1.19.2
- MongoDB

### Configuração do projeto:

Para instalar as dependências do projeto, rode o comando: `yarn`

Para configurar as variáveis de ambiente, crie um arquivo `.env` conforme a estrutura do arquivo `.env.sample` e informe a url de conexão do MongoDB. Faço isso também quando for executar os testes, crie um arquivo `.env.test`.

### Executando o projeto:

A porta padrão é a **3000**

Disponibilizando a API: `yarn dev` ou `yarn build && yarn start`

Rodar os testes: `yarn test`

### Dados da API:

URL: `http://localhost:3000`

Endpoints: `http://localhost:3000/apidoc`
