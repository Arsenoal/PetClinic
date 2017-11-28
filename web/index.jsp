<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="PetClinic">
  <head>
    <title>Pet Clinic</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>

    <script src="resources/js/app.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body>

  <div class="container" ng-controller="manager">
    <div class="row">
      <div class="col-sm-4">
        <h3>Pets administration</h3>
        <div ng-controller="petController">
        <form class="form-horizontal">
          <div class="form-group">
            <div class="col-sm-10">
              <input type="text" ng-model="pet.name" class="form-control" placeholder="name" >
            </div>
            <div class="col-sm-10">
              <input type="text"  ng-model="pet.age" class="form-control" placeholder="age">
            </div>
            <div class="col-sm-10">
              <input type="text" ng-model="pet.type" class="form-control" placeholder="type of pet" required>
            </div>
          </div>
        </form>
        </div>
      </div>
      <div class="col-sm-4">
        <h3>Owners administration</h3>
        <div ng-controller="ownerController">
        <form class="form-horizontal">
          <div class="form-group">
            <div class="col-sm-10">
              <input type="text" class="form-control" ng-model="owner.name" placeholder="name">
            </div>
            <div class="col-sm-10">
              <input type="text" class="form-control" ng-model="owner.age" placeholder="age">
            </div>
            <div class="col-sm-10">
              <input type="text" class="form-control" ng-model="owner.sex" placeholder="sex">
            </div>
          </div>
        </form>
        </div>
      </div>
      <div class="col-sm-4">
        <h3>vets administration</h3>
        <div ng-controller="vetController">
        <form class="form-horizontal">
          <div class="form-group">
            <div class="col-sm-10">
              <input type="text" class="form-control" ng-model="vet.name" placeholder="name">
            </div>
            <div class="col-sm-10">
              <input type="text" class="form-control" ng-model="vet.age" placeholder="age">
            </div>
            <div class="col-sm-10">
              <input type="text" class="form-control" ng-model="vet.sex" placeholder="sex">
            </div>
          </div>
        </form>
        </div>
      </div>
      <ul id="data">
      </ul>
    </div>
    <button class="btn btn-success" ng-click="addStory()">add story</button>
    <p ng-class="{'noProblem': petIsValid}">some of the fields is not correct</p>
  </div>

  </body>
</html>
