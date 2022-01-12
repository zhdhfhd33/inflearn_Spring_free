package hello.hellospring.controller;

public class MemberForm {
    private String name; //name과 html의 name="~" 이 매칭된다. 여기서는 name="name" 이라서 변수 명을 name이라고 했음.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
