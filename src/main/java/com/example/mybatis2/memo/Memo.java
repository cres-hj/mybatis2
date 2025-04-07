package com.example.mybatis2.memo;

import lombok.*;

import java.time.*;

// 스프링은 커맨드객체(사용자 입력을 통해 생성하는 객체)를 생성할 때 기본생성자로 생성한 후 @Setter로 값을 집어넣음
//    => 기본생성자, setter 있어야해
// 롬복에서 @Builder를 사용할 때 @AllArgsConstructor도 필요

@Getter
@Setter
@AllArgsConstructor
@Builder  // 얘도 배포할땐 지워야지
@NoArgsConstructor  // builder 빠지면 얘도 지워야지
@ToString  // 배포할땐 지워야지
// 값 받아올거니까 setter 있어야해
// 스프링 구조상 메모를 이용해서 입력을 받아와야하고 스프링은 생성자가 아니라 setter로 받아오기 때문에 일단 setter 쓰고 setter 만들지 않고 하는 방법은 나중에 배우자
public class Memo {
  private int mno;
  private String title;
  private String content;
  private String writer;
  // 빌더를 사용한 경우에 필드에 값을 직접 지정하는 인스턴스 초기화를 무시
  // @Builder.Default  db에서 기본값 줬으니까 @builder도 빼도 됨
  private LocalDate regDate;  // db에서 기본값 줘서 여기에는 기본값 안줘도 됨
}
