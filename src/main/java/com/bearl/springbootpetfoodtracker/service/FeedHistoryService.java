package com.bearl.springbootpetfoodtracker.service;

import com.bearl.springbootpetfoodtracker.exception.CustomException;
import com.bearl.springbootpetfoodtracker.model.FeedHistory;
import com.bearl.springbootpetfoodtracker.model.Pet;
import com.bearl.springbootpetfoodtracker.model.User;
import com.bearl.springbootpetfoodtracker.repository.IFeedHistoryRepository;
import com.bearl.springbootpetfoodtracker.repository.IPetRepository;
import com.bearl.springbootpetfoodtracker.repository.IUserRepository;
import com.bearl.springbootpetfoodtracker.repository.projection.IFeedHistoryItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FeedHistoryService {

    private final IFeedHistoryRepository feedHistoryRepository;

    private final IPetRepository petRepository;

    private final IUserRepository userRepository;

    public FeedHistoryService(IFeedHistoryRepository feedHistoryRepository, IPetRepository petRepository, IUserRepository userRepository) {
        this.feedHistoryRepository = feedHistoryRepository;
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FeedHistory saveFeedHistory(FeedHistory feedHistory){

        updatePetsFood(feedHistory);

        updateUsersFood(feedHistory);

        feedHistory.setId(0L);
        feedHistory.setFeedTime(LocalDateTime.now());
        return feedHistoryRepository.save(feedHistory);
    }

    @Transactional
    void updateUsersFood(FeedHistory feedHistory) {
        User feederUser=userRepository.findById(feedHistory.getUserId())
                .orElseThrow(()-> new CustomException("This person not exist"));
        feederUser.setCurrentFoodGram(feederUser.getCurrentFoodGram()- feedHistory.getFoodGram());
    }

    @Transactional
    void updatePetsFood(FeedHistory feedHistory) {
        Pet feededAnimal=petRepository.findById(feedHistory.getPetId())
                .filter(x-> Objects.equals(x.getUserId(), feedHistory.getUserId()))
                .orElseThrow(()-> new CustomException("This person cannot feed this pet or pet is not exist"));

        feededAnimal.setDailyFoodGram(feededAnimal.getDailyFoodGram()- feedHistory.getFoodGram());
    }

    public List<IFeedHistoryItem> findFeedHistoryOfUser(Long userId){
        return feedHistoryRepository.findAllFeedsOfUser(userId);
    }
}
