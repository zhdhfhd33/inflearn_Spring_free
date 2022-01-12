package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    //메모리에 유저를 저장하는 구현체임. Map을 사용
    //동시성 문제가 있을 수 있어서 실무에서는 cocurrentHashMap을 써야 하지만 그냥 진행한다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;//얘도 동시성 문제를 고려해서 0이 아니라 다른 자료형을 사용해야함.


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny(); //findAny()는 하나라도 찾는 것이다. 있는지 없는지 확인.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
