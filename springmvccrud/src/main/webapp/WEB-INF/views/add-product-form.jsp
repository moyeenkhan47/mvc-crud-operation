<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<h1 class="text-center mb-3">Fill the Product detail</h1>
				<form action="handle-product" method="post">

					<div class="form-group">
						<label for="name">Product Name</label> <input type="text"
							class="form-control" id="name" aria-describedby="emailHelp"
							name="name" placeholder="Enter the Product name here">
					</div>
					<div class="form-group">
						<label for="description">Product Description</label>
						<textarea row="5" class="form-control" id="description"
							name="description"
							placeholder="Enter the Product description here"></textarea>

					</div>
					<div class="form-group">
						<label for="price">Price</label> <input type="text"
							class="form-control" id="price" name="price"
							placeholder="Enter the price  here">
					</div>
					<div class="container text-center">
						<a href="${pageContext.request.contextPath }/"
							class="btn btn-outLine-danger">Back</a>
						<button class="btn btn-primary" type="submit">Save</button>
					</div>

				</form>

			</div>
		</div>
	</div>
</body>
</html>