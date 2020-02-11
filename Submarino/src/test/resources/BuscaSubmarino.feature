Feature: Compra no Site Extra

  Scenario: Busca por Produto
    Given que acesso o site do Extra
    When preencho o termo "smartphone" e clico na lupa
    Then exibe a lista de produtos

  Scenario: Busca por Produto Nao Encontrado
    Given que acesso o site do Extra
    When preencho o termo "qeafbfjnqonrf" e clico na lupa
    Then exibe a mensagem de produto nao encontrado