/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {

    var itemCounter = 0;
    $('#add-more').click(function () {
        itemCounter++;
        var row = $("<tr></tr>");
        var tdName = $("<td></td>");
        var inName = $('<input list="name-m" class="item-entry" \n\
id="" style="width: 380px" type="text" name="" /></td>');
        
        var tdUnitPrice =$("<td></td>");
        var tdQunatity =$("<td></td>");
        var inQuantity= $('<td><input type="number" name="" /></td>');
        var tdDiscount =$("<td></td>");
        var tdTotal =$("<td></td>");
        var tdDel =$("<td></td>");
        var btnDel =  $('<a class="btn btn-danger" c>Remove</a>');
        
        tdName.append(inName);
        tdQunatity.append(inQuantity);
        tdDel.append(btnDel);
        
        row.append(tdName, tdUnitPrice,tdQunatity,tdDiscount,tdTotal,tdDel);
         $('#items').append(row);

    });
    '<tr>'
            + '<td ><input list="name-m" class="item-entry" id="" style="width: 380px" type="text" name="" /></td>'
            + '<td></td>'
            + '<td><input type="number" name="" /></td>'
            + '<td></td>'
            + '<td></td>'
            + '<td><a class="btn btn-danger" c>Remove</a></td>'
            + '</tr>)'


});


