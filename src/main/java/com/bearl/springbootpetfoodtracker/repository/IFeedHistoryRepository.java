package com.bearl.springbootpetfoodtracker.repository;

import com.bearl.springbootpetfoodtracker.model.FeedHistory;
import com.bearl.springbootpetfoodtracker.repository.projection.IFeedHistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFeedHistoryRepository extends JpaRepository<FeedHistory,Long> {

    @Query("select "+
            "p.name as name, fh.foodGram as foodGram, fh.feedTime as feedTime " +
            "from FeedHistory fh left join Pet p on p.id = fh.petId " +
            "where fh.userId = :userId")
    List<IFeedHistoryItem> findAllFeedsOfUser(@Param("userId") Long userId);


}
