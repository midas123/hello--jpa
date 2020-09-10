package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Unidirectional_1N_Main {
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
            member.setTeam(team);
            em.persist(member);

            //em.flush();
            //em.close();

            TeamMember findMember = em.find(TeamMember.class, 1L);
            Team findTeam = mem.getTeam();
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
