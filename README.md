# Network22_project

류관곤 / 이승원 / 이소정 / 백민우

Language - Java


### Project - Auction pocker game
#### Client

- GUI - Swing
- Client에서는 총 2가지의 Thread를 사용한다.
  1. 게임 시작과 동시에 서버로부터 요청을 받는 대기 상태의 Thread를 실행시킨다.
  2. Client에서 Server에 요청을 보내는 Thread


#### Server

- Server Structure
  - Server
    - Server의 Socket을 열어주고 Client의 연결을 담당
  - User Thread
    - 각각의 Client에 할당되는 Thread.
    - Client의 request를 받는 부분.
  - Game Thread
    - 4명의 User가 모두 접속 했을 경우 실행되는 Thread.
    - 게임의 흐름을 담당하는 Thread.
  - Broadcast Thread
    - 게임에 참여중인 User에게 response를 전달하는 Thread.
  - Timer Thread
    - Client의 request처리 시간과 게임 카운트 다운을 병렬적으로 실행.
    
      <img width="569" alt="스크린샷 2022-12-16 오후 10 35 11" src="https://user-images.githubusercontent.com/97783148/208109846-3a795315-7b01-4952-967c-de896c8ebacc.png">

- Server Syncronization
  - Syncronized 를 사용 - Client로 부터 동시에 들어오는 request를 순차적으로 실행하기 위함.
  - wait - Client request 결과를 User가 동시에 반영기 위함.

