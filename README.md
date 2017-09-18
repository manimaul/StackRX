# StackRX - Candidate Exercise
##### Android exercise utilizing RXJava / RxKotlin, Retrofit, Moshi, Retro-lambda, Data-Binding

Rules:
1. Use any additional libraries you wish considering the ones already included with this project.
2. Use your choice of Kotlin or Java
3. Use your choice of Activities or Fragments
4. Talk though what your are doing and communicate as much as possible.
5. You may use the internet and or any development tool / resource you normally use. (With the exception of other individuals.)

## Tasks
```gherkin
Scenario: A user is viewing a list of questions (QuestionsFragment.java)
When: A user clicks on the "VIEW ANSWERS" button
Then: The screen should transition to another screen
And: "Answers" should display in the toolbar
```

```gherkin
Scenario: A user is viewing the answers to a stack overflow question
Given: A user has clicked on the "VIEW ANSWERS" button (QuestionsFragment.java)
When: The user is viewing the question's answers screen
Then: The answers should be displayed in a scrollable list
 
When: The answers list is empty
Then: Text saying "No answers" should be displayed
 
When: The back button is pressed
Then: The user should be returned to the questions list they were previously on
And: Their previous scroll position should be preserved
```


## Extra credit
```gherkin
Scenario: A user is viewing a list of questions (QuestionsFragment.java)
When: When the questions are displayed
Then: The user's name should be displayed
And: The user's avatar image should be displayed
```

```gherkin
Scenario: A user is viewing a list of questions (QuestionsFragment.java)
When: When the questions are displayed
Then: The question date should be displayed in the following format Sunday, Sept. 17, 2017
```

```gherkin
Scenario: A user is viewing a list of questions (QuestionsFragment.java)
When: A page of questions are displayed
And: The user begins to scroll past the first item in the last page
Then: Subsequent pages should be loaded
```