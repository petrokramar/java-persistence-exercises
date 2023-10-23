package com.bobocode.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "photo_comment")
public class PhotoComment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;
    private LocalDateTime createdOn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public PhotoComment(String text, Photo photo) {
        this.text = text;
        this.photo = photo;
        this.createdOn = LocalDateTime.now();
    }
}
