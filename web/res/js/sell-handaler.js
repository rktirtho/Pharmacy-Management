/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var total = 00;
var dataList;
var domainName = "/Pharmacy_Management"
var itemCounter = 0;
var productsId = new Array();
var quanteties = [];
$(function () {

    $('#total-bill').html(total);



    // Creating html elements by clicking add more button
    $('#add-more').click(function () {
        moreFeild();
        $(this).hide();

    });


    // Loading Data to data list by AJAX request

    function getHint() {

        var text = $(this).val();
        $.ajax({
            url: domainName + "/webapi/products/search?key=" + text,
            type: 'GET',
            success: function (data) {
                dataList = data;
                for (var i = 0; i < data.length; i++) {
                    $('#name-m').empty();
                    if (text !== "") {

                    }
                }

            }
        });
    }
    ;

//    $(document).keydown(function (e) {
//        
//        if (e.altKey && e.which === 78) {
//            moreFeild();
//        }
//    });

    $('#cancle-bill').click(function () {
        location.reload();
    });
    $('#go-to-order').click(function () {

        $.ajax({
            type: 'POST',
            url: domainName + "/webapi/invoice/create-bill?data=" + productsId,
            success: function (data) {
                window.location = domainName+"/bill?invocation="+data;

            }

        });

    });


});



function moreFeild() {
    itemCounter++;
    var quantity = 0;
    var row = $("<tr></tr>").attr("id", "item-row" + itemCounter);
    var tdName = $("<td></td>");
    var nameSpan = $("<span  id='name" + itemCounter + "'></span>");
    var tdbarcode = $("<td></td>");
    var inbarcode = $('<input list="name-m" autofocus class="item-entry" \n\
id="item' + itemCounter + '" style="width: 180px" type="text" name="" /></td>')
            .keyup(function () {
                $('#name-m').empty();
                var query = $(this).val();
                $.ajax({
                    url: domainName + "/webapi/products/search?key=" + query,
                    type: 'GET',
                    success: function (data) {
                        dataList = data;
                        $('#name-m').empty();
                        data.forEach(function (item) {
//                                console.log(item);
//                            $('#name-m').append('<option class="' + item.id + '" value="' + item.name + '">' + item.group + '</option')
                            $('#name' + itemCounter).html(data[0].name);

                        });

                    }
                });

                $('#name-m').empty();
            })
            .change(function (event) {
                var fldNumber = parseInt($(this).attr("id").replace("item", ""));

                $('#price' + fldNumber).html(dataList[0].unitSellingPrize)
                $('#discount' + fldNumber).html(dataList[0].quantity)
                $('#total' + fldNumber).html("0.00");
//                console.log(dataList[0])

            });

    var tdUnitPrice = $("<td></td>");
    var priceSpan = $("<span  id='price" + itemCounter + "'></span>");
    var tdQunatity = $("<td></td>");
    var inQuantity = $('<input type="number"/>')
            .attr("id", "price" + itemCounter)
            .keyup(function () {
                var fldNumber = parseInt($(this).attr("id").replace("price", ""));
                var q = $(this).val();

                $('#total' + fldNumber).html((dataList[0].unitSellingPrize * q).toFixed(2))


            })
            .on('focusout', function () {
                var q = $(this).val();
                total = total + (dataList[0].unitSellingPrize * q)
                $('#total-bill').html(total);
                quantity = q;

            });
    var tdDiscount = $("<td id='discount" + itemCounter + "'></td>");
    var discountSpan = $("<span  id='discount" + itemCounter + "'></span>");
    var tdTotal = $("<td></td>");
    var totalSpan = $("<span  id='total" + itemCounter + "'></span>");
    var tdDel = $("<td></td>");
    var btnDel = $('<a class="btn btn-danger">&times;</a>')
            .attr("id", itemCounter).click(function () {
        var delId = $(this).attr("id");
        var currentPosition = parseInt(delId);
        console.log(currentPosition);
        console.log("Deleted");
        productsId.pop(currentPosition - 1);
        $('#item-row' + delId).remove();
    });
    var btnAccept = $('<a id="acc' + itemCounter + '" class="btn btn-success mx-3">Add</i></a>')
            .click(function () {
                var fldNumber = $(this).attr("id").replace("acc", "");
                if (quantity <= 0) {
                    alert("Enter Quantity");
                } else {
                    var prod = [dataList[0].codeNumber
                        , parseInt(quantity),
                        (dataList[0].unitSellingPrize * quantity)];
                    productsId.push(prod);
                     moreFeild();
                }
            });
    tdbarcode.append(inbarcode);
    tdName.append(nameSpan);
    tdUnitPrice.append(priceSpan)
    tdQunatity.append(inQuantity);
    tdDiscount.append(discountSpan);
    tdTotal.append(totalSpan);
    tdDel.append(btnAccept, btnDel);

    row.append(tdbarcode, tdName, tdUnitPrice, tdQunatity, tdDiscount, tdTotal, tdDel);
    $('#items').append(row);
}


