#Blitz-Request
The best commandline REST API and stress tester.
![blitz-request demo](https://raw.githubusercontent.com/chenshuiluke/blitz-request/master/demo.gif)
##Usage:
    java -jar blitz-request.jar  -n <number_of_requests> -m <GET/POST/PUT/DELETE> -u <url>
##Example:
    java -jar blitz-request.jar  -n 5000 -m GET -u http://mappa-server.herokuapp.com -t 10

##Additional Arguments:
    -uq  --url-queries <json_url_queries> E.g. -uq \{\"email\":\"chenshuiluke@gmail.com\",\"password\":\"kb0359\"\}
    -t   --threads <max_number_of_threads> E.g. -t 10
    -se --show-errors
    -ss --show-successes
    -nc --num-connections <number_of_concurrent_connections>
##Example with all commandline arguments:
    java -jar blitz-request.jar  -n 5 -m POST -u http://mappa-server.herokuapp.com/users/login -uq \{\"email\":\"hehe@gmail.com\",\"password\":\"kb0359\"\} -t 2