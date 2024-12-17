package org.acme.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant

@Entity
@Table(name = "review")
data class Review(
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    val rating: Rating,

    @field:NotNull
    @Size(max = 2000, message = "Il commento non può superare i 2000 caratteri")
    @Column(name = "comment", length = 2000)
    val comment: String,

    @Column(name = "created_at")
    var createdAt: Instant? = null,

    @ManyToOne
    @field:NotNull
    @JoinColumn(name = "reader_id")
    val reader: Reader,

    @ManyToOne
    @field:NotNull
    @JoinColumn(name = "book_id")
    val book: Book
) {
    constructor() : this(
        id = null,
        rating = Rating.DECENT,
        comment = "",
        createdAt = null,
        reader = Reader(),
        book = Book()
    )

    @PrePersist
    fun prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now()
        }
    }

    enum class Rating {
        VERY_BAD, BAD, DECENT, GOOD, EXCELLENT
    }
}