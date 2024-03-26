<%@page isELIgnored="false"  %>
<html>
<head>
<%@include file="./base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body style="background-color: #f0f0f0;">

	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3"><i>Welcome to Product App</i></h1>
				<div class="container-fluid d-flex justify-content-end mt-2">
			
        <form class="form-inline float-right mb-3" action="logoutForm"
					method="post">
					<h2 class="text-primary mr-3" >Welcome <i>${username} !</i> </h2>
            <button type="submit" class="btn btn-danger">Logout</button>
            
        </form>
    </div>
				<form class="form-inline float-left mb-3" action="searchProduct"
					method="post">
					<input class="form-control mr-sm-2" type="text"
						placeholder="Enter Product ID/Name" aria-label="Search"
						name="searchKeyword">
					<button type="submit" class="btn btn-outline-primary">search</button>
				</form>

				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Pruduct Name</th>
							<th scope="col">Description</th>
							<th scope="col">Price</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${products}" var="p">
							<tr>
								<th scope="row">${p.id}</th>
								<td>${p.name }</td>
								<td>${p.description }</td>
								<td class="font-weight-bold">&#8377;${p.price }</td>
								<td><a href="delete/${p.id }"><i
										class="fa-solid fa-trash text-danger"></i></a>Delete<a
									href="updateSave/${p.id }"><i
										class="fas fa-edit text-primary"></i>Edit</a> <a
									href="viewProduct/${p.id }"><i
										class="fas fa-edit text-warning"></i>View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="container text-center">
					<a href="add-product" class="btn btn-outline-success">Add
						product</a>
				</div>
			</div>
		</div>
	</div>
	<!-- <script>
        function searchProduct() {
            var productId = document.getElementsByName("productId")[0].value;
            window.location.href = "searchProduct/" + productId;
            return false; // Prevents the form from submitting in the traditional way
        }
    </script> -->

</body>
</html>