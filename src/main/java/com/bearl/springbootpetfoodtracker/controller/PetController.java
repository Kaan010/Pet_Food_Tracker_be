package com.bearl.springbootpetfoodtracker.controller;

import com.bearl.springbootpetfoodtracker.model.Pet;
import com.bearl.springbootpetfoodtracker.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pet")
public class PetController {

    private final PetService petService;
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPets() {
        return new ResponseEntity<>(
                petService.findAllPets(),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPetById(@PathVariable Long id) {
        return new ResponseEntity<>(
                petService.findById(id),
                HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getAllPetsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(
                petService.findAllPetsByUserId(userId),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePet(@RequestBody Pet pet) {
        return new ResponseEntity<>(
                petService.savePet(pet),
                HttpStatus.CREATED);
    }

    @PutMapping("{petId}")
    public ResponseEntity<?> updatePet(@PathVariable Long petId, @RequestBody Pet pet) {
        return new ResponseEntity<>(
                petService.updatePet(petId, pet),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{petId}")
    public ResponseEntity<?> deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
