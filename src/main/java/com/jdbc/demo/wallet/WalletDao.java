package com.jdbc.demo.wallet;

import java.util.Collection;

public interface WalletDao {
    //CRUD

    Wallet saveWallet(Wallet newWallet) throws WalletException;

    Wallet getWalletByEmailId(String emailId) throws  WalletException;

    Wallet updateWallet(Wallet newWallet) throws  WalletException;

    String deleteWallet(String emailId) throws WalletNotFoundException;

    Collection<Wallet> getAllWallets() throws WalletNotFoundException;



}
