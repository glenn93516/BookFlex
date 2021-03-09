package com.ssafy.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBookDto {
    private Long userBookId;
    private Long userId;
    private Long bookIsbn;
}
