package com.codelib.securitydataintegration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for accessing {@link Message}s.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
