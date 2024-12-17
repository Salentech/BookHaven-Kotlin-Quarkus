package com.example.bookhaven.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.acme.entity.Reader
import java.time.Instant

@Entity
@Table(name = "notification")
data class Notification(
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @field:NotNull
    @Column(name = "message")
    val message: String,

    @Column(name = "created_at")
    var createdAt: Instant? = null,

    @Column(name = "is_read")
    var isRead: Boolean = false,

    @ManyToOne
    @field:NotNull
    @JoinColumn(name = "reader_id")
    val reader: Reader
) {
    @PrePersist
    fun prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now()
        }
    }
}