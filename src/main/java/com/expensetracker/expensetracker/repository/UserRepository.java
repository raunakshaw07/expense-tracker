package com.expensetracker.expensetracker.repository;

import com.expensetracker.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}
