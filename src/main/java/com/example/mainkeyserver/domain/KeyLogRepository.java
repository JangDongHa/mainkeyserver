package com.example.mainkeyserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyLogRepository extends JpaRepository<KeyLog, Long> {
}
