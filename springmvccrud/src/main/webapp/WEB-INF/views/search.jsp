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
				<h1 class="text-center mb-3"> Product detail</h1>
				<form  action="${pageContext.request.contextPath }/"
					method="get">
					<div class="form-group">
						<label for="name">ID</label> <input type="text"  readonly="readonly"
							class="form-control" id="name" aria-describedby="emailHelp"
							name="id" placeholder="Enter the Product Id here"
							value=${product.id }>
					
					
					</div>
					<div class="form-group">
						<label for="name">Product Name</label> <input type="text" readonly="readonly"
							class="form-control" id="name" aria-describedby="emailHelp"
							name="name" placeholder="Enter the Product name here"
							value=${product.name }>
					</div>
					<div class="form-group">
						<label for="description">Product Description</label>
						<textarea row="5" class="form-control" id="description"
							name="description"  readonly="readonly"
							placeholder="Enter the Product description here">${product.description }</textarea>

					</div>
					<div class="form-group">
						<label for="price">Price</label> <input type="text"
							class="form-control" id="price" name="price"  readonly="readonly"
							placeholder="Enter the price  here" value=${product.price }>
					</div>
					<div class="container text-center">
						<a href="${pageContext.request.contextPath }/index"
							class="btn btn-outline-dark">Back</a>
				
					</div>

				</form>

			</div>
		</div>
	</div>
</body>
</html>