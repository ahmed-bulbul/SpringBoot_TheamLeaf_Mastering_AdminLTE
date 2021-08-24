package com.thikthak.app.acl.auth.domain;

import com.thikthak.app.acl.auth.domain.Role;
import com.thikthak.app.acl.auth.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AUTH_USER_ROLE")
public class UserRole {
    private static final long serialVersionUID = 1;

    //    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(generator="SysPkIdSeq")
    @SequenceGenerator(name="SysPkIdSeq",sequenceName="SYS_PKID_SEQ", allocationSize=5)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    User user;
    @ManyToOne
    Role role;

    // System log fields
    Date creationDateTime;
    String creationUser;
    Date lastUpdateDateTime;
    String lastUpdateUser;



}
