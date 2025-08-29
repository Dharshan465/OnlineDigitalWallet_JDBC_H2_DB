package com.jdbc.demo.wallet;

import java.util.Collection;
import java.util.List;

public class WalletServiceImplementation implements WalletService {
    private final WalletDao walletDao = new WalletDaoImplementation();


    @Override
    public Wallet registerNewUserWallet(Wallet newWallet) throws WalletException {
        //check user already exists with same email id

        Wallet foundWallet = walletDao.getWalletByEmailId(newWallet.getEmail());
        if (foundWallet != null) {
            throw new WalletException("User already exists with emailId: " + newWallet.getEmail());
        } else {
            walletDao.saveWallet(newWallet);
        }
        return newWallet;
    }

    @Override
    public Wallet deleteWalletByEmailId(String emailId) throws WalletNotFoundException, WalletException {
        try {
            Wallet foundWallet = walletDao.getWalletByEmailId(emailId);
            if (foundWallet == null) {
                throw new WalletNotFoundException("User not found with emailId: " + emailId);
            } else {
                walletDao.deleteWallet(emailId);
                return foundWallet;
            }
        } catch (WalletNotFoundException | WalletException e) {
            throw new WalletException(e.getMessage());

        }

    }

    @Override
    public Double addFundsToWalletByEmailId(String emailId, Double amount) throws WalletAddFundException, WalletNotFoundException, WalletException {
        try {
            Wallet foundWallet = walletDao.getWalletByEmailId(emailId);
            if (foundWallet == null) {
                throw new WalletNotFoundException("User not found with emailId: " + emailId);
            } else {

                if (amount <= 0) {
                    throw new WalletAddFundException("Amount is less than 0, cannot add funds");
                } else {
                    double newBalance = foundWallet.getBalance() + amount;
                    foundWallet.setBalance(newBalance);
                    walletDao.updateWallet(foundWallet);
                    return newBalance;
                }
            }
        } catch (WalletAddFundException | WalletNotFoundException e) {
            throw new WalletException(e.getMessage());
        }
    }


    @Override
    public Double withdrawFundsFromWalletByEmailId(String emailId, Double amount) throws WalletWithdrawFundException, WalletNotFoundException, WalletException {
        try {
            Wallet foundWallet = walletDao.getWalletByEmailId(emailId);
            if (foundWallet == null) {
                throw new WalletNotFoundException("User not found with emailId: " + emailId);
            } else {
                if (amount <= 0) {
                    throw new WalletWithdrawFundException("Amount is less than 0, cannot add funds");
                } else if (foundWallet.getBalance() < amount) {
                    throw new WalletWithdrawFundException("Insufficient funds in the wallet to withdraw");
                } else {
                    double newBalance = foundWallet.getBalance() - amount;
                    foundWallet.setBalance(newBalance);
                    walletDao.updateWallet(foundWallet);
                    return newBalance;
                }
            }
        } catch (WalletNotFoundException | WalletWithdrawFundException e) {
            throw new WalletException(e.getMessage());
        }


    }

    @Override
    public Collection<Wallet> getAllCustomerWallets() throws WalletNotFoundException, WalletException {
        try{
            Collection<Wallet> allWallets=walletDao.getAllWallets();
            if(!allWallets.isEmpty()){
                return allWallets;
            }else{
                throw new WalletNotFoundException("No wallets found in the database");
            }
        }catch(WalletNotFoundException e){
            throw new WalletException(e.getMessage());
        }

    }

    @Override
    public Boolean transferFunds(String fromEmailId, String toEmailId, Double amount) throws WalletTransferFundException, WalletNotFoundException, WalletException {

        try{
            Wallet fromWallet=walletDao.getWalletByEmailId(fromEmailId);
            Wallet toWallet=walletDao.getWalletByEmailId(toEmailId);
            if(fromWallet==null){
                throw new WalletNotFoundException("Sender wallet not found with emailId: "+fromEmailId);
            }else if(toWallet==null){
                throw new WalletNotFoundException("Receiver wallet not found with emailId: "+toEmailId);
            }else{
                if(amount<=0){
                    throw new WalletTransferFundException("Amount is less than 0, cannot transfer funds");
                }else if(fromWallet.getBalance()<amount){
                    throw new WalletTransferFundException("Insufficient funds in the sender wallet to transfer");
                }else{
                    //withdraw from sender wallet
                    double newFromBalance=fromWallet.getBalance()-amount;
                    fromWallet.setBalance(newFromBalance);
                    walletDao.updateWallet(fromWallet);

                    //add to receiver wallet
                    double newToBalance=toWallet.getBalance()+amount;
                    toWallet.setBalance(newToBalance);
                    walletDao.updateWallet(toWallet);
                    return true;
                }
            }
        }catch(WalletNotFoundException | WalletTransferFundException e){
            throw new WalletException(e.getMessage());
        }

    }




}
