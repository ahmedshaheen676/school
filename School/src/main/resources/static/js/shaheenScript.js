$(document).ready(function () {
    $('.myfun ').on('click', function (event) {

        $('.myform #id').val('');
        $('.myform #name').val('');
//      
        $('.myform #exampleModal').modal();
    })

    $('.myfun2 ').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (classes, status) {
            $('.myform #id').val(classes.id);
            $('.myform #name').val(classes.name);

        })
        $('.myform #exampleModal').modal();
    })



});



       