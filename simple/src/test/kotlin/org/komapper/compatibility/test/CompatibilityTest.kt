package org.komapper.compatibility.test

import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.core.dsl.query.single
import org.komapper.jdbc.JdbcDatabase
import kotlin.test.Test
import kotlin.test.assertNotNull

class CompatibilityTest {

    @Test
    fun test() {
        val db = JdbcDatabase("jdbc:h2:mem:example;DB_CLOSE_DELAY=-1")
        val e = Meta.employee
        val employee = db.withTransaction {
            db.runQuery {
                QueryDsl.create(e)
            }

            val employee = Employee(
                id = 1,
                name = "Employee 1",
                address = Address(street = "Street 1", city = "City 2"),
                version = 0
            )
            db.runQuery {
                QueryDsl.insert(e).single(employee)
            }
            db.runQuery {
                QueryDsl.from(e).where { e.id eq 1 }.single()
            }
        }
        assertNotNull(employee)
    }
}
