#language: pt
Funcionalidade: Propondo lances

  Cenario: Propondo um único lance válido
    Dado que tenho um lance válido
    Quando propõe o lance para o produto do leilão
    Entao o lance é aceito

  Cenario: Propondo vários lances válidos
    Dado proponho um lance de 10.0 reais do usuario "fulano"
    E proponho um lance de 15.0 reais do usuario "Beltrano"
    Quando propõe vários lances válidos
    Entao os lances sao aceitos