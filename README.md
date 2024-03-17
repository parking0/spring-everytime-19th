# spring-everytime-19th
* * *

## 에브리타임 DB 모델링

### 1. 에브리타임 서비스의 주요 특징
- 사용자는 게시물과 댓글을 익명으로 작성할 수 있다.
- 게시글, 댓글, 대댓글에 '좋아요'를 누를 수 있다.
- 1대1 쪽지를 주고 받을 수 있다.
- 게시글에 사진을 여러 장 첨부할 수 있다.


### 2. 모델링 결과

![Screenshot 2024-03-17 at 10.22.42 PM.png](..%2F..%2F..%2F..%2FDesktop%2FScreenshot%202024-03-17%20at%2010.22.42%E2%80%AFPM.png)

#### Domain
![Screenshot 2024-03-17 at 10.29.37 PM.png](..%2F..%2F..%2F..%2FDesktop%2FScreenshot%202024-03-17%20at%2010.29.37%E2%80%AFPM.png)

1. Comment
- id : Comment의 기본키다. `@GeneratedValue`라는 기본 키를 자동으로 생성해주는 어노테이션을 사용했다.
  전략은 `GenerationType.IDENTITY`를 사용하여, AUTO_INCREMENT처럼 데이터베이스에 값을 저장하고 나서 기본 키 값을 구할 수 있을 때 사용한다.
- postId : 해당 댓글이 달린 게시글의 아이디다. `@ManyToOne(fetch = FetchType.LAZY)`를 이용하여 조인했다.
- parentComment : 만약 대댓글이 아닌 댓글일 경우, 값이 null이 된다.
- childrenComment : 대댓글은 여러 개일 수 있다. 따라서 List로 저장한다.
- author : 댓글 작성자다. `@ManyToOne`을 이용하여 Member와 조인했다.
- content : 댓글의 내용이다. 길이는 최대 1000이고, null일 수 없다.
- likes : 댓글의 좋아요 개수다.
- isAnonymous : 에브리타임은 익명으로 댓글을 작성할 수 있기 떄문에, boolean으로 익명 여부도 확인한다.
- isDeleted : 댓글이 삭제될 수 있기 때문에 boolean으로 확인한다.
- createdAt : 댓글이 작성된 시간이다.
- updatedAt : 댓글이 수정된 시간이다.

2. CommentLike
- id : CommentLike의 기본키다.
- commentId : 해당 댓글을 의미한다. `@ManyToOne`을 이용하여 Comment와 조인했다.
- user : 좋아요를 누른 회원이다. `@ManyToOne`으로 Member와 조인했다.

3. Member
- id : Member의 기본키다. 
- loginId : 사용자가 로그인할 때 사용하는 아이디다. 길이는 최대 20이고, null값으로 둘 수 없도록 했다.
- userPw : 사용자가 로그인할 때 사용하는 비밀번호다. 길이는 최대 30이고, null값이 될 수 없다.
- userName : 사용자가 에브리타임에서 사용하는 닉네임이다. 중복되는 닉네임은 있을 수 없고, 길이는 10, null값은 불가능하도록 했다.
- email : 사용자의 이메일도 입력받도록 했다. 이 또한 null이 될 수 없다.

4. Message
- id : Message의 기본키다. 
- sender : 메시지 발신자다. `@ManyToOne`으로 Member와 조인했다.
- receiver : 메시지 수신자다. `@ManyToOne`으로 Member와 조인했다.
- content : 메시지의 내용이다. 길이는 최대 2000이고 null일 수 없다.
- isRead : 수신 상태를 확인하기 위해 boolean값으로 했다. 기본값은 false다.
- createdAt : 쪽지가 보내진 시간이다.
 
5. Photo
- id : Photo의 기본키다. 
- postId : 사진이 첨부된 게시글의 아이디다. `@ManyToOne`으로 Post와 조인했다.
- photoURL : 사진 경로를 나타내기 위해 String으로 지정했다.

6. Post
- id : Post의 기본키다. 
- title : 게시글의 제목이다. 길이는 최대 100이고 null일 수 없다.
- content : 게시글의 내용이다. 길이는 최대 2000이고 Null일 수 없다.
- createdAt : 게시글이 작성된 시간이다.
- updatedAt : 게시글이 수정된 시간이다.
- author : 게시글의 작성자다. `@ManyToOne`으로 Member와 조인했다.
- isAnonymous : 게시글은 익명으로 작성할 수 있기 때문에 boolean값으로 지정했다.
- likes : 게시글이 받은 좋아요 개수다. 초기값은 0이다.

7. PostLike
- id : PostLike의 기본키다. 
- postId : `@ManyToOne`으로 Post와 조인했다.
- user : 좋아요를 누른 회원이다. `@ManyToOne`으로 Member와 조인했다.

