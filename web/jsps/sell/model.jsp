<%-- 
    Document   : model
    Created on : Sep 4, 2020, 2:17:54 PM
    Author     : rktirtho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<tr>
    <td ><input class="item-entry" style="width: 380px" type="text" name="" /></td>
    <td></td>
    <td><input type="number" name="" /></td>
    <td></td>
    <td><a class="btn btn-danger">Remove</a></td>
</tr>
<script>
    $('.item-entry').keyup(function () {
        console.log($(this).val())
    });

</script>