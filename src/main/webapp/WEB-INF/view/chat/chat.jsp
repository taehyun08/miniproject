<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Socket.io 1:1 Chat Example</title>
    <script src="http://localhost:3000/socket.io/socket.io.js"></script>
    <script>
        $(function(){
            $("#messageInput").keydown(function(key) {
                //해당하는 키가 엔터키(13) 일떄
                if (key.keyCode == 13) {
                    sendMessage();
                }
            });

            socket.on('private message', ({ sender, message }) => {
                const ul = document.getElementById('messages');
                const li = document.createElement('li');
                li.appendChild(document.createTextNode(`${sender}: ${message}`));
                ul.appendChild(li);
            });

        });
        const socket = io();
        let username = '';

        function setUsername() {
            const usernameInput = document.getElementById('usernameInput');
            username = usernameInput.value;
            socket.emit('setUsername', username);
            usernameInput.disabled = true;
        }

        function sendMessage() {
            const recipientInput = document.getElementById('recipientInput');
            const messageInput = document.getElementById('messageInput');
            const usernameInput = document.getElementById('usernameInput');
            const receiver = recipientInput.value;
            const message = messageInput.value;
            const sender = usernameInput.value;

            const ul = document.getElementById('messages');
            const li = document.createElement('li');
            li.appendChild(document.createTextNode(`${message}`));
            ul.appendChild(li);

            socket.emit('private message', { sender, receiver, message });
            messageInput.value = '';
        }
    </script>
</head>
<body>
<h1>1:1 채팅방</h1>
<div id="usernameInput" value="${user.userName}(${user.userId})">
<button onclick="setUsername()">Set Username</button>
<hr>
<input id="recipientInput" placeholder="Enter recipient's username">
<input id="messageInput" placeholder="Enter your message">
<button onclick="sendMessage()">Send Message</button>
<ul id="messages"></ul>


</body>
</html>
