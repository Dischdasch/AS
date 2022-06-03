<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<!-- header -->
<jsp:include page="header.jsp" />
<div>
    <div class="content">
        <h1>Login Page</h1>
        <form method="GET" action="FrontControllerServlet"
              onsubmit="return checkForm(this);">
            <p>
                Username: <input type="text" name="username">
            </p>
            <p>
                Password: <input type="password" name="pwd1">
            </p>
            <p>
                Confirm Password: <input type="password" name="pwd2">
                <input type="hidden" name="command" value="login">
            </p>
            <p>
                <input type="submit" value="login">
            </p>
        </form>
        <script type="text/javascript">
            function checkForm(form) {
                if (form.username.value == "") {
                    alert("Error: Username cannot be blank!");
                    form.username.focus();
                    return false;
                }
                re = /^\w+$/;
                if (!re.test(form.username.value)) {
                    alert("Error: Username must contain only letters, numbers and underscores!");
                    form.username.focus();
                    return false;
                }

                if (form.pwd1.value != ""
                    && form.pwd1.value == form.pwd2.value) {
                    if (form.pwd1.value.length < 6) {
                        alert("Error: Password must contain at least six characters!");
                        form.pwd1.focus();
                        return false;
                    }
                    if (form.pwd1.value == form.username.value) {
                        alert("Error: Password must be different from Username!");
                        form.pwd1.focus();
                        return false;
                    }
                    re = /[0-9]/;
                    if (!re.test(form.pwd1.value)) {
                        alert("Error: password must contain at least one number (0-9)!");
                        form.pwd1.focus();
                        return false;
                    }
                    re = /[a-z]/;
                    if (!re.test(form.pwd1.value)) {
                        alert("Error: password must contain at least one lowercase letter (a-z)!");
                        form.pwd1.focus();
                        return false;
                    }
                    re = /[A-Z]/;
                    if (!re.test(form.pwd1.value)) {
                        alert("Error: password must contain at least one uppercase letter (A-Z)!");
                        form.pwd1.focus();
                        return false;
                    }
                } else {
                    alert("Error: Please check that you've entered and confirmed your password!");
                    form.pwd1.focus();
                    return false;
                }
                return true;
            }
        </script>
    </div>
</div>
</body>
</html>