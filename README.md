# Ford Technical Training - Java FullStack 

## Online Digital Wallet with JDBC and H2 Database
- Practised JDBC connection with H2 database in Layered Architecture Project Online Digital Wallet


## Folders:
   - Wallet
     - Wallet(POJO)
     - WalletDao
     - WalletDaoImplementation
     - WalletService
     - WalletServiceImplementation
     - Exceptions
       - WalletNotFoundException
       - WalletWithdrawalException
       - WalletAddFundsException
       - WalletException
   - DemoApplication(Controller)
 

## Service Methods:
- newRegisterWallet
- addFundsToWallet
- withdrawFundsFromWallet
- getAllCustomerWallets
- deleteWalletByEmailID
- transferFunds

## DAO Methods:
- saveWallet
- getWalletByEmailID
- updateWallet
- deleteWallet
- getAllWallets


## H2 Database:
- TABLE WALLET
- COLUMNS: id, name, email, password, balance


## Dharshan S 
### CSID:ds68
