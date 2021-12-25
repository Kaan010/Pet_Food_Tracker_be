package com.bearl.springbootpetfoodtracker.repository.projection;

import java.time.LocalDateTime;

public interface IFeedHistoryItem {

    String getName();
    String getFoodGram();
    LocalDateTime getFeedTime();
}
