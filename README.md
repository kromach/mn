# 마시는 녀석들 
## 주요 구현 기능  
[SpringMVC]    
[SpringTiles]    
[SpringWebSocket]  
[myBatis]  
[CKEditor]  
[Kakao OAuth2.0]  
[gson]  
[Ajax]  
[log4j]  

## 개발완료 시연 주요 화면
<img src ="https://user-images.githubusercontent.com/64457004/97107609-ce690400-170b-11eb-9181-0a5550a51c32.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107610-d032c780-170b-11eb-93fd-a9d3b5317a47.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97135318-47f80500-1793-11eb-837c-67139c390387.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107611-d0cb5e00-170b-11eb-9a60-f3e89191a963.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107612-d163f480-170b-11eb-902d-2a990af8cf33.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107613-d163f480-170b-11eb-898a-b5ae9a4903eb.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107615-d1fc8b00-170b-11eb-81d5-58115353d604.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107617-d2952180-170b-11eb-85a7-5f7cf0005e84.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107618-d2952180-170b-11eb-876d-f30f986f7c49.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107619-d32db800-170b-11eb-9e0e-b9e1410c4009.png" width="600px" style="margin-bottom:15px"/><br>   
<img src ="https://user-images.githubusercontent.com/64457004/97107620-d32db800-170b-11eb-8329-9ec8f0a6448a.png" width="600px" style="margin-bottom:15px"/><br>   

## 개발일정   
<img src ="https://user-images.githubusercontent.com/64457004/95589062-2f8fa700-0a7f-11eb-85b3-439a412f62b1.png" width="600px"/>

## 기능정의서 中
<img src ="https://user-images.githubusercontent.com/64457004/95589066-328a9780-0a7f-11eb-9f5d-522d510e2b43.png" width="600px"/>  

## 디렉토리 정의서 中
<img src ="https://user-images.githubusercontent.com/64457004/95589073-34ecf180-0a7f-11eb-86b5-22522a98b1ec.png" width="600px"/>  

## 테이블 정의서 中
<img src ="https://user-images.githubusercontent.com/64457004/95589080-38807880-0a7f-11eb-9478-5fc00bf0ec28.png" width="600px"/>

## Coding Rule  
0. 회의시간 9:30~10:00
1. Controller마다 Data가 넘어오면 println으로 찍어놓기 - 형식(DataName=value)
2. coding Branch == master/ys/boo62/product/jahoon/209
3. Git push는 하나의 로직처리시 마다 단톡에 이야기 하고 push
4. pull은 매일 오전10:00 13:00 18:00 에 정기적으로, 상시pull은 단톡에 push가 올라오면 pull
5. 조장은 매일 20:00에 master와 branch를 merge하여 master에 Push
6. DAO는 DAO/DAOImpl 구조로 명명및 작성
7. UrlMapping과 Method이름을 일치시킨다

## Url 정리
Default localhost:8080/
## 디렉토리 구조
java/.../aop=AOP 
java/.../bean=chat/csv/fileDown/kakaoAPI/
			  CommonInterFace/JsonUtil/LowerHashMap/Pager  
			  
###Module      
java/.../module*/dao=DAO interface/class  
java/.../module*/vo=DTO  
java/.../module*/mvc=Controller  
java/.../module*/service=service  	
java/.../module*/sql=mybatis mapper xml    
WEB-INF/spring =servlet-context.xml을 제외한 모든 xml설정파일 
WEB-INF/views/tiles/*=layout관련 tileSetting파일

## AOP
memberAspect 쿠키/세션/관리자 검사   
Project.spring.aop.MemberAspect proceed를 기준으로 Session, Cookie검사및 Mapping method log 작성  
메소드이름 Ss로 끝내면 세션검사->로그인폼으로이동 AOP처리

## Spring tiles
ViewResolver String Return시에 "*.mn" 로 리턴시 template의 body

## Git을 다루면서 일어나는 문제들에 대한 Tip

## log4j
log4j.xml의 위치==src/main/resource/log4j.xml 

## Util 정리
### Pager(예제파일 : Project.spring.beans.example)
1. Pager AutoWired 이후, Pager Class의 pager(int PageNum,int count)를 사용할것  
2. controller에서 게시물 리스트 가져올때 사용법  articleList=boardDAO.getArticle(pageVO.getStartRow(),pageVO.getEndRow()) 
3. View에서 사용법 <c:if test="${pageVO.startPage > pageVO.pageBlock}"></c:if>

