package com.eyeeshot.member_api.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    @Column(name = "role", length = 50)
    private String role;

}
