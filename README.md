#Blitz-Request
The best commandline REST API and stress tester.
![blitz-request demo](https://github.com/chenshuiluke/blitz-request/blob/master/demo.gif?raw=true)

Prebuilt jar files can be found in `out/artifacts/blitz_request_jar`

*JSON commandline parameters for url queries and fields serve as basic key-value pairs. So `-uq '{"name":"hehe","password":"kb0359"}'` would translate to ?name=hehe&password=kb0359.*

##Usage:
    java -jar blitz-request.jar  -n <number_of_requests> -m <GET/POST/PUT/DELETE> -u <url>
##Example:
    java -jar blitz-request.jar  -n 5000 -m GET -u http://mappa-server.herokuapp.com -t 10

##Using files to pass arguments
Say you have a file named `server_test_params`:

    -n 
    10000
    -m 
    POST 
    -u 
    http://mappa-server.herokuapp.com/users/login 
    -uq 
    '{"email":"hehe@gmail.com","password":"kb0359"}'
    -f
    '{"email":"bleh@gmail.com","password":"kb0359"}'
    -se 
    -ss 
    -nc 
    1024
    -t
    10000

You can pass it to the program via: `java -jar blitz-request.jar @server_test_params`
##Additional Arguments:
    -uq  --url-queries '<json_url_queries>' E.g. -uq '{"email":"hehe@gmail.com","password":"kb0359"}'
    -f  --form-data '<json_form_data>' E.g. -uq '{"email":"bleh@gmail.com","password":"kb0359"}'
    -t   --threads <max_number_of_threads> E.g. -t 10
    -se --show-errors
    -ss --show-successes
    -nc --num-connections <number_of_concurrent_connections>
##Example with all commandline arguments:
    java -jar blitz-request.jar  -n 5 -m POST -u http://mappa-server.herokuapp.com/users/login -uq '{"email":"hehe@gmail.com","password":"kb0359"}' -f '{"email":"bleh@gmail.com","password":"kb0359"}' -se -ss -nc 2 -t 2
    
