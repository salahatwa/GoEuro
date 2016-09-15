# GoEuro
GoEuro test for online Webservice

Implement an API query and transform this data into a csv file Create a Java command line tool that takes as an input parameter a string

java -jar GoEuroTest.jar "CITY_NAME"

The program takes this string and queries with it our Location JSON API: The app should use this API endpoint:

http://api.goeuro.com/api/v2/position/suggest/en/CITY_NAME

Where CITY_NAME is the string that the user has entered as a parameter when calling the tool, e.g.

http://api.goeuro.com/api/v2/position/suggest/en/Berlin

The API endpoint returns JSON documents like these:
