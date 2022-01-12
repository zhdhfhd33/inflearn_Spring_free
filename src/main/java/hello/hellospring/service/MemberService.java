package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //위와 다르게 멤버에서 생성하지 않고 매개변수로 받도록 생성자를 변경. 이런 것을 dependency injection(DI)이라고 한다.
    //생성자가 하나면 알아서 Autowired해준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        //중복 x
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    //중복 x
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    //전체회원조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //하나 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
