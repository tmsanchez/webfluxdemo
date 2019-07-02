Webfluxdemo
===================


Simple demo app to use reactive functionality to consume some endpoints and store them in mongodb

# The idea

Idea behind this is to execute some steps in following order:

1. Receives a *transaction* request that contains a Post Id to search
2. Stores an initial record in mongodb
3. Call an Endpoint to get information for Post
4. Update post information in database
5. Call an Endpoint to get Information for first Photo for a UserId (from retrieved Post)
6. Update photo information in database
7. Return Stored information in database

Any feedback is welcome

# Running it

http://localhost:8080/api/v1/transactions

post body:

```json
{
	"postId":24
}
```

Response should be similar to

```json
{
    "id": "5d1b1f5e54031f17dc1d482f",
    "status": "Created",
    "postId": 24,
    "creationTime": "2019-07-02T04:09:50.274",
    "postAtTime": "2019-07-02T04:09:54.954",
    "photoAtTime": "2019-07-02T04:09:55.03",
    "post": {
        "id": 24,
        "userId": 3,
        "title": "autem hic labore sunt dolores incidunt",
        "body": "enim et ex nulla\nomnis voluptas quia qui\nvoluptatem consequatur numquam aliquam sunt\ntotam recusandae id dignissimos aut sed asperiores deserunt"
    },
    "photo": {
        "id": 21,
        "userId": 3,
        "title": "repudiandae voluptatem optio est consequatur rem in temporibus et"
    }
}
```
