<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<u-chat room='test' style="display:inline-block; width:500px; height:500px;">
  <script>U=window.U=window.U||{},U.events=U.events||[],U.chat=function(n){return{on:function(e,t){U.events.push([n,e,t])},off:function(e){for(var t=U.events.length;t>0;)U.events[--t][0]==n&&U.events[t][1]==e&&U.events.splice(t,1)}}};</script>
  <script>
    U.chat('*').on('after.create', function( room, data ) {
    // room.skin json 은 create 에서 정의되므로 after이여야된다.
      room.skin.menubar.add( {
        id : 'alert'
        , title : "its alert!!"
        , html : '<div>hi</div>'
        , condition : function ( room, data ) {
           if(room.my.auth > 1) // 멤버이상이라면 표시하지 않는다.
             return false;
        }
        , onClick : function( room, data ) {
            room.print('hi ! '+room.my.nick+'<br><span style="font-size:20px;">html야!!</span>');
        }     
      });

      room.skin.userMenu.add( {
        id : 'test'
        , text : '테스트야~'
        , onClick : function ( room, data ) {
            if(room.my.nick == data.target)
              alert(' 이건 나야! ');
            else
              alert( '안뇽 '+data.target );
          }
      });
    });
  </script>
</u-chat>`
</body>
</html>