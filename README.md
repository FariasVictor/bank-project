# Bank Project

This project consists in an api that provides a CRUD of accounts and customers of a bank, besides it allows to do bank operations like withdraw and deposits.

## Install instructions

 1. Clone this repository.
 2. On your postgres terminal create a database named "banco".
  - To create that, you'll need to use:
  `create database banco;`
 3. On IntelliJ IDEA it's necessary to enable annotation processing to Lombok.
 To do that, you can check the "Enable annotation processing" flag that can be found at "Settings > Build > Compiler > Annotation Processors"
 
## Running instructions 

- Open the package 'com.invillia.apiBancoCliente' found at 'src/main/java', run the 'ApiBancoClienteApplication' file and the API will be ready to use at localhost:8080 (be sure that this port is not being used by other program).
