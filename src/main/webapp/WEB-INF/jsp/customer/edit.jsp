

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<a href="/customer/view/${customer.id}">Back to customer</a>

<h1>Editing customer #${customer.id}</h1>

<form action="/customer/editSubmit" method="POST">

    <input type="hidden" name="id" id="id" value="${customer.id}">
    First name: <input type="text" class="text" name="firstName" id="firstName" value="${customer.firstName}"><br>
    Last name: <input type="text" class="text" name="lastName" id="lastName" value="${customer.lastName}"><br>

    <button type="submit">Submit</button>

</form>