
# ExpenseManager
In todays world everyone has a habit of overspending money and regretting it later. I often see that people calculate
how much they spent in 15 days and then they often get worried by overspending. Expense Manager will help the user to
control the expenses they make in everyday life

## How is it Different from other app's out there or in other words how did i get idea to implement this
In my project you can categorize my expenses as well unlike other apps which just stores and loads expenses because as a
university student what I care about the most is on which part do I spend my most money on is it Eating out, Grocery,
Entertainment or other


This is a java based application which is used to manage expenses of a user.
In this application a user can store their expenses with a ID choosen by them and retreive their expenses whenever 
they wish to do so. This app will also have a feature to set up a limit on the amount of money spent in a month. The app
will alarm the user when the user has crossed the specific amount

This is intended for everyone who wish to manage their expenses and 
control on their spending habits. As a university student, I can understand how hard it can be to manage everyday 
expenses in day to day life, thereby by this app I want to help everyone especially university students to manage their 
everyday expenses


## User Stories

*Features that a user will like to have in the app*:
- As a user, I want to store my expenses
- As a user, I want to have an id through which I can access my expenses
- As a user, I want to have a feature to set up a limit on my expenses 
- As a user, I want to see my available expense limit in the app
- As a user, I want to view my stored expenses
- As a user, I want to be able to update my expense limit

# Instructions for Grader

- You can generate the first required event by entering your name and choosing an id number and clicking on save expenses
- The id number chosen by the user is used to save and load expenses of the user and the user should have id>=0
- You can locate my visual component on the front screen, splashing screen 
- You can **save** the state of my application by **clicking on the save expenses button** and filling the expenses details
- By Default the user is assigned an **expense limit of $2000** although user has the option to change the expense limit at any point of time
- You can reload your expenses by **entering your name and id** and clicking on load expenses button in my app
- You will land on the Screen **Sorry no expenses** found if id number does not exist in the system
- You will land on screen **sorry your expense limit is not enough** to add expense if you are crossing your expense limit
  
# Phase 4: Task 2

Tue Nov 29 21:15:45 PST 2022
Thank you kavyansh for saving details with id number 1

Tue Nov 29 21:15:51 PST 2022
New Expense : abc with the price being : 1.0 was purchased on 1 Jan 2022 has been successfully added

Tue Nov 29 21:19:17 PST 2022
Your expense limit has been updated to 30000.0

Wed Nov 30 19:37:34 PST 2022
Thank you kavyansh for viewing your expenses 1

Wed Nov 30 19:37:34 PST 2022
You purchased  Pasta with price being  $ 20.0 and the purchase was made on : 20 Sep 2022 which belongs to : Eatout

# Phase4: Task 3
I believe that I am quite satisifed with the design of my program because I have an Customer class
which has some attriutes of the type expenses so technically there wil be a Customer who has all the expenses 
and I also have an Expense Divider class which is basically calculating the percentage of all the expenses
categories
