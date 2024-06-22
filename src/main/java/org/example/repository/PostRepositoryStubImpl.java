package org.example.repository;

import org.springframework.stereotype.Repository;
import org.example.exception.NotFoundException;
import org.example.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
    private final Map<Long, Post> allPosts;
    private final AtomicLong idCounter = new AtomicLong(0L);

    public PostRepositoryStubImpl(Map<Long, Post> allPosts) {
        this.allPosts = new ConcurrentHashMap<>();
    }

    public Collection<Post> all() {
        return allPosts.values();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(allPosts.get(id));
    }

    public Post save(Post savePost) {
        if (savePost.getId() == 0) {
            long id = idCounter.incrementAndGet();
            savePost.setId(id);
            allPosts.put(id, savePost);
        } else if (savePost.getId() != 0) {
            Long currentId = savePost.getId();
            allPosts.put(currentId, savePost);
        }
        return savePost;
    }

    public void removeById(long id) {
        if (allPosts.containsKey(id)) {
            allPosts.remove(id);
        } else {
            throw new NotFoundException("Введен неверный id");
        }
    }
}