package com.lcwd.electronic.store.helper;

import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class HelperPageResponse {

    public static <U,V> PageableResponse<V> getPageableResponse(Page<U> page,Class<V> type){ // here U is entity , V means Dto
        // get the user through page
        List<U> entity = page.getContent(); // U means  entity
        // here convert list of users to list of DtoUser through streams
        List<V> dtoList = entity.stream().map(object ->new ModelMapper().map(object,type)).collect(Collectors.toList());

        PageableResponse<V> response= new PageableResponse<>();
        response.setContent(dtoList);
        response.setPageNumber(page.getNumber()+1);
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return  response;
    }
}
