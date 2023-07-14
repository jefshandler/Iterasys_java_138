##language: pt
#  Funcionalidade: Selecionar Produto na loja
#    Cenario: Selecionar Produto com Sucesso
#      Dado que acesso a loja SauceDemo
#      Quando preencho o usuario e senha
#      E clica em Login
#      Entao exibe o titulo da pagina como "Products"
#      E exibe o link do carrinho de compras
#      Quando clica no produto "Sauce Labs Backpack"
  Feature: Select product in store
    Scenario: Selecting product with Sucess
#      Given I access SauceDemo store
#      When I filled user "standard_user" and password "secret_sauce"
#      And I click in Login
#      Then show page's title "Products"
#      And show cart's link
#      When I click in product "Sauce Labs Backpack"
#      Then I verify the product title "Sauce Labs Backpack"
#      And I verify the product price "$29.99"
#      When I click in add to Cart
#      And I click in cart icon
#      Then I verify the page's title "You Cart"
#      And I verify the product title "Sauce Labs Backpack"
#      And I verify the  quantity is "1"
#      And I verify the product price "$29.99"

      Given I access the SauceDemo store
      When I enter the username "standard_user" and password "secret_sauce"
      And I click on Login
      Then the page title should be "Products"
      And the cart link should be displayed
      When I click on the product "Sauce Labs Backpack"
      Then I verify that the product title is "Sauce Labs Backpack"
      And I verify that the product price is "$29.99"
      When I click on Add to Cart
      And I click on the cart icon
      Then I verify that the page title is "Your Cart"
      And I verify that the product title is "Sauce Labs Backpack"
      And I verify that the quantity is "1"
      And I verify that the product price is "$29.99"