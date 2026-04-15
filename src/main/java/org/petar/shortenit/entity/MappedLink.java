package org.petar.shortenit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "mapped_link")
@NoArgsConstructor
@AllArgsConstructor
public class MappedLink implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(name = "original")
    private String originalLink;

    @Column(name = "shortened")
    private String shortenedLink;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}
