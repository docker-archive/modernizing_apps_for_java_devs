#!/bin/bash

curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Malcolm","lastName":"Reynolds","password":"browncoat","emailAddress":"mal@firefly.com","dateOfBirth":38937953,"id":17297,"userName":"captain"}' http://localhost:8090/user
curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Zoe","lastName":"Washburn","password":"serenity","emailAddress":"zoe@firefly.com","dateOfBirth":-21628285,"id":17298,"userName":"firstmate"}' http://localhost:8090/user
curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Jayne","lastName":"Cobb","password":"vera1","emailAddress":"jayne@firefly.com","dateOfBirth":-247477885,"id":17299,"userName":"heroofcanton"}' http://localhost:8090/user
curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Kaylee","lastName":"Frye","password":"shiny","emailAddress":"kaylee@firefly.com","dateOfBirth":391882115,"id":17300,"userName":"xiaomeimei"}' http://localhost:8090/user
curl -H "Content-Type: application/json" -X POST -d '{"firstName":"Hoban","lastName":"Washburne","password":"leaf1","emailAddress":"wash@firefly.com","dateOfBirth":37987715,"id":17301,"userName":"wash"}' http://localhost:8090/user

