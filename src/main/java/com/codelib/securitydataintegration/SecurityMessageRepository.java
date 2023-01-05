package com.codelib.securitydataintegration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A repository that integrates with Spring Security for accessing {@link Message}s.
 */
@Repository
public interface SecurityMessageRepository extends MessageRepository {
    @Query("SELECT * FROM Message WHERE m.to_id = ?#{principal?.id }")
    List<Message> findAll();
}
