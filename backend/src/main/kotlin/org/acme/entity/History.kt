package org.acme.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.Instant

@Entity
@Table(name = "history")
data class History(
    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(name = "started_read_at")
    var startedReadAt: Instant? = null,

    @Column(name = "last_read_at")
    var lastReadAt: Instant? = null,

    @Column(name = "is_read")
    var isRead: Boolean = false,

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
        startedReadAt = null,
        lastReadAt = null,
        isRead = false,
        reader = Reader(),
        book = Book()
    )
}