<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Workflow Manager Task </title>

</head>
<body>
<h1> Process Started By ${pv.initiator}</h1>

<div>
    <form:form action="/handleForm">
        <label>Name : $(pv.applicant.name}</label>
        <label>Phone : $(pv.applicant.phone}</label>
        <label>Email : $(pv.applicant.email}</label>
        <label> Comments : </label>
        <form:textarea path=""></form:textarea>
        <form:button name="action" value="approve">Approve </form:button>
        <form:button name="action" value="cancel">Cancel</form:button>
    </form:form>
</div>


</body>
</html>