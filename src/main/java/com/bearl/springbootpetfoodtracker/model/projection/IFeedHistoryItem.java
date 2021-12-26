package com.bearl.springbootpetfoodtracker.model.projection;

import java.time.LocalDateTime;

public interface IFeedHistoryItem {

    String getName();
    String getFoodGram();
    LocalDateTime getFeedTime();
}
