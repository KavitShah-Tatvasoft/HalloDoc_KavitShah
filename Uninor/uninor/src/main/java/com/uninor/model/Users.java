package com.uninor.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private int userId;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "is_registered")
    private boolean isRegistered;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Users(int userId, String emailAddress, String passwordHash, String salt, LocalDateTime createdDate, LocalDateTime modifiedDate, int roleId, boolean isRegistered, boolean isDeleted) {
        super();
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.roleId = roleId;
        this.isRegistered = isRegistered;
        this.isDeleted = isDeleted;
    }
}
