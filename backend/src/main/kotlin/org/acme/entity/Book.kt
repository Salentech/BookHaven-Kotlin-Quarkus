package org.acme.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
@Table(
    name = "book",
    uniqueConstraints = [UniqueConstraint(
        columnNames = ["title", "genre", "author"]
    )]
)
data class Book(
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @field:NotNull
    @Column(name = "title")
    val title: String,

    @field:NotNull
    @Column(name = "num_pages")
    val pages: Int,

    @field:NotNull
    @Column(name = "description", length = 2000)
    val description: String,

    @field:NotNull
    @Column(name = "genre")
    val genre: String,

    @field:NotNull
    @Column(name = "author")
    val author: String,

    @field:NotNull
    @Column(name = "url")
    val url: String,

    @field:NotNull
    @Column(name = "published_year")
    val publishedYear: LocalDate,

    @OneToMany(mappedBy = "book", cascade = [CascadeType.ALL], orphanRemoval = true)
    val reviews: MutableList<Review> = mutableListOf(),

    @OneToMany(mappedBy = "book", cascade = [CascadeType.ALL], orphanRemoval = true)
    val histories: MutableList<History> = mutableListOf(),

    @OneToMany(mappedBy = "book", cascade = [CascadeType.ALL], orphanRemoval = true)
    val bookCards: MutableList<BookCard> = mutableListOf()
)