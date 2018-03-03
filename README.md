# SocialNetworkingChallenge

## Getting Started
### Prerequisites
- Git
- Java
- [Maven 3](https://maven.apache.org)
- [Wildfly](http://wildfly.org) (tested on Wildfly 11)
- [Postman](https://www.getpostman.com/) or [newman](https://www.npmjs.com/package/newman) (for tests)

### Building
1. Checkout the repository
```
git clone https://github.com/AdrianKMichalski/SocialNetworkingChallenge.git
```

2. Change the working directory
```
cd SocialNetworkingChallenge
```

3. Build everything via Maven
```
mvn clean install
```

### Deployment
1. Start Wildfly 11
2. Deploy `%SOCIAL_NETWORKING_HOME%/social-networking-core/target/social-networking-core.war` file to Wildfly using *jboss-cli* or copy it to the `%WILDFLY_HOME%/standalone/deployments` directory.
3. After succesful deployment (with default Wildfly's settings) API should be exposed under `http://localhost:8080/api/`

### Running tests
#### With newman
1. Go to the *tests* directory
2. Run tests using
```
newman run SocialNetworking.postman_collection.json
```

#### With Postman
1. Import collection from *tests* directory
2. Run tests with Collection Runner  
![](images/postman_run.png)  
![](images/postman_results.png)

## API Documentation
### Users
#### Add new post
```
POST /users/{userName}/post
```
Adds new posts for user *userName*. Creates new user if it does not exists. 
##### Example
```json
{
	"body": "Hello, I am Alice. This is my first post."
}
```
*Body* should be at most 140 characters long.
##### Response
```
Status: 201 Created
Location: http://localhost:8080/api/posts/d4dc2a72-e9e1-4eb1-91a3-4cfc219adc5e
Content-Length: 0
```

#### Show user's wall
```
GET /users/{userName}/wall
```
Shows all user's posts in reverse chronological order.
##### Response
```
Status: 200 OK
Content-Type: application/json
Content-Length: 151
```
```json
[
    {
        "id": "ec2e6673-f659-484c-8d71-b0ae5b435189",
        "user": {
            "name": "Alice"
        },
        "body": "Hello, I am Alice. This is my first post.",
        "creationDate": 1520107795745
    }
]
```

#### Follow an user
```
PUT /users/{userName}/follow/{followedUserName}
```
Makes *userName* following the *followedUserName*.
##### Response
```
Status: 200 OK
Content-Length: 0
```

#### Get followed user names
```
GET /users/{userName}/followed
```
##### Response
```
Status: 200 OK
Content-Type: application/json
Content-Length: 7
```
```json
[
    "Bob"
]
```

#### Get user's timeline
```
GET /users/{userName}/timeline
```
Shows all posts of users followed by *userName* in reverse chronological order.


### Posts
#### Get post by id
```
GET /posts/{postId}
```
##### Response
```
Status: 200 OK
Content-Type: application/json
Content-Length: 149
```
```json
{
    "id": "2da26a63-49b4-4d50-9247-d33369205ff7",
    "user": {
        "name": "Alice"
    },
    "body": "Hello, I am Alice. This is my first post.",
    "creationDate": 1520108916753
}
```

## Copyright
Copyright &copy; 2018 Adrian Michalski