package com.galvabank.portalapi.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galvabank.portalapi.User.User;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // This is a many-to-one relationship, it's not optional
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // This is a join column that is non-nullable, called "user_id"
    @JoinColumn(name = "user_id", nullable = false)
    // On delete, make sure that everything related is also deleted
    @OnDelete(action = OnDeleteAction.CASCADE)
    // Don't show this data when calling for statuses via an API call
    @JsonIgnore
    private User user;
}
