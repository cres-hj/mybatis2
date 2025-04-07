package com.example.mybatis2.memo;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface MemoDao {
  @Insert("insert into memo(mno, title, content, writer) values(memo_seq.nextval, #{title}, #{content}, #{writer})")
  int save(Memo memo);

  @Select("select mno, title, writer, reg_date as regDate from memo")
  // 이름 통일하는 쉬운 방법: reg_date as regDate: reg_date를 regDate로 담아라
  List<Memo> findAll();

  @Select("select * from memo where mno=#{mno} and rownum=1")
  Optional<Memo> findByMno(int mno);
  // 결과값의 개수가 1개라는걸 나는 알아. 그런데 오라클은 모르잖아(->mno 똑같은거 찾고 끝내면 되는데 얜 그걸 모르니까 끝까지 쭉 찾으러 감).
  // -> 개수를 오라클에게 알려줄 수 있어 => rownum=개수 <- 개수만큼 찾으면 종료
  // rownum 있든 없든 결과는 동일한데 적어주는편이 실행할 때 더 효율적이긴 하겠지
  // Optional: null이 발생할 수 있는 함수! null 생길 수 있으니까 알아둬~ 하고 Dao 사용하는 사람에게 알려주는거
  //    만약 Memo findByMno(int mno);로 적으면 얜 값이 절대 null이 아니야 무조건 값이 있어
  //    "null 발생할 수 있으니까 null 확인해! 하고 강요하는 친구야"

  @Update("update memo set content=#{content} where mno=#{mno} and rownum=1")
  public int update(String content, int mno);
  // 여러개 바꿀 수 있다 할거면 public int update(Memo memo)로 한번에 다 받아
  // 물론 하나만 바꾼다해도 Memo memo 이렇게 해도 됨 어떻게 할지는 알아서~합의~

  @Delete("delete from memo where mno=#{mno} and rownum=1")
  public int delete(int mno);
}
