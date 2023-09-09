package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account addUser(Account user) {
        if (user.getPassword().length() >= 4 && user.getUsername() !=""){
            return accountDAO.addUser(user);
        }
        return null;
    }

    public Account loginUser(Account user) {
        return accountDAO.loginUser(user);
    }

}
