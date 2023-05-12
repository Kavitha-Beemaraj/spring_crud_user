package com.springrestapi.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotNull(message = "name cannot be null")
    @Size(min = 4, max = 40, message = "enter the valid name")
    private String name;
    @NotNull(message = "email cannot be null")
    @Email(message = "invalid email format")
    private String email;
    private int age;
    @NotNull(message = "gender cannot be null")
    private String gender;
    @NotNull(message = "subject cannot be null")
    private String subject;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
