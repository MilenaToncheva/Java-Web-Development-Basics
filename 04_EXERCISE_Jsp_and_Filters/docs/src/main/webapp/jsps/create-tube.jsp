<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <c:import url="templates/head.jsp"/>

</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <form action="/tubes/create" method="post">
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1>Create Tube!</h1>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="inputTitle">Title</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input id="inputTitle" type="text" name="name">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="descriptionTextArea">Description:</label>
                        </div>
                        <div class="row d-flex justify-content-center ">
                            <textarea name="description" id="descriptionTextArea" cols="22" rows="4"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="youTube-link">YouTube Link</label>
                        </div>
                        <div class="col col-md-12 d-flex justify-content-center">
                            <input name="youTube-link" id="youTube-link" type="text">

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="uploader">Uploader</label>
                        </div>
                        <div class="col col-md-12 d-flex justify-content-center">
                            <input name="uploader" id="uploader" type="text">

                        </div>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <button class="btn btn-primary" type="submit">Create Tube</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to Home</a>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>

</div>
</body>
</html>
