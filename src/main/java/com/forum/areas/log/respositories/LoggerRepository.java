package com.forum.areas.log.respositories;

import com.forum.areas.log.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<Log, String> {
}
