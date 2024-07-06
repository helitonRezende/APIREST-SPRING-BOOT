# Spring-Boot -> API

1 - Subir projeto porta padrão 8080 --> http://localhost:8080/

Classe a ser executada

	class --> src/main/java/com/heliton/WebApplication.java

# Testes - PostMan

1 - Cadastrar um benefiário junto com seus documentos.

	Metodo --> POST
	EndPoint --> http://localhost:8080/adicionarBeneficiario	
	Body --> Json Exemplo abaixo -->
{
  "id": 0,
  "nome": "Beneficiario 1",
  "telefone": "(11) 9 9999-9999",
  "dataNascimento": "2012-04-23T18:25:43.511Z",
  "dataInclusao": "2024-07-05T18:25:43.511Z",
  "dataAtualizacao": "2024-07-05T18:25:43.511Z",
  "documento":[
        {
        "id":"0",
        "tipoDocumento":"CPF",
        "descricao":"408.147.773-68",
        "dataInclusao": "2024-07-05T18:25:43.511Z",
        "dataAtualizacao": "2024-07-05T18:25:43.511Z"
        },
        {
        "id":"0",
        "tipoDocumento":"RG",
        "descricao":"50575972-X",
        "dataInclusao": "2024-07-05T18:25:43.511Z",
        "dataAtualizacao": "2024-07-05T18:25:43.511Z"
        } ]    
}


2 - Listar todos os benefiários cadastrados.

	Metodo --> GET
	EndPoint --> http://localhost:8080/listarBeneficiario	
	
3 - Listar todos os documentos de um beneficiário a partir de seu id.

	Metodo --> GET
	EndPoint http://localhost:8080/listarBeneficiarioDocumento/1

4 - Atualizar os dados cadastrados de um benefiário.

	Metodo --> POST
	EndPoint --> http://localhost:8080/alterarBeneficiario	
	Body --> Json Exemplo abaixo -->
{
  "id": 1,
  "nome": "James T1",
  "telefone": "(11) 9 8888-8888",
  "dataNascimento": "2012-04-23T18:25:43.511Z",
  "dataInclusao": "2024-07-05T18:25:43.511Z",
  "dataAtualizacao": "2024-07-05T18:25:43.511Z"
}

5 - Remover um benefiário.

	Metodo --> POST
	EndPoint --> http://localhost:8080/deletarBeneficiario/1

# Testes - Class MAIN

Classe a ser executada

	class --> src/test/java/com/security/BeneficiatioTest.java

