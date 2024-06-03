package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", allocationSize = 1)
    private int clientId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "pan_number")
    private String panNumber;

    @Column(name = "gst_number")
    private String gstNumber;

    @Column(name = "wallet_amount")
    private String walletAmount;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "is_doc_validated")
    private boolean isDocValidated;

    @Column(name = "validation_attempts")
    private int validationAttempts;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Client(int clientId, Users user, String firstName, String lastName, LocalDate dateOfBirth, String street, String city, String state, String zipcode, String panNumber, String gstNumber, String walletAmount, String email, LocalDateTime createdDate, LocalDateTime modifiedDate, boolean isDocValidated, int validationAttempts, boolean isDeleted) {
        super();
        this.clientId = clientId;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.panNumber = panNumber;
        this.gstNumber = gstNumber;
        this.walletAmount = walletAmount;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.isDocValidated = isDocValidated;
        this.validationAttempts = validationAttempts;
        this.isDeleted = isDeleted;
    }
}
