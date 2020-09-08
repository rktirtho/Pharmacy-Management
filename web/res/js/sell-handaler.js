/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {

    var dataList;
    var domainName = "/Pharmacy_Management"
    var itemCounter = 0;

    // Creating html elements by clicking add more button
    $('#add-more').click(function () {
        itemCounter++;
        var row = $("<tr></tr>").attr("id", "item-row"+itemCounter);
        var tdName = $("<td></td>");
        var inName = $('<input list="name-m" class="item-entry" \n\
id="item'+itemCounter+'" style="width: 380px" type="text" name="" /></td>').keyup(function () {
            $('#name-m').empty();
            var query = $(this).val();
            $.ajax({
                url: domainName + "/webapi/products/search?key=" + query,
                type: 'GET',
                success: function (data) {
                    $('#name-m').empty();
                    data.forEach(function (item) {
                        console.log(item);
                        $('#name-m').append('<option class="oid">' + item.name + '</option');
                        $(this).val = data[0].name
                    });

                }
            });

            $('#name-m').empty();
        });

        var tdUnitPrice = $("<td></td>");
        var tdQunatity = $("<td></td>");
        var inQuantity = $('<td><input type="number" name="" /></td>');
        var tdDiscount = $("<td></td>");
        var tdTotal = $("<td></td>");
        var tdDel = $("<td></td>");
        var btnDel = $('<a class="btn btn-danger">Remove</a>')
                .attr("id", itemCounter).click(function () {
                    var delId = $(this).attr("id");
            $('#item-row'+delId).remove();
            
            
        });

        tdName.append(inName);
        tdQunatity.append(inQuantity);
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

    '<tr>'
            + '<td ><input list="name-m" class="item-entry" id="" style="width: 380px" type="text" name="" /></td>'
            + '<td></td>'
            + '<td><input type="number" name="" /></td>'
            + '<td></td>'
            + '<td></td>'
            + '<td><a class="btn btn-danger" c>Remove</a></td>'
            + '</tr>)'


});


