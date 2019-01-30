package org.ocean.account;

import org.ocean.type.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Long countByEmail(Email email);

    Account findByEmail(Email email);

}
