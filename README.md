Contest for ASTON company for the java developer role

Here is the contest:

Design and implement a RESTful API, backing service and data model to create bank accounts and transfer money between them. Interaction with API will be using HTTP requests. Requirements

• Accounts are created by supplying a name and four-digit PIN code. Account number is automatically created.

• Once account is created one can Deposit, Withdraw or Transfer money between accounts.

• Any operation which deducts funds from the account needs to include the correct PIN code.

• A specific call will fetch all the accounts: the name and their current balance. • APIs will use JSON payloads when applicable.

• Use in-memory database as a backing stores. Recommendations

• Use any technologies you feel comfortable with, but it is recommended you set up your project using Spring Boot.

• Make use of best practices when implementing your code to ensure a high-quality result. Use dependency injection, avoid statics, immutable types, etc…

• However, keep it simple and to the point. Do not over-engineer. e.g. there is no need to add authorization layers.

Most errors are held,feel free to test them. You will get json response for each of them

Account number generates from created UserDTO hashcode() But this approach could be updated with the help of generators

Also, there is no PasswordHashing inside, that could be upgraded as well with the help of bCrypt and sour from users data.

You can see all controller endPoints by accessing

http://localhost:8080/swagger-ui/index.html#/

after you run the project on your machine.