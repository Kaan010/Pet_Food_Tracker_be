package com.bearl.springbootpetfoodtracker.service;

import com.bearl.springbootpetfoodtracker.model.Pet;
import com.bearl.springbootpetfoodtracker.repository.IPetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PetService {

    private final IPetRepository petRepository;
    public PetService(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet updatePet(@PathVariable Long petId, @RequestBody Pet petInput){
        Optional<Pet> petOptional=petRepository.findById(petId);
        petOptional.ifPresent( pet-> {
                    pet.setName(petInput.getName());
                    pet.setAge(petInput.getAge());
                    pet.setGender(petInput.getGender());
                    pet.setWeight(petInput.getWeight());
                    pet.setIsSterilised(petInput.getIsSterilised());
                    pet.setAnimalType(petInput.getAnimalType());
                    pet.setDailyFoodGram(petInput.getDailyFoodGram());
                    petRepository.save(pet);
                }
        );
        return petOptional.orElseGet(Pet::new);
    }

    public Pet savePet(Pet pet){
        pet.setId(0L);
        pet.setName(pet.getName());
        pet.setAge(pet.getAge());
        pet.setGender(pet.getGender());
        pet.setWeight(pet.getWeight());
        pet.setIsSterilised(pet.getIsSterilised());
        pet.setAnimalType(pet.getAnimalType());
        pet.setDailyFoodGram(pet.getDailyFoodGram());
        pet.setCreateTime(LocalDateTime.now());
        pet.setUserId(pet.getUserId());
        return petRepository.save(pet);
    }

    public void deletePet(Long id){
        petRepository.deleteById(id);
    }

    public List<Pet> findAllPets(){
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id){
        return petRepository.findById(id);
    }

    public Optional<List<Pet>> findAllPetsByUserId(Long userId){
        return petRepository.findAllByUserId(userId);
    }







}
