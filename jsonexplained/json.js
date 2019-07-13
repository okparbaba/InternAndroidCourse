console.log("How to read json");
//javascript object to json
var ojb = {
  name:"Yan",
  age:"Yangon"
};

var a = 1;
var b = "sdkfj";

console.log(a);

var ojbJson = JSON.stringify(ojb);
console.log(ojbJson);

//Json to javascript object
var json = '{"name":"John","age":31}';
var jsonObj = JSON.parse(json);
document.getElementById('getjson').innerHTML = jsonObj.name;

//Json Array
var jsonArray = {
  employeeone:[
    {firstName:"John", lastName:"Doe"},
    {firstName:"Anna", lastName:"Smith"},
    {firstName:"Peter", lastName:"Jones"}
  ],
  employeetwo:[
    {firstName:"John", lastName:"Doe"},
    {firstName:"Anna", lastName:"Smith"},
    {firstName:"Peter", lastName:"Jones"}
  ]
};
for (var i = 0; i < jsonArray.employeeone.length; i++) {
  console.log(jsonArray.employeeone[1].firstName);
}
