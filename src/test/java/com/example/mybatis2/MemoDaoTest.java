package com.example.mybatis2;

import com.example.mybatis2.memo.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemoDaoTest {
  @Autowired
  private MemoDao memoDao;


  // junit 테스트는 항상 void고 파라미터 없어
  // @Test
  public void saveTest() {
    // Memo memo = new Memo(0, "토익 접수", "5월 토익 접수 준비", "spring", LocalDate.now());
    // 위처럼 쓰면 mno, regDate같은 기본값도 값을 줘야하니까 너무 길어져 -> builder 써서 해결하자~
    Memo m = Memo.builder().title("토익 접수").content("토익 접수 확인").writer("spring").build();
    int result = memoDao.save(m);
    assertEquals(1, result);
  }

  // @Test
  public void findAllTest() {
    assertEquals(1, memoDao.findAll().size());
    // 지금 테스트하면서 1개 들어있으니까 asesertEquals로 비교
  }

  // @Test

  public void findByMno() {
    Optional<Memo> result = memoDao.findByMno(1);
    // Optional을 전달받으면 get()으로 객체를 꺼낼 수 있다
    // 단 없으면 NoSuchElementException 발생
    // Memo memo = result.get();  <- 이렇게 바로 뽝 꺼내지 마
    if(result.isPresent()) {
      Memo memo = result.get();
    }
  }

  // @Test
  public void updateTest() {
    int result = memoDao.update("토익 접수 했니", 1);
    assertEquals(1, result);
    // 실제 상황에서는 실패하는것도 테스트해줘야 좋아
    // int result2 = memoDao.update("귀찮아요", 100);
    // assertEquals(0, result2);
  }

  // @Transactional  // Test에서 사용하면 자동으로 롤백(테스트가 아닌 곳에선 의미 달라져)
  // @Test
  public void deleteTest() {
    int result = memoDao.delete(1);
    assertEquals(1, result);
    // 실패하는 경우도 확인합시다
    result = memoDao.delete(100);
    assertEquals(0, result);
  }

}
