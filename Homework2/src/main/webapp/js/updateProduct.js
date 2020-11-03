
// $(document).ready(function () {
//     $("button#update").click(function (event) {
//         event.preventDefault()
//         var name = $("form.create-product-form input.name").val();
//         var price = $("form.create-product-form input.price").val();
//         var description = $("form.create-product-form input.description").val();
//         if (name == '' || price == '' || description == '') {
//             alert("Please fill all fields...!!!!!!");
//         } else if (price <= 0) {
//             alert("price should be greater than 0...")
//         } else {
//             var product = {
//                 name: name,
//                 price: price,
//                 description: description
//             };
//             $.post("update", product, function (data) {
//                 if (data.id != null) {
//                     window.location.href = "cabinet.jsp";
//                 }else {
//                     alert(data.message)
//                 }
//                 $("form")[0].reset();
//             }, "json");
//         }
//     });
// });