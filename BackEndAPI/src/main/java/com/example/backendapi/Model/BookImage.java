package com.example.backendapi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "book_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookImage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(columnDefinition = "uniqueidentifier")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String createdDate;
    private String image;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
