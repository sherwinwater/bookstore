<section id="contactInfo">   
    <h1>Enter your delivery contact information</h1>
    <p>Required <span>*</span></p>
    <form action="#" method="post" name="contactinfoForm" onsubmit="getContactinfo();return false" >
        <input type="hidden" name="todo" value="contactinfo">        
        <label class="pad_top">First Name</label>
        <input type="text" name="FirstName" value="${contact.firstName}"><span>*</span><br>
        <label class="pad_top">Last Name</label>
        <input type="text" name="LastName" value="${contact.lastName}"><span>*</span><br>
        <label class="pad_top">Email Address</label>
        <input type="text" name="Email" value="${contact.email}"><span>*</span><br>
        <label class="pad_top">Company Name</label>
        <input type="text" name="CompanyName" value="${contact.companyName}"><br>
        <label class="pad_top">Address1</label>
        <input type="text" name="Address1" value="${contact.address1}"><span>*</span><br>
        <label class="pad_top">Address2</label>
        <input type="text" name="Address2" value="${contact.address2}"><br>
        <label class="pad_top">City</label>
        <input type="text" name="City" value="${contact.city}"><span>*</span><br>
        <label class="pad_top">State</label>
        <input type="text" name="State" value="${contact.state}"><span>*</span><br>
        <label class="pad_top">Zip Code</label>
        <input type="text" name="Zip" value="${contact.zip}"><span>*</span><br>
        <label class="pad_top">Country</label>
        <input type="text" name="Country" value="${contact.country}"><span>*</span><br>
        <label>&nbsp;</label>
        <input type="submit" value="Continue" class="margin_left">
    </form>
</section>