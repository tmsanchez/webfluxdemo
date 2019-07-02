package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Post;
import reactor.core.publisher.Mono;

public interface IPostService {
    Mono<Post> getPost(Integer id);
}
