package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //JpaRepository를 안으로 들어가 보면 findAll()같은 애들은 기본적으로 구현되어 있다.
    //그래서 이 인터페이스 안은 비어있지만 동작하는 것임. 클래스가 아니라 인터페이스만으로 동작하는 것이 신기하다.
    Optional<Member> findByName(String name);
}