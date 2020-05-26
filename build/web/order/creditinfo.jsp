<section id="creditInfo">   
    <h1>Enter your credit card information</h1>
    <form action="#" method="post" name="creditinfoForm" onsubmit="placeOrder();return false" >
        <input type="hidden" name="todo" value="order">   
        <label class="pad_top">Credit card type</label>
        <select name="creditCardType">
            <option value="Visa">Visa</option>
            <option value="MasterCard">MasterCard</option>
            <option value="AmericanExpress">AmericanExpress</option>
        </select><br>
        <label class="pad_top">Card Holder</label>
        <input type="text" name="firstname" placeHolder="firstname">
        <input type="text" name="lastname" placeHolder="lastname"><br>
        <label class="pad_top">Credit card number</label>
        <input type="text" name="creditCardNumber" value="${contact.creditCardNumber}"><br>
        <label class="pad_top">Expiration Date</label>
        <select name="expirationMonth">
            <option value="01">01</option>
            <option value="02">02</option>
            <option value="03">03</option>
            <option value="04">04</option>
            <option value="05">05</option>
            <option value="06">06</option>
            <option value="07">07</option>
            <option value="08">08</option>
            <option value="09">09</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
        </select> /
        <select name="expirationYear">
            <option value="2020">2020</option>
            <option value="2021">2021</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
            <option value="2025">2025</option>
        </select><br>

        <input type="submit" value="Place Order" class="margin_left">
    </form>
</section>