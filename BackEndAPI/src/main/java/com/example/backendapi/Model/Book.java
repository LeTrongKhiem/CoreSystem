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
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "books")
//@Where(clause = "is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String name;
    private String author;
    private Date createdDate;
    private String description;
    private boolean isExchange;
    private String contactPhone;
    @OneToMany(mappedBy = "book")
    private Set<BookImage> bookImages;
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
