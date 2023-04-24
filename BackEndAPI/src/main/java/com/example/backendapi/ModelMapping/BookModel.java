package com.example.backendapi.ModelMapping;

import com.example.backendapi.Model.PartFileModel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BookModel {
    private String name;
    private String author;
    private Date createdDate;
    private String description;
    List<PartFileModel> productImages;
}
