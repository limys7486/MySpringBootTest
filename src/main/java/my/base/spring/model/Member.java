package my.base.spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.jdo.annotations.Unique;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_member")
@ToString
public class Member extends AbstractEntityModel
{
    private static final long serialVersionUID = 1L;

    /*
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    */
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "user_alias")
    private String userAlias ;

    @Column(name = "user_class")
    @Enumerated(EnumType.STRING)
    private UserClass userClass = UserClass.USER_GROUP_0 ;

    @Column(name = "user_mileage")
    private int userMileage = 0 ;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "phone")
    private String phone;

    @Column(name = "dept_group1")
    private String deptGroup1;

    @Column(name = "dept_group2")
    private String deptGroup2;

    @Column(name = "dept_group3")
    private String deptGroup3;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "emp_number")
    private String empNumber;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @Unique
    private String email;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private MemberRole role;
}
