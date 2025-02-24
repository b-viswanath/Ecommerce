package com.example.userservice.security.repositories;

import java.util.Optional;


//import com.scaler.userservicenovttseve.security.models.Client;
import com.example.userservice.security.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}