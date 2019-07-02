package mx.tmsanchez.reactdemo.service;

import mx.tmsanchez.reactdemo.model.Photo;
import reactor.core.publisher.Mono;

public interface IPhotoService {

    Mono<Photo> getPhoto(Integer userId);

}
