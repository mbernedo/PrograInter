$(document).ready(function(){
    $('img').click(function(){
        for(var i=0; i<10; i++){
            $(this).animate({left: '+=5px'},50);
            $(this).animate({left: '-=5px'},50);
        }
        $(this).effect('explode');
    });
    $('img').keys("O", function(){
        
    });
});