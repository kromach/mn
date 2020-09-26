<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<!--Start of Tawk.to Script == NOT group Chat-->
<!-- <script type="text/javascript">
var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
(function(){
var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
s1.async=true;
s1.src='https://embed.tawk.to/5f6eecfaf0e7167d0013ec39/default';
s1.charset='UTF-8';
s1.setAttribute('crossorigin','*');
s0.parentNode.insertBefore(s1,s0);
})();
</script> -->
<style>
        
    body {
        font-size: 12px;
    }

    * {
      box-sizing: border-box;
    }

    /* Container for everything TalkJS*/
    .chatbox-container {
        width: 420px;
        max-width: 100%; 
        margin: auto;
    }

    /* Custom header for the chatbox*/
    #chatbox-header {
        width: 100%;
        height: 110px;
        position: relative;
        background-color: #000;
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        /* Slightly curve the top of the header */
        border-radius: 10px 10px 0 0;
        margin-bottom: -3px;
        padding: 10px;
        position: relative;
    }

    #header-bg {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-size: cover;
      background-repeat: no-repeat;
      background-position: center;
      border-radius: inherit;
      opacity: 0.6;
    }

    #user-avatar {
      position: absolute;
      height: 50px;
      width: 50px;
      background-size: cover;
      background-repeat: no-repeat;
      background-position: center;
      border-radius: 50%;
      border: 2px solid #eee;
    }

    #chatbox-header p {
      font-family: "Helvetica", sans-serif;
      color: #eee;
      margin: 0;
    }

    #header-subject {
      position: absolute;
      font-size: 32px;

      left: 70px;
      top: 7px;
    }

    #header-alt {
      position: absolute;
      left: 70px;
      top: 42px;
      font-size: 18px;
    }

    /* Notification toggle */
    .toggle-container {
        text-align: right;
        position: absolute;
        bottom: 15px;
        right: 10px;
    }

    .toggle-container p {
        display: inline;
        font-size: 10px;
        padding-right: 10px;
        vertical-align: middle;
    }
    .toggle {
        vertical-align: middle;
        display: inline-block;
        position: relative;
        width: 51px;
        -webkit-user-select:none; 
        -moz-user-select:none; 
        -ms-user-select: none;
        user-select: none;
        height: 18px;
    }

    .toggle-checkbox {
        display: none;
    }

    .toggle-label {
        display: block; 
        overflow: hidden; 
        height: 17px; 
        padding: 0; 
        line-height: 17px;
        border: 0px solid #eee; 
        border-radius: 28px;
        background-color: rgba(135,135,135,0.75);
    }

    .toggle-label:before {
        content: "";
        display: block; 
        width: 28px; 
        margin: -5.5px;
        background: #FFFFFF;
        position: absolute; 
        top: 0; 
        bottom: 0;
        right: 30px;
        border-radius: 28px;
        box-shadow: 0 6px 5px 0px #000;
    }

    .toggle-checkbox:checked + .toggle-label {
        background-color: #0CCC26;
    }

    .toggle-checkbox:checked + .toggle-label, .toggle-checkbox:checked + .toggle-label:before {
       border-color: #0CCC26;
    }

    .toggle-checkbox:checked + .toggle-label {
        margin-left: 0;
    }

    .toggle-checkbox:checked + .toggle-label:before {
        right: 0px; 
        background-color: #FFFFFF; 
        box-shadow: 3px 6px 18px 0px rgba(0, 0, 0, 0.2);
    }
    </style>

</head>

<body>
    <!-- minified snippet to load TalkJS without delaying your page -->
    <script>
        (function(t,a,l,k,j,s){
      s=a.createElement('script');s.async=1;s.src="https://cdn.talkjs.com/talk.js";a.head.appendChild(s)
      ;k=t.Promise;t.Talk={v:1,ready:{then:function(f){if(k)return new k(function(r,e){l.push([f,r,e])});l
      .push([f])},catch:function(){return k&&new k()},c:l}};})(window,document,[]);
      </script>

    <!-- Container element for all TalkJS UI elements -->
    <div class="chatbox-container">

        <!-- Custom TalkJS chat header -->
        <div id="chatbox-header">

            <div id="header-bg"></div>
            
             <div id="user-avatar"></div>
             <p id="header-subject">Subject</p>
             <p id="header-alt">Offered by <span id="header-username">Username</span></p>
            
            <div class="toggle-container">
                <p>Enable browser notifications</p>
                <div class="toggle">
                    <input type="checkbox" name="notificationToggle" class="toggle-checkbox" id="toggle">
                    <label class="toggle-label" for="toggle"></label>
                </div>
            </div>
        </div>

        <!-- container element in which TalkJS will display a chat UI -->
        <div id="talkjs-container" style="height: 500px"><i>Loading chat...</i></div>

    </div>

    <script>
    Talk.ready.then(function() {
        var me = new Talk.User({
            id: "123456",
            name: "Alice",
            email: "alice@example.com",
            photoUrl: "https://demo.talkjs.com/img/alice.jpg",
            welcomeMessage: "Hey there! How are you? :-)"
        });
        window.talkSession = new Talk.Session({
            // Replace this with your own appId
            appId: "tGsiWlIO",
            me: me
        });
        var other = new Talk.User({
            id: "654321",
            name: "Sebastian",
            email: "Sebastian@example.com",
            photoUrl: "https://demo.talkjs.com/img/sebastian.jpg",
            welcomeMessage: "Hey, how can I help?"
        });

        var conversation = talkSession.getOrCreateConversation(Talk.oneOnOneId(me, other))
        conversation.setParticipant(me);
        conversation.setParticipant(other);

        // Set a custom image to use in the the header
        conversation.setAttributes({
            photoUrl: 'https://firebasestorage.googleapis.com/v0/b/klets-3642/o/user_files%2FHku1c4Pt%2F47edad6a000a40d19f369fc6cc683b57%2Fin-arlington-virginia-a-drafty-house-was-made-more-than-twice-as-efficient-with-the-addition-of-metal-both-inside-and-out-roof-panels-topping-the-brick-envelope-echo-its-original-geometry.jpg?alt=media&token=bea34579-566c-4cfb-8186-05b2c45273e2',
            subject: "Steel Preserve"
        })

        var chatbox = talkSession.createChatbox(conversation, {
            // Don't use the default header provided by TalkJS
            showChatHeader: false,
        });

        // Add the participant user's name to the custom header
        var headerUsername = document.getElementById('header-username');
        headerUsername.textContent = other.name;
        var headerSubject = document.getElementById('header-subject');
        headerSubject.textContent = conversation.subject;

        document.getElementById('header-bg').style.backgroundImage = "url(" + conversation.photoUrl + ")";
        document.getElementById('user-avatar').style.backgroundImage = "url(" + other.photoUrl + ")";


        // Custom Desktop Notifications checkbox
        var checkbox = document.getElementById('toggle');
        // See if the notification preference has been previously set, otherwise set them as disabled
        var notificationsEnabled = localStorage.getItem('desktopNotifications') || 'false';

        // Set the checkbox's state to the value stored in the storage, or false if notifications have not been eneabled previously
        notificationsEnabled === 'true' ? checkbox.checked = true : checkbox.checked = false;

        checkbox.addEventListener('click', function() {

            // When the checkbox is clicked, get the current notification state
            var currentlyEnabled = localStorage.getItem('desktopNotifications') || 'false';

            // If they aren't enabled, enable them when the checkbox is clicked
            if (currentlyEnabled === "false") {
                localStorage.setItem('desktopNotifications', 'true');
                checkbox.checked = true;
                chatbox.toggleDesktopNotifications(true);

            // If they are enabled, disable them when the checkbox is clicked
            } else {
                localStorage.setItem('desktopNotifications', 'false');
                checkbox.checked = false;
                chatbox.toggleDesktopNotifications(false);
            }
        });
        chatbox.mount(document.getElementById("talkjs-container"));
    });
    </script>
</body>
</html>