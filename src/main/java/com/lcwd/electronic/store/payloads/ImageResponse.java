package com.lcwd.electronic.store.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class ImageResponse {

        private String imageName;
        private Boolean success;
        private HttpStatus status;

    }

