package com.jdbc.demo.wallet;

import java.util.Collection;

public interface WalletService {

    Wallet registerNewUserWallet(Wallet newWallet) throws WalletException;

    Wallet deleteWalletByEmailId(String emailId) throws WalletNotFoundException, WalletException;

    Double addFundsToWalletByEmailId(String emailId, Double amount) throws WalletAddFundException, WalletNotFoundException, WalletException;

    Double withdrawFundsFromWalletByEmailId(String emailId, Double amount) throws WalletWithdrawFundException, WalletNotFoundException,WalletException;

    Boolean transferFunds(String fromEmailId, String toEmailId, Double amount) throws WalletTransferFundException, WalletNotFoundException, WalletException;

    Collection<Wallet> getAllCustomerWallets() throws WalletNotFoundException,WalletException;


}
