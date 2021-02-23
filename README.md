# effectiveRenegociation
1 - caso já tenha o docker instalado e já tenha gerado as imagens e startado os containers do kafka, zookeeper e do dynamoDB no projeto de renegociação,
não necessita executar novamente, caso contrário seguir os passos abaixa
2 - caso não tenha o docker e o docker-compose instalado, será necessário instalar
3 - após a instalação executar os comandos docker-compose -f docker-compose.yml -p renegociation up para subir o container do dynamoDB,do zookeeper e do kafka
4 - clonar o projeto de renegociação e subir a aplicação local (quando subir ele, irá criar algumas tabelas e popular no dynamoDB)
5 - clonar o projeto de efetivação de renegociação e subir a aplicação local
6 - clonar o projeto de listagem de ofertas e subir a aplicação local
7 - o projeto é baseado em arquitetura de eventos, então executar primeiramente os serviços do projeto de renegociação, cada endpoint do controller
irá enviar para um tópico específico no kafka que será escutado por um listener no projeto de efetivação de renegociação (no caso de simulação e efetivação)
e um listener no projeto de listagem de produtos que irá de fato execucação de cada item, para consultar os valores de retorno, pegar o id da transação no momento
do envio da mensagem no kafka e utilizar no controller de cada projeto
8 - antes de executar o serviço effectiveRenegociation é necessário rodar o microserviço renegociation, é necessário subir a aplicação local, e antes disso
os containers do docker devem estar rodando (local) para utilização do dynamoDB, do zookeeper e do kafka
9 - esse microserviço serve como consumer, mas coloquei também um endpoint para acessar via controller, para consultar o que foi processado, a simulação
de renegociação e a consulta da efetivação da renegociação

endpoint: http://localhost:8091/simulations
Verbo: GET

recurso: simulation renegociation


body request:
{
    "groupSimulationId": "794cb547-74de-45ca-b87b-c7cd2dba8a54" (id do grupo da simulação, esse id é gerado
																 através do microserviço renegociation, do enpoint
																simulation request, esse id é retornado no response)
}

exemplo de response:

{
    "simulations": [
        {
            "id": "e20faa70-98a5-47f5-b9f6-788deb182935",
            "date": "2021-02-17T22:59:10.658Z",
            "originalValue": "10120,10",
            "valueWithDiscount": "5566,05",
            "discountedValue": "4554,05",
            "discountPercent": "45",
            "plots": "2",
            "installmentValue": "2783,02",
            "documentId": "89852935089",
            "idDebts": [
                {
                    "id": "6c0dad3a-f288-48cf-a802-b1a06b7bd695",
                    "currentValue": "10000,00",
                    "originalValue": "1540,00",
                    "currency": "R$"
                },
                {
                    "id": "31506b42-3b33-4dc5-9ce9-4ca92bb27b7e",
                    "currentValue": "120,10",
                    "originalValue": "700,00",
                    "currency": "R$"
                }
            ],
            "status": "Efetivado",
            "groupSimulationId": "794cb547-74de-45ca-b87b-c7cd2dba8a54",
            "currency": "R$"
        },
        {
            "id": "aca73dae-acf0-4ba6-91c7-1ba840f7681b",
            "date": "2021-02-17T22:59:10.659Z",
            "originalValue": "10120,10",
            "valueWithDiscount": "3542,03",
            "discountedValue": "6578,07",
            "discountPercent": "65",
            "plots": "1",
            "installmentValue": "3542,03",
            "documentId": "89852935089",
            "idDebts": [
                {
                    "id": "6c0dad3a-f288-48cf-a802-b1a06b7bd695",
                    "currentValue": "10000,00",
                    "originalValue": "1540,00",
                    "currency": "R$"
                },
                {
                    "id": "31506b42-3b33-4dc5-9ce9-4ca92bb27b7e",
                    "currentValue": "120,10",
                    "originalValue": "700,00",
                    "currency": "R$"
                }
            ],
            "status": "Efetivado",
            "groupSimulationId": "794cb547-74de-45ca-b87b-c7cd2dba8a54",
            "currency": "R$"
        },
        {
            "id": "fc4b9dec-b0d4-49f2-b104-ceb8ab8f8e73",
            "date": "2021-02-17T22:59:10.656Z",
            "originalValue": "10120,10",
            "valueWithDiscount": "9108,09",
            "discountedValue": "1012,01",
            "discountPercent": "10",
            "plots": "10",
            "installmentValue": "910,81",
            "documentId": "89852935089",
            "idDebts": [
                {
                    "id": "6c0dad3a-f288-48cf-a802-b1a06b7bd695",
                    "currentValue": "10000,00",
                    "originalValue": "1540,00",
                    "currency": "R$"
                },
                {
                    "id": "31506b42-3b33-4dc5-9ce9-4ca92bb27b7e",
                    "currentValue": "120,10",
                    "originalValue": "700,00",
                    "currency": "R$"
                }
            ],
            "status": "Efetivado",
            "groupSimulationId": "794cb547-74de-45ca-b87b-c7cd2dba8a54",
            "currency": "R$"
        },
        {
            "id": "cb709281-6402-4d47-b7fc-189419781d0a",
            "date": "2021-02-17T22:59:10.658Z",
            "originalValue": "10120,10",
            "valueWithDiscount": "7590,07",
            "discountedValue": "2530,03",
            "discountPercent": "25",
            "plots": "5",
            "installmentValue": "1518,01",
            "documentId": "89852935089",
            "idDebts": [
                {
                    "id": "6c0dad3a-f288-48cf-a802-b1a06b7bd695",
                    "currentValue": "10000,00",
                    "originalValue": "1540,00",
                    "currency": "R$"
                },
                {
                    "id": "31506b42-3b33-4dc5-9ce9-4ca92bb27b7e",
                    "currentValue": "120,10",
                    "originalValue": "700,00",
                    "currency": "R$"
                }
            ],
            "status": "Efetivado",
            "groupSimulationId": "794cb547-74de-45ca-b87b-c7cd2dba8a54",
            "currency": "R$"
        }
    ]
}

endpoint: http://localhost:8091/renegociation
Verbo: GET

recurso: renegociation consultation


body request:
{
    "transactionId": "df496d4e-0adc-4ae6-8114-5031496aab13" (id da transação, esse id é gerado
																 através do microserviço renegociation, do enpoint
																renegociation request, esse id é retornado no response)
}

exemplo de response:

{
    "id": "9be182be-37ba-4d29-981e-c5ba9dbb5555",
    "documentId": "12768334073",
    "transactionId": "df496d4e-0adc-4ae6-8114-5031496aab13",
    "originalValue": "10120,10",
    "discountPercentage": "45",
    "discountedValue": "4554,05",
    "effectiveValue": "5566,05",
    "date": "2021-02-17T23:00:59.286Z",
    "plots": "2",
    "installmentValue": "2783,02",
    "status": "Efetivado",
    "simulationId": "e20faa70-98a5-47f5-b9f6-788deb182935",
    "currency": "R$",
    "debts": [
        {
            "id": "6c0dad3a-f288-48cf-a802-b1a06b7bd695",
            "currentValue": "10000,00",
            "originalValue": "1540,00",
            "currency": "R$"
        },
        {
            "id": "31506b42-3b33-4dc5-9ce9-4ca92bb27b7e",
            "currentValue": "120,10",
            "originalValue": "700,00",
            "currency": "R$"
        }
    ]
}
