package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Kunde;

/**
 * Interface for CRUD operations on a Kunde repository
 */
public interface KundeRepository extends CrudRepository<Kunde, Long> {
	Kunde findByName(String name);
}