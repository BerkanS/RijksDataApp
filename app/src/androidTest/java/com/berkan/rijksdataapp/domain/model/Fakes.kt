package com.berkan.rijksdataapp.domain.model

object Fakes {
    val artObject = ArtObject(
        objectNumber = "OBJ-001",
        title = "Test Object",
        author = "Rembrandt",
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
}
