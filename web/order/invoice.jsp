<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section id="section_invoice">
    <h1>Your Invoice</h1>
    <table >
        <tr >
            <td>Date</td>
            <td><%= java.time.LocalDate.now()%></td>
        </tr>
        <tr >
            <td>Ship to</td>
            <td>
                <p>${contact.firstName} ${contact.lastName}</p>
                <p>${contact.companyName} </p>
                <p>${contact.address1} </p>
                <p>${contact.address2} </p>
                <p>${contact.city} ${contact.state} ${contact.zip}</p>
                <p>${contact.country} </p>
            </td>
        </tr>
    </table>
    <hr>
    <table >
        <tr >
            <th>id</th>
            <th>price</th>
            <th>quantity</th>
            <th>total Price</th>
        </tr>

        <c:forEach var="item" items="${cart}">
            <tr>
                <td>${item.id}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>${item.totalprice}</td>
            </tr>
        </c:forEach>
    </table>

    <input type="button" value="Edit Address" style="display:inline-block" onclick="checkout('contactinfo')">
    <input type="button" value="Continue" style="display:inline-block" onclick="checkout('creditinfo')">

</section>