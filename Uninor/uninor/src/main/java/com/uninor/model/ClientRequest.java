package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_request")
@Getter @Setter @NoArgsConstructor
public class ClientRequest {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "request_seq")
    @SequenceGenerator(name = "request_seq", allocationSize = 1)
    private int requestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sim_id")
    private SimCard simCard;

    @Column(name = "request_type")
    private int requestType;

    @CreationTimestamp
    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "request_status")
    private int requestStatus;

    @Column(name = "modified_by")
    private int modifiedByAdmin;

    @UpdateTimestamp
    @Column(name = "completion_date")
    private LocalDateTime completionDate;


}
