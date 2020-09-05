package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        //기본적인 jpa 실행 과정
        /*
        * 1.EntityManagerFactory는 어플리케이션 전체에서 공유
        * 2.EntityManager는 DB 접근이 필요할때 생성해서 사용 후
        * 제거하므로 쓰레드 간에 공유되어서는 안됨
        * 3.데이터 변경은 트랜잭션 안에서 실행해야 함
        * */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*저장*/
//            Member mem = new Member();
//            mem.setId(1L);
//            mem.setName("hello");
//            em.persist(mem);

            /*수정
            * 조회한 엔티티는 jpa가 관리하므로 persist하지 않아도 commit 업데이트 된다.
            *
            * */
            Member mem = em.find(Member.class, 1L);
            mem.setName("hell again");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
