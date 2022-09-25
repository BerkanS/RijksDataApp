package com.berkan.rijksdataapp.domain.model

object Fakes {
    val artObject = ArtObject(
        objectNumber = "OBJ-001",
        title = "Test Object",
        author = "Vermeer",
        longTitle = "Test Object Long",
        webImage = ObjectImage("test"),
        headerImage = ObjectImage("test")
    )

    val artObjectTwo = ArtObject(
        objectNumber = "OBJ-002",
        title = "Test Object 2",
        author = "Rembrandt",
        longTitle = "Test Object 2 Long",
        webImage = ObjectImage("test"),
        headerImage = ObjectImage("test")
    )

    val artObjectThree = ArtObject(
        objectNumber = "OBJ-003",
        title = "Test Object 3",
        author = "Rembrandt",
        longTitle = "Test Object 3 Long",
        webImage = ObjectImage("test"),
        headerImage = ObjectImage("test")
    )

    val artObjectFour = ArtObject(
        objectNumber = "OBJ-004",
        title = "Test Object 4",
        author = "Rembrandt",
        longTitle = "Test Object 4 Long",
        webImage = ObjectImage("test"),
        headerImage = ObjectImage("test")
    )

    val artObjectFive = ArtObject(
        objectNumber = "OBJ-005",
        title = "Test Object 5",
        author = "Vermeer",
        longTitle = "Test Object 5 Long",
        webImage = ObjectImage("test"),
        headerImage = ObjectImage("test")
    )

    val artObjectSix = ArtObject(
        objectNumber = "OBJ-006",
        title = "Test Object 6",
        author = "Van Gogh",
        longTitle = "Test Object 6 Long",
        webImage = ObjectImage("test"),
        headerImage = ObjectImage("test")
    )
}
