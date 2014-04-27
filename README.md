tiny-parser
===========

A tiny JSON path parser.
This project fetches only given JSON object and JSON value for given key. There are two types of delimiter, first delimiter is object delimiter which is ":" character. The other delimiter is "," character. Left side of ":" indicates JSON objects, right side of ":" indicates keys. Comma separated objects represent nested objects, for example "vehicle,car,sport car:price" means the "sport car" JSONObject is covered by "car" object and "car" object is covered by "vehicle" object.

Example: 
```
{
    "vehicle":{  
        "color":"white", 
        "bicycle":{ 
            "color": "red", 
            "price": 19.95 } ,
        "car": { 
            "color": "blue", 
            "price":49.95, 
            "sport car":{ 
                "color":"black", 
                "price": 119.95 
            }
        }
    }
}
```
"vehicle:"
```
{
  "color":"white",
  "bicycle":{
    "price":19.95,
    "color":"red"
  },
  "car":{
    "price":49.95,
    "color":"blue",
    "sport car":{
      "price":119.95,
      "color":"black"
    }
  }
}
```
"vehicle:color"
```
white
```
"vehicle,bicycle:"
```
{
  "price":19.95,
  "color":"red"
}
```
"vehicle,bicycle:price"
```
19.95
```
"vehicle,car:"
```
{
  "price":49.95,
  "color":"blue",
  "sport car":{
    "price":119.95,
    "color":"black"
  }
}
```
"vehicle,car,sport car:price"
```
119.95
```
