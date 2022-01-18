# OpenLibrary

OpenLibrary is Android application that mainly search on open api library for anything you want from the books in the world, all you need to do is add you search query and the app will get the result for you

## The architecture

The code architecture is designed to follow the clean code architecture by creating everything is separated layer that is responsible to do one thing only

## Layers:
- Data Layer (Which contains the classes for creating the network calls on open library API
- UseCase (Responsible for the logic of calling the network layer and validate the result to provide the data for presentation layer
- Presentation layer where we call the data from the use case and render depending on the result we get

## Libraries:
- This application doesn't depend on any third library to achieve the required requirements, all the code is a native starting from calling the backend and parsing images form the web to show it
