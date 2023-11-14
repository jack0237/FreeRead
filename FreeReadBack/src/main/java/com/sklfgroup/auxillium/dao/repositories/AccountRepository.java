package com.sklfgroup.auxillium.dao.repositories;

import com.sklfgroup.auxillium.dao.entities.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUuid(String uuid);
    Optional<AccountEntity> findByEmail(String email);
    Optional<AccountEntity> findByActiveTrueAndDeletedFalseAndEmail(String email);


}
