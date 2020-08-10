# language: pt

Funcionalidade: Apenas usuarios cadastrados podem se logar
  
  Cenario: Um usuario valido consegue se logar
    Dado o usuario valido
    Quando realiza login
    Entao é redirecionado para a pagina de leiloes
    
    
  Cenario: Um usuario invalido não consegue se logar
    Dado o usuario invalido
    Quando tenta se logar
    Entao continua na página de login