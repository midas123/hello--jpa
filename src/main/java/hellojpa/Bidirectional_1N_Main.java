package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Bidirectional_1N_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            TeamMember member = new TeamMember();
            member.setName("member1");
            //양방향 연관관계 주인에게 값을 세팅
            em.persist(member);

            /*
            지연 로딩에 의해서 EntityManager가 flush 되기 전까지
            한 트랜잭션의 쿼리 결과는 1차 캐시에 보관된다. 이런 상태에서는 주인이 아닌 Team 엔티티에서
            멤버 리스트를 가져오면 1차 캐시에 저장된 것을 그대도 가져오므로 주인과 주인이 아닌 엔티티
            둘다 직접 연관관계 값을 세팅해줘야 이후 코드에서 멤버 리스트를 가져올 수 있다.
            값은 setter로 직접 세팅하는 것 보다는 엔티티 내부에 변경 메서드를
            만들어 주는게 바람직함.
            */

            member.changeTeam(team);
            /*
            주인(TeamMember) 또는 주인이 아닌 엔티티(Team), 둘 중 한 곳에서 연관 관계 값을 세팅
            team.addMember(member);
            * */

            //em.flush();
            //em.close();

            Team findTeam = em.find(Team.class, team.getId());

            List<TeamMember> members = findTeam.getTeamMembers();
            for(TeamMember m : members){
                System.out.println(m.getName());
            }

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
