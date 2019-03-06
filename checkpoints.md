# ZAAB-Project-Rate-Mentors

Models:
Mentor:
- long id (slack id)
- string name
- int points
- List<Review> reviews

Review
- long id (auto generated)
- string text
- bool isAnonym
- string reviewer (isAnonym true -> Anonymous; isAnonym false -> Student.name)
- enum +1, -1
- enum tag (secondar)
- User student

User
- long id (google auth)
- string name
- bool isAdmin
- List<Review> Review

Controllers:
MentorController:
GET
- getMentor (by default list all, can be filtered by query strings)
POST
- addNewMentor (admin joggal lehetséges - secondary prio) 
PUT
- editMentor (name)
DELETE
- deleteMentor

ReviewController:
GET
- getReviews (filter by query string)
PUT
- editReview
DELETE
- deleteReview
POST
- addReview (Slack API service here)

UserController:
GET
- getAll
- getByName
- getByAnything (requestparam)
POST
- by google login 
PUT
- editUser
DELETE
- deleteUser

AuthController:
GET
login with google, if user does not exist in database -> save
