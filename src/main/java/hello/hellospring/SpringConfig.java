package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    //extends JpaRepository하는 객체는 스프링이 자동으로 구현해주고 빈에 등록한다.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //-------------------------------------------------------------------------------
//    private DataSource dataSource;
//    //MemberRepository는 멤버로 둘 수 없다. 여기서 스프링 빈으로 등록하려하는데 AUtowired를 할 수가 없잖아. 그래서 circular dependencies가 뜬다. DataSource는 application.properties에서 빈을 등록해주니까 가능한 것이다.
////    private MemberRepository memberRepository;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource/*, MemberRepository memberRepository*/) {
//        this.dataSource = dataSource;
////        this.memberRepository = memberRepository;
//    }
    //-------------------------------------------------------------------------------
//    @Autowired private EntityManager em;
//------------------------------------------------------------


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); //안에서 함수를 호출한다. 이때 MembereRepository객체는 2개가 되는 것이 아니다. 근데 이 부분이 잘 이해되지 않는다.
    }

//-----------------------------spring data jpa사용하면서 필요 없게 됨.--------------------
//    @Bean
//    public MemberRepository memberRepository() {
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
    //-----------------------------------------------------------


    //dataSource가 스프링 빈에 등록되어 있기 때문에 멤버로 가능하다. MemoryMemberRepository는 멤버로 선언할 수가 없다.


    //잘 동작한다
//    @Bean
//    public String myString() {
//        return new String("abcde");
//    }

}
