package kr.or.kosa;

//가장 전형적인 DTO 클래스
// 이런 코드는 누가 만들어도 동일한 코드 (사라져야한다)
//annotation >lombok
//https://projectlombok.org/ 롬복을 통해서 자동화

public class NoteBook {
    private int key;

    public NoteBook() {
    }

    public NoteBook(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "NoteBook [key=" + key + "]";
    }
}

