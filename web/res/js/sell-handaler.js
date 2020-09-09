/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var total = 00;

$(function () {

    $('#total-bill').html(total);

    var dataList;
    var domainName = "/Pharmacy_Management"
    var itemCounter = 0;

    // Creating html elements by clicking add more button
    $('#add-more').click(function () {
        itemCounter++;
        var row = $("<tr></tr>").attr("id", "item-row" + itemCounter);
        var tdName = $("<td></td>");
        var inName = $('<input list="name-m" class="item-entry" \n\
id="item' + itemCounter + '" style="width: 380px" type="text" name="" /></td>')
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
                                $('#name-m').append('<option class="' + item.id + '" value="' + item.name + '">' + item.group + '</option')
                                        ;

                            });

                        }
                    });

                    $('#name-m').empty();
                })
                .change(function (event) {
                    var fldNumber = parseInt($(this).attr("id").replace("item", ""));

                    $('#price' + fldNumber).html(dataList[0].unitSellingPrize)
                    $('#discount' + fldNumber).html(dataList[0].discount)
                    $('#total' + fldNumber).html("0.00");
                    console.log(dataList[0])

                });

        var tdUnitPrice = $("<td></td>");
        var priceSpan = $("<span  id='price" + itemCounter + "'></span>");
        var tdQunatity = $("<td></td>");
        var inQuantity = $('<input id="price' + itemCounter + '" type="number" name="" />')
                .keyup(function () {
                    var fldNumber = parseInt($(this).attr("id").replace("price", ""));
                    var q = $(this).val();

                    $('#total' + fldNumber).html(dataList[0].unitSellingPrize * q)


                })
                .on('focusout', function () {
                    var q = $(this).val();
                    total = total+ (dataList[0].unitSellingPrize * q)
                      $('#total-bill').html(total)

                });
        var tdDiscount = $("<td id='discount" + itemCounter + "'></td>");
        var discountSpan = $("<span  id='discount" + itemCounter + "'></span>");
        var tdTotal = $("<td></td>");
        var totalSpan = $("<span  id='total" + itemCounter + "'></span>");
        var tdDel = $("<td></td>");
        var btnDel = $('<a class="btn btn-danger">Remove</a>')
                .attr("id", itemCounter).click(function () {
            var delId = $(this).attr("id");
            $('#item-row' + delId).remove();


        });

        tdName.append(inName);
        tdUnitPrice.append(priceSpan)
        tdQunatity.append(inQuantity);
        tdDiscount.append(discountSpan);
        tdTotal.append(totalSpan);
        tdDel.append(btnDel);

        row.append(tdName, tdUnitPrice, tdQunatity, tdDiscount, tdTotal, tdDel);
        $('#items').append(row);

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

$('#cancle-bill').click(function () {
        location.reload();
    });


});




