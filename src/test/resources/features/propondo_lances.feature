Feature: Propondo lances

  Scenario: Propondo um unico lance valido
    Given Dado um lance valido
    When Quando propoe o lance
    Then Entao o lance eh aceito