let products = null;

$.get("product", function (data){
    if (data != ""){
        products = data;
    }
}, "Json").done(function (){
    let cardsContent = "";
    console.log(products);

    jQuery.each(products,function (i, product){
        cardsContent += "<div class='card'>" +
            "<div class = 'card-body'>"+
            "<h5 class='card-title'>Product Name: " + product.name + "</h5>" +
            "<h6 class='card-subtitle mb-2 text-muted'>Price:" + product.price +"</h6>"+
            "<p class='card-text'> Description : " + product.description + "</p>" +
            "<a href='info?id=" + product.id + "'>About product</a>" +
            "<a href='update?id=" + product.id + "'>Update product</a>" +
            "</div>" +
            "</div>"
    });
    $("div#all-products").html(cardsContent);
});