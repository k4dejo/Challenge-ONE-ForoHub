package com.foroHub.foroHub.repository;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.foroHub.foroHub.model.Topic;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndMessage(String title, String message);
    List<Topic> findAllByOrderByCreationDateAsc(PageRequest pageRequest);

    List<Topic> findAllByOrderByCreationDateAsc(Pageable pageable);
}
