package course.springdata.intro.service.impl;

import course.springdata.intro.dao.AccountRepository;
import course.springdata.intro.entity.Account;
import course.springdata.intro.entity.User;
import course.springdata.intro.exception.InvalidAccountOperationException;
import course.springdata.intro.exception.NotExistingEntityException;
import course.springdata.intro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createUserAccount(User user, Account account) {
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepository.save(account);
    }

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new NotExistingEntityException(String.format("Entity with ID: %s does not exist.%n", accountId)));
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new NotExistingEntityException(String.format("Entity with ID: %s does not exist.%n", accountId)));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InvalidAccountOperationException(
                    String.format("Account ID: %s balance(%s) is less than required withdraw amount.%n",
                            accountId, account.getBalance()));
        }
        account.setBalance(account.getBalance().subtract(amount));
    }

    @Override
    public void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        withdrawMoney(amount, fromAccountId);
        depositMoney(amount, toAccountId);
    }

    @Override
    public List<Account> getAllAcounts() {
        return accountRepository.findAll();
    }
}
