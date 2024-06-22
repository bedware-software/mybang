# Description
MyBang! is a handy service for abbreviating website addresses and searches, inspired by the bangs ideas in DuckDuckGo. It makes it easy to create your own abbreviations and jump straight to search results.

# Installation
From the directory with `config.yaml` run the command:
```
# pwsh
docker run -d --name mybang -p 8080:8080 -v $pwd/config.yaml:/config.yaml bedware/mybang:1.0.4
# bash
docker run -d --name mybang -p 8080:8080 -v $(pwd)/config.yaml:/config.yaml bedware/mybang:1.0.4
```
Test by curl, you should see something like this:
```
> curl -i localhost:8080/!g
HTTP/1.1 307
Server: nginx/1.21.6
Date: Sat, 22 Jun 2024 20:11:49 GMT
Transfer-Encoding: chunked
Connection: keep-alive
Location: https://duckduckgo.com/?q=%21g

> curl -i localhost:8080/!ghs
HTTP/1.1 307
Server: nginx/1.21.6
Date: Sat, 22 Jun 2024 20:12:06 GMT
Transfer-Encoding: chunked
Connection: keep-alive
Location: https://github.com/bedware?q=&tab=stars
```
Congratulations! You are ready to use it :)
