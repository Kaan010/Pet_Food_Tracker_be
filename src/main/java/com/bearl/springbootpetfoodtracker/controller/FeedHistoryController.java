package com.bearl.springbootpetfoodtracker.controller;

import com.bearl.springbootpetfoodtracker.model.FeedHistory;
import com.bearl.springbootpetfoodtracker.model.User;
import com.bearl.springbootpetfoodtracker.service.FeedHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/feed-history")
public class FeedHistoryController {

    @Autowired
    private FeedHistoryService feedHistoryService;

    @PostMapping
    public ResponseEntity<?> saveFeedHistory(@RequestBody FeedHistory feedHistory) {
        return new ResponseEntity<>(
                feedHistoryService.saveFeedHistory(feedHistory),
                HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getAllFeedHistoriesOfUser(@PathVariable Long userId) {
        return new ResponseEntity<>(
                feedHistoryService.findFeedHistoryOfUser(userId),
                HttpStatus.OK
        );
    }


}
