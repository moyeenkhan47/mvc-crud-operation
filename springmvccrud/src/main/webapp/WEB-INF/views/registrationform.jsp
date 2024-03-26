<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>User Registration</title>
</head>
<body style="background-color: #80ced6;">

<div class="container mt-5">
    <h3 class="text-center">Registration Form</h3>
    <form action="processform" method="post">
        <div class="form-group">
            <label for="userName">User Name</label>
            <input type="text" class="form-control" id="userName" placeholder="Enter Name" name="username" required>
        </div>

        <div class="form-group">
            <label for="email">Email Address</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
        </div>

        <div class="form-group">
            <label for="userPassword">User Password</label>
            <div class="input-group">
                <input type="password" class="form-control" id="userPassword" placeholder="Enter password" name="password" required>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="togglePassword">View</button>
                </div>
            </div>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-success">Sign Up</button>
            <a href="loginForm" class="btn btn-warning ml-2">Login</a>
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script>
    $(document).ready(function(){
        $("#togglePassword").click(function(){
            var passwordField = $("#userPassword");
            var passwordFieldType = passwordField.attr('type');
            if(passwordFieldType == 'password'){
                passwordField.attr('type', 'text');
                $(this).text('Hide');
            }else{
                passwordField.attr('type', 'password');
                $(this).text('View');
            }
        });
    });
</script>

</body>
</html>
