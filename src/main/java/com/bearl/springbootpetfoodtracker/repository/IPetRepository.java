package com.bearl.springbootpetfoodtracker.repository;

import com.bearl.springbootpetfoodtracker.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPetRepository extends JpaRepository<Pet, Long> {

    Optional<List<Pet>> findAllByUserId(Long userId);
}
