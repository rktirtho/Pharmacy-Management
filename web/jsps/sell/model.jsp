<%-- 
    Document   : model
    Created on : Sep 4, 2020, 2:17:54 PM
    Author     : rktirtho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<tr>
    <td ><input list="name-m" class="item-entry" style="width: 380px" type="text" name="" /></td>
    <td></td>
    <td><input type="number" name="" /></td>
    <td></td>
    <td><a class="btn btn-danger">Remove</a></td>
</tr>

<datalist id="name-m">
            <option>
            <div>
                <p>Resul</p><br>
                <p>30</p>
            </div>
        </option>
        <option>Pu</option>
    </datalist>
<script>
//    $('.item-entry').keyup(function () {
//        console.log($(this).val())
//    });
    
     $('.item-entry').keyup(function () {
                var text = $(this).val();
                $.ajax({
                    url: domainName + "/webapi/products/search?key="+text,
//                    console.log(text);
                    type: 'GET',
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
//                            console.log(data[i]);
                            $('#name-m').empty();
                            $('#name-m').append('<option>' + data[i].name + '</option')

                        }

                    }
                });
            });

</script>