package com.foroHub.foroHub.service;


import com.foroHub.foroHub.dto.TopicDTO;
import com.foroHub.foroHub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.foroHub.foroHub.repository.TopicRepository;
import com.foroHub.foroHub.model.Topic;
import com.foroHub.foroHub.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }


    public Topic findById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }


    public Topic create(TopicDTO topicDTO) {
        if (topicRepository.existsByTitleAndMessage(topicDTO.getTitle(), topicDTO.getMessage())) {
            throw new IllegalArgumentException("El tópico con el mismo título y mensaje ya existe");
        }

        User author = (User) UserRepository.findByName(topicDTO.getAuthorId()).orElseThrow(() ->
                new IllegalArgumentException("El autor no existe"));

        Topic topic = new Topic();
        topic.setTitle(topicDTO.getTitle());
        topic.setMessage(topicDTO.getMessage());
        topic.setAuthor(author);
        topic.setStatus("ACTIVE"); // Por defecto, el estado es "ACTIVO"

        return topicRepository.save(topic);
    }


    public List<Topic> findAllTopicsByDate() {
        return topicRepository.findAllByOrderByCreationDateAsc(PageRequest.of(0, 10));
    }

    public List<Topic> findFirst10Topics() {
        return topicRepository.findAllByOrderByCreationDateAsc(PageRequest.of(0, 10));
    }

    public Page<Topic> findAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    public Optional<Topic> findTopicById(Long id) {
        return topicRepository.findById(id);
    }

    public Optional<Topic> updateTopic(Long id, Topic updatedTopic) {
        Optional<Topic> existingTopic = topicRepository.findById(id);
        if (existingTopic.isPresent()) {
            Topic topic = existingTopic.get();
            topic.setTitle(updatedTopic.getTitle());
            topic.setMessage(updatedTopic.getMessage());
            topic.setAuthor(updatedTopic.getAuthor());
            topicRepository.save(topic);
            return Optional.of(topic);
        } else {
            return Optional.empty();



        }
    }

    public boolean deleteTopic(Long id) {
        Optional<Topic> existingTopic = topicRepository.findById(id);
        if (existingTopic.isPresent()) {
            topicRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
