package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Konto;

/**
 * Interface for CRUD operations on a Konto repository
 */
public interface KontoRepository extends CrudRepository<Konto, Long> {
    
}