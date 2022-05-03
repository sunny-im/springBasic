package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // sequence는 0,1,2 ... 키값을 생성해주는애

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // get(id)가 null이 반환 될 가능성이 있다면 Optional~~~로 감싸서 반환 !
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()         // 자바 람다의 기본문법.. store의 데이터를 for문처럼 돌리는 것 !! 이건 다시 봐보자...
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  //values() >> member
    }
}
