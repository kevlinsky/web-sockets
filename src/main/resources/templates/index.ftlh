<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Web Sockets</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script>
        var webSocket;
        function connect() {
            webSocket = new WebSocket('ws://localhost:8080/chat');
            webSocket.onmessage = function receiveMessage(response) {
                let data = response['data']
                let parsedData = JSON.parse(data);
                $('#text').html(parsedData['text'])
            }
        }
        function sendMessage(text) {
            let message = {
                "text": text
            };
            webSocket.send(JSON.stringify(message))
        }
    </script>
</head>
<body onload="connect()">
    <label for="message">Введите сообщение...</label>
    <input id="message" name="message" type="text">
    <button onclick="sendMessage($('#message').val())">Отправить</button>
    <div id="text"></div>
</body>
</html>