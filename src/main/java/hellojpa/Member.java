package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Hello_Member")
/*
@Table(name = "Hello_Member", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "userNameConstraint")})
*/
/*@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)*/
/*@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)*/
public class Member {
    //숫자, 문자열 등 직접 생성한 id 할당시
    //@Id
    //private Long id;

    //자동 할당
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //insert 쿼리에 id 값이 null, DB에 위임
    private Long id;
    /*
    GenerationType.IDENTITY, autoincrement 전략을 사용하는 엔티티는
    persist 하는 시점에 바로 쿼리가 실행된다. 그리고 Jpa 내부적으로
    DB에서 생성된 id 값을 포함하여 엔티티를 영속성 컨텍스트에 저장한다.
    */

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    //GenerationType.SEQUENCE 전략은 기본적으로 persist 전에 시퀸스를 조회하여
    id 값을 미리 받아온다. 그리고 쿼리는 트랜잭션 커밋시 실행된다.
    persist 전에 매번 시퀸스에서 id 값을 조회하는 것을 최소화 하려면
    allocationSize를 설정한다. 설정한 값 만큼 미리 id 값을 DB 생성해두고
    최초한번, 그리고 생성한 id 값의 최대치에 도달했을때 시퀸스를 조회한다.
    */

    /*
    //테이블 맵핑 전략
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;*/

    @Column(name="username", nullable = true, length = 10)
    //@Column(name="username", columnDefinition = "varchar(10) default 'anonymous'")
    //@Column(name="username", updatable = false, insertable = true)
    private String name;

    @Column(length = 20)
    private Integer age;

    @Enumerated(EnumType.STRING)
    //EnumType.STRING 사용해야 문자 그대로 DB에 저장됨
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /*Java 8 Support in Hibernate 5*/
    //private LocalDate createdDate;
    //private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /*
    CLOB: String, char[]
    BLOB: byte[]
    */
    @Lob
    private String description;

    @Transient //field is not persistent
    private String temp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //getter, setter

}
