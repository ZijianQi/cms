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
                    "pic" : "https://www.test.se/1.png",
                    "title" : "Day 1 Summary ofuuu ESL One Genting 2017",
                    "description" : "Digital Chaos and Newbee advancing to the semifinals",
                    "href" : "https://www.test.comfinals-3376"
				}

				
	Delete a news column:
		Request link: http://{host}/news/{title}
		Request type: DELETE
		Assumption:
			The title is unique in the DB collection.
		
		Sample:
			Request link: http://{host}/news/Day 1 Summary ofuuu ESL One Genting 2017
			
			
	Modify an existing news column:
		Request link: http://{host}/news/{title}
		Request type: POST
		Raw headers: 
				Content-Type: application/json

	Assumption:
			The title is unique in the DB collection.
				
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
		

In addition:
	The appliction.properties file is uploaded only in this case of demonstrating.
