package mx.tmsanchez.reactdemo.model;

import org.springframework.data.annotation.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    @Id
    private String id;
    private String status;
    private Integer postId;
    private LocalDateTime creationTime;
    private LocalDateTime postAtTime;
    private LocalDateTime photoAtTime;
    private Post post;
    private Photo photo;
}
