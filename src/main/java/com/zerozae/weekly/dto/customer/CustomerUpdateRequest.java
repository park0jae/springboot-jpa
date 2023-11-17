package com.zerozae.weekly.dto.customer;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerUpdateRequest {

    @NotNull
    private String name;
}
