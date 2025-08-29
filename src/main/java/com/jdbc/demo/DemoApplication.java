package com.jdbc.demo;

import com.jdbc.demo.wallet.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        WalletService walletService = new WalletServiceImplementation();
        try {
            // Adding new users wallet to the database and also trying to add same user again to check exception(SAVE)

            // walletService.registerNewUserWallet(new Wallet(1, "Dharshan", "d@ford.com", "Dharshan@123", 5000.00));
            // walletService.registerNewUserWallet(new Wallet(2, "Kevin","k@ford.com","Kevin@123",3000.00));
            // System.out.println("New user's Wallet added Successfully "+walletService.registerNewUserWallet(new Wallet(2, "Abinash", "a@ford.com", "Abinash@123", 7000.00)));


            //Adding funds to the wallet and also trying to add negative funds to check exception(UPDATE)

            //System.out.println("Updated new Balance after add funds "+walletService.addFundsToWalletByEmailId("d@ford.com", 2000.00));
            //System.out.println("Updated new Balance after add funds"+walletService.addFundsToWalletByEmailId("ke@ford.com", 2000.00));
            //System.out.println("Updated new Balance after add funds "+walletService.addFundsToWalletByEmailId("k@ford.com",-1.0));


            //Withdrawing funds from the wallet and also trying to withdraw negative funds and more than balance funds to check exception(UPDATE)

            //    System.out.println("Updated new Balance after withdraw funds : "+ walletService.withdrawFundsFromWalletByEmailId("d@ford.com",1000.00));
            //System.out.println("Updated new Balance after withdraw funds"+ walletService.withdrawFundsFromWalletByEmailId("ke@ford.com",1000.00));
            //   System.out.println("Updated new Balance after withdraw funds"+ walletService.withdrawFundsFromWalletByEmailId("k@ford.com",5000.00));
            //System.out.println("Updated new Balance after withdraw funds"+ walletService.withdrawFundsFromWalletByEmailId("d@ford.com",-1000.00));


            //Deleting a wallet from the database(DELETE)

            //System.out.println(walletService.deleteWalletByEmailId("a@ford.com"));
            //System.out.println(walletService.deleteWalletByEmailId("ke@ford.com"));


        // Transferring funds from one wallet to another and also trying to transfer negative funds and more than balance funds to check exception(UPDATE)

            //System.out.println("Transferring funds from one wallet to another : " + walletService.transferFunds("d@ford.com","k@ford.com",100.00));
            //System.out.println("Transferring funds from one wallet to another : " + walletService.transferFunds("d@ford.com","ke@ford.com",100.00));
            //System.out.println("Transferring funds from one wallet to another : " + walletService.transferFunds("d@ford.com","k@ford.com",-100.00));
            //System.out.println("Transferring funds from one wallet to another : " + walletService.transferFunds("d@ford.com","k@ford.com",10000.00));

            //Getting all wallets from the database(SELECT)


            System.out.println(walletService.getAllCustomerWallets());

        } catch (WalletException e) {
            System.out.println(e.getMessage());
        }

    }

}
