package com.thikthak.app.acl.auth.domain;

import com.thikthak.app.domain.base.Organization;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="AUTH_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="UserPkIdSeq")
    @SequenceGenerator(name="UserPkIdSeq",sequenceName="USER_PKID_SEQ", allocationSize=5)
    @Column(name = "ID")
    private Long id; // userId

    @Size(max = 15)
    @NotEmpty
    @NotBlank(message = "*Name is mandatory")
    @Column(name = "USERNAME", length = 15, nullable = false, unique = true)
    private String username;
    @NotBlank(message = "*Password is mandatory")
    @Column(name = "PASSWORD")
    private String password;
    @Value("${true}")
    @Column(columnDefinition = "boolean default true")
    private boolean enabled;
//    private boolean enabled = true;
    private boolean accountExpired;
    boolean accountLocked;
    boolean passwordExpired;

    // added attributes
    String phoneNumber;          // as username // maximum length of 15 digits
    String firstName;
    String lastName;
    @Column(nullable=false)
    String displayName;          // marge firstName and lastName
    String profession;

    @Email(message="{errors.invalid_email}")
    @Column(name="email",nullable = false, unique = true)
    String email;                // [user]@[mysite].com = 64 + 255, but it should be 254
    String city;                 // [Dhaka, Chattogram, Sylhet...]
    String fullAddress;
    String userType;             // Group Checkbox [client, technician-default technician now]
    String expertiseArea;        // [Electronics, Electrical, Software, Mechanical.....]
    String expertiseKeywords;    // [Laptop, Mobile, TV]
    String gender;               // Optional
    Date birthDate;              // Optional
    Double targetEarningPerMonth;// Optional (25000, 35000, 50000... --- Inspirational )
    // securityCode ------------ 4 digit code sent to mobile and need put this input box
    Double rating;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date registrationDate;
    Boolean isApproved;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date approvalDate;

    String deviceType;
    String deviceToken;
    Boolean activeOnline;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;


    @Column(name = "PROFILE_PIC_PATH", length = 300)
    String profilePicPath;

    @Column(name = "PROFILE_PIC_PATH2", length = 300)
    String profilePicPath2;


    // below attributes only for technician
    @Column(name = "NUM_OF_CMPL_JOB")
    Integer numOfCmplJob;          // cmplTaskCounter // all completedTaskCounter
    //    @Column(name = "TODAY_CMPL_TASK_COUNTER")
//    Integer todayCmplTaskCounter;   // todayCompletedTaskCounter
//    @Column(name = "DAILY_TASK_CR_LIMIT")
//    Integer dailyTaskCrLimit;       // maximum capability of a day // it make Technician Busy / Available
//    @Column(name = "DAILY_UNCMPL_TASK_CR_LIMIT")
//    Integer dailyUncmplTaskCrLimit; // dailyUncompleteTaskCrLimit // todayNumOfIncompleteTask // maximum capability of a day // it make Technician Busy / Available
    @Column(name = "NUM_OF_PENDING_JOB")
    Integer numOfPendingJob;
    @Column(name = "PENDING_JOB_LIMIT")
    Integer pendingJobLimit;
//    @Column(name = "TODAY_JOB_TAG_NO")
//    Integer todayJobTagNo; // use later if needed
    @Column(name = "DAILY_JOB_ASSIGN_LIMIT")
    Integer dailyJobAssignLimit; // may be default 10 job // Integer dailyTaskCrLimit; // maximum capability of a day // it make Technician Busy / Available
    Boolean isBusy;





    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "auth_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }





}
