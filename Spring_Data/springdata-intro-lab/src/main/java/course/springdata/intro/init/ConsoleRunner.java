package course.springdata.intro.init;

import course.springdata.intro.entity.Account;
import course.springdata.intro.entity.User;
import course.springdata.intro.service.AccountService;
import course.springdata.intro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Ivan Petrov", 42);
        Account account1 = new Account(new BigDecimal(1500));

        userService.register(user1);
        accountService.createUserAccount(user1, account1);
        accountService.depositMoney(new BigDecimal(1000), account1.getId());
        accountService.withdrawMoney(new BigDecimal(1000), account1.getId());

        accountService.getAllAcounts().forEach(System.out::println);
    }
}
