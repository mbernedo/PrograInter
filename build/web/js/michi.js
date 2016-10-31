/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var endPointURL = "ws://192.168.130.22:8080/WebService/michi";
var chatClient = null;

function connect() {
    chatClient = new WebSocket(endPointURL);
    chatClient.onmessage = function (event) {
        console.log(event);
       // var jsonObj = JSON.parse(event.data);
       // var message = jsonObj.user + ": " + jsonObj.message + "\r\n";
        console.log(event.data);
    };
}

$("#btnEnviar").click(function () {
    sendMessage();
});

function disconnect() {
    chatClient.close();
}

function sendMessage() {
    var jsonObj = {"cod": 1, "msg": "message"};
    chatClient.send(JSON.stringify(jsonObj));
}