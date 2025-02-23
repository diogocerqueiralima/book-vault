package com.github.diogocerqueiralima.bookservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "authors")
data class Author(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val about: String,

    @ManyToMany
    @JoinTable(
        name = "book_authors",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "author_id", referencedColumnName = "id")]
    )
    val books: List<Book> = emptyList()

) {

    val image: String
        get() = "$name.png"

}
