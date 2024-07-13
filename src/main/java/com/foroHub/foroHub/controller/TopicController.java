package com.foroHub.foroHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.foroHub.foroHub.model.Topic;
import com.foroHub.foroHub.service.TopicService;
import com.foroHub.foroHub.dto.TopicDTO;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;


    @GetMapping("/{id}")
    public ResponseEntity<Topic> findById(@PathVariable Long id) {
        Topic topic = topicService.findById(id);
        if (topic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(topic);
    }

    @PostMapping
    public ResponseEntity<Topic> create(@RequestBody @Valid TopicDTO topicDTO, UriComponentsBuilder uriBuilder) {
        Topic createdTopic = topicService.create(topicDTO);

        var uri = uriBuilder.path("/api/topics/{id}").buildAndExpand(createdTopic.getId()).toUri();
        return ResponseEntity.created(uri).body(createdTopic);
    }


    @GetMapping
    public ResponseEntity<Page<Topic>> getAllTopics(@PageableDefault(size = 10) Pageable pageable) {
        Page<Topic> topics = topicService.findAllTopics((org.springframework.data.domain.Pageable) pageable);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Topic>> getTop10Topics() {
        List<Topic> topics = topicService.findFirst10Topics();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicService.findTopicById(id);
        if (topic.isPresent()) {
            return ResponseEntity.ok(topic.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(
            @PathVariable Long id,
            @Valid @RequestBody Topic updatedTopic) {
        Optional<Topic> topic = topicService.updateTopic(id, updatedTopic);
        if (topic.isPresent()) {
            return ResponseEntity.ok(topic.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        boolean isDeleted = topicService.deleteTopic(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
