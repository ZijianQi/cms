# DOCUMENTATION

Initialazation:
	Set the DB name and mongoClient port at the file java/resources/appliction.properties.
	Put the first news_entry1 into the a collection called News.

Functions:
	List all news columns:
		Request link: http://{host}/news
		Request tye:  GET
		
		
	Add a news column:
		Request link: http://{host}/news
		Request type: POST
		Raw headers: 
				Content-Type: application/json
				
		Assumption:
			pic,title,description and href are mandatory input, rendering_template is optional.
		
		Sample:
			Raw payload:
			   {
				"columns":[
					{
						"pic" : "http://www.dreamhack-leipzig.de/media/mandant/globale-verfuegbare-Medien/News-Upload/Dreamhack/DRM17_CSGOT_TEAMS_Open2groups.jpg",
						"title" : "Teams, groups and schedule for Leipzig",
						"description" : "A new year for DreamHack Open is upon us, and this year will deliver 9 CS:GO stops for you to enjoy",
						"href" : "http://www.dreamhack-leipzig.de/news/teams-groups-and-schedule-for-leipzig/551005"
					}, 
					{
						"pic" : "http://static.hltv.org/images/galleries/9313-medium/1477836791.8748.jpeg",
						"title" : "DH Leipzig talent lineup announced ",
						"description" : "DreamHack have revealed the talen lineup for DreamHack Open Leipzig",
						"href" : "http://www.hltv.org/news/19604-dreamhack-leipzig-viewers-guide"
					}]
				}

				
	Delete a news column:
		Request link: http://{host}/news/{title}
		Request type: DELETE
		Assumption:
			The title is unique in the DB collection.
		
		Description:
			The {title} is consisted of strings ad spaces, without any other characters such as "'", """ or "-". 
		
		Sample:
			Request link: http://{host}/news/Day 1 Summary ofuuu ESL One Genting 2017
			
			
	Modify an existing news column:
		Request link: http://{host}/news/{title}
		Request type: POST
		Raw headers: 
				Content-Type: application/json

		Assumption:
			The title is unique in the DB collection.
			
		Description:
			The {title} is consisted of strings ad spaces, without any other characters such as "'", """ or "-". 
				
		Sample:
			Request link: http://{host}/news/Day 1 Summary ofuuu ESL One Genting 2017
			Raw payload:
			    {
                    "pic" : "https://www.test.se/1.png",
                    "title" : "Day 1 Summary ofuuu ESL One Genting 2017",
                    "description" : "Digital Chaos and Newbee advancing to the semifinals",
                    "href" : "https://www.test.comfinals-3376"
				}
				
Exceptions:
	FileNotFoundException:
		Case:
			If the fetched data from MongoDB is null or dose not exists, the exception will be thrown.
		Response Http status: NOT FOUND
		Response Http status code: 404
		Response message: "Data is not found"

	IllegalArgumentException:
		Case: 
			When argument is not fulfill the specified requirements, the exception will be thrown.
		Response Http status: NOT_ACCEPTABLE
		Response Http status code: 406
		Response message: "Argument is invalid"
		
	FileAlreadyExistsExceptionHandler:
		Case:
			When the data ,which is tried to be inserted, exists already.
		Response Http status: CONFLICT
		Response Http status code: 409
		Response message: "the data exists already"
		
