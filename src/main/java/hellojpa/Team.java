package hellojpa;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @GeneratedValue
    @Id
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    /*
    mappedBy, 가짜 매핑, 두 객체의 연관 관계에서 주인이 아닌 쪽으로 읽기만 가능
    TeamMember가 연관 관계 주인으로 외래키를 관리한다.
    TeamMember의 필드에 있는 Team에 값이 들어가야 데이터베이스에 반영된다.
    아래 teamMembers 필드에 값을 넣어도 외래키는 바뀌지 않는다.
    연관 관계에 주인은 외래키를 가지고 있는쪽(TeamMember)이다.
     */
    @OneToMany(mappedBy = "team")
    List<TeamMember> teamMembers = new ArrayList<TeamMember>();

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

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void addMember(TeamMember member){
        member.setTeam(this);
        teamMembers.add(member);
    }
}
