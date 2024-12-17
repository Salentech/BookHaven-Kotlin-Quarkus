package org.acme.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "book_card")
data class BookCard(
    @Id
    @Column(name = "book_card_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @field:NotNull
    @Column(name = "title")
    val title: String,

    @field:NotNull
    @Column(name = "body", length = 2000)
    val body: String,

    @ManyToOne
    @field:NotNull
    @JoinColumn(name = "book_id")
    val book: Book
)