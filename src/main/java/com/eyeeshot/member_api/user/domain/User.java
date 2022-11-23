package com.eyeeshot.member_api.user.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String email;

    @Column(length = 50)
    private String nickName;

    @Column(length = 50)
    private String name;

    @Column(length = 11)
    private String phoneNumber;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_id")
    private Authority authorities;

    public boolean isMatchPassword(String password) {
      return this.password.equals(password);
    }
}
