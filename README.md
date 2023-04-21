
<h1>About me</h1>

<p>
Hey everybody! My name is Mitchell Cowart, and I am a currently a SWE at USAA Bank! I work in the payments space under the Internal Bill Pay team, creating and maintaining RESTful APIs to improve 
the and enable bill payment functionality for our members. I am born and raised Texan so H-E-B has been near and dear to my heart for a very long time. I love learning, about programming or otherwise,
and I am excited to meet everybody!
</p>

<h1>How to run the project!</h1>

<p>
This is my simple receipt API! To start the server, run ./gradlew bootRun or bootRun from your IDE. You can find a postman collection in src.main.postmanCollection
that I have created for interacting with each endpoint! This collection is also where the integration tests live, so feel free to check those out as well. The unit tests are located in the test folder and 
the jacoco test coverage will be generated in build.jacocoHtml as index.html.
</p>

<h1>Assumptions</h1>

<p>
I know that we will discuss the design choices made in the interview itself, but here are a few assumptions that I wanted to highlight or specifically speak to.
</p>

<p>
Code coverage right now is relatively low because the controller is not covered by unit tests. I think controller logic should generally be covered by integration tests, so I have included that in the postman collection.
I didn't exclude it from Jacoco coverage because I feel like that is not as future-proof, especially if a different maintainer adds business logic inside the controller (which I would advise against)
</p>

<p>
I took the verbiage cart to include both items and coupon. In my mind, the way this API will function is that after a customer has loaded their virtual cart and added their coupons, there will be a validation service that ensures the items in the cart and the coupons exist
and are currently available. After that check, I would assume there would be a logistics check for location specific availability, THEN this API would be called. In a sense, this would be a middleware API, so I am referencing validated coupons coming in rather than
checking a DB for validation. As I do not have access to the upstream or downstream APIs, all naming conventions and payload assumptions are what I would consider the 'safest'
</p>

<p>
My current implementation assumes coupons are reused for each applicable item. I know that quantities aren't specified on the item side, but it isn't clear that coupons should ever be 1 to 1 or not. 
I believe what I have makes since given the scope of this project, but I wanted to point out that it has been considered!
<p/>

<h1>Considerations</h1>

<p>
discountPrice in the coupon object is a bit of a misnomer since it is a credit to the end result. Additionally, there are a few different pieces of data that can be considered a discount price, namely subtotalAfterDiscount.
From a banking perspective, keeping the crediting/debiting verbiage consistent saves so much in time later on. My suggestion for a new name would be simply discount or discountCredit! I realize that this is a breaking change and might be a 
pain to implement if we don't have ownership of the service calling this service.
</p>

<p>
I realize that many of my class and variable names are very verbose. At USAA I have run into a lot of separate abbreviations that mean the same thing but are hard to keep consistent.
I have spelled everything out because I am not privy to the naming conventions in the other services and would rather have it be obvious when refactoring.
<p/>

<p>
Some people use interfaces for every service! This is a design choice that works well on a pedestal, but in practice I have seen many good arguments for keeping concrete implementations in the controller.
Maintainability is the biggest consideration that comes to mind, but without knowing the complete purpose of the API and what future considerations could be added to it, it is hard to say if the interfaces would be extraneous or necessary for further additions.
<p/>

<p>
After tax arithmatic, BigDecimal data type causes long trailing value. Return values for receipt have been 
truncated because the assumption is that this is where the transaction is 'finished' no more logic will be run on these calculations. Since tax must be a whole cent value to the customer, 
I have decided to round it at that point. The rounding methodology selected that I have selected is HALF_EVEN, and it is rounded only there because every other endpoint could be an intermediate step in theory. 
Discussions about different rounding methodologies or Data Objects are up for discussion, but I have selected this one based on my research for how to fairly and accurately round and manipulate currency.
<p/>

<p>
All endpoints are created with the idea that you might need individual pieces for other services. That, along with the fact that all business logic should live outside the controller 
in services leads to simple services like subtotalAfterDiscountsService that would probably be redundant otherwise.
<p/>

<p>
The logging examples I have here implement the default Lombok SLF4J logger, which is essentially just creating Sys.out logs. In the context of a real application 
these would be replaced with a custom logger that would output somewhere else that could be queried and monitored like Splunk
<p/>

