/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("#btnNuevo").click(function(){
    var user =$(".user").text();
    window.location.href = "http://localhost:8080/WebService/usuario?usuario=" + user;
});