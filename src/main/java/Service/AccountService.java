package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account addUser(Account user) {
        return accountDAO.addUser(user);
    }

    public Account loginUser(Account user) {
        return accountDAO.loginUser(user);
    }

}
