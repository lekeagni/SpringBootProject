package com.example.client.repository;

import com.example.client.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel,Integer> {
    Optional<ClientModel> findByEmail(String email);
}
