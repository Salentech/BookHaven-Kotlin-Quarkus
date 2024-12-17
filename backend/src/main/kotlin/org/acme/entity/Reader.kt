package org.acme.entity

import com.example.bookhaven.entity.Notification
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.acme.entity.utils.Gender

@Entity
@Table(name = "reader")
data class Reader(
    @Id
    @Column(name = "reader_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @field:NotNull
    @field:Pattern(regexp = ".+@.+\\..+", message = "Email non valida")
    @Column(unique = true, name = "email")
    val email: String,

    @field:NotNull
    @Column(unique = true, name = "username")
    val username: String,

    @field:NotNull
    @field:Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    @Column(name = "password")
    var password: String,

    @Column(name = "age")
    val age: Int? = null,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    val gender: Gender,

    @Column(name = "bio", length = BIO_MAX_LENGTH)
    val bio: String? = null,

    @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], orphanRemoval = true)
    val notificationList: MutableList<Notification> = mutableListOf(),

    @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], orphanRemoval = true)
    val historyList: MutableList<History> = mutableListOf(),

    @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], orphanRemoval = true)
    val reviewList: MutableList<Review> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "friendship",
        joinColumns = [JoinColumn(name = "reader_id")],
        inverseJoinColumns = [JoinColumn(name = "friend_id")]
    )
    val friendSet: MutableSet<Reader> = mutableSetOf()
) {

    fun addFriend(friend: Reader) {
        friendSet.add(friend)
    }

    companion object {
        const val BIO_MAX_LENGTH = 500
    }
}