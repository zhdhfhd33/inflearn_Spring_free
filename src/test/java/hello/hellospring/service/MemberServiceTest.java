package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //테스트가 실행 될 때 마다 초기화를 진행하기 위해서는 MemoryRepository를 멤버로 가지고 있어야 한다.
    //의문점 : memberService안의 memberRepository의 join()을 실행하는 것인데 이렇게 객체를 하나 만든다고 해서 이게 동작하게 되는 것이 신기하다.
    //MemoryMemberRepository가 static으로 store 멤버를 가지고 있기 때문에 가능하다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //BeforeEach와 MemberService의 생성자를 변경해서 테스트가 잘 돌아가게 만들 수 있다.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello"); //만약 여기서 spring1을 넣었다면 중복_회원_예외()에서 join(member1)일 때 에러가 난다.

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //givne
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        //when
        memberService.join(member1);

        //trycatch보다는 이 방식이 선호된다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다");

        //아래는 try catch로 감싸는 방식
        //Exception을 throw해 놓았기 때문에 이를 검사하기 위해서는 try-catch로 감싸야 한다.
        /*try {
            memberService.join(member2);
            //예외가 나와서 catch로 가지 않고 밑으로 온다면 잘못된 것이다.
            //test의 실패를 fail()로 알릴 수 있다.
            fail();

        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "이미 존재하는 회원입니다");
        }*/


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}