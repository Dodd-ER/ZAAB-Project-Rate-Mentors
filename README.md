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
- User student

User
- long id (google auth)
- string name
- bool isAdmin
- List<Review> Review

Controllers:
MentorController:
GET
- getAll
- getByName -> Contains filter, lehet teljes névre, vagy first, vagy last etc
- OrderByRanking (get) -> asc, desc
POST
- addNewMentor (admin joggal lehetséges) 
PUT
- editMentor (alap adatai, új review hozzáadása)
DELETE
- deleteMentor

ReviewController:
GET
- getReviewByUser
- getReviewByMentor
- getAllReviews
PUT
- editReview
DELETE
- deleteReview
POST(???) -> elvileg a put mentornál megoldottuk a review postolását, nem jut most eszembe h milyen már post metódok lehetnének

UserController:
GET
- getAll
- getByName
- getByAnything (requestparam)
POST
autentikációnál meg lehetne oldani - login with google, if user does not exist in database -> save
PUT
- editUser
DELETE
- deleteUser

AuthController:
GET
login with google, if user does not exist in database -> save
