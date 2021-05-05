# perryapis
## Automated Test for PerryApis

**Note: Ideally these tests would be hosted on Jenkins and be automically run when changes were committed to PerryApi.**
*In this instance the tests will need to be manually commenced.*

* Step 1:
Checkout the code

* Step 2: 
Add the code to your IDE.

* Step 3:  
Right click and "run" on TestSuite1

* Step 4:
Await and view results and Test Report that is produced

**Test Overview:**
*I created a number of tests that hit each of the different endpoints in the API, checked for the correct response and behaviour per REST guidelines. I found a number 
bugs/questionable behaviour of which I have reported below:*

# Bugs: 

* POST_ Create a Message Bugs
    *  [x]1. Create a message with only valid toUserId and message. **Result: Expected status code <400> but was <200>. Message is created.**
    *  [x]2. Create a message with valid fromUserId and invalid toUserid and message. **Result: Expected status code <400> but was <200>. Message is created.**
    *  [x]3. Create a message with invalid fromUserId and valid toUserid and message. **Result: Expected status code <400> but was <200>. Message is created.**
    *  [x]4. Create a message without fromUserId and  toUserid,  only message is provided. **Result: Expected status code <400> but was <200>. Message is created.**
    *  [x]5. Create a message without fromUserId, toUserid and message. **Result: Expected status code <400> but was <200>. Message is created.**

* GET_ListMessage Bugs
    *  [x]1.Get a message using one invalid fromUserId. **Result: Expected status code <400> but was <200>. Message is retrived.**
    *  [x]2.Get a message using one invalid toUserId. **Result: Expected status code <400> but was <200>. Message is retrived.**
    *  [x]3.Get a message using invalid fromUserId and invalid toUserId. **Result: Expected status code <400> but was <200>. Message is retrived.**
    *  [x]4.Get all messages belongs to the toUser using only the valid toUserId. **Result: Expected status code <400> but was <200>. Messages are retrived.**
    *  [x]5.Get all messages belongs to the fromUser using only the valid fromUserId. **Result: Expected status code <400> but was <200>. Messages are retrived.** 
    *  [x]6.Get all messages sent between two users without providing the fromUserId and toUserId. **Result: Expected status code <400> but was <200>. Messages are retrived.**

* GET_Message Bugs
    *  [x]1. Get a message without a messageId. **Result: Expected status code <404> but was <200>. Messages are retrived.**

* PUT_Message Bugs
    *  [x]1.Update a message using  invalid messageId and update the messages. **Result: Expected status code <400> but was <404>.**
    *  [x]2.Update a message using  valid messageId and update the messages. **Result: Expected status code <200> but was <404>.**
    *  [x]3.Update a message without a messageId and update the messages. **Result: Expected status code <400> but was <404>.**
    *  [x]4.Update a message using  valid messageId, without changing the existing messages. **Result: Expected status code <200> but was <404>.**

