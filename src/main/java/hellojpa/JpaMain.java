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
        * 2.EntityManager는 DB 접근이 필요할때 생성해서 사용 후 제거하므로 쓰레드 간에 공유되어서는 안됨
        * 3.데이터 변경은 트랜잭션 안에서 실행해야 함
        * */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*저장*/
//            Member mem = new Member(); //비영속, transient
//            mem.setId(1L);
//            mem.setName("hello");
//            em.persist(mem); //Member 엔티티가 영속성 컨텍스트에 저장되면서 영속 객체가 됨

            /*수정
            * 조회한 엔티티는 영속성 컨텍스트에 저장되고, 엔티티가 변경되면 변경감지에 의해서
            * 직접 update하지 않아도 트랜잭션 commit시 업데이트 된다.
            *
            * */
            Member mem = em.find(Member.class, 1L);
            mem.setName("hell again");

            //em.detach(mem); //영속성 -> 준영속 상태로 변경, 이 엔티티는 영속성 컨텍스트에서 제거됨
            //em.clear(); //영속성 컨텍스트 초기화
            //em.close(); //영속성 컨텍스트 종료
            
            tx.commit(); //영속성 엔티티에 대한 쿼리가 실행되는 시점

            /*
            em.flush(); //강제 호출, 영속성 컨텍스트의 내용은 유지됨
            트랜잭션 커밋시 자동으로 flush 호출, 영속성 컨텐스트의 변경 내용을 DB에 쿼리를 보낸다.
            JPQL 쿼리 실행 전에도 flush가 자동 호출됨.
             */
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
