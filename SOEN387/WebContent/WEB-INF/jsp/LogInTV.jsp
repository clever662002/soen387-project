<%@include file="../include/header.jsp" %>

	<h2> LOGIN </h2>
	<div id="container">
	
		<c:if test="${!empty error}">
			<p id="error"> ${error} </p>
		</c:if>
		<c:if test="${!empty info}">
			<p id="info"> ${info} </p>
		</c:if> 
		<c:if test="${!empty notifications}">
			<p id="info"> ${notifications} </p>
		</c:if> 
		
		<div id="loginContainer">
			<div id="login">
				<form action="" method="POST" target="_self">
							
					<table>
						<tr>
							<td><span>USERNAME </span></td>
							<td><input type="text" name="username" /></td>
						</tr>
						<tr>
							<td><span>PASSWORD </span></td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="DO IT" /> </td>
							<td><input type="reset" value="RESET" /></td>
						</tr>
					</table>
							
					<!-- span>USERNAME </span> <input type="text" name="username" /> </br>
					<span>PASSWORD </span> <input type="password" name="password" /> </br>
					<input type="submit" value="DO IT" /> <input type="reset" value="RESET" /></br-->
					
				</form>
			</div>
		</div>
	</div>
	
<%@include file="../include/footer.jsp" %>