package day_10_DB_connection

import slick.jdbc.PostgresProfile.api._
// connecting Scala -> PostgreSQL

object SlickConfig {
    val db: Database = Database.forURL(
      url = "jdbc:postgresql://localhost:5432/slickdb",
      user = "slickuser",
      password = "slick123",
      driver="org.postgresql.Driver"
    )
}
