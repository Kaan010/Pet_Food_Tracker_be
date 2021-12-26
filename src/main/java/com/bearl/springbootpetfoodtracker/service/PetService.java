package com.bearl.springbootpetfoodtracker.service;

import com.bearl.springbootpetfoodtracker.exception.CustomException;
import com.bearl.springbootpetfoodtracker.model.AnimalType;
import com.bearl.springbootpetfoodtracker.model.BodyType;
import com.bearl.springbootpetfoodtracker.model.Gender;
import com.bearl.springbootpetfoodtracker.model.Pet;
import com.bearl.springbootpetfoodtracker.repository.IPetRepository;
import com.bearl.springbootpetfoodtracker.repository.IUserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PetService {

    private final IPetRepository petRepository;
    private final IUserRepository userRepository;
    public PetService(IPetRepository petRepository, IUserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
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
                    pet.setBodyType(petInput.getBodyType());
                    petRepository.save(pet);
                }
        );
        return petOptional.orElseGet(Pet::new);
    }


    public Pet savePet(Pet pet){
        if(userRepository.findById(pet.getUserId()).isPresent()){
            pet.setId(0L);
            pet.setName(pet.getName());
            pet.setAge(pet.getAge());
            pet.setGender(pet.getGender());
            pet.setWeight(pet.getWeight());
            pet.setIsSterilised(pet.getIsSterilised());
            pet.setAnimalType(pet.getAnimalType());
            pet.setDailyFoodGram(calculateDailyFoodGram(pet));
            pet.setCreateTime(LocalDateTime.now());
            pet.setUserId(pet.getUserId());
            pet.setBodyType(pet.getBodyType());
        }
        else throw new CustomException("There is no user with given ID");
        return petRepository.save(pet);
    }


    private Double calculateDailyFoodGram(Pet pet) {
        double result;
        if(pet.getAnimalType().equals(AnimalType.DOG)){
            if(pet.getAge()<1){
                result= pet.getWeight()*60;
                return calculate(pet, result);
            }
            else{
                result= pet.getWeight()*80;
                return calculate(pet, result);
            }
        }
        else if(pet.getAnimalType().equals(AnimalType.CAT)){
            result=pet.getWeight()*13;
            return calculate(pet,result);
        }
        else{
            //calculate for fish..
            return 0.0;
        }

    }

    private double calculate(Pet pet, double result) {
        if(pet.getGender().equals(Gender.MALE))
            result *=1.1;
        if(!pet.getIsSterilised())
            result *=1.05;
        if(pet.getBodyType().equals(BodyType.FAT))
            result *=0.95;
        if(pet.getBodyType().equals(BodyType.THIN))
            result *=1.05;
        return result;
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
