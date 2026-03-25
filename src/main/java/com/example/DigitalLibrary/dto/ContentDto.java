package com.example.DigitalLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentDto {
    Long id;

    @NotBlank(message = "title can't be null or empty")
    @Size(max = 100, message = "Title must be at most 100 characters")
    @Pattern(
            regexp = "^[A-Za-z0-9 .,:'\"!?()&-]+$",
            message = "Title contains invalid characters"
    )
    String title;

    @NotBlank(message = "title can't be null or empty")
    @Size(max = 500, message = "Title must be at most 100 characters")
    String description;

    @NotNull(message = "contentType can't be null or empty")
    @Pattern(regexp = "^(PDF|AUDIO|VIDEO)$", message = "Content type must be PDF, AUDIO, or VIDEO")
    String contentType;

    @NotNull(message = "visibility can't be null or empty")
    @Pattern(regexp = "^(STUDENT|OFFICIAL|BOTH)$", message = "Content type must be STUDENT, OFFICIAL, or BOTH")
    String visibility;

    String filePath;

    String coverImgPath;

    String thumbnailImgPath;

    Long uploadedBy;

    Boolean isActive;
    Boolean status;
}
