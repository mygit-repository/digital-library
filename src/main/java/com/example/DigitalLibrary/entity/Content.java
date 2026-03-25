package com.example.DigitalLibrary.entity;

import com.example.DigitalLibrary.constants.ContentStatus;
import com.example.DigitalLibrary.constants.ContentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "content_details")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Content extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", length = 500)
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    ContentType contentType; // PDF, AUDIO, VIDEO

    // ENABLED / DISABLED (controls STUDENT visibility only)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    ContentStatus status = ContentStatus.DISABLED;

    @Column(nullable = false)
    String filePath;

    @Column(name = "thumbnail_image_path")
    String thumbnailImagePath;

    @Column(name = "is_free")
    Boolean isFree;

    // Who uploaded the content (OFFICIAL / ADMIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", nullable = false)
    User uploadedBy;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;
}
