package com.github.diogocerqueiralima.bookservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "books")
data class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,

    @ManyToMany(mappedBy = "books")
    val authors: List<Author> = emptyList(),

    @Column(unique = true)
    val isbn: String

) {

    val fileName: String
        get() = "$isbn.pdf"

}
