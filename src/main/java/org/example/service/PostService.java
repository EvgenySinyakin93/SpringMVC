package org.example.service;

import org.springframework.stereotype.Service;
import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.repository.PostRepository;

import java.util.Collection;

@Service
public class PostService {
    // сервис завязан на интерфейс, а не на конкретную реализацию
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Collection<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}