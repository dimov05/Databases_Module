package course.springdata.intro.service;

import course.springdata.intro.entity.Account;
import course.springdata.intro.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface AccountService {
    Account createUserAccount(User user, Account account);

    void depositMoney(BigDecimal amount, Long accountId);

    void withdrawMoney(BigDecimal amount, Long accountId);

    void transferMoney(BigDecimal bigDecimal, Long fromAccountId, Long toAccountId);


    List<Account> getAllAcounts();
}
