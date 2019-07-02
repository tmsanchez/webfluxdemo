package mx.tmsanchez.reactdemo.model;

import lombok.Data;

@Data
public class Photo {
    private Integer id;
    private Integer userId;
    private String title;
}
