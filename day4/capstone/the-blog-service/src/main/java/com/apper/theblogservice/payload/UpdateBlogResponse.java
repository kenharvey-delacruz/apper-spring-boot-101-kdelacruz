package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateBlogResponse {
    private String id;

    private String blogger_id;

    @JsonProperty("created_at")
    private LocalDateTime created_at;

    @JsonProperty("last_updated")
    private LocalDateTime last_updated = LocalDateTime.now();
}
