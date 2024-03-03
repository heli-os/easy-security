package ac.weekly

import com.github.michaelbull.result.*

/**
 * @author Theo
 * @since 2024/03/03
 */
fun main() {
    val repository = Repository()
    repository.get(1L)
        .andThen(::sanitizedName)
        .mapBoth(
            { item -> println("SUCCESS: $item") },
            { error -> println("ERROR: ${error.message}") }
        )

    repository.get(2L)
        .andThen(::sanitizedName)
        .mapBoth(
            { item -> println("SUCCESS: $item") },
            { error -> println("ERROR: ${error.message}") }
        )
}

fun sanitizedName(input: Item): Result<Item, DomainError> {
    return if (input.name.length > 10) {
        Err(DomainError("Input is too long"))
    } else {
        Ok(input.copy(name = "Sanitized"))
    }
}

class Repository {
    private val items = mapOf(
        1L to Item("first"),
        2L to Item("123456790123456790")
    )

    fun get(id: Long): Result<Item, DomainError> {
        return items[id].toResultOr { DomainError("Error") }
    }

}

data class Item(
    val name: String
)

data class DomainError(
    val message: String
)
