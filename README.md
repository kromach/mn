# Spring Project Git hub Page
## Coding Rule
0. 회의시간 9:30~10:00
1. Controller마다 Data가 넘어오면 println으로 찍어놓기 - 형식(DataName=value)
2. coding Branch == coding
3. Git push는 하나의 로직처리시 마다 단톡에 이야기 하고 push
4. pull은 매일 오전10:00 13:00 18:00 에 정기적으로, 상시pull은 단톡에 push가 올라오면 pull
5. 조장은 매일 20:00에 master와 branch를 merge하여 master에 Push
6. DAO는 DAO/DAOImpl 구조로 명명및 작성
7. UrlMapping과 Method이름을 일치시킨다

## Url 정리
Default localhost:8080/
## 디렉토리 구조
java/.../bean=모든 기능구현 클래스  
java/.../module*/dao=DAO interface/class  
java/.../module*/vo=DTO  
java/.../module*/mvc=Controller  
java/.../module*/service=service  	
java/.../module*/sql=mybatis mapper xml    
WEB-INF/spring =servlet-context.xml을 제외한 모든 xml설정파일 
## AOP 구상
memberAspect 쿠키/세션/관리자 검사   

## Git을 다루면서 일어나는 문제들에 대한 Tip

## Util 정리
### Pager(예제파일 : Project.spring.beans.example)
1. Pager AutoWired 이후, Pager Class의 pager(int PageNum,int count)를 사용할것  
2. controller에서 게시물 리스트 가져올때 사용법  articleList=boardDAO.getArticle(pageVO.getStartRow(),pageVO.getEndRow()) 
3. View에서 사용법 <c:if test="${pageVO.startPage > pageVO.pageBlock}"></c:if>

