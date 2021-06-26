

$('#listInTable').on('click', function() {
    $("#home").hide();
    $("#tableHome").show();
});

$('#buttonCleanFilter').on('click', function(){
    $('.nameInput').val("");
    $('.selectCountry').val("---");
    $('.selectStatus').val("---");
});