package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name="client_documents")
public class ClientDocuments {

    @Id
    @Column(name = "document_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "document_seq")
    @SequenceGenerator(name = "document_seq", allocationSize = 1)
    private int documentID;

    @Column(name = "pan_card_extension")
    private String panCardExtension;

    @Column(name = "aadhar_card_extension")
    private String aadharCardExtension;

    @Column(name = "profile_photo_extension")
    private String profilePhotoExtension;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "is_aadhar_card_verified")
    private boolean isAadharCardVerified;

    @Column(name = "is_pan_card_verified")
    private boolean isPanCardVerified;

    public ClientDocuments(int documentID, String panCardExtension, String aadharCardExtension, String profilePhotoExtension, Client client) {
        this.documentID = documentID;
        this.panCardExtension = panCardExtension;
        this.aadharCardExtension = aadharCardExtension;
        this.profilePhotoExtension = profilePhotoExtension;
        this.client = client;
    }
}
