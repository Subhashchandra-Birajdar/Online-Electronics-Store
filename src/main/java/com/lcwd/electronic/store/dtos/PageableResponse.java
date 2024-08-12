package com.lcwd.electronic.store.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableResponse<T> {
    // this class we can use at the time of user response when user getting/fetching record
    // this PageableResponse we use for all module that's we took generic here

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

}
