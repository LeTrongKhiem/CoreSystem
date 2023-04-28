package com.example.backendapi.ModelMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingModel<T> {
    public List<T> results;
    public int PageSize;
    public int TotalCount;
    public int CurrentPage;
    public int TotalPages;
}
