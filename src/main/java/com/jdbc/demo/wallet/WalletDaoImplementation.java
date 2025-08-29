package com.jdbc.demo.wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WalletDaoImplementation  implements WalletDao {

   private final Connection connection;

   public WalletDaoImplementation() {
       try {
           this.connection = DriverManager.getConnection("jdbc:h2:file:./data/demo", "sa", "password");
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }


    @Override
    public Wallet saveWallet(Wallet newWallet) throws WalletException {
        String insertQuery = "INSERT INTO wallet(id,name,email,password,balance) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, newWallet.getWalletId());
            preparedStatement.setString(2, newWallet.getCustomerName());
            preparedStatement.setString(3, newWallet.getEmail());
            preparedStatement.setString(4, newWallet.getPassword());
            preparedStatement.setDouble(5, newWallet.getBalance());
            int recordCount = preparedStatement.executeUpdate();
            if (recordCount > 0) {
                return newWallet;
            }
            else{
                throw new WalletException("Wallet could not be added to the database");
            }
        }
        catch(SQLException e){
                throw new WalletException(e.getMessage());
            }

    }

    @Override
    public Wallet getWalletByEmailId(String emailId) throws WalletException {
       String selectQuery="SELECT * FROM wallet WHERE email=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(selectQuery);
            preparedStatement.setString(1,emailId);
            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                Wallet foundWallet=new Wallet();
                foundWallet.setWalletId(resultSet.getInt("id"));
                foundWallet.setCustomerName(resultSet.getString("name"));
                foundWallet.setEmail(resultSet.getString("email"));
                foundWallet.setPassword(resultSet.getString("password"));
                foundWallet.setBalance(resultSet.getDouble("balance"));
                return foundWallet;

            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new WalletException(e.getMessage());
        }

    }

    @Override
    public Wallet updateWallet(Wallet newWallet) throws WalletException {
       String updateQuery="UPDATE wallet SET balance=? WHERE email=?";
       try{
           PreparedStatement preparedStatement=connection.prepareStatement(updateQuery);
           preparedStatement.setDouble(1,newWallet.getBalance());
           preparedStatement.setString(2,newWallet.getEmail());
           int recordCount=preparedStatement.executeUpdate();
           if(recordCount>0){
               return newWallet;
           }else{
               throw new WalletException("Wallet could not be updated in the database");
           }
       }catch(SQLException e){
           throw new WalletException(e.getMessage());
       }
    }

    @Override
    public String deleteWallet(String emailId) throws WalletNotFoundException {
       String deleteQuery="DELETE FROM wallet WHERE email=?";
       try{
              PreparedStatement preparedStatement=connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1,emailId);
                int recordCount=preparedStatement.executeUpdate();
                if(recordCount>0){
                    return "Wallet Deleted Successfully for "+emailId;
                }
                else{
                    throw new WalletNotFoundException("Wallet could not be deleted from the database");
                }
       }catch(SQLException e){
              throw new WalletNotFoundException(e.getMessage());
       }

    }

    @Override
    public Collection<Wallet> getAllWallets() throws WalletNotFoundException {
       String selectAllQuery="SELECT * FROM wallet";
       try{
           PreparedStatement preparedStatement=connection.prepareStatement(selectAllQuery);
           ResultSet resultSet=preparedStatement.executeQuery();
           List<Wallet> allWallets =new ArrayList<>();

           while(resultSet.next()){
                Wallet foundWallet=new Wallet();
                foundWallet.setWalletId(resultSet.getInt("id"));
                foundWallet.setCustomerName(resultSet.getString("name"));
                foundWallet.setEmail(resultSet.getString("email"));
                foundWallet.setPassword(resultSet.getString("password"));
                foundWallet.setBalance(resultSet.getDouble("balance"));
                allWallets.add(foundWallet);
           }
           return allWallets;

       }catch(SQLException e){
              throw new WalletNotFoundException(e.getMessage());
       }


    }
}
