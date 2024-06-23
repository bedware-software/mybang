# MyBang!
## Overview
MyBang! is a handy service for abbreviating website addresses and searches, inspired by the bangs ideas in DuckDuckGo. It makes it easy to create your own abbreviations and jump straight to search results.

## Idea and inspiration
The original DuckDuckGo search engine allows you to refer to different websites using short cuts called bangs. A bang is, in its original form, an exclamation point and a shortcut. Bangs allow you to use a shortcut starting with the exclamation point instead of typing the full address of a site. For example, instead of writing the full address "google.com", you can just write "!g" and it will redirect you to Google. If you add a query string to that as well, you can go straight to the search results page.

## Problems with the existing solution
The main problem with the original service is that it doesn't allow users to add or edit bangs. They have a certain set of preset abbreviations that are very difficult to change or add to (it's either impossible or can take up to several months). This limits the possibilities and is not always convenient.

## How MyBang! works
MyBang! service works on a similar principle. In the browser's query string, instead of the full website address, you enter bang - an exclamation point and a shortcut. A search string can be added after the shortcut.

Try it yourself! Copy this string into your browser address bar: `bang.bedware.software/!yama notebook` or `bang.bedware.software/!learn python`.

The `bang.bedware.software/` prefix can be removed by making MyBang! the default search engine in the browser.

However, unlike the original service, in MyBang! you will have the ability to create your own bangs and customize them to your needs. There are no restrictions.

# Installation
## Configure bangs
Beforehand configure your `config.yaml` to suit your needs. You can use `config.yaml` from the project as a template.

## Run
Two main options available: using Docker or using Maven

### Using Docker
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
